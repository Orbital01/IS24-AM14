package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfPlayersReachedException;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.network.ClientHandler;
import it.polimi.ingsw.is24am14.server.network.ClientHandlerList;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a lobby for managing players joining and starting a game session.
 * This class facilitates player management, game initialization, and state handling.
 */
public class Lobby implements Serializable {
    private final ClientHandlerList players;
    private final int maxPlayers;
    private GameContext gameContext;
    private final String host;

    /**
     * Constructs a new Lobby with a specified host and maximum number of players.
     *
     * @param host       the host username for this lobby
     * @param maxPlayers the maximum number of players allowed in the lobby
     */
    public Lobby(String host, int maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.players = new ClientHandlerList();
        this.host = host;
    }

    /**
     * Retrieves the username of the host for this lobby.
     *
     * @return the host username
     */
    public String getHost() {
        return host;
    }

    /**
     * Retrieves the maximum number of players allowed in this lobby.
     *
     * @return the maximum number of players
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Retrieves the list of players currently in this lobby.
     *
     * @return the list of players as a {@code ClientHandlerList}
     */
    public ClientHandlerList getPlayers() {
        return players;
    }

    /**
     * Retrieves the game context associated with this lobby.
     *
     * @return the game context
     */
    public GameContext getGameContext() {
        return gameContext;
    }

    /**
     * Adds a player represented by the client handler to the lobby.
     *
     * @param player the client handler representing the player to add
     * @throws Exception if an error occurs while adding the player to the lobby
     */
    public void joinLobby(ClientHandler player) throws Exception {
        this.players.add(player);
        printPlayers();
    }

    /**
     * Prints the list of players currently in the lobby to the console.
     *
     * @throws Exception if an error occurs while printing the players
     */
    public void printPlayers() throws Exception {
        System.out.println("Players in lobby: ");
        for (ClientHandler player : this.players) {
            System.out.println(player.getUsername());
        }
    }

    /**
     * Sets the color for a specified player in the game context.
     *
     * @param player the username of the player whose color is to be set
     * @param color  the color to set for the player
     * @throws Exception if an error occurs while setting the player's color
     */
    public void setColor(String player, TokenColour color) throws Exception {
        this.gameContext.setColor(player, color);
    }

    /**
     * Starts the game by initializing a new game context, adding players, and setting initial game states.
     *
     * @throws Exception if an error occurs during game initialization or player addition
     */
    public void startGame() throws Exception {
        this.gameContext = new GameContext(new Game(maxPlayers));

        for (ClientHandler player : players) {
            try {
                this.gameContext.game.addPlayer(new Player(player.getUsername()));
            } catch (MaximumNumberOfPlayersReachedException e) {
                throw new RuntimeException(e);
            }
        }

        gameContext.getGame().init();

        gameContext.setGameStateEnum(GameStateEnum.DeckInit);
        System.out.println("Game initialized");

        gameContext.setGameStateEnum(GameStateEnum.ChoosingColor);
        System.out.println("Choosing color");
    }
}
