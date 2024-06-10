package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class JoinGameController {

    private GUIView context;
    private Scene scene;

    @FXML
    private ListView<String> lobbyList;

    public void initialize() {
        ArrayList<String> lobbies = context.getClient().lobbyList();
        ObservableList<String> observableList = FXCollections.observableList(lobbies);
        lobbyList.setItems(observableList);
    }

    public JoinGameController(GUIView context){
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

    private void joinLobby() {
        lobbyList.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) { //doppio click per entrare nella lobby
                String selectedLobby = lobbyList.getSelectionModel().getSelectedItem();
                try {
                    context.getClient().joinLobby(selectedLobby);
                    goToLobby();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void goToLobby() {
        int POA = 2;
        LobbyController lobbyController = new LobbyController(context, POA);
        lobbyController.showScene();
    }

}
