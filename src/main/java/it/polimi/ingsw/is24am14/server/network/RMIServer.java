package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.client.ClientConnection;
import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServer extends UnicastRemoteObject implements RMIServerConnection {
    private Game game;
    ClientConnection client;
    ArrayList<ServerConnection> serverList;

    public RMIServer(ArrayList<ServerConnection> servers) throws RemoteException {
        this.serverList = servers;
    }

    @Override
    public void askForMove() throws Exception {
        client.makeMove();
    }

    @Override
    public void flipCard(int cardIndex) throws Exception {
        ArrayList<PlayableCard> playerHand = game.getPlayers().get(game.getIndexActivePlayer()).getPlayerHand();
        if (cardIndex > 0 && cardIndex < playerHand.size()) {
            playerHand.get(cardIndex).flipSide();
        }
        client.makeMove();
    }

    @Override
    public PlayableCard chooseCardFromHand() throws Exception {
        int indexHandCard = client.sendInt();
        Player activePlayer = game.getPlayers().get(game.getIndexActivePlayer());
        return activePlayer.getPlayerHand().get(indexHandCard);
    }

    public Card alreadyPlacedCard() throws Exception {
        int xPlacedCard = client.sendInt();
        int yPlacedCard = client.sendInt();
        Coordinates alreadyPlacedCoordinates = new Coordinates(xPlacedCard, yPlacedCard);
        return game.getPlayers().get(game.getIndexActivePlayer()).getPlayerBoard().getCard(alreadyPlacedCoordinates);
    }

    public int getCornerIndex() throws Exception {
        return client.sendInt();
    }

    public int getDrawDeckIndex() throws Exception {
        return client.sendInt();
    }

    public int getFaceUpCardIndex() throws Exception {
        return client.sendInt();
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

    public void acceptConnection(ClientConnection client, Game game) throws RemoteException {
        this.client = client;
        this.game = game;
        System.out.println("RMI client connected");
        serverList.add(this);
    }
}
