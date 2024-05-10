package it.polimi.ingsw.is24am14.server.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    private int port;
    private ArrayList<ServerConnection> serverList;

    public TCPServer(ArrayList<ServerConnection> server) {
        this.port = port;
        this.serverList = server;
    }

    public void startServer() {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("TCP server started");
        while (true) {
            try {
                Socket socket = serverSocket.accept();

                TCPClientHandler handler = new TCPClientHandler(socket, serverList);
                serverList.add(handler.getMediator());
                executor.submit(handler);
                System.out.println("TCP client connected");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
