package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import javax.naming.NameAlreadyBoundException;
import java.nio.channels.AlreadyConnectedException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * RMIClient represents the client-side implementation of a client-server interaction via RMI.
 * It implements the ClientInterface to define methods for interacting with the server.
 */
public class RMIClient extends UnicastRemoteObject implements ClientInterface {
    private String username;
    private final RMIServerInterface server;
    private GameContext context;

    /**
     * Constructs an RMIClient and establishes connection to the RMIServer.
     *
     * @throws Exception If an error occurs during RMI connection setup.
     */
    public RMIClient() throws Exception {
        Registry registry;
        registry = LocateRegistry.getRegistry(NetworkSettings.serverAddress, NetworkSettings.RMIPort);
        this.server = (RMIServerInterface) registry.lookup("RMIServer");
        this.context = null;
    }

    /**
     * Retrieves the username associated with this client.
     *
     * @return The username of the client.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public String getUsername() throws Exception {
        return username;
    }

    /**
     * Connects the client to the server with the specified username.
     *
     * @param username The username chosen by the client.
     * @throws NameAlreadyBoundException If the username is already taken.
     * @throws RemoteException           If an RMI-related communication error occurs.
     */
    @Override
    public void connect(String username) throws Exception {
            this.server.acceptConnection(this, username);
            this.username = username;
    }

    /**
     * Creates a new lobby with the specified number of players.
     *
     * @param numPlayers The number of players the lobby should accommodate.
     * @throws AlreadyConnectedException If the client is already connected to a lobby.
     * @throws RemoteException           If an RMI-related communication error occurs.
     */
    @Override
    public void createLobby(int numPlayers) throws Exception {
        try {
            this.server.createNewLobby(this, numPlayers);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * Joins the client to an existing lobby hosted by another client.
     *
     * @param host The username of the client hosting the lobby to join.
     * @throws AlreadyConnectedException If the client is already connected to a lobby.
     * @throws RemoteException           If an RMI-related communication error occurs.
     */
    @Override
    public void joinLobby(String host) throws Exception {
        try {
            this.server.joinLobby(this, host);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * Requests the server to start the game in the current lobby.
     *
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void startGame() throws Exception {
        this.server.startGame(this);
    }

    /**
     * Chooses a token color for the player in the game.
     *
     * @param color The TokenColour enum representing the chosen color.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void pickColor(TokenColour color) throws Exception {
        this.server.setColor(this, color);
    }

    /**
     * Updates the client's local game context from the server.
     *
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void updateGameContext() throws Exception {
        this.context = this.server.getGameContext(this);
    }

    /**
     * Chooses an objective card for the player in the game.
     *
     * @param objectiveCard The ObjectiveCard object representing the card to be chosen.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void pickObjectiveCard(ObjectiveCard objectiveCard) throws Exception {
        this.server.setObjectiveCard(this, objectiveCard);
    }

    /**
     * Flips a card in the player's hand.
     *
     * @param index The index of the card in the player's hand to flip.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void flipCard(int index) throws Exception {
        this.server.flipCard(this, index);
    }

    /**
     * Requests to draw a gold card for the player.
     *
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void drawGoldCard() throws Exception {
        this.server.drawGoldCard(this);
    }

    /**
     * Requests to draw a resource card for the player.
     *
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void drawResourceCard() throws Exception {
        this.server.drawResourceCard(this);
    }

    /**
     * Requests to draw a face-up card for the player.
     *
     * @param index The index of the face-up card to draw.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void drawFaceUpCard(int index) throws Exception {
        this.server.faceUpCard(this, index);
    }

    /**
     * Places a card from the player's hand onto the board.
     *
     * @param handCardIndex The index of the card in the player's hand to place.
     * @param coordinates   The coordinates specifying where to place the card on the board.
     * @param cornerIndex   The corner index of the card.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void putCard(int handCardIndex, Coordinates coordinates, int cornerIndex) throws Exception {
        this.server.putCard(this, handCardIndex, coordinates, cornerIndex);
    }

    /**
     * Sends a message from the player to another player.
     *
     * @param receiver The username of the message receiver.
     * @param message  The message content to be sent.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void sendMessage(String receiver, String message) throws Exception {
        this.server.addMessage(this, receiver, message);
    }

    /**
     * Sets the starter card for the player in the game.
     *
     * @param starterCard The StarterCard object representing the card to be set.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void setStarterCard(StarterCard starterCard) throws Exception {
        this.server.setStarterCard(this, starterCard);
    }

    /**
     * Retrieves the current game context from the client side.
     *
     * @return The GameContext object representing the current game state and information.
     */
    public GameContext getGameContext() {
        return this.context;
    }

    /**
     * Retrieves the list of active lobbies from the server.
     *
     * @return An ArrayList of Strings representing the names of active lobbies.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    public ArrayList<String> getLobbyList() throws Exception {
        return this.server.getLobbyList();
    }

    /**
     * Retrieves the list of clients in a specific lobby hosted by the given host username.
     *
     * @param lobbyHost The username of the client hosting the lobby.
     * @return An ArrayList of Strings representing the usernames of clients in the specified lobby.
     * @throws Exception If the lobby does not exist or if there are errors accessing lobby information.
     */
    public ArrayList<String> getLobbyClients(String lobbyHost) throws Exception {
        return this.server.getLobbyClients(lobbyHost);
    }

}
