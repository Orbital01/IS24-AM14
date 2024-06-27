package it.polimi.ingsw.is24am14.server;

import it.polimi.ingsw.is24am14.client.TUIViewLauncher;
import it.polimi.ingsw.is24am14.server.network.NetworkSettings;
import it.polimi.ingsw.is24am14.client.GUIViewLauncher;
import it.polimi.ingsw.is24am14.server.network.ServerLauncher;

public class Main {

    private static final String SERVER_ADDRESS = "--address";
    private static final String SOCKET_PORT = "--socket-port";
    private static final String RMI_PORT = "--rmi-port";
    private static final String GUI_CLI = "--mode";

    private static final String IPV4_REGEX =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";


    /**
     * this method is the main method of the game
     * it takes the arguments from the command line and sets the server address and the ports for RMI and socket connections
     * based on the arguments provided it can launch the GUI, the CLI or the server
     * @param args
     */
    public static void main(String[] args)  {
        if (args.length!=0){
            for ( int i = 0; i < args.length; i++ ) {

                if (args[i].equals(SERVER_ADDRESS)){
                    if (args[i+1].matches(IPV4_REGEX)){
                        NetworkSettings.setServerAddress(args[i+1]);
                        NetworkSettings.getServerAddress();
                    }
                }
                if (args[i].equals(SOCKET_PORT)){
                    if (Integer.parseInt(args[i+1])>1024 && Integer.parseInt(args[i+1])<65535){
                        NetworkSettings.setSocketPort(Integer.parseInt(args[i+1]));
                        NetworkSettings.getSocketPort();
                    }
                }
                if (args[i].equals(RMI_PORT)){
                    if (Integer.parseInt(args[i+1])>1024 && Integer.parseInt(args[i+1])<65535){
                        NetworkSettings.setRmiPort(Integer.parseInt(args[i+1]));
                        NetworkSettings.getRMIPort();
                    }
                }
                if (args[i].equals(GUI_CLI)){
                    if (args[i+1].equals("0")){
                        GUIViewLauncher.LaunchGUI();
                    } else if (args[i+1].equals("1")){
                        try {
                            TUIViewLauncher.main(args);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }else if (args[i+1].equals("2")) {
                        ServerLauncher.main(args);
                    }
                }

            }
        }else {
            System.out.println("No arguments provided, using default settings");
            NetworkSettings.printSettings();
            try {
                TUIViewLauncher.main(args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}