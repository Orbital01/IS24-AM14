package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.LobbyList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    private final LobbyList lobbyList;

    public SocketServer(LobbyList lobbyList) {
        this.lobbyList = lobbyList;
        this.startServer();
    }

    public void startServer() {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(NetworkSettings.socketPort);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("TCP server started");
        while (true) {
            try {
                Socket socket = serverSocket.accept();

                SocketClientHandler handler = new SocketClientHandler(socket, lobbyList);

                executor.submit(handler);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}