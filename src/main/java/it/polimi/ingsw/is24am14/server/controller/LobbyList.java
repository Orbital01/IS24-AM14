package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.network.ClientHandler;
import it.polimi.ingsw.is24am14.server.network.ClientHandlerList;

import java.io.Serializable;
import java.util.ArrayList;

public class LobbyList implements Serializable {
  ClientHandlerList clients;
  ArrayList<Lobby> lobbies;

    public LobbyList(ClientHandlerList client) {
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

    public Lobby getPlayersLobby(String playerName) {
        for (Lobby lobby : lobbies) {
            for (ClientHandler client : lobby.getPlayers()) {
                try {
                    if (client.getClientUsername().equals(playerName)) return lobby;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    public Lobby getLobbyByHost(String host) {
        for (Lobby lobby : lobbies) {
            if (lobby.getHost().equals(host)) return lobby;
        }
        return null;
    }
}
