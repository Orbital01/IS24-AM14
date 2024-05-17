package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIClientHandler extends UnicastRemoteObject implements ClientHandler {
    private final ClientConnection client;
    private GameContext gameContext;    //  let me think if I need it or not

    protected RMIClientHandler(ClientConnection client) throws RemoteException {
        this.client = client;
    }

    @Override
    public void askStartingOption(LobbyList lobbyList) throws Exception {
        this.client.joinLobby(lobbyList);
    }

    @Override
    public String getClientUsername() throws Exception {
        return client.getUsername();
    }

    @Override
    public void setGameContext(GameContext gameContext) throws Exception {
        this.gameContext = gameContext;
    }

    @Override
    public ClientConnection getClientConnection() throws Exception {
        return client;
    }

    ///////////////////////////////////////////////////////////////////
    //                         InitGameState                         //
    ///////////////////////////////////////////////////////////////////
    @Override
    public void assignColor(List<TokenColour> tokenColourList, Player player) throws Exception {
        int option = this.client.pickColor(tokenColourList);
        player.setColour(tokenColourList.get(option));
        tokenColourList.remove(option);
    }

    @Override
    public void chooseSecretObjective(int playerIndex, Deck<ObjectiveCard> objectiveDeck) throws Exception {
        ArrayList<ObjectiveCard> objectiveCards = new ArrayList<>();
        objectiveCards.add(objectiveDeck.removeTop());
        objectiveCards.add(objectiveDeck.removeTop());
        this.client.pickSecretObjective(playerIndex, objectiveCards);
    }

    @Override
    public void sendIsFirstPlayer(boolean isFirstPlayer) throws Exception {
        this.client.receiveIsFirstPlayer(isFirstPlayer);
    }

    ///////////////////////////////////////////////////////////////////
    //                           PlayState                           //
    ///////////////////////////////////////////////////////////////////

    @Override
    public void askForMove(Player player) throws Exception {
        this.client.makeMove(player);
    }

    @Override
    public void askPickChoice(Deck<GoldCard> goldCardDeck, Deck<ResourceCard> resourceCardDeck, ArrayList<PlayableCard> faceUpCards) throws Exception {
        this.client.pickChoice(goldCardDeck, resourceCardDeck, faceUpCards);
    }

    ///////////////////////////////////////////////////////////////////
    //                           EndState                            //
    ///////////////////////////////////////////////////////////////////

    @Override
    public void sendScore(int score) throws Exception {
        this.client.printScore(score);
    }

    @Override
    public void sendWinner(String winner) throws Exception {
        this.client.printWinner(winner);
    }
}
