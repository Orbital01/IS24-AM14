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
    public static void setRmiPort(int Port) {
        NetworkSettings.RMIPort = Port;
    }

    public static void setSocketPort(int Port) {
        NetworkSettings.socketPort = Port;
    }

    /**
     * Prints the current network settings.
     */
    public static void printSettings() {
        System.out.println("Server address: " + serverAddress);
        System.out.println("RMI port: " + RMIPort);
        System.out.println("Socket port: " + socketPort);
    }

    /**
     * Prints the current server address.
     */
    public static void getServerAddress() {
        System.out.println(serverAddress);
    }

    /**
     * Prints the current RMI port.
     */
    public static void getRMIPort() {
        System.out.println(RMIPort);
    }

    /**
     * Prints the current socket port.
     */
    public static void getSocketPort() {
        System.out.println(socketPort);
    }

}
