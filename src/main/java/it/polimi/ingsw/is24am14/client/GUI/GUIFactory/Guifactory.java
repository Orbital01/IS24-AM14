package it.polimi.ingsw.is24am14.client.GUI.GUIFactory;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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

        GridPane gridPane = new GridPane();
        for(int i = 0; i <= columns; i++){
            int row = boardMinRow(board) + i;
            for(int j = 0; j <= rows; j++){
                int column = boardMinColumn(board) + j;

                Card card = board.getCard(new Coordinates(row, column));
                StackPane cellStack = new StackPane();

                if (card != null) {
                    ImageView cardImage = displayCardImage(card);
                    cardImage.setPreserveRatio(true);
                    cardImage.setFitWidth(200);

                    cellStack.getChildren().add(cardImage);

                } else {
                    // Aggiungi un nodo vuoto
                    cellStack.getChildren().add(emptyNode());
                }
                gridPane.add(cellStack, i, j);
                gridPane.setVgap(-55);
                gridPane.setHgap(-45);
            }

        }
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }

    private static int boardMaxColumn(GameArea board) {
        int maxColumn;
        maxColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).max().orElse(0);
        return maxColumn;
    }

    private static int boardMinColumn(GameArea board) {
        int minColumn;
        minColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).min().orElse(0);
        return minColumn;
    }

    private static int boardMaxRow(GameArea board) {
        int maxRow;
        maxRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).max().orElse(0);
        return maxRow;
    }

    private static int boardMinRow(GameArea board) {
        int minRow;
        minRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).min().orElse(0);
        return minRow;
    }

    private static Node emptyNode() {
        Pane pane = new Pane();
        pane.setPrefSize(10, 10);
        return pane;
    }

}
