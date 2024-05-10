package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.game.Game;

import java.util.ArrayList;

public class TCPMediator implements ServerConnection {
    private String buffer;
    private ArrayList<ServerConnection> connections;

    public TCPMediator(ArrayList<ServerConnection> connections) {
        this.connections = connections;
    }
    @Override
    public void askForMove() throws Exception {
        //  send a request-move message to the client
    }

    @Override
    public void flipCard(int cardIndex) throws Exception {

    }
}
