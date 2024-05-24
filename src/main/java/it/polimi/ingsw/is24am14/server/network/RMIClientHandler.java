package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIClientHandler extends UnicastRemoteObject implements ClientHandler {
    private final String username;
    private final RMIClientInterface client;

    protected RMIClientHandler(RMIClientInterface client, String username) throws RemoteException {
        this.username = username;
        this.client = client;
    }

    @Override
    public String getUsername() throws Exception {
        return username;
    }

    @Override
    public void askStartingOption(ArrayList<String> lobbiesNames) throws Exception {
        this.client.chooseOption(lobbiesNames);
    }

    @Override
    public void sendPlayersInLobby(ArrayList<String> players) throws Exception {
        this.client.receivePlayersInLobby(players);
    }

    @Override
    public void assignColor(List<TokenColour> colors) throws Exception {
        this.client.selectColor(colors);
    }

    @Override
    public void askSecretObjective(ObjectiveCard card1, ObjectiveCard card2) throws Exception {
        this.client.selectObjectiveCard(card1, card2);
    }

    @Override
    public void sendIsFirstPlayer() throws Exception {
        this.client.printBlackToken();
    }

    @Override
    public void askForMove(ArrayList<PlayableCard> hand, GameArea board) throws Exception {
        this.client.chooseMove(hand, board);
    }

    @Override
    public void askPickChoice(Deck<GoldCard> goldDeck, Deck<ResourceCard> resourceDeck, ArrayList<PlayableCard> faceUpCards) throws Exception {
        this.client.pickChoice(goldDeck, resourceDeck, faceUpCards);
    }

    @Override
    public void sendScore(int score) throws Exception {
        this.client.receiveScore(score);
    }

    @Override
    public void sendWinner(String winner) throws Exception {
        this.client.receiveWinner(winner);
    }
}
