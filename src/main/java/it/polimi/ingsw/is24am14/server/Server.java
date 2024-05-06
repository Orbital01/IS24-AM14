package it.polimi.ingsw.is24am14.server;

import it.polimi.ingsw.is24am14.server.controller.*;
import it.polimi.ingsw.is24am14.server.network.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Server {
    private LobbyList lobbyList;
    private ArrayList<SocketClientHandler> socketClientHandlers;

    public void start(int port) {
        //crea la lobby list
        lobbyList = new LobbyList();

        //crea il client handler per i socket

        //chiama il metodo run per il client handler dei socket
    }
}