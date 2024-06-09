package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import javax.naming.NameAlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RMIClient extends UnicastRemoteObject implements ClientInterface {
    private String username;
    private final RMIServerInterface server;
    private GameContext context;

    protected RMIClient() throws Exception {
        Registry registry;
        registry = LocateRegistry.getRegistry("127.0.0.1", 12345);
        this.server = (RMIServerInterface) registry.lookup("RMIServer");
        this.context = null;
    }

    @Override
    public String getUsername() throws Exception {
        return username;
    }

    @Override
    public void connect(String username) throws Exception {
        try {
            this.server.acceptConnection(this, username);
            this.username = username;
        } catch (NameAlreadyBoundException e) {
            System.out.println("Name already taken");
        }
    }

    @Override
    public void createLobby(int numPlayers) throws Exception {
        try {
            this.server.createNewLobby(this, numPlayers);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void joinLobby(String host) throws Exception {
        try {
            this.server.joinLobby(this, host);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void startGame() throws Exception {
        this.server.startGame(this);
    }

    @Override
    public void pickColor(TokenColour color) throws Exception {
        this.server.setColor(this, color);
    }

    @Override
    public void updateGameContext() throws Exception {
        this.context = this.server.getGameContext(this);
    }

    @Override
    public void pickObjectiveCard(ObjectiveCard objectiveCard) throws Exception {
        this.server.setObjectiveCard(this, objectiveCard);
    }

    @Override
    public void flipCard(int index) throws Exception {
        this.server.flipCard(this, index);
    }

    @Override
    public void drawGoldCard() throws Exception {
        this.server.drawGoldCard(this);
    }

    @Override
    public void drawResourceCard() throws Exception {
        this.server.drawResourceCard(this);
    }

    @Override
    public void drawFaceUpCard(int index) throws Exception {
        this.server.faceUpCard(this, index);
    }

    @Override
    public void putCard(int handCardIndex, Coordinates coordinates, int cornerIndex) throws Exception {
        this.server.putCard(this, handCardIndex, coordinates, cornerIndex);
    }

    @Override
    public void sendMessage(String receiver, String message) throws Exception {
        this.server.addMessage(this, receiver, message);
    }

    GameContext getGameContext() {
        return this.context;
    }
}
