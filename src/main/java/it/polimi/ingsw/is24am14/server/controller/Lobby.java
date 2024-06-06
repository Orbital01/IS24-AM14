package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfPlayersReachedException;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.network.ClientHandler;
import it.polimi.ingsw.is24am14.server.network.ClientHandlerList;
import it.polimi.ingsw.is24am14.server.network.NotYourColorTurnException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Lobby implements Serializable {
    private final ClientHandlerList players;
    private final int maxPlayers;
    private GameContext gameContext;
    private final String host;

    public Lobby(String host, int maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.players = new ClientHandlerList();
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public ClientHandlerList getPlayers() {
        return players;
    }

    public GameContext getGameContext() {
        return gameContext;
    }

    public ClientHandler getClientHandler(String username) throws Exception {
        for (ClientHandler clientHandler : players) {
            if (Objects.equals(clientHandler.getUsername(), username)) return clientHandler;
        }
        return null;
    }

    public void joinLobby(ClientHandler player) throws Exception {
        this.players.add(player);
        printPlayers();
    }

    public void printPlayers() throws Exception {
        System.out.println("Players in lobby: ");
        for (ClientHandler player : this.players) {
            System.out.println(player.getUsername());
        }
    }

    public void setColor(String player, TokenColour color) throws Exception {
        this.gameContext.setColor(player, color);
    }

    public void startGame() throws Exception {
        this.gameContext = new GameContext(new Game(maxPlayers));

        for (ClientHandler player : players) {
            try {
                this.gameContext.game.addPlayer(new Player(player.getUsername(), player));
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
