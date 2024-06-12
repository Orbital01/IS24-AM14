package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import it.polimi.ingsw.is24am14.server.view.TUIView;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StarterCardController {

    private GUIView context;
    private Scene scene;
    private VBox layout;

    private StarterCard starterCard;
    private ImageView cardImage;

    @FXML
    Button flipButton;

    @FXML
    Button confirmButton;

    public void initialize() {
        flipButton.setOnAction(this::handleFlipButtonAction);
        confirmButton.setOnAction(this::handleConfirmButtonAction);
    }

    public StarterCardController(GUIView context){
        this.context = context;

        layout = new VBox();

        flipButton = new Button("Flip");
        flipButton.setOnAction(this::handleFlipButtonAction);
        layout.getChildren().add(flipButton);

        confirmButton = new Button("Confirm");
        confirmButton.setOnAction(this::handleConfirmButtonAction);
        layout.getChildren().add(confirmButton);

        try {
            starterCard = context.getClient().getGameContext().getGame().getPlayer(context.getClient().getUsername()).getStarterCard();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        printStarterCard();

        scene = new Scene(layout, 600, 400);
    }

    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void handleFlipButtonAction(ActionEvent actionEvent) {
        starterCard.flipSide();
        printStarterCard();
    }

    private void handleConfirmButtonAction(ActionEvent actionEvent) {
        try {
            context.getClient().setStarterCard(starterCard);
            goToGame();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void goToGame() {
        GameController GameController = new GameController(context);
        System.out.println("Going to game");
        GameController.showScene();
    }

    private void printStarterCard() {
        //rimuovo l'immagine precedente se esiste
        if (cardImage != null) {
            layout.getChildren().remove(cardImage);
        }
        Image image = Guifactory.displayCardImage(starterCard);
        cardImage = new ImageView(image);
        layout.getChildren().add(cardImage);
    }

}
