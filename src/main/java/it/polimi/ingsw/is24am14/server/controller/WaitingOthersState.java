package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.player.Player;

public class WaitingOthersState implements LobbyState {

private LobbyContext context;

    public WaitingOthersState(LobbyContext context) {
        this.context = context;
    }


    public void handle() {
        //TODO
        //aspetta che tutti i giocatori si siano connessi
        //quando tutti i giocatori si sono connessi si passa allo stato AllPlayersConnectedState
        if (context.getPlayers().size() == context.getNumberOfPlayers()) {
            context.setState(new AllPlayersConnectedState(context));
            context.getState().handle();
        }
        else {
            //TODO
            //mandare un messaggio di attesa al Client
        }
    }

}
