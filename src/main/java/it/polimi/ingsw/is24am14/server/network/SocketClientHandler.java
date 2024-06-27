package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.LobbyList;

import java.net.Socket;

public class SocketClientHandler implements Runnable {
    private final SocketMediator mediator;

    /**
     * Constructs a SocketClientHandler instance.
     * Initializes a SocketMediator instance for handling client-server communication.
     * @param socket the client socket
     * @param lobbyList the list of active lobbies
     */
    public SocketClientHandler(Socket socket, LobbyList lobbyList) {
        this.mediator = new SocketMediator(socket, lobbyList);
    }

    /**
     * Retrieves the SocketMediator instance associated with this handler.
     * @return the SocketMediator instance
     */
    public SocketMediator getMediator() {
        return mediator;
    }

    /**
     * Implements the run method of Runnable interface.
     * Starts the management of client-server communication using the mediator.
     * Catches any exceptions that occur during communication and prints the stack trace.
     * Throws a RuntimeException if an exception occurs to ensure the thread terminates.
     */
    public void run() {
        try {
            mediator.manage();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("Mediator ended");
    }
}