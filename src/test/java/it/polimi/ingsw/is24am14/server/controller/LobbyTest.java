package it.polimi.ingsw.is24am14.server.controller;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.network.ClientHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LobbyTest {
    private Lobby lobby;
    private ClientHandler mockClientHandler;

    @BeforeEach
    public void setup() throws Exception {
        lobby = new Lobby("TestHost", 4);
        mockClientHandler = mock(ClientHandler.class);
        when(mockClientHandler.getUsername()).thenReturn("TestPlayer");
    }
    // Tests if a player can join the lobby
    @Test
    public void testJoinLobby() throws Exception {
        lobby.joinLobby(mockClientHandler);
        assertEquals(1, lobby.getPlayers().size());
    }

    //Tests if the game can be started
    @Test
    public void testStartGame() throws Exception {
        lobby.joinLobby(mockClientHandler);
        lobby.startGame();
        assertNotNull(lobby.getGameContext());
        assertEquals(Game.class, lobby.getGameContext().getGame().getClass());
    }
    // Test if the colour a player selects is removed from the list of available colours
    @Test
    public void testSetColorRemovesColor() throws Exception {
        lobby.joinLobby(mockClientHandler);
        lobby.startGame();
        lobby.setColor("TestPlayer", TokenColour.GREEN);
        assertFalse(lobby.getGameContext().getColors().contains(TokenColour.GREEN));
    }

    // Test if the colour a player selects is set correctly
    @Test
    public void testSetColor() throws Exception {
        lobby.joinLobby(mockClientHandler);
        lobby.startGame();
        lobby.setColor("TestPlayer", TokenColour.GREEN);
        assertEquals(TokenColour.GREEN, lobby.getGameContext().getGame().getPlayer("TestPlayer").getColour());
    }
}