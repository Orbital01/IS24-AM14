package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController {

    private GUIView context;
    private Scene scene;
    private VBox layout;

    private ArrayList<PlayableCard> playerHand;

    private ScheduledExecutorService gameStatusExecutorService;
    private GameStateEnum previousGameState = null;

    private ScheduledExecutorService myTurnExecutorService;
    private boolean myTurn = false;

    private ScheduledExecutorService handUpdateExecutorService;

    public GameController(GUIView context){
        this.context = context;

        layout = new VBox();
        Guifactory.setAutomaticBackground(layout);
        scene = new Scene(layout, 600, 400);

        gameStatusExecutorService = Executors.newSingleThreadScheduledExecutor();
        gameStatusExecutorService.scheduleAtFixedRate(this::checkGameStatus, 0, 1, TimeUnit.SECONDS);

        myTurnExecutorService = Executors.newSingleThreadScheduledExecutor();
        myTurnExecutorService.scheduleAtFixedRate(this::checkMyTurn, 0, 1, TimeUnit.SECONDS);

        handUpdateExecutorService = Executors.newSingleThreadScheduledExecutor();
        handUpdateExecutorService.scheduleAtFixedRate(this::updateHand, 0, 1, TimeUnit.SECONDS);
    }

    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
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
        //in base al game state abilito delle azioni per il giocatore
        try {
            switch (context.getClient().getGameContext().getGameStateEnum()) {
                case Move:
                    makeMove();
                    break;

                case Draw:
                    drawCard();
                    break;

                case LastMove:
                    break;

                case LastDraw:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void makeMove() {
        if(myTurn) {

        }
    }

    private void drawCard() {
        if(myTurn) {

        }
    }

    private void checkMyTurn() {
        try{
            if(context.getClient().getGameContext().getGame().getActivePlayer().getPlayerNickname().equals(context.getClient().getUsername())) {
                myTurn = true;
            } else {
                myTurn = false;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void updateHand() {
        try {
            playerHand = context.getClient().getGameContext().getGame().getPlayer(context.getClient().getUsername()).getPlayerHand();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
