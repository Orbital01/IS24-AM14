package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.Lobby;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public interface ClientHandler extends Remote {
    String getUsername() throws Exception;

    void askStartingOption(ArrayList<String> lobbiesNames) throws Exception;

    void sendPlayersInLobby(Lobby lobby) throws Exception;

    void assignColor(List<TokenColour> colours) throws Exception;

    void askSecretObjective(ObjectiveCard card1, ObjectiveCard card2) throws Exception;

    void sendIsFirstPlayer() throws Exception;

    void askForMove(ArrayList<PlayableCard> hand, GameArea board) throws Exception;

    void askPickChoice(Deck<GoldCard> goldDeck, Deck<ResourceCard> resourceDeck, ArrayList<PlayableCard> faceUpCards) throws Exception;

    void sendScore(int score) throws Exception;

    void sendWinner(String winner) throws Exception;
}
