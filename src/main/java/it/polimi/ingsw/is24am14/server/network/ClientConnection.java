package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public interface ClientConnection extends Remote {
    void joinLobby(LobbyList lobbyList) throws Exception;

    void execute() throws Exception;

    String getUsername() throws Exception;

    int pickColor(List<TokenColour> colours) throws Exception;

    void pickSecretObjective(int playerIndex, ArrayList<ObjectiveCard> objectiveCardDeck) throws Exception;

    void receiveIsFirstPlayer(boolean isFirstPlayer) throws Exception;

    void makeMove(Player player) throws Exception;

    void pickChoice(Deck<GoldCard> goldCardDeck, Deck<ResourceCard> resourceCardDeck, ArrayList<PlayableCard> faceUpCards) throws Exception;

    void printScore(int score) throws Exception;

    void printWinner(String winner) throws Exception;
}
