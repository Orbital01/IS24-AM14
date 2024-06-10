package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.network.RMIClient;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LobbyController {

    private GUIView context;
    private Scene scene;
    private int POA;

    @FXML
    private Button startButton;

    public void initialize() {
        if (POA == 1) {
            startButton.setVisible(true);
        } else {
            startButton.setVisible(false);
        }
        startButton.setOnAction(this::handleStartButtonAction);
    }

    public LobbyController(GUIView context, int pointOfAccess){
        this.context = context;
        this.POA = pointOfAccess;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("lobby.fxml"));
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

    private void goToToken(){

        RMIClient client = (RMIClient) context.getClient(); // andrà tolto perchè gameContext andrà generalizzato

        if(client.getGameContext().getGameStateEnum() == GameStateEnum.ChoosingColor) {
            MenuTokenController tokenController = new MenuTokenController(context);
            tokenController.showScene();
        }
    }

    private void handleStartButtonAction(ActionEvent event) {
        try {
            context.getClient().startGame();
            RMIClient client = (RMIClient) context.getClient(); // andrà tolto perchè gameContext andrà generalizzato
            if(client.getGameContext().getGameStateEnum() == GameStateEnum.DeckInit){
                goToToken();
            }
        } catch (Exception e) {
            //da gestire il caso in cui ci siano ancora meno giocatori rispetto al numero settato
            e.printStackTrace(); // non deve lanciare l'eccezione, ma attendere
        }
    }
}
