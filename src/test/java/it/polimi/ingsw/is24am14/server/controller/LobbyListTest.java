package it.polimi.ingsw.is24am14.server.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class LobbyListTest {
    private LobbyList lobbyList;
    private Lobby lobby1;
    private Lobby lobby2;

    @BeforeEach
    public void setUp() {
        lobbyList = new LobbyList();
        lobby1 = new Lobby();
        lobby2 = new Lobby();
    }

    @Test
    public void testAddLobby() {
        lobbyList.addLobby(lobby1);
        assertTrue(lobbyList.getLobbies().contains(lobby1));
    }

    @Test
    public void testAddLobby_firstPlayerConnected() {
        lobbyList.addLobby(lobby1);
        assertEquals(FirstConnectionState.class, lobby1.getContext().getState().getClass());
    }

    @Test
    public void testRemoveLobby() {
        lobbyList.addLobby(lobby1);
        lobbyList.addLobby(lobby2);
        lobbyList.removeLobby(lobby1);
        assertEquals(1, lobbyList.getLobbies().size());
        assertEquals(lobby2, lobbyList.getLobbies().getFirst());
    }

    @Test
    public void testGetLobbies() {
        lobbyList.addLobby(lobby1);
        lobbyList.addLobby(lobby2);
        assertEquals(2, lobbyList.getLobbies().size());
        assertEquals(lobby1, lobbyList.getLobbies().get(0));
        assertEquals(lobby2, lobbyList.getLobbies().get(1));
    }




}
