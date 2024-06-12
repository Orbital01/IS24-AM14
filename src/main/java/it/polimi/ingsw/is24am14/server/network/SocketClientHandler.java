package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.LobbyList;

import java.net.Socket;

public class SocketClientHandler implements Runnable {
    private final SocketMediator mediator;

    public SocketClientHandler(Socket socket, LobbyList lobbyList) {
        this.mediator = new SocketMediator(socket, lobbyList);
    }

    public SocketMediator getMediator() {
        return mediator;
    }

    public void run() {
        try {
            mediator.manage();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("Mediator ended");
    }
}