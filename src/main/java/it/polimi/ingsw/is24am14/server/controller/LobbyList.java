package it.polimi.ingsw.is24am14.server.controller;

import java.util.ArrayList;

/**
 * This class is used to manage the list of lobbies.
 */

public class LobbyList {
    private final ArrayList<Lobby> lobbies;

    public LobbyList() {
        lobbies = new ArrayList<Lobby>(); //questa classe viene creata dal main all'avio del server
    }

    public void addLobby(Lobby lobby) {
        lobbies.add(lobby);
        lobby.firstPlayerConnected(/*dovrò passargli qualcosa*/);
    }

    public void removeLobby(Lobby lobby) {
        lobbies.remove(lobby);
    } //chiamato dall'end game state per modificare la lista delle lobby, toglie la lobby appena terminata

    public ArrayList<Lobby> getLobbies() {
        return lobbies;
    }



}
