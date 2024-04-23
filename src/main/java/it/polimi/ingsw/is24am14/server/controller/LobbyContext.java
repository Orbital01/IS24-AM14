package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.player.Player;

import java.util.ArrayList;

/**
 * this class is the context of the lobby, it will contain the state of the lobby
 * this context will be used by the lobby state machine to exchange information
 */

public class LobbyContext {
    private LobbyState state;
    private int numberOfPlayers;
    private ArrayList<Player> players; //da sostituire poi con il client che si connette
    private Boolean isPlayerReady;

    //altri attributi che possono servire

    /**
     * this method will be called by the state to change the state of the context
     * @param state the new state
     */
    public void setState(LobbyState state) {
        this.state = state;
    }

    public LobbyState getState() {
        return state;
    }

    public void handle() {
        state.handle();
    }

    //metodi getter e setter per recuperare i dati necessari al cambio di stato

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void setPlayerReady(Boolean isPlayerReady) {
        this.isPlayerReady = isPlayerReady;
    }

    public Boolean isPlayerReady() {
        return isPlayerReady;
    }

}
