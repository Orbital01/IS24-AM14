package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.game.Game;

public class GameContext {
    Game game;
    GameState gameState;
    PlayableCard lastPlayedCard;


    public GameContext(Game game) {
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Game getGame() {
        return game;
    }

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
