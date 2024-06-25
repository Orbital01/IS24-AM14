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
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StarterCardController {

    private GUIViewLauncher context;
    private Scene scene;
    private VBox layout;

    private StarterCard starterCard;
    private ImageView cardImage;

    private ScheduledExecutorService gameStatusExecutorService;
    private GameStateEnum previousGameState = null;

    @FXML
    Button flipButton;

    @FXML
    Button confirmButton;

    public void initialize() {
        flipButton.setOnAction(this::handleFlipButtonAction);
        confirmButton.setOnAction(this::handleConfirmButtonAction);
    }

    public StarterCardController(GUIViewLauncher context){
        this.context = context;

        layout = new VBox();

        flipButton = new Button("Flip");
        flipButton.setOnAction(this::handleFlipButtonAction); //si può togliere?
        layout.getChildren().add(flipButton);

        confirmButton = new Button("Confirm");
        confirmButton.setOnAction(this::handleConfirmButtonAction);
        layout.getChildren().add(confirmButton);

        try {
            starterCard = context.getClient().getGameContext().getGame().getPlayer(context.getClient().getUsername()).getStarterCard();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        printStarterCard();
        scene = new Scene(layout, 600, 400);

        gameStatusExecutorService = Executors.newSingleThreadScheduledExecutor();
        gameStatusExecutorService.scheduleAtFixedRate(this::checkGameStatus, 0, 1, TimeUnit.SECONDS);
    }

    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void handleFlipButtonAction(ActionEvent actionEvent) {
        starterCard.flipSide();
        printStarterCard();
    }

    private void handleConfirmButtonAction(ActionEvent actionEvent) {
        try {
            context.getClient().setStarterCard(starterCard);
            flipButton.setVisible(false);
            confirmButton.setVisible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void printStarterCard() {
        //rimuovo l'immagine precedente se esiste
        if (cardImage != null) {
            layout.getChildren().remove(cardImage);
        }
        ImageView image = Guifactory.displayCardImage(starterCard);
        cardImage = image;
        layout.getChildren().add(cardImage);
    }

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

    private void updateSceneBasedOnGameState() {
        if(previousGameState == GameStateEnum.ChoosingSecretObjective) {
            //passo alla scelta della carta iniziale
            System.out.println("Going to objective card selection");
            goToObjective();
        }
    }

    private void goToObjective() {
        //devo terminare l'esecuzione dei thread di aggiornamento di questa fase
        this.gameStatusExecutorService.shutdown();
        ObjectiveCardController objectiveCardController = new ObjectiveCardController(context);
        System.out.println("Going to secret objective card selection");
        objectiveCardController.showScene();
    }

}
