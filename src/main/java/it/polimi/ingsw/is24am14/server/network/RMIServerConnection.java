package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface RMIServerConnection extends ServerConnection, Remote {
    public void playCard(PlayableCard playedCard, Card alreadyPlacedCard, int cornerIndex) throws Exception;
    public ArrayList<PlayableCard> getPlayerHand() throws Exception;
    public HashMap<Coordinates, Card> getGameBoard() throws Exception;

    public String getClientNickname() throws RemoteException;
}
