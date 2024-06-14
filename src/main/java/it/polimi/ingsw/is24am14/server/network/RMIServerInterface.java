package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIServerInterface extends Remote {
    void acceptConnection(ClientInterface client, String username) throws Exception;
    void createNewLobby(ClientInterface client, int numPlayers) throws Exception;
    void joinLobby(ClientInterface client, String lobbyHost) throws Exception;
    void startGame(ClientInterface client) throws Exception;
    void setColor(ClientInterface client, TokenColour color) throws Exception;
    GameContext getGameContext(ClientInterface client) throws Exception;
    void setObjectiveCard(ClientInterface client, ObjectiveCard card) throws Exception;
    void flipCard(ClientInterface client, int index) throws Exception;
    void putCard(ClientInterface client, int handCardIndex, Coordinates cardToOverlap, int cornerIndex) throws Exception;
    void drawGoldCard(ClientInterface client) throws Exception;
    void drawResourceCard(ClientInterface client) throws Exception;
    void faceUpCard(ClientInterface client, int index) throws Exception;
    void addMessage(ClientInterface client, String receiver, String message) throws Exception;
    void setStarterCard(ClientInterface client, StarterCard card) throws Exception;
    ArrayList<Message> getMessages(ClientInterface client) throws Exception;

    ArrayList<String> getLobbyList() throws Exception;
    ArrayList<String> getLobbyClients(String lobbyHost) throws Exception;
}
