package it.polimi.ingsw.is24am14.client.GUI.GUIFactory;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;

/**
 * The Class Guifactory is a factory class that provides the GUI elements during the game.
 */

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

}
