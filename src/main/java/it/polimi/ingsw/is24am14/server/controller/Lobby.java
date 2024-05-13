package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfPlayersReachedException;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.network.ServerConnection;

import java.util.ArrayList;

public class Lobby {
    ArrayList<ServerConnection> players;
    int maxPlayers;

    public Lobby(int maxPlayers, ArrayList<ServerConnection> players) {
        this.maxPlayers = maxPlayers;
        this.players = players;
    }

    public void addPlayer(ServerConnection player) {
        players.add(player);
    }

    public void startGame() {
        //questo metodo avvia la partita chiamando il pattern state del game
        Game game = new Game(maxPlayers);
        //creo n players in base al numero impostato dall'utente
        // e li associo a dei player
        for (ServerConnection player : players) {
            Player newPlayer = null;
            try {
                newPlayer = new Player(player.getClientNickname(), player);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //va aggiunto questo metodo al server TCP RMI
            try {
                game.addPlayer(newPlayer);
            } catch (MaximumNumberOfPlayersReachedException e) {
                System.out.println("Maximum number of players reached, returning to start"); // ma dove finisco??
                break;
            }
        }
        //creo il contesto del gioco e setto lo stato iniziale
        GameContext context = new GameContext(game);
        context.setGameState(new InitGameState(context));
        context.executeState(); // lancio il gioco
    }

}
