package it.polimi.ingsw.is24am14.client;

import it.polimi.ingsw.is24am14.client.GUI.MenuConnectionController;
import it.polimi.ingsw.is24am14.server.network.ClientInterface;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the launcher for the GUI.
 * It starts the GUI and shows the connection screen.
 */
public class GUIViewLauncher extends Application {

    //this class must start the GUI
    //stage is the window
    //scene is the content of the window

    private Stage primaryStage;
    private ClientInterface client; //poi deve diventare una ClientInterface

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showConnectionScreen();
    }

    /** this method is called for starting the GUI
    *
     */
    public static void LaunchGUI() {
        launch();
    }

    /**
     * This method shows the connection screen.
     */
    public void showConnectionScreen() {
        MenuConnectionController connectionController = new MenuConnectionController(this);
        connectionController.showScene();
    }

    /**
     * This method return the main window of the GUI.
     */
    public Stage getStage (){
        return primaryStage;
    }

    /**
     * This method gets the client.
     */
    public ClientInterface getClient(){
        return client;
    } //idem come sopra

    /**
     * This method sets the client.
     */
    public void setClient(ClientInterface client){
        this.client = client;
    } //idem come sopra

}
