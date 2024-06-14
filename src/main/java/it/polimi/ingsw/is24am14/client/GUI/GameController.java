package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.client.view.printer.RenderBoard;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import it.polimi.ingsw.is24am14.server.view.TUIView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import javafx.stage.Modality;
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
    private Boolean myTurn = false;

    private ArrayList<PlayableCard> playerHand;

    private ScheduledExecutorService updatePlayerBoardExecutorService;
    private GameArea playerBoard;

    private ScheduledExecutorService updatePointsExecutorService;

    public GameController(GUIView context) {
        this.context = context;
        scene = new Scene(layout, 600, 400);
        Guifactory.setAutomaticBackground(layout);

        gameStatusExecutorService = Executors.newSingleThreadScheduledExecutor();
        gameStatusExecutorService.scheduleAtFixedRate(this::checkGameStatus, 0, 1, TimeUnit.SECONDS);

        updatePlayerBoardExecutorService = Executors.newSingleThreadScheduledExecutor();
        updatePlayerBoardExecutorService.scheduleAtFixedRate(this::updatePlayerBoard, 0, 1, TimeUnit.SECONDS);

        updatePointsExecutorService = Executors.newSingleThreadScheduledExecutor();
        updatePointsExecutorService.scheduleAtFixedRate(this::updatePoints, 0, 1, TimeUnit.SECONDS);
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
                //aggiorno anche se è il mio turno
                if (context.getClient().getGameContext().getGame().getActivePlayer().getPlayerNickname()
                        .equals(context.getClient().getUsername())) {
                    myTurn = true;
                } else {
                    myTurn = false;
                }
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
                    System.out.println("Move stage");
                    makeMove();
                    break;
                case Draw:
                    System.out.println("Draw stage");
                    makeDraw();
                    break;
                case LastMove:
                    System.out.println("Last move stage");
                    makeMove();
                    break;
                case EndGame:
                    System.out.println("End game stage");
                    //Todo: implement end game
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void makeMove() {
        try {
            playerHand = context.getClient().getGameContext().getGame().getPlayer(context.getClient().getUsername()).getPlayerHand();
            if(playerHand.size()==3){
                printPlayerHand();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void printPlayerHand() {
        HBox hand = new HBox();
        try {
            //rimuovo la mano precedente se presente
            layout.getChildren().remove(layout.getBottom());
            //creo le Vbox per le carte
            VBox card1 = new VBox();
            VBox card2 = new VBox();
            VBox card3 = new VBox();
            //recupero le immagini delle carte
            ImageView card1Image = Guifactory.displayCardImage(playerHand.get(0));
            ImageView card2Image = Guifactory.displayCardImage(playerHand.get(1));
            ImageView card3Image = Guifactory.displayCardImage(playerHand.get(2));
            //aggiungo gli handlers per il drag and drop
            addDragHandlers(card1Image, 0);
            addDragHandlers(card2Image, 1);
            addDragHandlers(card3Image, 2);
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
            //protraggo la modifica sul server
            context.getClient().flipCard(index);
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

    private void printPlayerBoard() {
        GridPane board = Guifactory.getBoard(playerBoard);
        board.setAlignment(Pos.CENTER);
        Platform.runLater(() -> layout.setCenter(board));

        //handlers per il drag and drop
        for (Node cell : board.getChildren()) {
            cell.setOnDragOver(event -> {
                if (event.getGestureSource() != cell && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
                event.consume();
            });

            cell.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();

                System.out.println("Drag dropped for card " + db.getString());

                boolean success = false;
                if (db.hasImage()) {
                    //recupero le coordinate della cella
                    Integer row = GridPane.getRowIndex(cell);
                    Integer column = GridPane.getColumnIndex(cell);

                    if (row == null) row = 0;  // Handle null values
                    if (column == null) column = 0;  // Handle null values

                    //aggiungo la carta alla board se è il mio turno
                    if (myTurn) {
                        int droppedCardIndex = Integer.parseInt(db.getString());
                        selectCorner(droppedCardIndex, row, column);
                        System.out.println("Card dropped at row " + row + ", column " + column);
                    }
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            });
        }
    }

    private void addDragHandlers(ImageView cardView, int index) {
        // Drag detected event handler
        cardView.setOnDragDetected(event -> {
            Dragboard db = cardView.startDragAndDrop(TransferMode.ANY);

            System.out.println("Drag detected for card " + index);

            ClipboardContent content = new ClipboardContent();
            content.putImage(cardView.getImage());
            content.putString(String.valueOf(index));
            db.setContent(content);

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            WritableImage snapshot = cardView.snapshot(params, null);
            db.setDragView(snapshot, snapshot.getWidth() / 2, snapshot.getHeight() / 2);

            event.consume();
        });
    }

    private void selectCorner(int cardIndex, int row, int column){
        Card cardToOverlap = playerBoard.getCard(new Coordinates(row, column));

        //apro una finestra modale che mostra la carta selezionata e permette di selezionare l'angolo
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        BorderPane modalLayout = new BorderPane();
        ImageView cardView = (Guifactory.displayCardImage(cardToOverlap));
        modalLayout.setCenter(cardView);

        TextField cornerInput = new TextField();
        Label cornerInputLabel = new Label("Inserisci un numero tra 0 e " + (cardToOverlap.getCorners().size()-1));
        VBox inputBox = new VBox(cornerInputLabel, cornerInput);
        modalLayout.setBottom(inputBox);

        cornerInput.setOnAction(event -> {
            // Recupera l'input dell'utente dal TextField e lo converte in un intero
            int corner = Integer.parseInt(cornerInput.getText());
            try {
                context.getClient().putCard(cardIndex, new Coordinates(row, column), corner);
                System.out.println("Card " +cardIndex + " placed at row " + row + ", column " + column + ", corner " + corner);
                //aggiorno la mano del giocatore
                makeMove();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Chiudi la finestra modale
            modalStage.close();
        });
        //mostro la finestra modale
        Scene modalScene = new Scene(modalLayout, 500, 500);
        modalStage.setScene(modalScene);
        modalStage.showAndWait();
    }

    private void makeDraw() {
        //apro una finestra modale che mi fa scelgiere da dove voglio pescare
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        BorderPane modalLayout = new BorderPane();
        Label label = new Label("Da dove vuoi pescare?");
        Button goldCardsButton = Guifactory.createButton("Gold Cards", 100, 50);
        Button resourceCardsButton = Guifactory.createButton("Resource Cards", 100, 50);
        Button faceUpCardsButton = Guifactory.createButton("Face up Cards", 100, 50);

        modalLayout.setTop(label);
        HBox buttons = new HBox(goldCardsButton, resourceCardsButton, faceUpCardsButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        modalLayout.setCenter(buttons);

        goldCardsButton.setOnAction(event -> {
            try {
                context.getClient().drawGoldCard();
                System.out.println("Drawn from gold cards");
                //aggiorno la mano del giocatore
                makeMove();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Chiudi la finestra modale
            modalStage.close();
        });

        resourceCardsButton.setOnAction(event -> {
            try {
                context.getClient().drawResourceCard();
                System.out.println("Drawn from resource cards");
                //aggiorno la mano del giocatore
                makeMove();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Chiudi la finestra modale
            modalStage.close();
        });

        faceUpCardsButton.setOnAction(event -> {
            //mostro le face up cards
            GridPane faceUpCards;

            try {
                faceUpCards = Guifactory.getFaceUpCards(context.getClient().getGameContext().getGame().getFaceUpCards());

                //ogni face up cards ha un handler per il click
                for (Node cell : faceUpCards.getChildren()) {
                    cell.setOnMouseClicked(event1 -> {
                        //chiamo il metodo draw sulla carta selezionata
                        Integer column = GridPane.getColumnIndex(cell);
                        try {
                            context.getClient().drawFaceUpCard(column);
                            System.out.println("Drawn from face up cards");
                            modalStage.close();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                    Platform.runLater(() -> modalLayout.setCenter(faceUpCards));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //mostro la finestra modale
        Scene modalScene = new Scene(modalLayout, 600, 500);
        modalStage.setScene(modalScene);
        modalStage.showAndWait();
    }

    private void updatePoints() {
        //Todo: implementare tabellone punti
    }


}