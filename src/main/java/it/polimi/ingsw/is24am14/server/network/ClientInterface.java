package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {
    String getUsername() throws Exception;
    void connect(String username) throws Exception;
    void createLobby(int numPlayers) throws Exception;
    void joinLobby(String host) throws Exception;
    void startGame() throws Exception;
    void pickColor(TokenColour color) throws Exception;
    void updateGameContext() throws Exception;
    void pickObjectiveCard(ObjectiveCard objectiveCard) throws Exception;
    void flipCard(int index) throws Exception;
    void drawGoldCard() throws Exception;
    void drawResourceCard() throws Exception;
    void drawFaceUpCard(int index) throws Exception;
    void putCard(int handCardIndex, Coordinates coordinates, int cornerIndex) throws Exception;
    void sendMessage(String receiver, String message) throws Exception;
    void setStarterCard(StarterCard starterCard) throws Exception;

    ArrayList<String> getLobbyList() throws Exception;
}
