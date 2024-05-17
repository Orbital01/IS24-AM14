package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.*;
import java.rmi.Remote;

public interface ServerConnection extends Remote {
    void startServer() throws Exception;
    void joinExistingLobby(Object o, Lobby lobby) throws Exception;
    void joinNewLobby(Object o, int numPlayers) throws Exception;
}
