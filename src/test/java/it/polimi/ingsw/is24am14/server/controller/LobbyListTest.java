package it.polimi.ingsw.is24am14.server.controller;
import it.polimi.ingsw.is24am14.server.network.ClientHandler;
import it.polimi.ingsw.is24am14.server.network.ClientHandlerList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LobbyListTest {
    private LobbyList lobbyList;
    private ClientHandler mockClientHandler;

    @BeforeEach
    public void setup() throws Exception {
        ClientHandlerList clientHandlerList = new ClientHandlerList();
        lobbyList = new LobbyList(clientHandlerList);
        mockClientHandler = mock(ClientHandler.class);
        when(mockClientHandler.getUsername()).thenReturn("TestPlayer");
    }

    // Tests if a client handler can be added and retrieved
    @Test
    public void testAddClientHandler() {
        lobbyList.addClientHandler(mockClientHandler);
        assertEquals(mockClientHandler, lobbyList.getClientHandler("TestPlayer"));
    }

    // Tests if a lobby can be created and retrieved by host name
    @Test
    public void testCreateLobby() {
        lobbyList.createLobby("TestHost", 4);
        assertNotNull(lobbyList.getLobbyByHost("TestHost"));
    }

    // Tests if a player can join a lobby
    @Test
    public void testJoinLobby() throws Exception {
        lobbyList.addClientHandler(mockClientHandler);
        ClientHandler mockClientHandler2 = mock(ClientHandler.class);
        when(mockClientHandler2.getUsername()).thenReturn("TestPlayer2");
        lobbyList.addClientHandler(mockClientHandler2);
        lobbyList.createLobby("TestHost", 2);
        lobbyList.joinLobby("TestPlayer", "TestHost");
        lobbyList.joinLobby("TestPlayer2", "TestHost");
        assertEquals("TestHost", lobbyList.getPlayersLobby("TestPlayer").getHost());
    }

    // Tests if all lobby names can be retrieved
    @Test
    public void testGetLobbiesNames() {
        lobbyList.createLobby("TestHost1", 4);
        lobbyList.createLobby("TestHost2", 4);
        assertTrue(lobbyList.getLobbiesNames().contains("TestHost1"));
        assertTrue(lobbyList.getLobbiesNames().contains("TestHost2"));
    }
}


