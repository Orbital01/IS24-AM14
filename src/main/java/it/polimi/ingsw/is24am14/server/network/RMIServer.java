package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.Lobby;
import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;

public class RMIServer extends UnicastRemoteObject implements RMIServerInterface {
    private final ClientHandlerList clients;
    private final LobbyList lobbyList;

    protected RMIServer(ClientHandlerList clients, LobbyList lobbyList) throws RemoteException {
        this.clients = clients;
        this.lobbyList = lobbyList;
    }

    public void startServer() throws Exception {
        Registry registry = LocateRegistry.createRegistry(12345);
        try {
            registry.bind("RMIServer", this);
        }
        catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
        System.out.println("RMI server started");
    }

    @Override
    public void acceptConnection(ClientConnection client) throws Exception {
        RMIClientHandler clientHandler = new RMIClientHandler(client);
        this.clients.add(clientHandler);
        System.out.println("RMI client connected");
        clientHandler.askStartingOption(this.lobbyList);
    }

    @Override
    public void assignSecretObjective(Object o, int playerIndex, ObjectiveCard objectiveCard) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(((ClientConnection) o).getUsername());
        lobby.getGameContext().getGame().getPlayers().get(playerIndex).setObjectiveCard(objectiveCard);
    }

    @Override
    public void flipCard(Object o, int cardIndex) throws Exception {
        ClientConnection clientConnection = (ClientConnection) o;
        Lobby lobby = this.lobbyList.getPlayersLobby(clientConnection.getUsername());
        Game game = lobby.getGameContext().getGame();
        game.getPlayers().get(game.getIndexActivePlayer()).getPlayerHand().get(cardIndex).flipSide();
        for (PlayableCard c : game.getPlayers().get(game.getIndexActivePlayer()).getPlayerHand())
        {
            System.out.println(c.getSide());
        }
        //game.getPlayers().get(game.getIndexActivePlayer()).getConnection().askForMove(game.getPlayers().get(game.getIndexActivePlayer()));
        clientConnection.makeMove(game.getPlayers().get(game.getIndexActivePlayer()));
    }

    @Override
    public void putCard(Object o, int indexToPlay, Coordinates alreadyPlayedCoordinates, int cornerIndex) throws Exception {
        //  get the objects I need
        Lobby lobby = this.lobbyList.getPlayersLobby(((ClientConnection) o).getUsername());
        Game game = lobby.getGameContext().getGame();
        Card alreadyPlayedCard = game.getPlayers().get(game.getIndexActivePlayer()).getPlayerBoard().getCard(alreadyPlayedCoordinates);
        PlayableCard cardToPlay = game.getPlayers().get(game.getIndexActivePlayer()).getPlayerHand().get(indexToPlay);

        //  put the card on the board
        game.getPlayers().get(game.getIndexActivePlayer()).placeCard(alreadyPlayedCard, cardToPlay, cornerIndex);

        //  remove the card from the player's hand
        game.getPlayers().get(game.getIndexActivePlayer()).removeCardFromHand(indexToPlay);

        System.out.println("______");
        for (Card card : game.getPlayers().get(game.getIndexActivePlayer()).getPlayerBoard().getBoard().values())
        {
            System.out.println(card.getFrontImage());
        }
        System.out.println("______");
    }


    @Override
    public void joinExistingLobby(Object o, Lobby lobby) throws Exception {
        ClientHandler clientHandler = this.clients.getClientHandler(((ClientConnection) o).getUsername());
        this.lobbyList.getLobbyByHost(lobby.getHost()).joinLobby(clientHandler);
    }

    @Override
    public void joinNewLobby(Object o, int numPlayers) throws Exception {
        ClientHandler client = this.clients.getClientHandler(((ClientConnection) o).getUsername());
        Lobby lobby = new Lobby(client.getClientUsername(), numPlayers);
        this.lobbyList.createLobby(lobby);
        lobby.joinLobby(client);
    }

    @Override
    public void drawGoldDeck(Object o) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(((ClientConnection) o).getUsername());
        Game game = lobby.getGameContext().getGame();
        game.getPlayers().get(game.getIndexActivePlayer()).addCardToHand(game.popGoldDeck());
    }

    @Override
    public void drawResourceDeck(Object o) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(((ClientConnection) o).getUsername());
        Game game = lobby.getGameContext().getGame();
        game.getPlayers().get(game.getIndexActivePlayer()).addCardToHand(game.popResourceDeck());
    }

    @Override
    public void drawFaceUpCard(Object o, int cardIndex) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(((ClientConnection) o).getUsername());
        Game game = lobby.getGameContext().getGame();
        game.getPlayers().get(game.getIndexActivePlayer()).addCardToHand(game.drawFaceUpCard(cardIndex));
    }
}
