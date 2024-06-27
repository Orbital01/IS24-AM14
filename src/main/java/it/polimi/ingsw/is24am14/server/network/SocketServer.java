package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.LobbyList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Represents a TCP socket server that accepts incoming client connections and handles them using threads.
 */
public class SocketServer {
    private final LobbyList lobbyList;

    /**
     * Constructs a SocketServer with a specified lobby list.
     * @param lobbyList the LobbyList object containing active lobbies
     */
    public SocketServer(LobbyList lobbyList) {
        this.lobbyList = lobbyList;
        this.startServer();
    }

    /**
     * Starts the TCP server to accept incoming client connections.
     * Uses a cached thread pool to handle client connections concurrently.
     */
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