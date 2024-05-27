package it.polimi.ingsw.is24am14.server.network;

import java.net.Socket;
import it.polimi.ingsw.is24am14.server.controller.LobbyList;

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
            throw new RuntimeException(e);
        }
        System.out.println("Mediator ended");
    }
}
