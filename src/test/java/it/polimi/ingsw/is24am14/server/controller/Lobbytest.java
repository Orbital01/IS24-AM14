package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Lobbytest {
    private Lobby lobby = new Lobby();

    @BeforeEach
    public void setUp() {
        lobby.firstPlayerConnected();
    }

    @Test
    public void testFirstPlayerConnected() {
        assertEquals(lobby.getContext().getState().getClass(), FirstConnectionState.class);
    }

    @Test
    public void testIsAllPlayersConnected_false() {
        assertFalse(lobby.isAllPlayersConnected());
    }

    @Test
    public void testIsAllPlayersConnected_true() {
        lobby.getContext().setNumberOfPlayers(2);
        lobby.getContext().setPlayers(new ArrayList<Player>());
        //lobby.getContext().getPlayers().add(new Player("player1", blue)); //come si aggiungono i giocatori? VITTORIO
        //lobby.getContext().getPlayers().add(new Player("player1", blue));
        lobby.firstPlayerConnected();
        assertTrue(lobby.isAllPlayersConnected());
    }

    @Test
    public void testGetPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();
        lobby.getContext().setPlayers(players);
        assertEquals(lobby.getPlayers(), players);
    }

    @Test
    public void testGetNumberOfPlayers() { //useless
        lobby.getContext().setNumberOfPlayers(2);
        assertEquals(lobby.getNumberOfPlayers(), 2);
    }



}
