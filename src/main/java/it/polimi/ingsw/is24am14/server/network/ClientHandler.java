package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.GameContext;

import java.rmi.Remote;

public interface ClientHandler extends Remote {
    String getUsername() throws Exception;
}
