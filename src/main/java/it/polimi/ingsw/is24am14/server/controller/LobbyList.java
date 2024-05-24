package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.network.ClientHandler;
import it.polimi.ingsw.is24am14.server.network.ClientHandlerList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class LobbyList implements Serializable {
  ClientHandlerList clients;
  ArrayList<Lobby> lobbies;

    public LobbyList(ClientHandlerList client) {
        this.clients = client;
        this.lobbies = new ArrayList<>();
    }

    public void addClientHandler(ClientHandler client) {
        this.clients.add(client);
    }

    public ClientHandler getClientHandler(String name) {
        return this.clients.getClientHandler(name);
    }

    public void createLobby(String host, int maxPlayers) {
        this.lobbies.add(new Lobby(host, maxPlayers));
    }

    public void removeLobby(Lobby lobby) {
        lobbies.remove(lobby);
    }

    public void joinLobby(ClientHandler client, String host) {
        this.getLobbyByHost(host).joinLobby(client);
    }

    public void joinLobby(String client, String host) {
        this.getLobbyByHost(host).joinLobby(this.getClientHandler(client));
    }

    public Lobby getPlayersLobby(String playerName) {
        for (Lobby lobby : lobbies) {
            for (ClientHandler client : lobby.getPlayers()) {
                try {
                    if (client.getUsername().equals(playerName)) return lobby;
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

    public ArrayList<String> getLobbiesNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Lobby lobby : lobbies) {
            names.add(lobby.getHost());
        }
        return names;
    }

    public HashMap<String, String> getLobbiesInfo() {
        HashMap<String, String> info = new HashMap<>();
        for (Lobby lobby : lobbies) {
            info.put(lobby.getHost(), Integer.toString(lobby.getMaxPlayers()));
        }
        return info;
    }
}
