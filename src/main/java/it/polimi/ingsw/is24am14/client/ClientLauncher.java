package it.polimi.ingsw.is24am14.client;

import java.rmi.RemoteException;

public class ClientLauncher {
    public static void main(String[] args) {
        ClientConnection connection;
        try {
            connection = new RMIClient();
            connection.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
