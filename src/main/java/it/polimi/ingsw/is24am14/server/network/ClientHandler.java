package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public interface ClientHandler extends Remote {
    void askStartingOption(LobbyList lobbyList) throws Exception;
    String getClientUsername() throws Exception;
    void setGameContext(GameContext gameContext) throws Exception;
    ClientConnection getClientConnection() throws Exception;
    void assignColor(List<TokenColour> tokenColourList, Player player) throws Exception;
    void chooseSecretObjective(int playerIndex, Deck<ObjectiveCard> objectiveDeck) throws Exception;
    void sendIsFirstPlayer(boolean isFirstPlayer) throws Exception;
    void askForMove(Player player) throws Exception;
    void askPickChoice(Deck<GoldCard> goldCardDeck, Deck<ResourceCard> resourceCardDeck, ArrayList<PlayableCard> faceUpCards) throws Exception;
    void sendScore(int score) throws Exception;
    void sendWinner(String winner) throws Exception;
}
