package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.client.ClientConnection;
import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.controller.PlayState;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMIServer extends UnicastRemoteObject implements RMIServerConnection {
    private GameContext context;
    ClientConnection client;
    ArrayList<ServerConnection> serverList;

    public RMIServer(ArrayList<ServerConnection> servers) throws RemoteException {
        this.serverList = servers;
    }

    public void setContext(GameContext context) {
        this.context = context;
    }

    @Override
    public void askForMove() throws Exception {
        client.makeMove();
    }

    @Override
    public void flipCard(int cardIndex) throws Exception {
        ArrayList<PlayableCard> playerHand = context.getGame().getPlayers().get(context.getGame().getIndexActivePlayer()).getPlayerHand();
        if (cardIndex > 0 && cardIndex < playerHand.size()) {
            playerHand.get(cardIndex).flipSide();
        }
        client.makeMove();
    }

    @Override
    public String getClientNickname() throws Exception {
        return "";
    }

    @Override
    public void assignColor(List<TokenColour> colors, Player player) throws Exception {
        client.chooseColor(colors, player);
    }

    public void playCard(PlayableCard playedCard, Card alreadyPlacedCard, int cornerIndex) throws Exception {
        boolean success = PlayState.playCard(playedCard, alreadyPlacedCard, cornerIndex, context.getGame().getPlayers().get(context.getGame().getIndexActivePlayer()));
        if (!success) {
            client.makeMove();
        }
        context.setLastPlayedCard(playedCard);
    }

    public void chooseSecretObjective(Player player, Deck<ObjectiveCard> objectiveDeck) throws Exception {
        ArrayList<ObjectiveCard> chooseSecret = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            chooseSecret.add(objectiveDeck.removeTop()); //POLYMORPHISM ERROR: to be fixed by Matteo by introducing Java generics types
        }
        // client.pickObjective(chooseSecrets, player);
    }

    @Override
    public void drawGoldCard() throws Exception {
        Player currentPlayer = context.getGame().getPlayers().get(context.getGame().getIndexActivePlayer());
        currentPlayer.addCardToHand(context.getGame().popGoldDeck());
        if (context.getGame().areDecksEmpty()) context.getGame().setEndGame();
    }

    //  Non viene gestito il caso in cui se il gold deck è vuoto pesco dal resource
    @Override
    public void drawResourceCard() throws Exception {
        Player currentPlayer = context.getGame().getPlayers().get(context.getGame().getIndexActivePlayer());
        currentPlayer.addCardToHand(context.getGame().popResourceDeck());
        if (context.getGame().areDecksEmpty()) context.getGame().setEndGame();
    }

    //  Non viene gestito il caso in cui se il resource deck è vuoto pesco dal gold
    @Override
    public void drawFromFaceUp(int faceUpIndex) throws Exception {
        Player currentPlayer = context.getGame().getPlayers().get(context.getGame().getIndexActivePlayer());
        currentPlayer.addCardToHand(context.getGame().removeFaceUpCard(faceUpIndex));
        if (context.getGame().areDecksEmpty()) context.getGame().setEndGame();
    }

    public ArrayList<PlayableCard> getPlayerHand() throws Exception {
        return new ArrayList<>(context.getGame().getPlayers().get(context.getGame().getIndexActivePlayer()).getPlayerHand());
    }

    public HashMap<Coordinates, Card> getGameBoard() throws Exception {
        return new HashMap<>(context.getGame().getPlayers().get(context.getGame().getIndexActivePlayer()).getPlayerBoard().getBoard());
    }

    public void startServer() throws Exception {
        Registry registry = LocateRegistry.createRegistry(12345);
        try {
            registry.bind("RMIServer", this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("RMI server started");
    }

    public void acceptConnection(ClientConnection client) throws RemoteException {
        this.client = client;
        System.out.println("RMI client connected");
        serverList.add(this);
    }
}
