package it.polimi.ingsw.is24am14.server.controller;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.network.Message;
import it.polimi.ingsw.is24am14.server.network.NotYourColorTurnException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameContextTest {
    private GameContext gameContext;
    private Game mockGame;
    private Player mockPlayer;


    @BeforeEach
    public void setup() {
        mockGame = mock(Game.class);
        mockPlayer = mock(Player.class);
        Deck<ObjectiveCard> mockDeck = mock(Deck.class);
        when(mockGame.getObjectiveDeck()).thenReturn(mockDeck);
        gameContext = new GameContext(mockGame);
    }

    @Test
    public void shouldSetColorSuccessfully() throws Exception {
        when(mockGame.getPlayer("TestPlayer")).thenReturn(mockPlayer);
        when(mockGame.getPlayers()).thenReturn(new ArrayList<>(Collections.singletonList(mockPlayer)));
        gameContext.setColor("TestPlayer", TokenColour.GREEN);
        verify(mockPlayer).setColour(TokenColour.GREEN);
    }

    @Test
    public void shouldThrowExceptionWhenPlayerNotFoundInSetColor() {
        assertThrows(RuntimeException.class, () -> gameContext.setColor("TestPlayer", TokenColour.GREEN));
    }

    @Test
    public void shouldReturnAvailableColors() {
        List<TokenColour> colors = gameContext.getColors();
        assertTrue(colors.contains(TokenColour.RED));
        assertTrue(colors.contains(TokenColour.GREEN));
        assertTrue(colors.contains(TokenColour.YELLOW));
        assertTrue(colors.contains(TokenColour.BLUE));
        assertFalse(colors.contains(TokenColour.BLACK));
    }

    @Test
    public void shouldReturnFalseWhenColorStateNotCompleted() {
        when(mockGame.getPlayers()).thenReturn(new ArrayList<>(Collections.singletonList(mockPlayer)));
        assertFalse(gameContext.colorStateCompleted());
    }

    @Test
    public void shouldReturnTrueWhenColorStateCompleted() {
        when(mockPlayer.getColour()).thenReturn(TokenColour.GREEN);
        when(mockGame.getPlayers()).thenReturn(new ArrayList<>(Collections.singletonList(mockPlayer)));
        assertTrue(gameContext.colorStateCompleted());
    }

    @Test
    public void shouldGetGameStateEnumSuccessfully() {
        gameContext.setGameStateEnum(GameStateEnum.Move);
        assertEquals(GameStateEnum.Move, gameContext.getGameStateEnum());
    }

    @Test
    public void shouldSetGameStateEnumSuccessfully() {
        gameContext.setGameStateEnum(GameStateEnum.Move);
        assertEquals(GameStateEnum.Move, gameContext.getGameStateEnum());
        gameContext.setGameStateEnum(GameStateEnum.Draw);
        assertEquals(GameStateEnum.Draw, gameContext.getGameStateEnum());
    }

    @Test
    public void shouldGetGameSuccessfully() {
        assertEquals(mockGame, gameContext.getGame());
    }

    @Test
    public void shouldGetObjectiveCardsSuccessfully() {
        ArrayList<ObjectiveCard> mockObjectiveCards = new ArrayList<>();
        gameContext.objectiveCardChoices.put("TestPlayer", mockObjectiveCards);
        assertEquals(mockObjectiveCards, gameContext.getObjectiveCards("TestPlayer"));
    }

    @Test
    public void shouldReturnNullWhenUserNotFoundInGetObjectiveCards() {
        assertNull(gameContext.getObjectiveCards("TestPlayer"));
    }

    @Test
    public void shouldPlaceStarterCardSuccessfully() {
        when(mockGame.getPlayer("TestPlayer")).thenReturn(mockPlayer);
        when(mockPlayer.getPlayerBoard()).thenReturn(mock(GameArea.class));
        gameContext.placeStarterCard("TestPlayer", mock(StarterCard.class));
    }

    @Test
    public void shouldAddMessageSuccessfully() {
        gameContext.addMessage("sender", "receiver", "message");
        Message message = gameContext.getMessages().getFirst();
        assertEquals("sender", message.getSender());
        assertEquals("receiver", message.getReceiver());
        assertEquals("message", message.getMessage());
    }

    @Test
    public void shouldReturnEmptyListWhenNoMessages() {
        assertTrue(gameContext.getMessages().isEmpty());
    }

}
