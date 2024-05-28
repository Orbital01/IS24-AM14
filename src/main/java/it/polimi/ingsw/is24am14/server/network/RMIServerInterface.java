package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {
    void acceptConnection(RMIClientInterface client, String username) throws Exception;

    void createNewLobby(RMIClientInterface client, int numPlayers) throws Exception;

    void joinExistingLobby(RMIClientInterface client, String lobbyHost) throws Exception;

    void setColor(RMIClientInterface client, TokenColour color) throws Exception;

    void setSecretObjective(RMIClientInterface client, ObjectiveCard card) throws Exception;

    void flipCard(RMIClientInterface client, int cardIndex) throws Exception;

    void playCard(RMIClientInterface client, PlayableCard cardToPlay, Coordinates boardCard, int cornerIndex) throws Exception;

    void playCard(RMIClientInterface client, int indexCardToPlay, Coordinates boardCard, int cornerIndex) throws Exception;

    void drawFromGoldDeck(RMIClientInterface client) throws Exception;

    void drawFromResourceDeck(RMIClientInterface client) throws Exception;

    void drawFromFaceUpCards(RMIClientInterface client, int cardIndex) throws Exception;
}
