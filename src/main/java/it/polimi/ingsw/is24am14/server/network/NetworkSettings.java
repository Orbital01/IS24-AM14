package it.polimi.ingsw.is24am14.server.network;

/**
 * This class contains the network settings for the server and the client.
 * It includes the server address and the ports for RMI and socket connections.
 */
public class NetworkSettings {

    public static String serverAddress = "127.0.0.1";
    public static int RMIPort = 12345;
    public static int socketPort = 12346;

    /**
     * Sets the server address.
     *
     * @param serverAddress The new server address.
     */
    public static void setServerAddress(String serverAddress) {
        NetworkSettings.serverAddress = serverAddress;
    }

    /**
     * Sets the ports for RMI and socket connections.
     *
     * @param Port The new port.
     */
    public static void setPort(int Port) {
        NetworkSettings.RMIPort = RMIPort;
        NetworkSettings.socketPort = socketPort;
    }


}
