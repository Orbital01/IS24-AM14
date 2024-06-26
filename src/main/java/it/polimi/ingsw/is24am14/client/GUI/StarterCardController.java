package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.client.GUIViewLauncher;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class is the controller for the starter card selection phase of the game.
 * It allows the player to choose a starter card and confirm it.
 */
public class StarterCardController {

    private GUIViewLauncher context;
    private Scene scene;
    private VBox layout = new VBox();

    private StarterCard starterCard;
    private ImageView cardImage;

    private ScheduledExecutorService gameStatusExecutorService;
    private GameStateEnum previousGameState = null;

    @FXML
    Button flipButton;

    @FXML
    Button confirmButton;

    /**
     * Constructor for the StarterCardController class.
     * it creates the layout for the starter card selection phase and sets the actions for the buttons.
     * @param context the GUIViewLauncher object that contains the client and the stage
     */
    public StarterCardController(GUIViewLauncher context){
        this.context = context;

        Guifactory.setAutomaticBackground(layout);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Label title = Guifactory.printLabel("Choose your starter card", 50);
        layout.getChildren().add(title);

        flipButton = Guifactory.createButton("Flip", 100, 50);
        flipButton.setOnAction(this::handleFlipButtonAction); //aggiungo l'azione al bottone
        layout.getChildren().add(flipButton);

        confirmButton = Guifactory.createButton("Confirm", 100, 50);
        confirmButton.setOnAction(this::handleConfirmButtonAction);
        layout.getChildren().add(confirmButton);
        layout.setSpacing(10);

        try {
            starterCard = context.getClient().getGameContext().getGame().getPlayer(context.getClient().getUsername()).getStarterCard();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        printStarterCard();

        scene = new Scene(layout, 1920, 1080);

        gameStatusExecutorService = Executors.newSingleThreadScheduledExecutor();
        gameStatusExecutorService.scheduleAtFixedRate(this::checkGameStatus, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * This method shows the scene for the starter card selection phase.
     */
    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method handles the action of the flip button.
     * It flips the starter card and prints it.
     * @param actionEvent the event of the button
     */
    private void handleFlipButtonAction(ActionEvent actionEvent) {
        starterCard.flipSide();
        printStarterCard();
    }

    /**
     * This method handles the action of the confirm button.
     * It sets the starter card chosen by the player and hides the buttons.
     * @param actionEvent the event of the button
     */
    private void handleConfirmButtonAction(ActionEvent actionEvent) {
        try {
            context.getClient().setStarterCard(starterCard);
            flipButton.setVisible(false);
            confirmButton.setVisible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method prints the starter card on the scene.
     */
    private void printStarterCard() {
        //rimuovo l'immagine precedente se esiste
        if (cardImage != null) {
            layout.getChildren().remove(cardImage);
        }
        ImageView image = Guifactory.displayCardImage(starterCard);
        cardImage = image;
        layout.getChildren().add(cardImage);
    }

    /**
     * This method checks the game status and updates the scene based on the game state.
     */
    private void checkGameStatus() {
        System.out.println("Checking game status");
        try {
            context.getClient().updateGameContext();
            if (context.getClient().getGameContext() != null) {
                GameStateEnum currentGameState = context.getClient().getGameContext().getGameStateEnum();
                System.out.println("Current game state: " + currentGameState);
                if (currentGameState != previousGameState) {
                    previousGameState = currentGameState;
                    Platform.runLater(this::updateSceneBasedOnGameState);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method updates the scene based on the game state.
     */
    private void updateSceneBasedOnGameState() {
        if(previousGameState == GameStateEnum.ChoosingSecretObjective) {
            //passo alla scelta della carta iniziale
            System.out.println("Going to objective card selection");
            goToObjective();
        }
    }

    /**
     * This method goes to the objective card selection phase.
     */
    private void goToObjective() {
        //devo terminare l'esecuzione dei thread di aggiornamento di questa fase
        this.gameStatusExecutorService.shutdown();
        ObjectiveCardController objectiveCardController = new ObjectiveCardController(context);
        System.out.println("Going to secret objective card selection");
        objectiveCardController.showScene();
    }

}
