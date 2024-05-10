package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.ClientListUpdater;
import it.polimi.ingsw.is24am14.server.controller.LobbyList;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ServerLauncher {
    //questa classe è la lobby dell'esempio di @Stefan Bogdanovic, solo che lobby è già presente nel package controller

    public static void main(String[] args) {
        ArrayList<ServerConnection> servers = new ArrayList<>();

        try {
            RMIServer RMIserver = new RMIServer(servers);
            TCPServer TCPserver = new TCPServer(servers);

            RMIserver.startServer();
            TCPserver.startServer();

            LobbyList lobbyList = new LobbyList(servers);
            ClientListUpdater updater = new ClientListUpdater(servers, lobbyList);
            updater.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
