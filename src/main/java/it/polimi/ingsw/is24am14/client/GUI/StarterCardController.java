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

public class StarterCardController {

    private GUIView context;
    private Scene scene;

    @FXML
    Button flipButton;

    @FXML
    Button confirmButton;

    //TODO: add the method to show the starter card i GUIFactory

    public void initialize() {
        flipButton.setOnAction(this::handleFlipButtonAction);
        confirmButton.setOnAction(this::handleConfirmButtonAction);
    }

    public StarterCardController(GUIView context){
        this.context = context;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("starterCard.fxml"));
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

    private void handleFlipButtonAction(ActionEvent actionEvent) {
        try {
            //context.getClient().
        } catch (Exception e) {
            System.out.println("unable to flip starter card");
        }
    }

    private void handleConfirmButtonAction(ActionEvent actionEvent) {
        try {
            //context.getClient().
            //goToGame();
        } catch (Exception e) {
            System.out.println("unable to confirm starter card");
        }
    }

}
