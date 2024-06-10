package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CreateGameController {
    private GUIView context;
    private Scene scene;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField numPlayers;

    public void initialize() {
        // mi assicuro che il campo numPlayers accetti solo numeri
        numPlayers.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numPlayers.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public CreateGameController(GUIView context){
        this.context = context;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("JoinLobby.fxml"));
        loader.setController(this);
        try {
            Parent root = loader.load();
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void handleConfirmButtonAction(ActionEvent event) {
        String inputText = numPlayers.getText();
        try {
            int numPlayersInt = Integer.parseInt(inputText);
            try {
                context.getClient().createLobby(numPlayersInt);
                goToLobby();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void goToLobby() {
        int POA = 1;
        LobbyController lobbyController = new LobbyController(context, POA);
        lobbyController.showScene();
    }
}
