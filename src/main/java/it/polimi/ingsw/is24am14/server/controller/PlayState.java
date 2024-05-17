package it.polimi.ingsw.is24am14.server.controller;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.controller.GameContext;

import java.util.ArrayList;

public class PlayState implements GameState {

    private PlayableCard playedCard;
    private final Player currentPlayer;
    private GameContext context;

    public boolean isEndGame;

    public PlayState(GameContext context) {
        this.context = context;
        Game game = context.game;
        int playerIndex = game.getIndexActivePlayer();
        this.currentPlayer = game.getPlayers().get(playerIndex);
    }

    public PlayableCard returnPlayedCard(){
        return playedCard;
    }

    @Override
    public void execute(){
        try {
            currentPlayer.getConnection().askForMove(currentPlayer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

