package it.polimi.ingsw.is24am14.server.network;

public class NetworkSettings {

    //TODO: change this to the actual server address
    public static String serverAddress = "127.0.0.1";
    public static int RMIPort = 12345;
    public static int socketPort = 12346;


    public static void setServerAddress(String serverAddress) {
        NetworkSettings.serverAddress = serverAddress;
    }

    public static void setPort(int Port) {
        NetworkSettings.RMIPort = RMIPort;
        NetworkSettings.socketPort = socketPort;
    }


}
