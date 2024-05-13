package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.network.ServerConnection;

import java.io.Serializable;
import java.util.ArrayList;

public class LobbyList implements Serializable {
  ArrayList<ServerConnection> clients;
  ArrayList<Lobby> lobbies;

    public LobbyList(ArrayList<ServerConnection> client) {
        this.clients = client;
        this.lobbies = new ArrayList<>();
    }

    public void createLobby(Lobby lobby) {
        lobbies.add(lobby);
    }

    public void removeLobby(Lobby lobby) {
        lobbies.remove(lobby);
    }

    public ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

}
