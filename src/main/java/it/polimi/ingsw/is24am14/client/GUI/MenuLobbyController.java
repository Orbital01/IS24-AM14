package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuLobbyController {

    private GUIView context;
    private Scene scene;

    @FXML
    private Button createButton;
    @FXML
    private Button joinButton;

    public void initialize() {
       createButton.setOnAction(this::handleCreateButtonAction);
       joinButton.setOnAction(this::handleJoinButtonAction);
    }

    public MenuLobbyController(GUIView context){
        this.context = context;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MenuLobbyChoice.fxml"));
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

    private void goToJoinLobby(){
        JoinGameController joinController = new JoinGameController(context);
        joinController.showScene();
    }

    private void goToCreateLobby(){
        CreateGameController createGameController = new CreateGameController(context);
        createGameController.showScene();
    }

    private void handleCreateButtonAction(ActionEvent event) {
        goToCreateLobby();
    }

    private void handleJoinButtonAction(ActionEvent event) {
        goToJoinLobby();
    }

}
