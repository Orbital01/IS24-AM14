package it.polimi.ingsw.is24am14.server.controller;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.card.Deck;
import java.util.ArrayList;

public class DrawState implements GameState{
    private final Game game;
    private final Player currentPlayer;

    //Obtain player, their gameArea, initialize isEndGame as false
    public DrawState(GameContext context) {
        this.game = context.game;
        int playerIndex = game.getIndexActivePlayer();
        currentPlayer = game.getPlayers().get(playerIndex);
    }

    @Override
    public void execute(){
        try {
            currentPlayer.getConnection().askPickChoice(this.game.getGoldDeck(), this.game.getResourceDeck(), this.game.getFaceUpCards());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}