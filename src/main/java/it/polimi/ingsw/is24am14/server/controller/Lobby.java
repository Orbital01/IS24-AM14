package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.player.Player;

import java.util.ArrayList;

public class Lobby {

    private LobbyContext context;

    public Lobby() {
        context = new LobbyContext();
    }

    public void firstPlayerConnected() {
        context.setState(new FirstConnectionState(context));
        context.getState().handle();
    }

    public boolean isAllPlayersConnected() {
        return context.getState().equals(new AllPlayersConnectedState(context));
        //anche questo è da testare ASAP!!!

        //a questo punto lancio il gioco

    }

    public ArrayList<Player> getPlayers() {
        return context.getPlayers();
    }

    public int getNumberOfPlayers() {
        return context.getNumberOfPlayers();
    }

    public LobbyContext getContext() {
        return context;
    }

}
