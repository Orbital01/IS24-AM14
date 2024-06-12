package it.polimi.ingsw.is24am14.client.GUI.GUIFactory;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;

/**
 * The Class Guifactory is a factory class that provides the GUI elements during the game.
 */
//TODO: Add textfield creation method
//TODO: Add javaDoc

public class Guifactory {

    public static Text printMessage(String message){
        Text errorText = new Text(message);
        errorText.setFont(Font.font("Verdana", 20));
        errorText.setFill(Color.RED);
        return errorText;
    }

    public static Image displayCardImage(Card Card) {
        String imagePath = Card.getImage();
        Image image = new Image("file:"+ imagePath);
        return image;
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

    //public static void

}
