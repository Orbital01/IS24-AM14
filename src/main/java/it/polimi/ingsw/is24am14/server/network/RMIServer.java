package it.polimi.ingsw.is24am14.server.network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import it.polimi.ingsw.is24am14.server.controller.*;
import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

public class RMIServer extends UnicastRemoteObject implements RMIServerInterface {
    private final LobbyList lobbyList;

    protected RMIServer(LobbyList lobbies) throws RemoteException {
        this.lobbyList = lobbies;

        Registry registry = LocateRegistry.createRegistry(12345);
        try {
            registry.bind("RMIServer", this);
        }
        catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
        System.out.println("RMI Server started");
    }

    @Override
    public void acceptConnection(RMIClientInterface client, String username) throws Exception {
        if (lobbyList.getClientHandler(username) != null) throw new Exception("Username already taken");
        RMIClientHandler newClient = new RMIClientHandler(client, username);
        this.lobbyList.addClientHandler(newClient);
        newClient.askStartingOption(lobbyList.getLobbiesNames());
    }

    @Override
    public void createNewLobby(RMIClientInterface client, int numPlayers) throws Exception {
        this.lobbyList.createLobby(client.getUsername(), numPlayers);
        this.lobbyList.joinLobby(client.getUsername(), client.getUsername());

        ArrayList<String> playerUsernames = new ArrayList<>();
        for (ClientHandler player : this.lobbyList.getLobbyByHost(client.getUsername()).getPlayers()) {
            playerUsernames.add(player.getUsername());
        }
        client.receivePlayersInLobby(playerUsernames);
    }

    @Override
    public void joinExistingLobby(RMIClientInterface client, String lobbyHost) throws Exception {
        this.lobbyList.joinLobby(client.getUsername(), lobbyHost);

        ArrayList<String> playerUsernames = new ArrayList<>();
        for (ClientHandler player : this.lobbyList.getLobbyByHost(lobbyHost).getPlayers()) {
            playerUsernames.add(player.getUsername());
        }
        client.receivePlayersInLobby(playerUsernames);
    }

    @Override
    public void setColor(RMIClientInterface client, TokenColour color) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(client.getUsername());
        lobby.setColor(client.getUsername(), color);
        lobby.getGameContext().getGame().removeColor(color);
    }

    @Override
    public void setSecretObjective(RMIClientInterface client, ObjectiveCard card) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(client.getUsername());
        lobby.getGameContext().getGame().getPlayer(client.getUsername()).setSecretObjective(card);
    }

    @Override
    public void flipCard(RMIClientInterface client, int cardIndex) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(client.getUsername());
        lobby.getGameContext().getGame().getActivePlayer().getPlayerHand().get(cardIndex).flipSide();
        client.chooseMove(lobby.getGameContext().getGame().getActivePlayer().getPlayerHand(), lobby.getGameContext().getGame().getActivePlayer().getPlayerBoard());
    }

    @Override
    public void playCard(RMIClientInterface client, PlayableCard cardToPlay, Coordinates boardCard, int cornerIndex) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(client.getUsername());
        lobby.getGameContext().getGame().getActivePlayer().placeCard(boardCard, cardToPlay, cornerIndex);
        lobby.getGameContext().getGame().getActivePlayer().getPlayerHand().remove(cardToPlay);
    }

    @Override
    public void playCard(RMIClientInterface client, int indexCardToPlay, Coordinates boardCard, int cornerIndex) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(client.getUsername());
        PlayableCard cardToPlay = lobby.getGameContext().getGame().getActivePlayer().getPlayerHand().get(indexCardToPlay);
        lobby.getGameContext().getGame().getActivePlayer().placeCard(boardCard, cardToPlay, cornerIndex);
    }

    @Override
    public void drawFromGoldDeck(RMIClientInterface client) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(client.getUsername());
        lobby.getGameContext().getGame().getActivePlayer().addCardToHand(lobby.getGameContext().getGame().popGoldDeck());
    }

    @Override
    public void drawFromResourceDeck(RMIClientInterface client) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(client.getUsername());
        lobby.getGameContext().getGame().getActivePlayer().addCardToHand(lobby.getGameContext().getGame().popResourceDeck());
    }

    @Override
    public void drawFromFaceUpCards(RMIClientInterface client, int cardIndex) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(client.getUsername());
        lobby.getGameContext().getGame().getActivePlayer().addCardToHand(lobby.getGameContext().getGame().drawFaceUpCard(cardIndex));
    }
}
