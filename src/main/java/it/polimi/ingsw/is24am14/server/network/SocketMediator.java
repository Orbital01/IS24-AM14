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

public class SocketMediator implements ClientHandler {
    public PrintWriter socketOut;
    public BufferedReader socketIn;
    private String username;
    private final LobbyList lobbyList;
    private final Gson gson;

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

    public void send(SocketResponse message) throws Exception {
        this.socketOut.println(this.gson.toJson(message));
    }

    public SocketResponse receive() throws Exception {
        return this.gson.fromJson(this.socketIn.readLine(), SocketResponse.class);
    }

    @Override
    public String getUsername() throws Exception {
        return username;
    }

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

    public void createNewLobby(int numPlayers) throws Exception {
        if (this.lobbyList.getPlayersLobby(username) != null) {
            send(new SocketResponse(409, "you are already in a lobby"));
        }

        this.lobbyList.createLobby(username, numPlayers);
        this.lobbyList.joinLobby(username, username);

        send(new SocketResponse(200, "createdNewLobby"));
    }

    public void joinLobby(String lobbyHost) throws Exception {
        if (this.lobbyList.getPlayersLobby(username) != null) {
            send(new SocketResponse(409, "you are already in a lobby"));
        }

        this.lobbyList.joinLobby(username, lobbyHost);

        send(new SocketResponse(200, "joinedLobby"));
    }

    public void sendGameContext() throws Exception {
        SocketResponse message = new SocketResponse();
        try {
            GameContext context = lobbyList.getPlayersLobby(username).getGameContext();
            message.code = 200;
            message.message = "gameContext";
            message.strings.add(gson.toJson(context));
            if (message.strings.isEmpty()) throw new RuntimeException("No game context");
        } catch (Exception e) {
            message.code = 409;
            message.message = "errorSendingGameContext";
        }
        send(message);
    }

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

    public void setColor(String color) throws Exception {
        TokenColour colorValue = TokenColour.valueOf(color);
        this.lobbyList.getPlayersLobby(username).setColor(username, colorValue);
        send(new SocketResponse(200, "colorSet"));
    }

    public void setStarterCard(StarterCard card) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(username);
        lobby.getGameContext().placeStarterCard(username, card);
        send(new SocketResponse(200, "starterCardSet"));
    }

    public void setObjectiveCard(ObjectiveCard card) throws Exception {
        Lobby lobby = this.lobbyList.getPlayersLobby(username);
        Player player = lobby.getGameContext().getGame().getPlayer(username);

        lobby.getGameContext().setObjectiveCard(player, card);
        send(new SocketResponse(200, "objectiveCardSet"));
    }

    public void flipCard(int index) throws Exception {
        try {
            Lobby lobby = this.lobbyList.getPlayersLobby(username);
            lobby.getGameContext().getGame().getPlayer(username).getPlayerHand().get(index).flipSide();

            send(new SocketResponse(200, "cardFlipped"));
        } catch (Exception e) {
            send(new SocketResponse(400, "flipCardException", e.getMessage()));
        }
    }

    public void putCard(int handCardIndex, Coordinates cardToOverlap, int cornerIndex) throws Exception {
        try {
            this.lobbyList.getPlayersLobby(username).getGameContext().putCard(username, handCardIndex, cardToOverlap, cornerIndex);

            send(new SocketResponse(200, "cardPut"));
        } catch (Exception e) {
            send(new SocketResponse(400, "putCardException", e.getMessage()));
        }
    }

    public void drawGoldCard() throws Exception {
        try {
            this.lobbyList.getPlayersLobby(username).getGameContext().drawGoldCard(username);

            send(new SocketResponse(200, "goldCardDraw"));
        } catch (Exception e) {
            send(new SocketResponse(400, "drawGoldCardException", e.getMessage()));
        }
    }

    public void drawResourceCard() throws Exception {
        try {
            this.lobbyList.getPlayersLobby(username).getGameContext().drawResourceCard(username);

            send(new SocketResponse(200, "resourceCardDraw"));
        } catch (Exception e) {
            send(new SocketResponse(400, "drawResourceCardException", e.getMessage()));
        }
    }

    public void faceUpCard(int index) throws Exception {
        try {
            this.lobbyList.getPlayersLobby(username).getGameContext().drawFaceUpCard(username, index);

            send(new SocketResponse(200, "faceUpCardDraw"));
        } catch (Exception e) {
            send(new SocketResponse(400, "drawFaceUpCardException", e.getMessage()));
        }
    }

    public void addMessage(String receiver, String message) throws Exception {
        try {
            Lobby lobby = this.lobbyList.getPlayersLobby(username);
            lobby.getGameContext().addMessage(username, receiver, message);

            send(new SocketResponse(200, "addedMessage"));
        } catch (Exception e) {
            send(new SocketResponse(400, "addMessageException", e.getMessage()));
        }
    }

    public void getLobbyList() throws Exception {
        try {
            SocketResponse message = new SocketResponse(200, "getLobbyList");
            message.strings.add(gson.toJson(this.lobbyList.getLobbiesNames()));

            send(message);
        } catch (Exception e) {
            send(new SocketResponse(400, "getLobbyListException", e.getMessage()));
        }

    }

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
