package it.polimi.ingsw.is24am14.client;

import it.polimi.ingsw.is24am14.client.GUI.MenuConnectionController;
import it.polimi.ingsw.is24am14.server.network.ClientInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUIViewLauncher extends Application {

    //questa classe deve far partire la GUI
    //stage è la finestra
    //scene è il contenuto della finestra

    private Stage primaryStage;
    private ClientInterface client; //poi deve diventare una ClientInterface

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showConnectionScreen();
    }

    public static void LaunchGUI() { //era main non so se ora funziona, modificato per JAR
        launch();
    }

    public void showConnectionScreen() {
        MenuConnectionController connectionController = new MenuConnectionController(this);
        connectionController.showScene();
    }

    public Stage getStage (){
        return primaryStage;
    }

    public ClientInterface getClient(){
        return client;
    } //idem come sopra

    public void setClient(ClientInterface client){
        this.client = client;
    } //idem come sopra

}
