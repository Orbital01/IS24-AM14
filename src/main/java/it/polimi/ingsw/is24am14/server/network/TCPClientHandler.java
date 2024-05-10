package it.polimi.ingsw.is24am14.server.network;

import java.net.Socket;
import java.util.ArrayList;

public class TCPClientHandler implements Runnable {
    private Socket socket;
    private TCPMediator mediator;

    public TCPClientHandler(Socket socket, ArrayList<ServerConnection> list) {
        this.socket = socket;
        this.mediator = new TCPMediator(list);
    }

    public TCPMediator getMediator() {
        return mediator;
    }

    @Override
    public void run() {
        //  cosa deve fare il server (per ciascun client)
    }
}
