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
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class JoinGameController {

    private GUIView context;
    private Scene scene;

    @FXML
    private ListView<String> lobbyList;

    private ScheduledExecutorService updateLobbyListService;

    public void initialize() {
        try {
            ArrayList<String> lobbies = context.getClient().getLobbyList();
            ObservableList<String> observableList = FXCollections.observableList(lobbies);
            lobbyList.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        joinLobby();//?
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
        updateLobbyListService = Executors.newSingleThreadScheduledExecutor();
        updateLobbyListService.scheduleAtFixedRate(this::updateLobbyList, 0, 1, java.util.concurrent.TimeUnit.SECONDS);
    }


    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void updateLobbyList() {
        try {
            ArrayList<String> lobbies = context.getClient().getLobbyList();
            ObservableList<String> observableList = FXCollections.observableList(lobbies);
            lobbyList.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        //kill scheduled executor
        updateLobbyListService.shutdown();

        int AP = 2;
        LobbyController lobbyController = new LobbyController(context, AP);
        lobbyController.showScene();
    }

}
