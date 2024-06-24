package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.server.network.ClientInterface;
import it.polimi.ingsw.is24am14.server.network.RMIClient;
import it.polimi.ingsw.is24am14.server.network.SocketClient;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuConnectionController {

    private GUIView context;
    private Scene scene;
    private ClientInterface client;
    private BorderPane layout = new BorderPane();

    public MenuConnectionController(GUIView context){
        this.context = context;
        scene = new Scene(layout, 1920, 1080);
        Guifactory.setAutomaticBackground(layout);

        VBox container = new VBox();
        container.setSpacing(20);
        container.setAlignment(javafx.geometry.Pos.CENTER);
        //aggiungo l'immagine del logo
        container.getChildren().add(Guifactory.displayLogo());
        //aggiungo i due bottoni per la connessione
        Button rmiButton = Guifactory.createButton("Connessione RMI", 200, 50);
        rmiButton.setOnAction(this::handleRmiButtonAction);

        Button tcpButton = Guifactory.createButton("Connessione TCP", 200, 50);
        tcpButton.setOnAction(this::handleTcpButtonAction);

        HBox buttonContainer = new HBox();
        buttonContainer.setSpacing(20);
        buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);
        buttonContainer.getChildren().addAll(rmiButton, tcpButton);

        container.getChildren().add(buttonContainer);

        layout.setCenter(container);
    }

    private void handleRmiButtonAction(ActionEvent event) {
        //avvio la connessione RMI
        System.out.println("Connessione RMI");
        try {

            client = new RMIClient(); //dovrò poi aggiungere l'indirizzo IP
            context.setClient(client);
            //passo alla scena di selezione del nickname
            GoToUsername();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di connessione");
            alert.setHeaderText(null);
            alert.setContentText("Errore nella connessione RMI");
            alert.showAndWait();
            System.exit(1);
        }
    }

    private void handleTcpButtonAction(ActionEvent event) {
        System.out.println("Connessione TCP");
        try {

            client = new SocketClient(); //dovrò poi aggiungere l'indirizzo IP
            context.setClient(client);
            //passo alla scena di selezione del nickname
            GoToUsername();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di connessione");
            alert.setHeaderText(null);
            alert.setContentText("Errore nella connessione TCP");
            alert.showAndWait();
            System.exit(1);
        }
    }

    public void GoToUsername(){
        //passo alla scena di selezione del nickname
        MenuNicknameController nicknameController = new MenuNicknameController(context);
        nicknameController.showScene();
    }

    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

}
