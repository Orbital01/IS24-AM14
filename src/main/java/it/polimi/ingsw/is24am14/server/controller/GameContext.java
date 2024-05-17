package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.Game;

import java.io.Serializable;

public class GameContext implements Serializable {
    Game game;
    GameState gameState;
    PlayableCard lastPlayedCard;


    public GameContext(Game game) {
        this.game = game;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    //questi due metodi non servono
    public Game getGame() {
        return game;
    }
    //questi due metodi non servono
    public void setGame(Game game) {
        this.game = game;
    }

    public PlayableCard getLastPlayedCard() {
        return lastPlayedCard;
    }

    public void setLastPlayedCard(PlayableCard lastPlayedCard) {
        this.lastPlayedCard = lastPlayedCard;
    }

    public void executeState() {
        gameState.execute();
    }


}
