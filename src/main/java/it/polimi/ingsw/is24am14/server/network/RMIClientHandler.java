package it.polimi.ingsw.is24am14.server.network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMIClientHandler represents a handler for a client connected to the server via RMI.
 * It implements the ClientHandler interface to provide methods for server-side interactions with the client.
 */
public class RMIClientHandler extends UnicastRemoteObject implements ClientHandler {
    private final String username;
    private final ClientInterface client;

    /**
     * Constructs an RMIClientHandler with the specified client and username.
     *
     * @param client   The ClientInterface object representing the connected client.
     * @param username The username associated with the client.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    protected RMIClientHandler(ClientInterface client, String username) throws RemoteException {
        this.username = username;
        this.client = client;
    }

    /**
     * Retrieves the username associated with this client handler.
     *
     * @return The username of the client.
     * @throws RemoteException If an RMI-related communication error occurs.
     */
    @Override
    public String getUsername() throws Exception {
        return this.username;
    }
}
