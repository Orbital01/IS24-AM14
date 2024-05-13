package it.polimi.ingsw.is24am14.server.network;

import java.rmi.Remote;
import java.util.List;

import it.polimi.ingsw.is24am14.server.controller.Lobby;
import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

public interface ServerConnection extends Remote {
    void askForMove() throws Exception;
    void flipCard(int cardIndex) throws Exception;
    String getClientNickname() throws Exception;
    void assignColor(List<TokenColour> colors, Player player) throws Exception;
    void chooseSecretObjective(Player player, Deck<ObjectiveCard> objectiveDeck) throws Exception;
    void drawGoldCard() throws Exception;
    void drawResourceCard() throws Exception;
    void drawFromFaceUp(int faceUpIndex) throws Exception;
    void askStartingOption() throws Exception;
    void joinExistingLobby(Lobby lobby) throws Exception;
    void joinNewLobby(int numPlayers) throws Exception;
}
