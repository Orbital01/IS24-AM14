package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.controller.state.InitGameState;
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
        GameContext context;
        context = new GameContext();
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("TestPlayer1");
        players.add(player1);
        Player player2 = new Player("TestPlayer2");
        players.add(player2);
        Player player3 = new Player("TestPlayer3");
        players.add(player3);
        Player player4 = new Player("TestPlayer4");
        players.add(player4);

        context.getGame().setPlayers(players);

        InitGameState initGameState = new InitGameState(context);
        initGameState.execute();
    }
}