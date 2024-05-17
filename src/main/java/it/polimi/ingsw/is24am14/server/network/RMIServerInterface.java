package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.player.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends ServerConnection, Remote {
    void acceptConnection(ClientConnection client) throws Exception;
    void assignSecretObjective(Object o, int playerIndex, ObjectiveCard objectiveCard) throws Exception;
    void flipCard(Object o, int cardIndex) throws Exception;
    void putCard(Object o, int cardToPlay, Coordinates alreadyPlayedCard, int cornerIndex) throws Exception;
    void drawGoldDeck(Object o) throws Exception;
    void drawResourceDeck(Object o) throws Exception;
    void drawFaceUpCard(Object o, int cardIndex) throws Exception;
}
