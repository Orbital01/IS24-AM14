package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public interface RMIClientInterface extends Remote {
    void chooseOption(ArrayList<String> lobbiesNames) throws Exception;

    String getUsername() throws Exception;

    void receivePlayersInLobby(ArrayList<String> players) throws Exception;

    void selectColor(List<TokenColour> colors) throws Exception;

    void selectObjectiveCard(ObjectiveCard card1, ObjectiveCard card2) throws Exception;

    void printBlackToken() throws Exception;

    void chooseMove(ArrayList<PlayableCard> hand, GameArea board) throws Exception;

    void flipCard(ArrayList<PlayableCard> hand) throws Exception;

    void putCard(ArrayList<PlayableCard> hand, GameArea board) throws Exception;

    void pickChoice(Deck<GoldCard> goldDeck, Deck<ResourceCard> resourceDeck, ArrayList<PlayableCard> faceUpCards) throws Exception;

    void receiveScore(int score) throws Exception;

    void receiveWinner(String winner) throws Exception;
}
