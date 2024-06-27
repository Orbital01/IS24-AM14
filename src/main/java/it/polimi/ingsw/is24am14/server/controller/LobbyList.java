package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.network.ClientHandler;
import it.polimi.ingsw.is24am14.server.network.ClientHandlerList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a collection of lobbies and manages client connections within those lobbies.
 * This class provides methods to create, join, and retrieve information about lobbies.
 */
public class LobbyList implements Serializable {
  ClientHandlerList clients;
  ArrayList<Lobby> lobbies;

    /**
     * Constructs a new LobbyList initialized with a list of clients.
     *
     * @param client the list of client handlers to initialize with
     */
    public LobbyList(ClientHandlerList client) {
        this.clients = client;
        this.lobbies = new ArrayList<>();
    }

    /**
     * Adds a client handler to the list of connected clients.
     *
     * @param client the client handler to add
     */
    public void addClientHandler(ClientHandler client) {
        this.clients.add(client);
    }

    /**
     * Retrieves the client handler associated with a specific client name.
     *
     * @param name the name of the client
     * @return the client handler associated with the name, or {@code null} if not found
     */
    public ClientHandler getClientHandler(String name) {
        return this.clients.getClientHandler(name);
    }

    /**
     * Creates a new lobby with the specified host and maximum number of players.
     *
     * @param host       the host username for the new lobby
     * @param maxPlayers the maximum number of players allowed in the lobby
     */
    public void createLobby(String host, int maxPlayers) {
        this.lobbies.add(new Lobby(host, maxPlayers));
    }

    /**
     * Allows a client to join a lobby hosted by a specified host.
     *
     * @param client the name of the client joining the lobby
     * @param host   the host of the lobby to join
     * @throws Exception if an error occurs while attempting to join the lobby
     */
    public void joinLobby(String client, String host) throws Exception {
        this.getLobbyByHost(host).joinLobby(this.getClientHandler(client));
    }

    /**
     * Retrieves the lobby in which a specific player is currently located.
     *
     * @param playerName the name of the player whose lobby is to be retrieved
     * @return the lobby object containing the player, or {@code null} if not found
     */
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

    /**
     * Retrieves the lobby associated with a specific host username.
     *
     * @param host the host username of the lobby to retrieve
     * @return the lobby object associated with the host, or {@code null} if not found
     */
    public Lobby getLobbyByHost(String host) {
        for (Lobby lobby : lobbies) {
            if (lobby.getHost().equals(host)) return lobby;
        }
        return null;
    }

    /**
     * Retrieves the names of all active lobbies.
     *
     * @return an {@code ArrayList} of strings representing the host names of active lobbies
     */
    public ArrayList<String> getLobbiesNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Lobby lobby : lobbies) {
            names.add(lobby.getHost());
        }
        return names;
    }
}
