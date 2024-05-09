package it.polimi.ingsw.is24am14.server.controller;

public class AllPlayersConnectedState implements LobbyState {

    private LobbyContext context;

    public AllPlayersConnectedState(LobbyContext context) {
        this.context = context;
    }

    public void handle() {
        context.setPlayerReady(true);
        //mandare un messaggio a tutti i giocatori che la partita sta per iniziare
    }

}
