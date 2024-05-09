package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;

import java.util.ArrayList;

public class GameStateScheduler {
    /*
    GameContext context;

    public GameStateScheduler(int numPlayer, ArrayList<String> nickNames) {
        Game newGame = new Game(numPlayer);

        for (int i = 0; i < numPlayer; i++) {
            newGame.addPlayer(new Player(nickNames.get(i)));
        }

        context = new GameContext(newGame);
    }

    public void run() {
        context.setGameState(new initGameState(context.game));
        context.executeState();

        //  while it's not endGame
        while (!context.game.isEndGame()) {
            //  for each player
            for (int i = 0; i < context.game.getNumPlayers(); i++) {
                context.setGameState(new PlayState(context));
                context.executeState();

                context.setGameState(new DrawState(context));
                context.executeState();

                context.setGameState(new EndTurnState(context));
                context.executeState();
            }
        }

        //  last round when in end game
        for (int i = 0; i < context.game.getNumPlayers(); i++) {
            context.setGameState(new PlayState(context));
            context.executeState();

            context.setGameState(new DrawState(context));
            context.executeState();

            context.setGameState(new EndTurnState(context));
            context.executeState();
        }

        context.setGameState(new EndGameState(context));
        context.executeState();
    }
    */
}
