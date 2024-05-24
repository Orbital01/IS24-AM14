package it.polimi.ingsw.is24am14.server.network;

public class ClientLauncher {
    public static void main(String[] args) {
        RMIClientInterface client = null;
        try {
            client = new RMIClient();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
