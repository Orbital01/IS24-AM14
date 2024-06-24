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
 */
//TODO: Add javaDoc

public class Guifactory {

    public static Text printMessage(String message){
        Text errorText = new Text(message);
        errorText.setFont(Font.font("Verdana", 20));
        errorText.setFill(Color.RED);
        return errorText;
    }

    public static Label printLabel(String message, int fontSize){
        Label label = new Label(message);
        label.setFont(Font.font("Verdana", fontSize));
        label.setStyle("-fx-background-color: #dfbd93; -fx-border-color: #a87d55");
        return label;
    }

    public static ImageView displayCardImage(Card Card) {
        String imagePath = Card.getImage();
        Image image = new Image(Guifactory.class.getResource(imagePath).toExternalForm());
        //Image image = new Image("file:"+ imagePath);
        ImageView imageView = new ImageView(image);
        applyShadow(imageView);
        return imageView;
    }

    public static ImageView displayLogo() {

        Image image = new Image(Guifactory.class.getResource("/images/codexlogo.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private static void applyShadow(ImageView imageView) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.color(0, 0, 0, 0.7));
        imageView.setEffect(dropShadow);
    }

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

    public static TextField createTextField(String text, int width, int height){
        TextField textField = new TextField(text);
        textField.setPrefSize(width, height);
        return textField;
    }

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

    public static void addStarterCard(GridPane board, Card card){
        ImageView cardImage = displayCardImage(card);
        cardImage.setPreserveRatio(true);
        cardImage.setFitWidth(200);
        //la posiziono al centro
        board.add(cardImage, 50, 50);
    }

    public static int boardMaxColumn(GameArea board) {
        int maxColumn;
        maxColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).max().orElse(0);
        return maxColumn;
    }

    public static int boardMinColumn(GameArea board) {
        int minColumn;
        minColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).min().orElse(0);
        return minColumn;
    }

    public static int boardMaxRow(GameArea board) {
        int maxRow;
        maxRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).max().orElse(0);
        return maxRow;
    }

    public static int boardMinRow(GameArea board) {
        int minRow;
        minRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).min().orElse(0);
        return minRow;
    }

    private static Node emptyNode() {
        Pane pane = new Pane();
        pane.setPrefSize(100, 100);
        return pane;
    }

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
        return gridPane;
    }

}
