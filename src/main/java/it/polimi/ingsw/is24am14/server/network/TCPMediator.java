package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.Lobby;
import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.Deck;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.util.ArrayList;
import java.util.List;

public class TCPMediator implements ServerConnection {
    private String buffer;
    private ArrayList<ServerConnection> connections;

    public TCPMediator(ArrayList<ServerConnection> connections) {
        this.connections = connections;
    }
    @Override
    public void askForMove() throws Exception {
        //  send a request-move message to the client
    }

    @Override
    public void flipCard(int cardIndex) throws Exception {

    }

    @Override
    public String getClientNickname() throws Exception {
        return "";
    }

    @Override
    public void assignColor(List<TokenColour> colors, Player player) throws Exception {

    }

    @Override
    public void chooseSecretObjective(Player player, Deck<ObjectiveCard> objectiveDeck) throws Exception {

    }

    @Override
    public void drawGoldCard() throws Exception {

    }

    @Override
    public void drawResourceCard() throws Exception {

    }

    @Override
    public void drawFromFaceUp(int faceUpIndex) throws Exception {

    }

    @Override
    public void askStartingOption() throws Exception {

    }

    @Override
    public void joinExistingLobby(Lobby lobby) throws Exception {

    }

    @Override
    public void joinNewLobby(int numPlayers) throws Exception {

    }
}
