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

public class SocketClient implements ClientInterface {
    private final PrintWriter socketOut;
    private final BufferedReader socketIn;
    private final Gson gson;
    private String username;
    private GameContext context;
    private final ReentrantReadWriteLock lock;

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

    public synchronized void send(SocketResponse message) throws Exception {
        lock.writeLock().lock();
        this.socketOut.println(this.gson.toJson(message));
    }

    public synchronized SocketResponse receive() throws Exception {
        String response = this.socketIn.readLine();
        lock.writeLock().unlock();
        return this.gson.fromJson(response, SocketResponse.class);
    }

    private void validate() throws Exception {
        SocketResponse response = this.receive();
        if (response.code != 200) {
            System.out.println("Error: " + response.message);
            throw new Exception(response.message);
        }
    }

    @Override
    public String getUsername() throws Exception {
        return username;
    }

    @Override
    public void connect(String username) throws Exception {
        SocketResponse message = new SocketResponse(100, "connectionRequest");
        message.strings.add(username);
        this.send(message);

        validate();
        this.username = username;
    }

    @Override
    public void createLobby(int numPlayers) throws Exception {
        SocketResponse message = new SocketResponse(100, "createLobby");
        message.strings.add(Integer.toString(numPlayers));
        this.send(message);

        validate();
    }

    @Override
    public void joinLobby(String host) throws Exception {
        SocketResponse message = new SocketResponse(100, "joinLobby");
        message.strings.add(host);
        this.send(message);

        validate();
    }

    @Override
    public void startGame() throws Exception {
        SocketResponse message = new SocketResponse(100, "startGame");
        this.send(message);

        validate();
    }

    @Override
    public void pickColor(TokenColour color) throws Exception {
        SocketResponse message = new SocketResponse(100, "pickColor");
        message.strings.add(color.toString());
        this.send(message);
        validate();
    }

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

    @Override
    public void pickObjectiveCard(ObjectiveCard objectiveCard) throws Exception {
        SocketResponse message = new SocketResponse(100, "pickObjectiveCard");
        message.strings.add(gson.toJson(objectiveCard));
        send(message);

        validate();
    }

    @Override
    public void flipCard(int index) throws Exception {
        SocketResponse message = new SocketResponse(100, "flipCard");
        message.strings.add(String.valueOf(index));
        send(message);

        validate();
    }

    @Override
    public void drawGoldCard() throws Exception {
        SocketResponse message = new SocketResponse(100, "drawGoldCard");
        this.send(message);

        validate();
    }

    @Override
    public void drawResourceCard() throws Exception {
        SocketResponse message = new SocketResponse(100, "drawResourceCard");
        this.send(message);

        validate();
    }

    @Override
    public void drawFaceUpCard(int index) throws Exception {
        SocketResponse message = new SocketResponse(100, "drawFaceUpCard");
        message.strings.add(String.valueOf(index));
        this.send(message);

        validate();
    }

    @Override
    public void putCard(int handCardIndex, Coordinates coordinates, int cornerIndex) throws Exception {
        SocketResponse message = new SocketResponse(100, "putCard");
        message.strings.add(String.valueOf(handCardIndex));
        message.strings.add(gson.toJson(coordinates));
        message.strings.add(String.valueOf(cornerIndex));

        send(message);

        validate();
    }

    @Override
    public void sendMessage(String receiver, String message) throws Exception {
        SocketResponse textMessage = new SocketResponse(200, "textMessage");
        textMessage.strings.add(receiver);
        textMessage.strings.add(message);
        this.send(textMessage);

        validate();
    }

    @Override
    public void setStarterCard(StarterCard starterCard) throws Exception {
        SocketResponse message = new SocketResponse(100, "setStarterCard");
        message.strings.add(gson.toJson(starterCard));
        send(message);

        validate();
    }

    @Override
    public GameContext getGameContext() throws Exception {
        return this.context;
    }

    @Override
    public ArrayList<String> getLobbyList() throws Exception {
        send(new SocketResponse(100, "getLobbyList"));

        return gson.fromJson(receive().strings.getFirst(), ArrayList.class);
    }

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
