package it.polimi.ingsw.is24am14.server.controller;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import it.polimi.ingsw.is24am14.server.network.ServerConnection;
import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfPlayersReachedException;

public class LobbyTest {
    @Test
    void addPlayerToLobby() {
        ArrayList<ServerConnection> players = new ArrayList<>();
        Lobby lobby = new Lobby(4, players);
        ServerConnection player = new ServerConnection();
        lobby.addPlayer(player);
        assertTrue(players.contains(player));
    }

    @Test
    void startGameWithMaxPlayers() {
        ArrayList<ServerConnection> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new ServerConnection());
        }
        Lobby lobby = new Lobby(4, players);
        assertDoesNotThrow(() -> lobby.startGame());
    }

    @Test
    void startGameWithLessThanMaxPlayers() {
        ArrayList<ServerConnection> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new ServerConnection());
        }
        Lobby lobby = new Lobby(4, players);
        assertDoesNotThrow(() -> lobby.startGame());
    }

    @Test
    void startGameWithMoreThanMaxPlayers() {
        ArrayList<ServerConnection> players = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            players.add(new ServerConnection());
        }
        Lobby lobby = new Lobby(4, players);
        assertThrows(MaximumNumberOfPlayersReachedException.class, () -> lobby.startGame());
    }
}