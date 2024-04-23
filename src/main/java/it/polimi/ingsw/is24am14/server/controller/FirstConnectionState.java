package it.polimi.ingsw.is24am14.server.controller;

public class FirstConnectionState implements LobbyState {
    private LobbyContext context;

    public FirstConnectionState(LobbyContext context) {
        this.context = context;
    }

    public void handle() {
        //il PlayerClient si è appena connesso, bisogna chiedergli di specificare quanti giocatori ci sono

        //TODO

        //si verifica che il player abbia passato un parametro corretto
        // il numero di giocatori deve essere compreso tra 2 e 4
        // se il numero di giocatori è corretto si passa allo stato WaitingOthersState
        // altrimenti si rimane in questo stato mandando un messaggio di errore al player
        if (context.getNumberOfPlayers() >= 2 && context.getNumberOfPlayers() <= 4) {
            context.setState(new WaitingOthersState(context));
            context.getState().handle(); //questa cosa va testata!!!
        }
        else {
            //TODO

            //mandare un messaggio di errore al Client
        }

    }
}