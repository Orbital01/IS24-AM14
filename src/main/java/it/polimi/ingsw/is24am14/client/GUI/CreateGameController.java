package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.client.GUIViewLauncher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is responsible for creating a new game.
 * It provides a GUI for the user to input the number of players and confirm the creation of the game.
 */
public class CreateGameController {
    private GUIViewLauncher context;
    private Scene scene;

    private Button confirmButton;

    private TextField numPlayers = new TextField();

    private VBox layout = new VBox();

    /**
     * Constructor for the CreateGameController class.
     * It initializes the GUI for creating a new game.
     * @param context the GUIViewLauncher object that contains the client and the stage
     */
    public CreateGameController(GUIViewLauncher context){
        this.context = context;

        scene = new Scene(layout, 1920, 1080);
        Guifactory.setAutomaticBackground(layout);

        Label title = Guifactory.printLabel("insert the number of players", 50);
        layout.getChildren().add(title);

        numPlayers.setPromptText("Number of players");
        numPlayers.setMaxWidth(400);
        layout.getChildren().add(numPlayers);

        confirmButton = Guifactory.createButton("Confirm", 100, 50);
        layout.getChildren().add(confirmButton);
        layout.setSpacing(10);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        // mi assicuro che il campo numPlayers accetti solo numeri
        numPlayers.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numPlayers.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        confirmButton.setOnAction(this::handleConfirmButtonAction);
    }

    /**
     * This method shows the scene for creating a new game.
     */
    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method handles the action of the confirm button.
     * It creates a new lobby with the number of players specified by the user.
     * If the number of players is not a number, it prints an error message.
     * If the creation of the lobby fails, it prints an error message.
     * If the creation of the lobby is successful, it goes to the lobby scene.
     * @param event the action event
     */
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

    /**
     * This method goes to the lobby scene.
     */
    private void goToLobby() {
        int AP = 1;
        LobbyController lobbyController = new LobbyController(context, AP);
        lobbyController.showScene();
    }
}
