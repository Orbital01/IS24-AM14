package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.game.Game;

public class GameContext {
    Game game;
    GameState gameState;

    public GameContext(Game game) {
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void executeState() {
        gameState.execute();
    }
}
