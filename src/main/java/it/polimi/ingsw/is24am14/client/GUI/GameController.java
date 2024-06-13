package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController {

    private GUIView context;
    private Scene scene;
    private BorderPane layout = new BorderPane();

    private ScheduledExecutorService gameStatusExecutorService;
    private GameStateEnum previousGameState = null;

    private ArrayList<PlayableCard> playerHand;

    private ScheduledExecutorService updatePlayerBoardExecutorService;
    private GameArea playerBoard;

    public GameController(GUIView context) {
        this.context = context;
        scene = new Scene(layout, 600, 400);
        Guifactory.setAutomaticBackground(layout);

        gameStatusExecutorService = Executors.newSingleThreadScheduledExecutor();
        gameStatusExecutorService.scheduleAtFixedRate(this::checkGameStatus, 0, 1, TimeUnit.SECONDS);

        updatePlayerBoardExecutorService = Executors.newSingleThreadScheduledExecutor();
        updatePlayerBoardExecutorService.scheduleAtFixedRate(this::updatePlayerBoard, 0, 1, TimeUnit.SECONDS);
    }

    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void checkGameStatus() {
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
        try {
            switch (context.getClient().getGameContext().getGameStateEnum()) {
                case Move:
                    makeMove();
                    break;
                case Draw:
                    break;
                case LastMove:
                    break;
                case EndGame:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void makeMove() {
        try {
            playerHand = context.getClient().getGameContext().getGame().getPlayer(context.getClient().getUsername()).getPlayerHand();
            printPlayerHand();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void printPlayerHand() {
        HBox hand = new HBox();
        try {
            //recupero la mano del giocatore
            //creo le Vbox per le carte
            VBox card1 = new VBox();
            VBox card2 = new VBox();
            VBox card3 = new VBox();
            //recupero le immagini delle carte
            ImageView card1Image = Guifactory.displayCardImage(playerHand.get(0));
            ImageView card2Image = Guifactory.displayCardImage(playerHand.get(1));
            ImageView card3Image = Guifactory.displayCardImage(playerHand.get(2));
            //aggiungo gli handlers per il drag and drop
            addDragHandlers(card1Image);
            addDragHandlers(card2Image);
            addDragHandlers(card2Image);
            //setto la dimensione delle immagini
            card1Image.setPreserveRatio(true);
            card1Image.setFitWidth(200);
            card2Image.setPreserveRatio(true);
            card2Image.setFitWidth(200);
            card3Image.setPreserveRatio(true);
            card3Image.setFitWidth(200);
            //creo i bottoni per le carte
            Button flip1 = Guifactory.createButton("flip", 100, 50);
            Button flip2 = Guifactory.createButton("flip", 100, 50);
            Button flip3 = Guifactory.createButton("flip", 100, 50);
            flip1.setOnAction(event -> flipHandCard(event, 0));
            flip2.setOnAction(event -> flipHandCard(event, 1));
            flip3.setOnAction(event -> flipHandCard(event, 2));
            hand.getChildren().add(flip1);
            hand.getChildren().add(flip2);
            hand.getChildren().add(flip3);
            //aggiungo tutto alle Vbox
            card1.getChildren().add(card1Image);
            card1.getChildren().add(flip1);
            card2.getChildren().add(card2Image);
            card2.getChildren().add(flip2);
            card3.getChildren().add(card3Image);
            card3.getChildren().add(flip3);
            //centro il contenuto delle Vbox
            card1.setAlignment(Pos.CENTER);
            card2.setAlignment(Pos.CENTER);
            card3.setAlignment(Pos.CENTER);
            //aggiungo uno spazio tra le Vbox
            card1.setSpacing(10);
            card2.setSpacing(10);
            card3.setSpacing(10);
            //aggiungo le Vbox alla Hbox
            hand.getChildren().add(card1);
            hand.getChildren().add(card2);
            hand.getChildren().add(card3);
            //centro la Hbox
            hand.setSpacing(50);
            hand.setAlignment(Pos.CENTER);
            //aggiorno la mano del giocatore
            Platform.runLater(() -> layout.setBottom(hand));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void flipHandCard(ActionEvent event, int index) {
        try {
            playerHand.get(index).flipSide();
            printPlayerHand();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePlayerBoard(){
        try {
            playerBoard = context.getClient().getGameContext().getGame().getPlayer(context.getClient().getUsername()).getPlayerBoard();
            printPlayerBoard();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void printPlayerBoard(){
        GridPane board = Guifactory.getBoard(playerBoard);
        board.setGridLinesVisible(true);
        board.setAlignment(Pos.CENTER);
        Platform.runLater(() -> layout.setCenter(board));
    }

    private void addDragHandlers(ImageView cardView) {
        // Drag detected event handler
        cardView.setOnDragDetected(event -> {
            Dragboard db = cardView.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putImage(cardView.getImage());
            db.setContent(content);

            // Create a snapshot of the ImageView for the drag view
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            WritableImage snapshot = cardView.snapshot(params, null);
            cardView.setImage(db.getImage());
            db.setDragView(snapshot, snapshot.getWidth() / 2, snapshot.getHeight() / 2);

            event.consume();
        });
    }
}