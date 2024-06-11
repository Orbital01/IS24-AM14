package it.polimi.ingsw.is24am14.server.view;

import it.polimi.ingsw.is24am14.client.GUI.MenuConnectionController;
import it.polimi.ingsw.is24am14.server.network.ClientInterface;
import it.polimi.ingsw.is24am14.server.network.RMIClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIView extends Application {

    //questa classe deve far partire la GUI
    //stage è la finestra
    //scene è il contenuto della finestra

    private Stage primaryStage;
    private RMIClient client; //poi deve diventare una ClientInterface

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showConnectionScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showConnectionScreen() {
        MenuConnectionController connectionController = new MenuConnectionController(this);
        connectionController.showScene();
    }

    public Stage getStage (){
        return primaryStage;
    }

    public RMIClient getClient(){
        return client;
    } //idem come sopra

    public void setClient(RMIClient client){
        this.client = client;
    } //idem come sopra

}
