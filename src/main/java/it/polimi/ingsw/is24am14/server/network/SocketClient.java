package it.polimi.ingsw.is24am14.server.network;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.utils.GSONAdapters.InitGSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Represents a client that communicates with a server using sockets.
 * Implements ClientInterface for handling communication protocols.
 */
public class SocketClient implements ClientInterface {
    private final PrintWriter socketOut;
    private final BufferedReader socketIn;
    private final Gson gson;
    private String username;
    private GameContext context;
    private final ReentrantReadWriteLock lock;

    /**
     * Constructs a SocketClient object and establishes connection with the server.
     * @throws Exception if there is an error initializing socket connection
     */
    public SocketClient() throws Exception {
        String hostName = NetworkSettings.serverAddress;
        int portNumber = NetworkSettings.socketPort;
        Socket socket = new Socket(hostName, portNumber);
        this.socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socketOut = new PrintWriter(socket.getOutputStream(), true);
        this.gson = InitGSON.init();
        this.context = null;
        this.lock = new ReentrantReadWriteLock();
    }

    /**
     * Sends a SocketResponse message to the server.
     * @param message the SocketResponse object to send
     * @throws Exception if there is an error sending the message
     */
    public synchronized void send(SocketResponse message) throws Exception {
        lock.writeLock().lock();
        this.socketOut.println(this.gson.toJson(message));
    }

    /**
     * Receives a SocketResponse message from the server.
     * @return the received SocketResponse object
     * @throws Exception if there is an error receiving the message
     */
    public synchronized SocketResponse receive() throws Exception {
        String response = this.socketIn.readLine();
        lock.writeLock().unlock();
        return this.gson.fromJson(response, SocketResponse.class);
    }

    /**
     * Validates the received response from the server.
     * @throws Exception if the received response indicates an error
     */
    private void validate() throws Exception {
        SocketResponse response = this.receive();
        if (response.code != 200) {
            System.out.println("Error: " + response.message);
            throw new Exception(response.message);
        }
    }

    // Implementing methods from ClientInterface...

    /**
     * Gets the username associated with this client.
     * @return the username of the client
     * @throws Exception if there is an error retrieving the username
     */
    @Override
    public String getUsername() throws Exception {
        return username;
    }

    /**
     * Connects the client to the server with a specified username.
     * @param username the username to connect with
     * @throws Exception if there is an error connecting to the server
     */
    @Override
    public void connect(String username) throws Exception {
        SocketResponse message = new SocketResponse(100, "connectionRequest");
        message.strings.add(username);
        this.send(message);

        validate();
        this.username = username;
    }

    /**
     * Sends a request to create a lobby with the specified number of players to the server.
     * @param numPlayers the number of players for the lobby
     * @throws Exception if there is an error sending the request or handling the response
     */
    @Override
    public void createLobby(int numPlayers) throws Exception {
        SocketResponse message = new SocketResponse(100, "createLobby");
        message.strings.add(Integer.toString(numPlayers));
        this.send(message);

        validate();
    }

    /**
     * Sends a request to join a lobby hosted by the specified host to the server.
     * @param host the host of the lobby to join
     * @throws Exception if there is an error sending the request or handling the response
     */
    @Override
    public void joinLobby(String host) throws Exception {
        SocketResponse message = new SocketResponse(100, "joinLobby");
        message.strings.add(host);
        this.send(message);

        validate();
    }

    /**
     * Sends a request to start the game to the server.
     * @throws Exception if there is an error sending the request or handling the response
     */
    @Override
    public void startGame() throws Exception {
        SocketResponse message = new SocketResponse(100, "startGame");
        this.send(message);

        validate();
    }

    /**
     * Sends a request to pick a token color to the server.
     * @param color the TokenColour to pick
     * @throws Exception if there is an error sending the request or handling the response
     */
    @Override
    public void pickColor(TokenColour color) throws Exception {
        SocketResponse message = new SocketResponse(100, "pickColor");
        message.strings.add(color.toString());
        this.send(message);
        validate();
    }

    /**
     * Sends a request to update the game context from the server and updates the local context.
     * @throws Exception if there is an error sending the request, receiving the response, or handling the game context update
     */
    @Override
    public void updateGameContext() throws Exception {
        SocketResponse message = new SocketResponse(100, "updateGameContext");
        this.send(message);

        SocketResponse response = this.receive();

        if (response.code != 200) {
            System.out.println("Update Game Context error");
            throw new RuntimeException(response.message);
        }

        if (response.strings.isEmpty()) throw new Exception("Received no game context in updateGameContext.\n" + response.message);
        this.context = gson.fromJson(response.strings.getFirst(), GameContext.class);
    }

    /**
     * Sends a request to pick an objective card to the server.
     * @param objectiveCard the ObjectiveCard to pick
     * @throws Exception if there is an error sending the request or handling the response
     */
    @Override
    public void pickObjectiveCard(ObjectiveCard objectiveCard) throws Exception {
        SocketResponse message = new SocketResponse(100, "pickObjectiveCard");
        message.strings.add(gson.toJson(objectiveCard));
        send(message);

        validate();
    }

    /**
     * Sends a request to flip a card at the specified index to the server.
     * @param index the index of the card to flip
     * @throws Exception if there is an error sending the request or handling the response
     */
    @Override
    public void flipCard(int index) throws Exception {
        SocketResponse message = new SocketResponse(100, "flipCard");
        message.strings.add(String.valueOf(index));
        send(message);

        validate();
    }

    /**
     * Sends a request to draw a gold card from the server.
     * @throws Exception if there is an error sending the request or handling the response
     */
    @Override
    public void drawGoldCard() throws Exception {
        SocketResponse message = new SocketResponse(100, "drawGoldCard");
        this.send(message);

        validate();
    }

    /**
     * Sends a request to draw a resource card from the server.
     * @throws Exception if there is an error sending the request or handling the response
     */
    @Override
    public void drawResourceCard() throws Exception {
        SocketResponse message = new SocketResponse(100, "drawResourceCard");
        this.send(message);

        validate();
    }

    /**
     * Sends a request to draw a face-up card at the specified index from the server.
     * @param index the index of the face-up card to draw
     * @throws Exception if there is an error sending the request or handling the response
     */
    @Override
    public void drawFaceUpCard(int index) throws Exception {
        SocketResponse message = new SocketResponse(100, "drawFaceUpCard");
        message.strings.add(String.valueOf(index));
        this.send(message);

        validate();
    }

    /**
     * Sends a request to place a card from hand at the specified index to the server.
     * @param handCardIndex the index of the card in hand to be placed
     * @param coordinates the coordinates specifying where to place the card on the board
     * @param cornerIndex the index of the corner on the board to place the card
     * @throws Exception if there is an error sending the request or handling the response
     */
    @Override
    public void putCard(int handCardIndex, Coordinates coordinates, int cornerIndex) throws Exception {
        SocketResponse message = new SocketResponse(100, "putCard");
        message.strings.add(String.valueOf(handCardIndex));
        message.strings.add(gson.toJson(coordinates));
        message.strings.add(String.valueOf(cornerIndex));

        send(message);

        validate();
    }

    /**
     * Sends a text message to a specified receiver on the server.
     * @param receiver the username of the message receiver
     * @param message the text message to send
     * @throws Exception if there is an error sending the message or handling the response
     */
    @Override
    public void sendMessage(String receiver, String message) throws Exception {
        SocketResponse textMessage = new SocketResponse(200, "textMessage");
        textMessage.strings.add(receiver);
        textMessage.strings.add(message);
        this.send(textMessage);

        validate();
    }

    /**
     * Sends a request to set a starter card for the player to the server.
     * @param starterCard the StarterCard object to set as the starter card
     * @throws Exception if there is an error sending the request or handling the response
     */
    @Override
    public void setStarterCard(StarterCard starterCard) throws Exception {
        SocketResponse message = new SocketResponse(100, "setStarterCard");
        message.strings.add(gson.toJson(starterCard));
        send(message);

        validate();
    }

    /**
     * Retrieves the current game context from the server.
     * @return the GameContext object representing the current game state
     * @throws Exception if there is an error retrieving the game context
     */
    @Override
    public GameContext getGameContext() throws Exception {
        return this.context;
    }

    /**
     * Retrieves the list of lobbies available on the server.
     * @return an ArrayList containing the names of available lobbies
     * @throws Exception if there is an error retrieving the lobby list
     */
    @Override
    public ArrayList<String> getLobbyList() throws Exception {
        send(new SocketResponse(100, "getLobbyList"));

        return gson.fromJson(receive().strings.getFirst(), ArrayList.class);
    }

    /**
     * Retrieves the list of clients in a specified lobby.
     * @param lobbyHost the host of the lobby to retrieve clients from
     * @return an ArrayList containing the usernames of clients in the lobby
     * @throws Exception if there is an error retrieving the lobby clients
     */
    @Override
    public ArrayList<String> getLobbyClients(String lobbyHost) throws Exception {
        SocketResponse message = new SocketResponse(100, "getLobbyClients");
        message.strings.add(lobbyHost);

        send(message);

        SocketResponse response = receive();
        if (response.code != 200) {
            throw new Exception(response.strings.getFirst());
        }
        return gson.fromJson(response.strings.getFirst(), ArrayList.class);
    }
}
