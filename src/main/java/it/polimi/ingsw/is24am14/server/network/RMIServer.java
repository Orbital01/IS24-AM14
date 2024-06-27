package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.controller.Lobby;
import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import javax.naming.NameAlreadyBoundException;
import java.nio.channels.AlreadyConnectedException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * RMIServer represents the server-side implementation of the RMI server for handling game operations.
 * It implements the RMIServerInterface to provide remote methods for clients to interact with the game.
 */
public class RMIServer extends UnicastRemoteObject implements RMIServerInterface {
    private final LobbyList lobbyList;

    /**
     * Constructor for RMIServer. Initializes the server and binds it to the RMI registry.
     *
     * @param lobbies The LobbyList containing all active lobbies.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    protected RMIServer(LobbyList lobbies) throws RemoteException {
        this.lobbyList = lobbies;

        // Set the hostname for RMI server
        System.setProperty("java.rmi.server.hostname", NetworkSettings.serverAddress);

        // Create RMI registry on specified port
        Registry registry = LocateRegistry.createRegistry(NetworkSettings.RMIPort);
        try {
            registry.bind("RMIServer", this);
        }
        catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
        System.out.println("RMI Server started");
    }

    /**
     * Accepts a new client connection and add it to the client handler list.
     *
     * @param client   The ClientInterface object representing the connected client.
     * @param username The username chosen by the client.
     * @throws NameAlreadyBoundException If the username is already taken in the lobby.
     * @throws RemoteException           If an RMI-related communication error occurs.
     */
    @Override
    public void acceptConnection(ClientInterface client, String username) throws Exception {
        if (lobbyList.getClientHandler(username) != null) throw new NameAlreadyBoundException("Username already taken");
        RMIClientHandler newClient = new RMIClientHandler(client, username);
        this.lobbyList.addClientHandler(newClient);
        System.out.println("New client connected");
    }

    /**
     * Creates a new lobby hosted by the client and joins the client to it.
     *
     * @param client    The ClientInterface object representing the client creating the lobby.
     * @param numPlayers The number of players the lobby should accommodate.
     * @throws AlreadyConnectedException If the client is already connected to a lobby.
     * @throws RemoteException          If an RMI-related communication error occurs.
     */
    @Override
    public void createNewLobby(ClientInterface client, int numPlayers) throws Exception {
        if (this.lobbyList.getPlayersLobby(client.getUsername()) != null) throw new AlreadyConnectedException();

        this.lobbyList.createLobby(client.getUsername(), numPlayers);
        this.lobbyList.joinLobby(client.getUsername(), client.getUsername());
    }

    /**
     * Allows a client to join an existing lobby hosted by another client.
     *
     * @param client    The ClientInterface object representing the client joining the lobby.
     * @param lobbyHost The username of the client hosting the lobby to join.
     * @throws AlreadyConnectedException If the client is already connected to a lobby.
     * @throws RemoteException          If an RMI-related communication error occurs.
     */
    @Override
    public void joinLobby(ClientInterface client, String lobbyHost) throws Exception {
        if (this.lobbyList.getPlayersLobby(client.getUsername()) != null) throw new AlreadyConnectedException();

        this.lobbyList.joinLobby(client.getUsername(), lobbyHost);
    }

    /**
     * Starts the game for a lobby if conditions are met (host and maximum players).
     *
     * @param client The ClientInterface object representing the client starting the game.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void startGame(ClientInterface client) throws Exception {
        Lobby lobby = lobbyList.getLobbyByHost(client.getUsername());
        if (lobby != null && lobby.getPlayers().size() == lobby.getMaxPlayers()) {
            System.out.println("Starting game");
            lobby.startGame();
        }
    }

    /**
     * Sets the token color for a player in the lobby.
     *
     * @param client The ClientInterface object representing the client setting the color.
     * @param color  The TokenColour enum representing the chosen color.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void setColor(ClientInterface client, TokenColour color) throws Exception {
        this.lobbyList.getPlayersLobby(client.getUsername()).setColor(client.getUsername(), color);
    }

    /**
     * Retrieves the current game context for a client in the lobby.
     *
     * @param client The ClientInterface object representing the client requesting the game context.
     * @return The GameContext object containing the current game state and information.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public GameContext getGameContext(ClientInterface client) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(client.getUsername());
        return lobby.getGameContext();
    }

    /**
     * Sets the objective card for a player in the lobby's game context.
     *
     * @param client The ClientInterface object representing the client setting the objective card.
     * @param card   The ObjectiveCard object representing the card to be set.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void setObjectiveCard(ClientInterface client, ObjectiveCard card) throws Exception {
        String username = client.getUsername();
        Lobby lobby = this.lobbyList.getPlayersLobby(username);
        Player player = lobby.getGameContext().getGame().getPlayer(username);

        lobby.getGameContext().setObjectiveCard(player, card);
    }

    /**
     * Flips the specified card in the player's hand in the lobby's game context.
     *
     * @param client The ClientInterface object representing the client flipping the card.
     * @param index  The index of the card in the player's hand to flip.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void flipCard(ClientInterface client, int index) throws Exception {
        String username = client.getUsername();
        Lobby lobby = this.lobbyList.getPlayersLobby(username);
        lobby.getGameContext().getGame().getPlayer(username).getPlayerHand().get(index).flipSide();
    }

    /**
     * Places a card from the player's hand onto the board in the lobby's game context.
     *
     * @param client          The ClientInterface object representing the client placing the card.
     * @param handCardIndex   The index of the card in the player's hand to place.
     * @param cardToOverlap   The coordinates specifying where to place the card on the board.
     * @param cornerIndex     The corner index of the card.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void putCard(ClientInterface client, int handCardIndex, Coordinates cardToOverlap, int cornerIndex) throws Exception {
        String username = client.getUsername();
        this.lobbyList.getPlayersLobby(username).getGameContext().putCard(username, handCardIndex, cardToOverlap, cornerIndex);
    }

    /**
     * Allows the player to draw a gold card in the lobby's game context.
     *
     * @param client The ClientInterface object representing the client drawing the gold card.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void drawGoldCard(ClientInterface client) throws Exception {
        String username = client.getUsername();
        this.lobbyList.getPlayersLobby(username).getGameContext().drawGoldCard(username);
    }

    /**
     * Allows the player to draw a resource card in the lobby's game context.
     *
     * @param client The ClientInterface object representing the client drawing the resource card.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void drawResourceCard(ClientInterface client) throws Exception {
        String username = client.getUsername();
        this.lobbyList.getPlayersLobby(username).getGameContext().drawResourceCard(username);
    }

    /**
     * Draws the face-up card at the specified index for the player in the lobby's game context.
     *
     * @param client The ClientInterface object representing the client drawing the face-up card.
     * @param index  The index of the face-up card to draw.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void faceUpCard(ClientInterface client, int index) throws Exception {
        String username = client.getUsername();
        this.lobbyList.getPlayersLobby(username).getGameContext().drawFaceUpCard(username, index);
    }

    /**
     * Adds a message from one player to another player in the lobby's game context.
     *
     * @param client   The ClientInterface object representing the client sending the message.
     * @param receiver The username of the message receiver.
     * @param message  The message content to be sent.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void addMessage(ClientInterface client, String receiver, String message) throws Exception {
        String username = client.getUsername();
        Lobby lobby = this.lobbyList.getPlayersLobby(username);
        lobby.getGameContext().addMessage(username, receiver, message);
    }

    /**
     * Sets the starter card for a player in the lobby's game context.
     *
     * @param client The ClientInterface object representing the client setting the starter card.
     * @param card   The StarterCard object representing the card to be set.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public void setStarterCard(ClientInterface client, StarterCard card) throws Exception {
        String username = client.getUsername();
        Lobby lobby = this.lobbyList.getPlayersLobby(username);
        lobby.getGameContext().placeStarterCard(username, card);
    }

    /**
     * Retrieves the list of active lobby names.
     *
     * @return An ArrayList of Strings representing the names of active lobbies.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public ArrayList<String> getLobbyList() throws RemoteException {
        return this.lobbyList.getLobbiesNames();
    }

    /**
     * Retrieves the list of clients in a specific lobby hosted by the given host username.
     *
     * @param lobbyHost The username of the client hosting the lobby.
     * @return An ArrayList of Strings representing the usernames of clients in the specified lobby.
     * @throws Exception If the lobby does not exist or if there are errors accessing lobby information.
     */
    @Override
    public ArrayList<String> getLobbyClients(String lobbyHost) throws Exception {
        ArrayList<String> clients = new ArrayList<>();
        Lobby lobby = this.lobbyList.getLobbyByHost(lobbyHost);
        for (ClientHandler client : lobby.getPlayers()) {
            clients.add(client.getUsername());
        }
        return clients;
    }
}
