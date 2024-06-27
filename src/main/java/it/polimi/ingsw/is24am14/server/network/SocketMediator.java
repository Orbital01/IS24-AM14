package it.polimi.ingsw.is24am14.server.network;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.controller.Lobby;
import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.utils.GSONAdapters.InitGSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Represents a mediator for handling client-server communication over sockets.
 * Implements ClientHandler interface to manage client requests and responses.
 */
public class SocketMediator implements ClientHandler {
    /** PrintWriter for sending responses to the client. */
    public PrintWriter socketOut;

    /** BufferedReader for receiving requests from the client. */
    public BufferedReader socketIn;

    /** The username associated with the client. */
    private String username;

    /** The LobbyList containing active lobbies. */
    private final LobbyList lobbyList;

    /** Gson instance for JSON serialization and deserialization. */
    private final Gson gson;

    /**
     * Constructs a SocketMediator object with the specified socket and lobby list.
     * Initializes the Gson instance and sets up input/output streams for socket communication.
     * @param socket the client socket
     * @param lobbyList the LobbyList containing active lobbies
     */
    public SocketMediator(Socket socket, LobbyList lobbyList) {
        this.lobbyList = lobbyList;

        gson = InitGSON.init();

        try {
            this.socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.socketOut = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends a SocketResponse object to the client.
     * @param message the SocketResponse object to send
     * @throws Exception if there is an error sending the response
     */
    public void send(SocketResponse message) throws Exception {
        this.socketOut.println(this.gson.toJson(message));
    }

    /**
     * Receives a SocketResponse object from the client.
     * @return the received SocketResponse object
     * @throws Exception if there is an error receiving the response
     */
    public SocketResponse receive() throws Exception {
        return this.gson.fromJson(this.socketIn.readLine(), SocketResponse.class);
    }

    /**
     * Retrieves the username associated with this client.
     * @return the username of the client
     * @throws Exception if there is an error retrieving the username
     */
    @Override
    public String getUsername() throws Exception {
        return username;
    }

    /**
     * Manages client requests indefinitely.
     * Interprets incoming messages and invokes corresponding methods to handle them.
     * @throws Exception if there is an error managing client requests
     */
    public void manage() throws Exception {
        this.acceptConnection();
        while (true) {
            SocketResponse message = new Gson().fromJson(this.socketIn.readLine(), SocketResponse.class);

            switch (message.message) {
                case "createLobby":
                    this.createNewLobby(Integer.parseInt(message.strings.getFirst()));
                    break;
                case "joinLobby":
                    this.joinLobby(message.strings.getFirst());
                    break;
                case "updateGameContext":
                    this.sendGameContext();
                    break;
                case "startGame":
                    this.startGame();
                    break;
                case "pickColor":
                    this.setColor(message.strings.getFirst());
                    break;
                case "setStarterCard":
                    this.setStarterCard(gson.fromJson(message.strings.getFirst(), StarterCard.class));
                    break;
                case "pickObjectiveCard":
                    this.setObjectiveCard(gson.fromJson(message.strings.getFirst(), ObjectiveCard.class));
                    break;
                case "flipCard":
                    this.flipCard(Integer.parseInt(message.strings.getFirst()));
                    break;
                case "putCard":
                    this.putCard(Integer.parseInt(message.strings.getFirst()), gson.fromJson(message.strings.get(1), Coordinates.class), Integer.parseInt(message.strings.get(2)));
                    break;
                case "drawGoldCard":
                    this.drawGoldCard();
                    break;
                case "drawResourceCard":
                    this.drawResourceCard();
                    break;
                case "drawFaceUpCard":
                    this.faceUpCard(Integer.parseInt(message.strings.getFirst()));
                    break;
                case "textMessage":
                    this.addMessage(message.strings.getFirst(), message.strings.get(1));
                    break;
                case "getLobbyList":
                    this.getLobbyList();
                    break;
                case "getLobbyClients":
                    this.getLobbyClients(message.strings.getFirst());
                    break;
                default:
                    System.out.println("Wrong message");
            }
        }
    }

    /**
     * Accepts a connection request from the client.
     * Verifies the username's availability and adds the client handler to the lobby list.
     * Sends a response indicating the connection status.
     * @throws Exception if there is an error accepting the connection or sending the response
     */
    public void acceptConnection() throws Exception {
        String user = receive().strings.getFirst();
        if (lobbyList.getClientHandler(user) != null) {
            send(new SocketResponse(409, "username already existing"));
            return;
        }

        this.username = user;
        this.lobbyList.addClientHandler(this);

        send(new SocketResponse(200, "connectionAccepted"));
        System.out.println("Socket Client connected");
    }

    /**
     * Creates a new lobby with the specified number of players.
     * Checks if the client is already in a lobby and sends an appropriate response.
     * @param numPlayers the number of players for the new lobby
     * @throws Exception if there is an error creating the lobby or sending the response
     */
    public void createNewLobby(int numPlayers) throws Exception {
        if (this.lobbyList.getPlayersLobby(username) != null) {
            send(new SocketResponse(409, "you are already in a lobby"));
        }

        this.lobbyList.createLobby(username, numPlayers);
        this.lobbyList.joinLobby(username, username);

        send(new SocketResponse(200, "createdNewLobby"));
    }

    /**
     * Joins an existing lobby hosted by the specified username.
     * Checks if the client is already in a lobby and sends an appropriate response.
     * @param lobbyHost the username of the lobby host to join
     * @throws Exception if there is an error joining the lobby or sending the response
     */
    public void joinLobby(String lobbyHost) throws Exception {
        if (this.lobbyList.getPlayersLobby(username) != null) {
            send(new SocketResponse(409, "you are already in a lobby"));
        }

        this.lobbyList.joinLobby(username, lobbyHost);

        send(new SocketResponse(200, "joinedLobby"));
    }

    /**
     * Sends the current game context to the client.
     * Retrieves the game context associated with the client's lobby and sends it as JSON.
     * Sends an error response if the game context retrieval fails.
     * @throws Exception if there is an error sending the game context
     */
    public void sendGameContext() throws Exception {
        SocketResponse message = new SocketResponse();
        try {
            GameContext context = lobbyList.getPlayersLobby(username).getGameContext();
            message.code = 200;
            message.message = "gameContext";
            message.strings.add(gson.toJson(context));
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
            message.code = 409;
            message.message = "errorSendingGameContext";
        }
        send(message);
    }

    /**
     * Starts the game for the client's lobby.
     * Checks if the lobby has sufficient players to start the game.
     * Sends a success response upon successfully starting the game.
     * Sends an error response if there are not enough players in the lobby.
     * @throws Exception if there is an error starting the game or sending the response
     */
    public void startGame() throws Exception {
        Lobby lobby = lobbyList.getLobbyByHost(username);
        if (lobby != null && lobby.getPlayers().size() == lobby.getMaxPlayers()) {
            System.out.println("Starting game");
            lobby.startGame();
            send(new SocketResponse(200, "starting game"));
            return;
        }
        send(new SocketResponse(412, "missing players"));
    }

    /**
     * Sets the color for the client's player in the lobby.
     * Validates the color value and assigns it to the client's player.
     * Sends a success response upon successfully setting the color.
     * Sends an error response if the color setting fails due to turn order or other reasons.
     * @param color the color to set for the client's player
     * @throws Exception if there is an error setting the color or sending the response
     */
    public void setColor(String color) throws Exception {
        try {
            TokenColour colorValue = TokenColour.valueOf(color);
            this.lobbyList.getPlayersLobby(username).setColor(username, colorValue);
            send(new SocketResponse(200, "colorSet"));
        } catch (NotYourColorTurnException notYourColorTurnException) {
            send(new SocketResponse(409, "notYourColorTurnException"));
        } catch (Exception e) {
            send(new SocketResponse(409, "errorSettingColor"));
        }
    }

    /**
     * Places the starter card for the client's player in the game context.
     * Sends a success response upon successfully setting the starter card.
     * Sends an error response if setting the starter card fails.
     * @param card the starter card to set
     * @throws Exception if there is an error setting the starter card or sending the response
     */
    public void setStarterCard(StarterCard card) throws Exception {
        try {
            Lobby lobby = this.lobbyList.getPlayersLobby(username);
            lobby.getGameContext().placeStarterCard(username, card);
            send(new SocketResponse(200, "starterCardSet"));
        } catch (Exception e) {
            send(new SocketResponse(409, "errorSettingStarterCard"));
        }
    }

    /**
     * Sets the objective card for the client's player in the game context.
     * Sends a success response upon successfully setting the objective card.
     * Sends an error response if setting the objective card fails.
     * @param card the objective card to set
     * @throws Exception if there is an error setting the objective card or sending the response
     */
    public void setObjectiveCard(ObjectiveCard card) throws Exception {
        try {
            Lobby lobby = this.lobbyList.getPlayersLobby(username);
            Player player = lobby.getGameContext().getGame().getPlayer(username);

            lobby.getGameContext().setObjectiveCard(player, card);
            send(new SocketResponse(200, "objectiveCardSet"));
        } catch (Exception e) {
            send(new SocketResponse(409, "errorSettingObjectiveCard"));
        }
    }

    /**
     * Flips the side of the card at the given index in the client's player hand.
     * Sends a success response upon successfully flipping the card.
     * Sends an error response if flipping the card fails.
     * @param index the index of the card to flip
     * @throws Exception if there is an error flipping the card or sending the response
     */
    public void flipCard(int index) throws Exception {
        try {
            Lobby lobby = this.lobbyList.getPlayersLobby(username);
            lobby.getGameContext().getGame().getPlayer(username).getPlayerHand().get(index).flipSide();

            send(new SocketResponse(200, "cardFlipped"));
        } catch (Exception e) {
            send(new SocketResponse(400, "flipCardException", e.getMessage()));
        }
    }

    /**
     * Places the card from the client's hand onto the specified coordinates on the game board.
     * Sends a success response upon successfully placing the card.
     * Sends an error response if placing the card fails.
     * @param handCardIndex the index of the card in the client's hand
     * @param cardToOverlap the coordinates where the card will be placed
     * @param cornerIndex the index of the corner to place the card
     * @throws Exception if there is an error placing the card or sending the response
     */
    public void putCard(int handCardIndex, Coordinates cardToOverlap, int cornerIndex) throws Exception {
        try {
            this.lobbyList.getPlayersLobby(username).getGameContext().putCard(username, handCardIndex, cardToOverlap, cornerIndex);

            send(new SocketResponse(200, "cardPut"));
        } catch (Exception e) {
            send(new SocketResponse(400, "putCardException", e.getMessage()));
        }
    }

    /**
     * Draws a gold card for the client's player from the game context.
     * Sends a success response upon successfully drawing the gold card.
     * Sends an error response if drawing the gold card fails.
     * @throws Exception if there is an error drawing the gold card or sending the response
     */
    public void drawGoldCard() throws Exception {
        try {
            this.lobbyList.getPlayersLobby(username).getGameContext().drawGoldCard(username);

            send(new SocketResponse(200, "goldCardDraw"));
        } catch (Exception e) {
            send(new SocketResponse(400, "drawGoldCardException", e.getMessage()));
        }
    }

    /**
     * Draws a resource card for the client's player from the game context.
     * Sends a success response upon successfully drawing the resource card.
     * Sends an error response if drawing the resource card fails.
     * @throws Exception if there is an error drawing the resource card or sending the response
     */
    public void drawResourceCard() throws Exception {
        try {
            this.lobbyList.getPlayersLobby(username).getGameContext().drawResourceCard(username);

            send(new SocketResponse(200, "resourceCardDraw"));
        } catch (Exception e) {
            send(new SocketResponse(400, "drawResourceCardException", e.getMessage()));
        }
    }

    /**
     * Draws a face-up card for the client's player from the game context.
     * Sends a success response upon successfully drawing the face-up card.
     * Sends an error response if drawing the face-up card fails.
     * @param index the index of the face-up card to draw
     * @throws Exception if there is an error drawing the face-up card or sending the response
     */
    public void faceUpCard(int index) throws Exception {
        try {
            this.lobbyList.getPlayersLobby(username).getGameContext().drawFaceUpCard(username, index);

            send(new SocketResponse(200, "faceUpCardDraw"));
        } catch (Exception e) {
            send(new SocketResponse(400, "drawFaceUpCardException", e.getMessage()));
        }
    }

    /**
     * Adds a message from the client to another player in the game context.
     * Sends a success response upon successfully adding the message.
     * Sends an error response if adding the message fails.
     * @param receiver the username of the message receiver
     * @param message the message content
     * @throws Exception if there is an error adding the message or sending the response
     */
    public void addMessage(String receiver, String message) throws Exception {
        try {
            Lobby lobby = this.lobbyList.getPlayersLobby(username);
            lobby.getGameContext().addMessage(username, receiver, message);

            send(new SocketResponse(200, "addedMessage"));
        } catch (Exception e) {
            send(new SocketResponse(400, "addMessageException", e.getMessage()));
        }
    }

    /**
     * Sends the list of active lobbies to the client.
     * Retrieves the list of active lobby names and sends it as JSON.
     * Sends an error response if retrieving the lobby list fails.
     * @throws Exception if there is an error sending the lobby list or sending the response
     */
    public void getLobbyList() throws Exception {
        try {
            SocketResponse message = new SocketResponse(200, "getLobbyList");
            message.strings.add(gson.toJson(this.lobbyList.getLobbiesNames()));

            send(message);
        } catch (Exception e) {
            send(new SocketResponse(400, "getLobbyListException", e.getMessage()));
        }

    }

    /**
     * Sends the list of clients in a specific lobby to the client.
     * Retrieves the list of client usernames in the specified lobby and sends it as JSON.
     * Sends an error response if retrieving the lobby clients fails.
     * @param lobbyHost the username of the lobby host
     * @throws Exception if there is an error sending the lobby clients or sending the response
     */
    public void getLobbyClients(String lobbyHost) throws Exception {
        try {
            ArrayList<String> clients = new ArrayList<>();
            Lobby lobby = this.lobbyList.getLobbyByHost(lobbyHost);
            for (ClientHandler client : lobby.getPlayers()) {
                clients.add(client.getUsername());
            }

            send(new SocketResponse(200, "getLobbyClients", gson.toJson(clients)));
        } catch (Exception e) {
            send(new SocketResponse(400, "getLobbyClientsException", e.getMessage()));
        }
    }
}
