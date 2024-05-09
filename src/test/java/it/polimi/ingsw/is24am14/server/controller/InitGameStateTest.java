package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.controller.InitGameState;
import it.polimi.ingsw.is24am14.server.model.game.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import org.junit.jupiter.api.*;

import it.polimi.ingsw.is24am14.server.model.player.*;
import it.polimi.ingsw.is24am14.server.controller.*;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class InitGameStateTest {
    @Test
    void correctInitializationExecuteTest(){
        //Creating all parameters needed
        Game game = new Game(4);
        GameContext context = new GameContext(game);
        ArrayList<Player> players = new ArrayList<Player>();
        Player player1 = new Player("TestPlayer1", TokenColour.GREEN);
        players.add(player1);
        Player player2 = new Player("TestPlayer2", TokenColour.RED);
        players.add(player2);
        Player player3 = new Player("TestPlayer3", TokenColour.YELLOW);
        players.add(player3);
        Player player4 = new Player("TestPlayer4", TokenColour.BLUE);
        players.add(player4);

        context.getGame().setPlayers(players);

        InitGameState initGameState = new InitGameState(context);
        initGameState.execute();
    }
}