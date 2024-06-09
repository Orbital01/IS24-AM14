package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.controller.Lobby;
import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import javax.naming.NameAlreadyBoundException;
import java.nio.channels.AlreadyConnectedException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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
    public void acceptConnection(ClientInterface client, String username) throws Exception {
        if (lobbyList.getClientHandler(username) != null) throw new NameAlreadyBoundException("Username already taken");
        RMIClientHandler newClient = new RMIClientHandler(client, username);
        this.lobbyList.addClientHandler(newClient);
        System.out.println("New client connected");
    }

    @Override
    public void createNewLobby(ClientInterface client, int numPlayers) throws Exception {
        if (this.lobbyList.getPlayersLobby(client.getUsername()) != null) throw new AlreadyConnectedException();

        this.lobbyList.createLobby(client.getUsername(), numPlayers);
        this.lobbyList.joinLobby(client.getUsername(), client.getUsername());
    }

    @Override
    public void joinLobby(ClientInterface client, String lobbyHost) throws Exception {
        if (this.lobbyList.getPlayersLobby(client.getUsername()) != null) throw new AlreadyConnectedException();

        this.lobbyList.joinLobby(client.getUsername(), lobbyHost);
    }

    @Override
    public void startGame(ClientInterface client) throws Exception {
        Lobby lobby = lobbyList.getLobbyByHost(client.getUsername());
        if (lobby != null && lobby.getPlayers().size() == lobby.getMaxPlayers()) {
            System.out.println("Starting game");
            lobby.startGame();
        }
    }

    @Override
    public void setColor(ClientInterface client, TokenColour color) throws Exception {
        this.lobbyList.getPlayersLobby(client.getUsername()).setColor(client.getUsername(), color);
    }

    @Override
    public GameContext getGameContext(ClientInterface client) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(client.getUsername());
        return lobby.getGameContext();
    }

    @Override
    public void setObjectiveCard(ClientInterface client, ObjectiveCard card) throws Exception {
        //  move check from here to controller
        String username = client.getUsername();
        Lobby lobby = this.lobbyList.getPlayersLobby(username);
        Player player = lobby.getGameContext().getGame().getPlayer(username);

        if (lobby.getGameContext().getGameStateEnum() == GameStateEnum.ChoosingSecretObjective && player.getSecretObjective() == null) {
            lobby.getGameContext().setObjectiveCard(player, card);
        }
    }

    @Override
    public void flipCard(ClientInterface client, int index) throws Exception {
        String username = client.getUsername();
        Lobby lobby = this.lobbyList.getPlayersLobby(username);
        lobby.getGameContext().getGame().getPlayer(username).getPlayerHand().get(index).flipSide();
    }

    @Override
    public void putCard(ClientInterface client, int handCardIndex, Coordinates cardToOverlap, int cornerIndex) throws Exception {
        String username = client.getUsername();
        this.lobbyList.getPlayersLobby(username).getGameContext().putCard(username, handCardIndex, cardToOverlap, cornerIndex);
    }

    @Override
    public void drawGoldCard(ClientInterface client) throws Exception {
        String username = client.getUsername();
        this.lobbyList.getPlayersLobby(username).getGameContext().drawGoldCard(username);
    }

    @Override
    public void drawResourceCard(ClientInterface client) throws Exception {
        String username = client.getUsername();
        this.lobbyList.getPlayersLobby(username).getGameContext().drawResourceCard(username);
    }

    @Override
    public void faceUpCard(ClientInterface client, int index) throws Exception {
        String username = client.getUsername();
        this.lobbyList.getPlayersLobby(username).getGameContext().drawFaceUpCard(username, index);
    }

    @Override
    public void addMessage(ClientInterface client, String receiver, String message) throws Exception {
        String username = client.getUsername();
        Lobby lobby = this.lobbyList.getPlayersLobby(username);
        lobby.getGameContext().addMessage(username, receiver, message);
    }
}
