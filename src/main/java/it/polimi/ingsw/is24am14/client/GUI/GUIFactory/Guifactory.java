package it.polimi.ingsw.is24am14.client.GUI.GUIFactory;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.*;

/**
 * The Class Guifactory is a factory class that provides the GUI elements during the game.
 * It includes methods for creating and displaying various GUI elements such as messages, labels, images, buttons, and text fields.
 * It also includes methods for setting the background of the layout and for creating and managing the game board.
 */
public class Guifactory {

    /**
     * Prints a message with a specified font and color.
     *
     * @param message The message to print.
     * @return The printed message as a Text object.
     */
    public static Text printMessage(String message){
        Text errorText = new Text(message);
        errorText.setFont(Font.font("Verdana", 20));
        errorText.setFill(Color.RED);
        return errorText;
    }

    /**
     * Prints a label with a specified message and font size.
     *
     * @param message The message to print.
     * @param fontSize The font size of the message.
     * @return The printed label.
     */
    public static Label printLabel(String message, int fontSize){
        Label label = new Label(message);
        label.setFont(Font.font("Verdana", fontSize));
        label.setStyle("-fx-background-color: #dfbd93; -fx-border-color: #a87d55");
        return label;
    }

    /**
     * Retrieves the image of a card and displays it as an ImageView object.
     *
     * @param Card The card to display.
     * @return The displayed card as an ImageView object.
     */
    public static ImageView displayCardImage(Card Card) {
        String imagePath = Card.getImage();
        Image image = new Image(Guifactory.class.getResource(imagePath).toExternalForm());
        //Image image = new Image("file:"+ imagePath);
        ImageView imageView = new ImageView(image);
        applyShadow(imageView);
        return imageView;
    }

    /**
     * Displays the logo of the game.
     *
     * @return The displayed logo as an ImageView object.
     */
    public static ImageView displayLogo() {

        Image image = new Image(Guifactory.class.getResource("/images/codexlogo.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    /**
     * Applies a shadow effect to an ImageView object to make it appear as if it is 3D.
     *
     * @param imageView The ImageView object to apply the shadow effect to.
     */
    private static void applyShadow(ImageView imageView) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.color(0, 0, 0, 0.7));
        imageView.setEffect(dropShadow);
    }

    /**
     * Sets the background of a VBox layout automatically.
     *
     * @param layout The VBox layout to set the background of.
     */
    public static void setAutomaticBackground(VBox layout) {

        Image image = null;
        try {
            image = new Image(Guifactory.class.getResource("/images/background.jpg").toExternalForm());
            System.out.println("JPG Image loaded successfully: " + "/images/background.jpg");
        } catch (NullPointerException e) {
            System.out.println("JPG Resource not found: " + "/images/background.jpg");
        }

        BackgroundSize backgroundSize = new BackgroundSize(1920, 1080, true, true, true, true);

        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize);

        Platform.runLater(() -> layout.setBackground(new Background(backgroundImage)));
        //layout.setBackground(new Background(backgroundImage));
    }

    /**
     * Sets the background of a BorderPane layout automatically.
     *
     * @param layout The BorderPane layout to set the background of.
     */
    public static void setAutomaticBackground(BorderPane layout) {

        Image image = null;
        try {
            image = new Image(Guifactory.class.getResource("/images/background.jpg").toExternalForm());
            System.out.println("JPG Image loaded successfully: " + "/images/background.jpg");
        } catch (NullPointerException e) {
            System.out.println("JPG Resource not found: " + "/images/background.jpg");
        }

        BackgroundSize BackgroundSize = new BackgroundSize(600, 400, true, true, true, true);

        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize);

        layout.setBackground(new Background(backgroundImage));
    }

    /**
     * Creates a button with a specified text, width, and height.
     * The button changes color when the mouse hovers over it.
     * The button also scales up by 10% when the mouse hovers over it.
     *
     * @param text The text of the button.
     * @param width The width of the button.
     * @param height The height of the button.
     * @return The created button.
     */
    public static Button createButton(String text, int width, int height){
        Button button = new Button(text);
        button.setPrefSize(width, height);
        button.setStyle("-fx-background-color: #dfbd93; -fx-border-color: #a87d55");
        button.setOnMouseEntered(event -> {
            button.setScaleX(1.1); // Ingrandisce il tasto del 10%
            button.setScaleY(1.1); // Ingrandisce il tasto del 10%
        });

        button.setOnMouseExited(event -> {
            button.setScaleX(1.0); // Ripristina la dimensione originale
            button.setScaleY(1.0); // Ripristina la dimensione originale
        });
        return button;
    }

    /**
     * Creates a button with a specified width, height, and image path.
     *
     * @param width The width of the button.
     * @param height The height of the button.
     * @param imagePath The path of the image to display on the button.
     * @return The created button.
     */
    public static Button createButtonWithGraphic(int width, int height, String imagePath){
        Button button = new Button();

        Image image = new Image("file:" + imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        button.setGraphic(imageView);

        button.setOnMouseEntered(event -> {
            button.setScaleX(1.1); // Ingrandisce il tasto del 10%
            button.setScaleY(1.1); // Ingrandisce il tasto del 10%
        });

        button.setOnMouseExited(event -> {
            button.setScaleX(1.0); // Ripristina la dimensione originale
            button.setScaleY(1.0); // Ripristina la dimensione originale
        });
        return button;
    }

    /**
     * Creates a text field with a specified text, width, and height.
     *
     * @param text The text of the text field.
     * @param width The width of the text field.
     * @param height The height of the text field.
     * @return The created text field.
     */
    public static TextField createTextField(String text, int width, int height){
        TextField textField = new TextField(text);
        textField.setPrefSize(width, height);
        return textField;
    }

    /**
     * Gets the game board as a GridPane object.
     *
     * @return The game board as a GridPane object.
     */
    public static GridPane getBoard(){

        //alloco la griglia
        int rows = 100;
        int columns = 100;

        GridPane gridPane = new GridPane();
        for(int i = 0; i <= columns; i++) {
            for (int j = 0; j <= rows; j++) {
                gridPane.add(emptyNode(), i, j);
            }
        }


        gridPane.setVgap(-55);
        gridPane.setHgap(-45);
        gridPane.setGridLinesVisible(false);
        return gridPane;
    }

    /**
     * Adds a card to the game board at a specified row, column, and corner.
     *
     * @param gridPane The game board to add the card to.
     * @param card The card to add.
     * @param row The row to add the card at.
     * @param column The column to add the card at.
     * @param corner The corner to add the card at.
     * @param index The index of the card.
     */
    public static void addCard(GridPane gridPane,Card card, int row, int column, int corner, int index){
        System.out.println("Row: " + row + " Column: " + column + " index " + index);
        //devo calcolare come aumentare la riga e la colonna in base all'angolo
        if (corner == 0){
            row = row - 1;
            column = column - 1;
        } else if (corner == 1){
            row = row - 1;
            column = column + 1;
        } else if (corner == 2){
            row = row + 1;
            column = column - 1;
        } else if (corner == 3){
            row = row + 1;
            column = column + 1;
        }

        System.out.println("Row: " + row + " Column: " + column + " index " + index);
        System.out.println();

        ImageView cardImage = displayCardImage(card);
        cardImage.setPreserveRatio(true);
        cardImage.setFitWidth(200);
        cardImage.setViewOrder(-index);
        gridPane.add(cardImage, column, row);
    }

    /**
     * Adds a starter card to the game board.
     *
     * @param board The game board to add the starter card to.
     * @param card The starter card to add.
     */
    public static void addStarterCard(GridPane board, Card card){
        ImageView cardImage = displayCardImage(card);
        cardImage.setPreserveRatio(true);
        cardImage.setFitWidth(200);
        //la posiziono al centro
        board.add(cardImage, 50, 50);
    }

    /**
     * Gets the maximum column of the game board.
     *
     * @param board The game board to get the maximum column of.
     * @return The maximum column of the game board.
     */
    public static int boardMaxColumn(GameArea board) {
        int maxColumn;
        maxColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).max().orElse(0);
        return maxColumn;
    }

    /**
     * Gets the minimum column of the game board.
     *
     * @param board The game board to get the minimum column of.
     * @return The minimum column of the game board.
     */
    public static int boardMinColumn(GameArea board) {
        int minColumn;
        minColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).min().orElse(0);
        return minColumn;
    }

    /**
     * Gets the maximum row of the game board.
     *
     * @param board The game board to get the maximum row of.
     * @return The maximum row of the game board.
     */
    public static int boardMaxRow(GameArea board) {
        int maxRow;
        maxRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).max().orElse(0);
        return maxRow;
    }

    /**
     * Gets the minimum row of the game board.
     *
     * @param board The game board to get the minimum row of.
     * @return The minimum row of the game board.
     */
    public static int boardMinRow(GameArea board) {
        int minRow;
        minRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).min().orElse(0);
        return minRow;
    }

    /**
     * Creates an empty node to populate the game board.
     *
     * @return The created empty node.
     */
    private static Node emptyNode() {
        Pane pane = new Pane();
        pane.setPrefSize(100, 100);
        return pane;
    }


    /**
     * Gets the face-up cards as a GridPane object.
     *
     * @param faceUpCards The face-up cards to get.
     * @return The face-up cards as a GridPane object.
     */
    public static GridPane getFaceUpCards(ArrayList<PlayableCard> faceUpCards){
        GridPane gridPane = new GridPane();
        int i = 0;
        for (PlayableCard card : faceUpCards) {
            ImageView cardImage = displayCardImage(card);
            cardImage.setPreserveRatio(true);
            cardImage.setFitWidth(200);
            gridPane.add(cardImage, i, 0);
            i++;
        }
        gridPane.setHgap(10);
        return gridPane;
    }

    //TODO: Implement this method
    public static void printOthersBoard(){

    }

}
