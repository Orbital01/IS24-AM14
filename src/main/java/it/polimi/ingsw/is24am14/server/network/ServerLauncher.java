package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.LobbyList;

public class ServerLauncher {
    public static void main(String[] args) {
        ClientHandlerList servers = new ClientHandlerList();

        try {
            LobbyList lobbyList = new LobbyList(servers);

            RMIServer RMIserver = new RMIServer(servers, lobbyList);

            RMIserver.startServer();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}