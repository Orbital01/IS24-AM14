package it.polimi.ingsw.is24am14.client.GUI.GUIFactory;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

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
        label.setFont(Font.font("Verdana", 20));
        label.setFont(Guifactory.getFont(30));
        return label;
    }

    public static Font getFont(int fontSize){
        return Font.font("Verdana", fontSize);
    }

    public static ImageView displayCardImage(Card Card) {
        String imagePath = Card.getImage();
        Image image = new Image("file:"+ imagePath);
        ImageView imageView = new ImageView(image);
        applyShadow(imageView);
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
        Image image = new Image("file:" + "src/main/resources/images/background.jpg");

        BackgroundSize BackgroundSize = new BackgroundSize(600, 400, true, true, true, true);

        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize);

        layout.setBackground(new Background(backgroundImage));
    }

    public static void setAutomaticBackground(BorderPane layout) {
        Image image = new Image("file:" + "src/main/resources/images/background.jpg");

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
        return button;
    }

    public static TextField createTextField(String text, int width, int height){
        TextField textField = new TextField(text);
        textField.setPrefSize(width, height);
        return textField;
    }

    public static GridPane getBoard(GameArea board){
        //non posso avere valori negativi negli indici della grid
        int rows = boardMaxRow(board) - boardMinRow(board);
        int columns = boardMaxColumn(board) - boardMinColumn(board);

        //alloco la griglia
        GridPane gridPane = new GridPane();
        for(int i = 0; i <= columns; i++) {
            for (int j = 0; j <= rows; j++) {
                gridPane.add(emptyNode(), i, j);
            }
        }

        //aggiungo le carte alla griglia
        for(int i=boardMaxRow(board); i>=boardMinRow(board); i--) {

            int row = boardMaxRow(board) - i;

            for (int j = boardMinColumn(board); j <= boardMaxColumn(board); j++) {

                Card card = board.getCard(new Coordinates(i, j));

                //ricalibro l'indice di colonna
                int column = j - boardMinColumn(board);

                if (card != null) {
                    ImageView cardImage = displayCardImage(card);
                    cardImage.setPreserveRatio(true);
                    cardImage.setFitWidth(200);

                    cardImage.setViewOrder(j);

                    gridPane.add(cardImage, column, row); //colonna / riga

                } else {
                    // Aggiungi un nodo vuoto
                    gridPane.add(emptyNode(), column, row);
                }
            }
        }



//        metodo funzionante
//        GridPane gridPane = new GridPane();
//        for(int i = 0; i <= columns; i++){
//            int column = boardMinColumn(board) + i;
//
//            for(int j = 0; j <= rows; j++){
//                int row = boardMaxRow(board) - j;
//
//                Card card = board.getCard(new Coordinates(row, column));
//                System.out.println("grid column: " + i + " grid row: " + j);
//                System.out.println("board row: " + row + " board Column: " + column);
//
//                if (card != null) {
//                    ImageView cardImage = displayCardImage(card);
//                    cardImage.setPreserveRatio(true);
//                    cardImage.setFitWidth(200);
//
//                    System.out.println("card is present");
//                    System.out.println();
//
//                    gridPane.add(cardImage, i, j); //colonna / riga
//
//                } else {
//                    // Aggiungi un nodo vuoto
//                    gridPane.add(emptyNode(), i, j);
//
//                    System.out.println("card is NOT present");
//                    System.out.println();
//                }
//                gridPane.setVgap(-55);
//                gridPane.setHgap(-45);
//            }
//
//        }

        gridPane.setVgap(-55);
        gridPane.setHgap(-45);
        gridPane.setGridLinesVisible(true);
        return gridPane;
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
        pane.setPrefSize(10, 10);
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

    //todo: implementare questo metodo per la stampa sulla ScoreBoard
    //public static HBox playerScoreBoard(/*Game game*/){}

}
