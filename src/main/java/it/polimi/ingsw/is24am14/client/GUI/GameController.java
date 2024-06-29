package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.client.GUI.GuiHelper.GuiHelper;
import it.polimi.ingsw.is24am14.client.GUIViewLauncher;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.network.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class is responsible for controlling the game's GUI.
 * It handles the game's state, player's turn, player's hand, and game board.
 * It also manages the game's chat interface and point board.
 */

public class GameController {

    private GUIViewLauncher context;
    private Scene scene;

    private BorderPane layout = new BorderPane();
    private BorderPane leftLayout = new BorderPane();

    GridPane board;
    int index = 1;

    private ScheduledExecutorService gameStatusExecutorService;
    private GameStateEnum previousGameState = null;
    private Boolean myTurn = false;

    private ArrayList<PlayableCard> playerHand;

    ScrollPane scrollPane = new ScrollPane();
    private GameArea playerBoard;

    private final GuiHelper GuiHelper = new GuiHelper();

    /**
     * Constructor for GameController.
     * Initializes the GUI, chat interface, objectives, and game status executor service.
     *
     * @param context The client context.
     */
    public GameController(GUIViewLauncher context) {
        this.context = context;
        scene = new Scene(layout, 1920, 1080);
        Guifactory.setAutomaticBackground(layout);
        layout.setLeft(leftLayout);
        createChatInterface();
        addObjButton();
        gameStatusExecutorService = Executors.newSingleThreadScheduledExecutor();
        gameStatusExecutorService.scheduleAtFixedRate(this::checkGameStatus, 0, 1, TimeUnit.SECONDS);

    }

    /**
     * Displays the game scene on the stage.
     */
    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Checks the game status and updates the game scene based on the current game state.
     */
    private void checkGameStatus() {
        try {
            context.getClient().updateGameContext();
        } catch (Exception e) {
            //todo: gestire l'eccezione in maniera più elegante
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        try {
            if (context.getClient().getGameContext() != null) {
                GameStateEnum currentGameState = context.getClient().getGameContext().getGameStateEnum();
                //update even if it's my turn
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
            //todo: gestire l'eccezione in maniera più elegante
            throw new RuntimeException(e);
        }
    }
    /**
     * Updates the game phase based on the server state.
     */
    private void updateSceneBasedOnGameState() {
        try {
            switch (context.getClient().getGameContext().getGameStateEnum()) {
                case Move:
                    System.out.println("Move stage");
                    makeMove();
                    createPointBoard();
                    //printScore();
                    break;
                case Draw:
                case LastDraw:
                    System.out.println("Draw stage");
                    makeDraw();
                    createPointBoard();
                    System.out.println("Draw stage"); //debug line
                    break;
                case LastMove:
                    System.out.println("Last move stage");
                    makeMove();
                    createPointBoard();
                    break;
                case EndGame:
                    System.out.println("End game stage");
                    endGame();
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the player's move.
     * it displays the player's hand, board, and turn status.
     */
    private void makeMove() {
        try {
            playerHand = context.getClient().getGameContext().getGame().getPlayer(context.getClient().getUsername()).getPlayerHand();
            if(playerHand.size()==3){
                printPlayerHand();
                updatePlayerBoard();

                itsYourTurn(myTurn);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints the player's hand in a HBox placed at the bottom of the layout.
     */
    private void printPlayerHand() {
        HBox hand = new HBox();
        try {
            //remove old hand if present
            layout.getChildren().remove(layout.getBottom());
            //create VBoxes for the cards
            VBox card1 = new VBox();
            VBox card2 = new VBox();
            VBox card3 = new VBox();
            //create ImageViews for the cards
            ImageView card1Image = Guifactory.displayCardImage(playerHand.get(0));
            ImageView card2Image = Guifactory.displayCardImage(playerHand.get(1));
            ImageView card3Image = Guifactory.displayCardImage(playerHand.get(2));
            //add drag handlers to the cards
            addDragHandlers(card1Image, 0);
            addDragHandlers(card2Image, 1);
            addDragHandlers(card3Image, 2);
            //set image size
            card1Image.setPreserveRatio(true);
            card1Image.setFitWidth(200);
            card2Image.setPreserveRatio(true);
            card2Image.setFitWidth(200);
            card3Image.setPreserveRatio(true);
            card3Image.setFitWidth(200);
            //create flip buttons for the cards
            Button flip1 = Guifactory.createButton("flip", 100, 50);
            Button flip2 = Guifactory.createButton("flip", 100, 50);
            Button flip3 = Guifactory.createButton("flip", 100, 50);
            flip1.setOnAction(event -> flipHandCard(event, 0));
            flip2.setOnAction(event -> flipHandCard(event, 1));
            flip3.setOnAction(event -> flipHandCard(event, 2));
            hand.getChildren().add(flip1);
            hand.getChildren().add(flip2);
            hand.getChildren().add(flip3);
            //add the ImageViews to the VBoxes
            card1.getChildren().add(card1Image);
            card1.getChildren().add(flip1);
            card2.getChildren().add(card2Image);
            card2.getChildren().add(flip2);
            card3.getChildren().add(card3Image);
            card3.getChildren().add(flip3);
            //center the VBox content
            card1.setAlignment(Pos.CENTER);
            card2.setAlignment(Pos.CENTER);
            card3.setAlignment(Pos.CENTER);
            //set spacing between the cards
            card1.setSpacing(10);
            card2.setSpacing(10);
            card3.setSpacing(10);
            //add the VBoxes to the HBox
            hand.getChildren().add(card1);
            hand.getChildren().add(card2);
            hand.getChildren().add(card3);
            //center the HBox content
            hand.setSpacing(50);
            hand.setAlignment(Pos.CENTER);
            //add the HBox to the layout
            Platform.runLater(() -> layout.setBottom(hand));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints the objectives in a modal window.
     */
    private void printObjectives(){

        ArrayList<ObjectiveCard> commonObj;
        ObjectiveCard privateObj;

        //retrieve common objectives
        commonObj = GuiHelper.getCommonObjectives(context);

        //retrieve secret objective
        try {
            privateObj = context.getClient().getGameContext().getGame().getPlayer(context.getClient().getUsername()).getSecretObjective();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //open a modal window to display the objectives
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        BorderPane modalLayout = new BorderPane();

        VBox objectivesContainer = new VBox();
        objectivesContainer.setAlignment(Pos.CENTER);
        objectivesContainer.setSpacing(10);

        //add private objectives
        Label title1 = Guifactory.printLabel("Private Objective", 10);
        title1.setAlignment(Pos.CENTER);
        objectivesContainer.getChildren().add(title1);

        ImageView privateObjImage = Guifactory.displayCardImage(privateObj);
        privateObjImage.setPreserveRatio(true);
        privateObjImage.setFitWidth(200);
        objectivesContainer.getChildren().add(privateObjImage);

        HBox commonObjContainer = new HBox();
        //add common objectives
        Label title2 = Guifactory.printLabel("Common Objectives", 10);
        title2.setAlignment(Pos.CENTER);
        objectivesContainer.getChildren().add(title2);
        for (ObjectiveCard obj : commonObj) {
            ImageView objImage = Guifactory.displayCardImage(obj);
            objImage.setPreserveRatio(true);
            objImage.setFitWidth(200);
            commonObjContainer.getChildren().add(objImage);
        }

        commonObjContainer.setAlignment(Pos.CENTER);
        commonObjContainer.setSpacing(10);
        objectivesContainer.getChildren().add(commonObjContainer);

        modalLayout.setCenter(objectivesContainer);
        //add a close button
        Scene modalScene = new Scene(modalLayout, 600, 600);
        modalStage.setScene(modalScene);
        modalStage.showAndWait();

    }

    /**
     * Adds a button to the left layout that displays the objectives if clicked.
     */
    private void addObjButton(){
        Button objButton = Guifactory.createButton("Objectives", 100, 50);
        objButton.setOnAction(event -> printObjectives());
        leftLayout.setBottom(objButton);
    }

    /**
     * Displays a message indicating whether it's the player's turn.
     *
     * @param myTurn A boolean indicating whether it's the player's turn.
     */
    private void itsYourTurn(Boolean myTurn) {
        //remove old message if present
        if (layout.getTop() != null) {
            layout.getChildren().remove(layout.getTop());
        }
        //if it's my turn I print a message
        HBox messageContainer;
        if (myTurn) {
            Label label = Guifactory.printLabel("It's your turn!", 50);
            label.setAlignment(Pos.CENTER);
            label.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            messageContainer = new HBox();
            messageContainer.setAlignment(Pos.CENTER);
            messageContainer.getChildren().add(label);
        }else {
            Label label = Guifactory.printLabel("It's NOT your turn!", 50);
            label.setAlignment(Pos.CENTER);
            label.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            messageContainer = new HBox();
            messageContainer.setAlignment(Pos.CENTER);
            messageContainer.getChildren().add(label);
        }

        Platform.runLater(() -> layout.setTop(messageContainer));
    }

    /**
     * Flips a card in the player's hand
     * it updates the player's hand and also protracts the change on the server.
     *
     * @param event The ActionEvent.
     * @param index The index of the card in the player's hand.
     */
    private void flipHandCard(ActionEvent event, int index) {
        try {
            playerHand.get(index).flipSide();
            //protract the change on the server
            context.getClient().flipCard(index);
            printPlayerHand();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the player's board.
     */
    private void updatePlayerBoard(){
        try {
            playerBoard = context.getClient().getGameContext().getGame().getPlayer(context.getClient().getUsername()).getPlayerBoard();
            printPlayerBoard();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the player's board in the center of the layout.
     * it uses a local copy of the board to avoid unnecessary server calls and also to properly display card's Z axis.
     * it also adds drag and drop handlers to the cells in the board.
     */
    private void printPlayerBoard() {

        if(board == null) {
            //create a local copy of the board
            board = Guifactory.getBoard();
            board.setStyle("-fx-background-color: transparent;");
            //add the starter card to the board
            Card starterCard = playerBoard.getCard(new Coordinates(0, 0));
            Guifactory.addStarterCard(board, starterCard);

            scrollPane.setContent(board);
            scrollPane.setPannable(true);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
            scrollPane.setVvalue(0.5);
            scrollPane.setHvalue(0.5);
        }

        //add drag handlers to the cells
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
                    //retrieve cell coordinates
                    Integer row = GridPane.getRowIndex(cell);
                    Integer column = GridPane.getColumnIndex(cell);

                    if (row == null) row = 0;  // Handle null values
                    if (column == null) column = 0;  // Handle null values

                    //proceed to add card to board only if it's the player's turn
                    if (myTurn) {
                        int droppedCardIndex = Integer.parseInt(db.getString());
                        selectCorner(droppedCardIndex, row, column);
                        System.out.println("Card dropped at row " + row + ", column " + column);

                        updatePlayerBoard();
                    }
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            });
        }

        Platform.runLater(() -> layout.setCenter(scrollPane));
    }

    /**
     * Adds drag handlers to a card situated in the player's hand.
     *
     * @param cardView The ImageView of the card.
     * @param index The index of the card.
     */
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

    /**
     * Handles the corner selection when dropping a hand card on a card placed on the board.
     *
     * @param cardIndex The index of the card.
     * @param row The row of the cell.
     * @param column The column of the cell.
     */
    private void selectCorner(int cardIndex, int row, int column){

        //attention these are rows and columns of the GRID not the board
        int realRow = -(row - 50);
        int realColumn = column - 50;

        Card cardToOverlap = playerBoard.getCard(new Coordinates(realRow, realColumn));
        //proceed with corner selection only if move is valid
        if (cardToOverlap == null) {
            System.out.println("Invalid move: no card to overlap -> realRow: " + realRow + ", realColumn: " + realColumn);
            return;
        }

        System.out.println("Select corner for card " + cardIndex + " at row " + row + ", column " + column);

        //open a modal window that shows the selected card and allows to choose a corner
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        BorderPane modalLayout = new BorderPane();

        StackPane cardStack = new StackPane();

        AnchorPane anchorPane = new AnchorPane();

        Button corner0 = Guifactory.createButton("0", 50, 50);
        Button corner1 = Guifactory.createButton("1", 50, 50);
        Button corner2 = Guifactory.createButton("2", 50, 50);
        Button corner3 = Guifactory.createButton("3", 50, 50);

        AnchorPane.setTopAnchor(corner0, 0.0);
        AnchorPane.setLeftAnchor(corner0, 0.0);

        AnchorPane.setTopAnchor(corner1, 0.0);
        AnchorPane.setRightAnchor(corner1, 0.0);

        AnchorPane.setBottomAnchor(corner2, 0.0);
        AnchorPane.setLeftAnchor(corner2, 0.0);

        AnchorPane.setBottomAnchor(corner3, 0.0);
        AnchorPane.setRightAnchor(corner3, 0.0);

        anchorPane.getChildren().addAll(corner0, corner1, corner2, corner3);

        cardStack.getChildren().addAll(Guifactory.displayCardImage(cardToOverlap), anchorPane);

        modalLayout.setCenter(cardStack);

        corner0.setOnAction(event -> {
            // Retrieve user input from the TextField and convert it to an integer
            int corner = 0;
            try {
                context.getClient().putCard(cardIndex, new Coordinates(realRow, realColumn), corner);

                //Add the card to the board
                //This operation should only be done if the card has actually been placed
                Guifactory.addCard(board, playerHand.get(cardIndex), row, column, corner, index);
                index++;

                System.out.println("Card " +cardIndex + " placed at row " + realRow + ", column " + realColumn + ", corner " + corner);
                //update the player's hand
                makeMove();
            } catch (Exception e) {
                //show an error message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mossa non concessa");
                alert.setHeaderText(null);
                alert.setContentText("Non puoi piazzare la carta in questa posizione");
                alert.showAndWait();
                modalStage.close();
                throw new RuntimeException(e);
            }
            // Close the modal window
            modalStage.close();
        });
        corner1.setOnAction(event -> {
            // Retrieve user input from the TextField and convert it to an integer
            int corner = 1;
            try {
                context.getClient().putCard(cardIndex, new Coordinates(realRow, realColumn), corner);

                //Add the card to the board
                //This operation should only be done if the card has actually been placed
                Guifactory.addCard(board, playerHand.get(cardIndex), row, column, corner, index);
                index++;

                System.out.println("Card " +cardIndex + " placed at row " + realRow + ", column " + realColumn + ", corner " + corner);
                //update the player's hand
                makeMove();
            } catch (Exception e) {
                //show an error message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mossa non concessa");
                alert.setHeaderText(null);
                alert.setContentText("Non puoi piazzare la carta in questa posizione");
                alert.showAndWait();
                modalStage.close();
                throw new RuntimeException(e);
            }
            // Close the modal window
            modalStage.close();
        });
        corner2.setOnAction(event -> {
            // Retrieve user input from the TextField and convert it to an integer
            int corner = 2;
            try {
                context.getClient().putCard(cardIndex, new Coordinates(realRow, realColumn), corner);

                //Add the card to the board
                //This operation should only be done if the card has actually been placed
                Guifactory.addCard(board, playerHand.get(cardIndex), row, column, corner, index);
                index++;

                System.out.println("Card " +cardIndex + " placed at row " + realRow + ", column " + realColumn + ", corner " + corner);
                //update the player's hand
                makeMove();
            } catch (Exception e) {
                //show an error message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mossa non concessa");
                alert.setHeaderText(null);
                alert.setContentText("Non puoi piazzare la carta in questa posizione");
                alert.showAndWait();
                modalStage.close();
                throw new RuntimeException(e);
            }
            // Close the modal window
            modalStage.close();
        });
        corner3.setOnAction(event -> {
            // Retrieve user input from the TextField and convert it to an integer
            int corner = 3;
            try {
                context.getClient().putCard(cardIndex, new Coordinates(realRow, realColumn), corner);

                //Add the card to the board
                //This operation should only be done if the card has actually been placed
                Guifactory.addCard(board, playerHand.get(cardIndex), row, column, corner, index);
                index++;

                System.out.println("Card " +cardIndex + " placed at row " + realRow + ", column " + realColumn + ", corner " + corner);
                //update the player's hand
                makeMove();
            } catch (Exception e) {
                //show an error message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mossa non concessa");
                alert.setHeaderText(null);
                alert.setContentText("Non puoi piazzare la carta in questa posizione");
                alert.showAndWait();
                modalStage.close();
                throw new RuntimeException(e);
            }
            // Close the modal window
            modalStage.close();
        });

        // Show the modal window
        Scene modalScene = new Scene(modalLayout, 400, 300);
        modalStage.setScene(modalScene);
        modalStage.showAndWait();
    }

    /**
     * Handles the player's draw
     * it creates a modal window that allows the player to choose where to draw from.
     * The player can choose to draw from gold cards, resource cards, or face up cards.
     * The player can only draw if it's their turn.
     */
    private void makeDraw() {
        //remove old message if present
        //I have to check if it's my turn
        if (!myTurn) {
            return;
        }

        AtomicBoolean drawn = new AtomicBoolean(false);

        while (!drawn.get()) {

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
                    drawn.set(true);
                    System.out.println("Drawn from gold cards");
                    //update the player's hand
                    makeMove();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                // Close the modal window
                modalStage.close();
            });

            resourceCardsButton.setOnAction(event -> {
                try {
                    context.getClient().drawResourceCard();
                    drawn.set(true);
                    System.out.println("Drawn from resource cards");
                    //update the player's hand
                    makeMove();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                // Close the modal window
                modalStage.close();
            });

            faceUpCardsButton.setOnAction(event -> {
                //remove old message if present
                GridPane faceUpCards;

                try {
                    faceUpCards = Guifactory.getFaceUpCards(context.getClient().getGameContext().getGame().getFaceUpCards());

                    // every face up card is clickable
                    for (Node cell : faceUpCards.getChildren()) {
                        cell.setOnMouseClicked(event1 -> {
                            // I call the drawFaceUpCard method with the column of the clicked card
                            Integer column = GridPane.getColumnIndex(cell);
                            try {
                                context.getClient().drawFaceUpCard(column);
                                drawn.set(true);
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

            // show the modal window
            Scene modalScene = new Scene(modalLayout, 850, 600);
            modalStage.setScene(modalScene);
            modalStage.showAndWait();
        }
    }

    /**
     * Ends the game by displaying a modal window with the winning player.
     */
    private void endGame() {
        // show a modal window with the winner
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        BorderPane modalLayout = new BorderPane();

        Label label;
        try {
             label = new Label("Il giocatore " + GuiHelper.getWinner(context.getClient().getGameContext().getGame()).getPlayerNickname() + " ha vinto!");
        } catch (Exception e) {
             label = new Label("Errore nel recupero del vincitore");
            throw new RuntimeException(e);
        }

        Button closeButton = Guifactory.createButton("Close", 100, 50);
        closeButton.setOnAction(event -> {
            modalStage.close();
            System.exit(0);
        });

        modalLayout.setCenter(label);
        modalLayout.setBottom(closeButton);
        modalStage.setScene(new Scene(modalLayout, 300, 300));
        modalStage.showAndWait();
    }

    /**
     * Creates the chat interface and places it on the right side of the layout.
     */
    private void createChatInterface() {
        VBox chatContainer = new VBox();
        chatContainer.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        // add a text field for the chat input
        TextField chatInput = new TextField();
        // add a choice box for the recipient
        ChoiceBox<String> recipientChoice = new ChoiceBox<>();
        recipientChoice.getItems().add("All");
        try {
            for (Player player : context.getClient().getGameContext().getGame().getPlayers()) {
                recipientChoice.getItems().add(player.getPlayerNickname());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        recipientChoice.setValue("All");

        Button sendButton = Guifactory.createButton("Send", 100, 50);
        sendButton.setOnAction(event -> {
            try {
                String selectedValue = recipientChoice.getValue();
                if (selectedValue.equals("All")) {
                    selectedValue = "";
                }
                Message message = new Message(context.getClient().getUsername(), selectedValue, chatInput.getText());
                System.out.println("Sending message to: " + message.getReceiver() + " selected voice " + selectedValue); //debug line
                context.getClient().sendMessage(message.getReceiver(), message.getMessage());
                chatInput.clear();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //add a text area for the chat messages
        TextArea chatMessages = new TextArea();
        chatMessages.setEditable(false);
        chatMessages.setPrefHeight(400);
        chatContainer.getChildren().add(chatMessages);

        // add checks
        HBox chatInputContainer = new HBox(chatInput, sendButton);
        chatInputContainer.setAlignment(Pos.CENTER);
        chatInputContainer.setSpacing(10);
        chatContainer.getChildren().add(chatInputContainer);
        chatContainer.getChildren().add(recipientChoice);


        // update the messages every second
        ScheduledExecutorService chatExecutorService;
        chatExecutorService = Executors.newSingleThreadScheduledExecutor();
        chatExecutorService.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> GuiHelper.updateMessages(chatMessages, context));
        }, 0, 1, TimeUnit.SECONDS);


        chatContainer.setMaxWidth(300);
        chatContainer.setAlignment(Pos.CENTER);
        layout.setRight(chatContainer);
    }

    /**
     * Creates the point board and places it on the left side of the layout.
     */
    private void createPointBoard(){
        try {
            StackPane pointBoard = GuiHelper.getPointBoard(context.getClient().getGameContext().getGame());
            pointBoard.setAlignment(Pos.CENTER_LEFT);
            Platform.runLater(() -> leftLayout.setCenter(pointBoard));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //TODO: implementare la visualizzazione delle board degli altri giocatori
    private void getOthersBoard(){
        // get the players in the game
        ArrayList<Player> players = null;
        try {
            players = context.getClient().getGameContext().getGame().getPlayers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // show n buttons where n is the number of players
        for(Player player: players){
            Button button = Guifactory.createButtonWithGraphic(100, 100,
                    "src/main/resources/images/boards/"+player.getColour().toString().toLowerCase()+"_token.png");
            button.setOnAction(event -> {
                // open a modal window with the selected player's board
                Stage modalStage = new Stage();
                modalStage.initModality(Modality.APPLICATION_MODAL);
                BorderPane modalLayout = new BorderPane();

                // get the selected player's board



                // show the modal window
                Scene modalScene = new Scene(modalLayout, 500, 500);
                modalStage.setScene(modalScene);
                modalStage.showAndWait();
            });
            layout.setBottom(button);
        }

    }
}