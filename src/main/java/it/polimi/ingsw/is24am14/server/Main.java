package it.polimi.ingsw.is24am14.server;

import it.polimi.ingsw.is24am14.server.network.NetworkSettings;
import it.polimi.ingsw.is24am14.client.GUIViewLauncher;

public class Main {

    public static void main(String[] args) {
        if (args.length > 1) {
            if ("client".equals(args[0])) {
                //chiedo IP e porta del server
                if(args.length>=3){
                    NetworkSettings.setServerAddress(args[1]);
                    NetworkSettings.setPort(Integer.parseInt(args[2]));
                    if(args.length>3){
                        if(args[3].equals("GUI")){
                            GUIViewLauncher.LaunchGUI();
                        }else if (args[3].equals("CLI")) {

                            try {
                                //TUIViewLauncher.LaunchCLI();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }else {
                        System.out.println("parametri insufficienti");
                        System.out.println("avvio il client con interfaccia testuale");
                        //CLIView.LaunchCLI(); //TODO: implementare CLI LAUNCHER
                    }

                }else {
                    System.out.println("parametri insufficienti");
                    System.exit(0);
                }

            } else if ("server".equals(args[0])) {
                //chiedo la porta del server
                if(args.length>=2){
                    NetworkSettings.setPort(Integer.parseInt(args[1]));
                }
                //ServerLauncher.start();
            } else {
                System.out.println("Argomento non riconosciuto. Usa 'client' o 'server'.");
            }
        } else {
            System.out.println("Nessun argomento fornito. Usa 'client' o 'server'.");
        }
    }

}