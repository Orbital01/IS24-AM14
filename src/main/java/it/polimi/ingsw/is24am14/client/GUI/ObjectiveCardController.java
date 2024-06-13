package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ObjectiveCardController {

    private GUIView context;
    private Scene scene;
    private VBox layout;

    @FXML
    Button select1;

    @FXML
    Button select2;

    ImageView firstCardImage;
    ImageView secondCardImage;

    private ScheduledExecutorService gameStatusExecutorService;
    private GameStateEnum previousGameState = null;

    public ObjectiveCardController(GUIView context){
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

        scene = new Scene(layout, 600, 400);

        gameStatusExecutorService = Executors.newSingleThreadScheduledExecutor();
        gameStatusExecutorService.scheduleAtFixedRate(this::checkGameStatus, 0, 1, TimeUnit.SECONDS);
    }

    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    public void handleSelect1(javafx.event.ActionEvent actionEvent) {
        try {
            context.getClient().pickObjectiveCard(context.getClient().getGameContext().getObjectiveCardChoices(context.getClient().getUsername()).getFirst());
            select1.setVisible(false);
            select2.setVisible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void handleSelect2(javafx.event.ActionEvent actionEvent) {
        try {
            context.getClient().pickObjectiveCard(context.getClient().getGameContext().getObjectiveCardChoices(context.getClient().getUsername()).get(1));
            select1.setVisible(false);
            select2.setVisible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

    private void updateSceneBasedOnGameState() {
        if(previousGameState == GameStateEnum.Move) {
            //passo alla scelta della carta iniziale
            System.out.println("Going to game");
            goToGame();
        }
    }

    private void goToGame() {
        //devo terminare l'esecuzione dei thread di aggiornamento di questa fase
        this.gameStatusExecutorService.shutdown();
        GameController gameController = new GameController(context);
        gameController.showScene();
    }

    private void printObjectiveCards(){
        try {
            HBox cardLayout = new HBox();

            VBox firstCardLayout = new VBox();

            ObjectiveCard objectiveCard1 = context.getClient().getGameContext().getObjectiveCardChoices(context.getClient().getUsername()).getFirst();
            ImageView image = Guifactory.displayCardImage(objectiveCard1);
            firstCardImage = image;
            firstCardLayout.getChildren().add(firstCardImage);

            select1 = new Button("Select First");
            select1.setOnAction(this::handleSelect1);
            firstCardLayout.getChildren().add(select1);

            cardLayout.getChildren().add(firstCardLayout);

            VBox secondCardLayout = new VBox();

            ObjectiveCard objectiveCard2 = context.getClient().getGameContext().getObjectiveCardChoices(context.getClient().getUsername()).get(1);
            image = Guifactory.displayCardImage(objectiveCard2);
            secondCardImage = image;
            secondCardLayout.getChildren().add(secondCardImage);

            select2 = new Button("Select Second");
            select2.setOnAction(this::handleSelect2);
            secondCardLayout.getChildren().add(select2);

            cardLayout.getChildren().add(secondCardLayout);

            layout.getChildren().add(cardLayout);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
