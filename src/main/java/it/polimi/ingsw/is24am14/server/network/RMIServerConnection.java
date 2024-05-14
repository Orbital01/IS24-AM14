package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.client.ClientConnection;
import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;

public interface RMIServerConnection extends ServerConnection, Remote {
    void playCard(PlayableCard playedCard, Card alreadyPlacedCard, int cornerIndex) throws Exception;
    ArrayList<PlayableCard> getPlayerHand() throws Exception;
    HashMap<Coordinates, Card> getGameBoard() throws Exception;
    void acceptConnection(ClientConnection client) throws Exception;
}
