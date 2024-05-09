package it.polimi.ingsw.is24am14.server;

import it.polimi.ingsw.is24am14.server.controller.*;
import it.polimi.ingsw.is24am14.server.network.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Server {
    private LobbyList lobbyList;
    private ArrayList<ServerConnection> clientHandlers;

    public void start(int port) {
        //crea la lobby list
        lobbyList = new LobbyList();


        try {
            //crea il client handler per i socket
            RMIServer rmiServer = new RMIServer(clientHandlers);
            rmiServer.startServer();

            //chiama il metodo run per il client handler dei socket
            TCPServer tcpServer = new TCPServer(clientHandlers);
            tcpServer.startServer();

        }catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}