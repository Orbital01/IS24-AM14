package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.client.GUIViewLauncher;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class is the controller for the ObjectiveCard.fxml file.
 * It is used to display the two objective cards to the player and let him choose one.
 */
public class ObjectiveCardController {

    private GUIViewLauncher context;
    private Scene scene;
    private VBox layout;

    Button select1;

    Button select2;

    ImageView firstCardImage;
    ImageView secondCardImage;

    private ScheduledExecutorService gameStatusExecutorService;
    private GameStateEnum previousGameState = null;

    /**
     * Constructor for the ObjectiveCardController class.
     * it initializes the layout and the scene and starts the gameStatusExecutorService.
     * @param context the GUIViewLauncher object that contains the client and the stage
     */
    public ObjectiveCardController(GUIViewLauncher context){
        this.context = context;
        try{
            context.getClient().updateGameContext();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        layout = new VBox();
        layout.setPrefWidth(600);
        layout.setPrefHeight(400);

        Guifactory.setAutomaticBackground(layout);
        printObjectiveCards();

        scene = new Scene(layout, 1920, 1080);

        gameStatusExecutorService = Executors.newSingleThreadScheduledExecutor();
        gameStatusExecutorService.scheduleAtFixedRate(this::checkGameStatus, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * This method is used to show the scene.
     */
    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to handle the selection of the first objective card.
     * It calls the pickObjectiveCard method of the client and hides the two buttons.
     * @param actionEvent the event that triggered the method
     */
    public void handleSelect1(javafx.event.ActionEvent actionEvent) {
        try {
            context.getClient().pickObjectiveCard(context.getClient().getGameContext().getObjectiveCardChoices(context.getClient().getUsername()).getFirst());
            select1.setVisible(false);
            select2.setVisible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to handle the selection of the second objective card.
     * It calls the pickObjectiveCard method of the client and hides the two buttons.
     * @param actionEvent the event that triggered the method
     */
    public void handleSelect2(javafx.event.ActionEvent actionEvent) {
        try {
            context.getClient().pickObjectiveCard(context.getClient().getGameContext().getObjectiveCardChoices(context.getClient().getUsername()).get(1));
            select1.setVisible(false);
            select2.setVisible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to check the game status.
     * It calls the updateGameContext method of the client and checks if the game state has changed.
     * If the game state has changed, it calls the updateSceneBasedOnGameState method.
     */
    private void checkGameStatus() {
        System.out.println("Checking game status");
        try {
            context.getClient().updateGameContext();
            if (context.getClient().getGameContext() != null) {
                GameStateEnum currentGameState = context.getClient().getGameContext().getGameStateEnum();
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
     * This method is used to update the scene based on the game state.
     * If the previous game state was Move, it calls the goToGame method.
     */
    private void updateSceneBasedOnGameState() {
        if(previousGameState == GameStateEnum.Move) {
            //passo alla scelta della carta iniziale
            System.out.println("Going to game");
            goToGame();
        }
    }

    /**
     * This method is used to go to the game.
     * It shuts down the gameStatusExecutorService and creates a new GameController object.
     */
    private void goToGame() {
        //devo terminare l'esecuzione dei thread di aggiornamento di questa fase
        this.gameStatusExecutorService.shutdown();
        GameController gameController = new GameController(context);
        gameController.showScene();
    }

    /**
     * This method is used to print the two objective cards.
     * It creates two VBox objects, each containing an ImageView and a Button.
     * The ImageView contains the image of the objective card and the Button is used to select the card.
     */
    private void printObjectiveCards(){
        try {
            HBox cardLayout = new HBox();

            VBox firstCardLayout = new VBox();

            ObjectiveCard objectiveCard1 = context.getClient().getGameContext().getObjectiveCardChoices(context.getClient().getUsername()).getFirst();
            ImageView image = Guifactory.displayCardImage(objectiveCard1);
            firstCardImage = image;
            firstCardLayout.getChildren().add(firstCardImage);

            select1 = Guifactory.createButton("Select First", 100, 100);
            select1.setOnAction(this::handleSelect1);
            firstCardLayout.getChildren().add(select1);

            firstCardLayout.setAlignment(javafx.geometry.Pos.CENTER);

            cardLayout.getChildren().add(firstCardLayout);

            VBox secondCardLayout = new VBox();

            ObjectiveCard objectiveCard2 = context.getClient().getGameContext().getObjectiveCardChoices(context.getClient().getUsername()).get(1);
            image = Guifactory.displayCardImage(objectiveCard2);
            secondCardImage = image;
            secondCardLayout.getChildren().add(secondCardImage);

            select2 = Guifactory.createButton("Select Second", 100, 100);
            select2.setOnAction(this::handleSelect2);
            secondCardLayout.getChildren().add(select2);

            secondCardLayout.setAlignment(javafx.geometry.Pos.CENTER);

            cardLayout.getChildren().add(secondCardLayout);

            cardLayout.setAlignment(javafx.geometry.Pos.CENTER);

            layout.getChildren().add(cardLayout);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
