package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.GameContext;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientHandler extends UnicastRemoteObject implements ClientHandler {
    private final String username;
    private final ClientInterface client;

    protected RMIClientHandler(ClientInterface client, String username) throws RemoteException {
        this.username = username;
        this.client = client;
    }

    @Override
    public String getUsername() throws Exception {
        return this.username;
    }
}
