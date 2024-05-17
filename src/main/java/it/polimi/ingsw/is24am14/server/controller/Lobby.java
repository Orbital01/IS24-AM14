package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfPlayersReachedException;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.network.ClientConnection;
import it.polimi.ingsw.is24am14.server.network.ClientHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Lobby implements Serializable {
    private final ArrayList<ClientHandler> players;
    private final int maxPlayers;
    private GameContext gameContext;
    private final String host;

    public Lobby(String host, int maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>();
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public GameContext getGameContext() {
        return gameContext;
    }

    public ClientHandler getClientHandler(String username) {
        for (ClientHandler clientHandler : players) {
            try {
                if (Objects.equals(clientHandler.getClientConnection().getUsername(), username)) return clientHandler;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void joinLobby(ClientHandler player) {
        this.players.add(player);
        if(this.players.size() == maxPlayers){
            System.out.println("Starting the game");
            startGame();
        }
    }

    public void startGame() {
        //questo metodo avvia la partita chiamando il pattern state del game
        this.gameContext = new GameContext(new Game(maxPlayers));
        GameStateScheduler scheduler = new GameStateScheduler(gameContext);
        //creo n players in base al numero impostato dall'utente
        // e li associo a dei player
        for (ClientHandler player : players) {
            Player newPlayer = null;
            try {
                newPlayer = new Player(player.getClientUsername(), player);
                newPlayer.getConnection().setGameContext(gameContext);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //va aggiunto questo metodo al server TCP RMI
            try {
                this.gameContext.game.addPlayer(newPlayer);
            } catch (MaximumNumberOfPlayersReachedException e) {
                System.out.println("Maximum number of players reached, returning to start"); // ma dove finisco??
                break;
            }
        }
        //creo il contesto del gioco e setto lo stato iniziale

        scheduler.run();
    }

}
