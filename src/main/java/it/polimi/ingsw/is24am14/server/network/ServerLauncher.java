package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.LobbyList;

import java.rmi.RemoteException;

public class ServerLauncher {
    public static void main(String[] args) {
        LobbyList lobbyList = new LobbyList(new ClientHandlerList());
        try {
            RMIServer server = new RMIServer(lobbyList);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
