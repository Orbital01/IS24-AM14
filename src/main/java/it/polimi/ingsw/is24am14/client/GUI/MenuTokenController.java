package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MenuTokenController {

    private GUIView context;
    private Scene scene;

    @FXML
    Text waitText;

    @FXML
    Text goText;

    @FXML
    Button blueTokenButton;

    @FXML
    Button yellowTokenButton;

    @FXML
    Button redTokenButton;

    @FXML
    Button greenTokenButton;

    private ScheduledExecutorService colorExecutorService;

    private ScheduledExecutorService gameStatusExecutorService;
    private GameStateEnum previousGameState = null;

    private ScheduledExecutorService turnExecutorService;
    private boolean myTurn = false;
    int myIndex;

    public void initialize() {
        blueTokenButton.setOnAction(this::handleBlueTokenButton);
        yellowTokenButton.setOnAction(this::handleYellowTokenButton);
        redTokenButton.setOnAction(this::handleRedTokenButton);
        greenTokenButton.setOnAction(this::handleGreenTokenButton);

        waitText.setVisible(true);
        goText.setVisible(false);

        try {
            myIndex = context.getClient().getGameContext().getGame().getPlayers().
                    indexOf(context.getClient().getGameContext().getGame().getPlayer(context.getClient().getUsername()));
            if (myIndex == -1) { //il giocatore non è stato trovato nella partita corrente
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore di connessione");
                alert.setHeaderText(null);
                alert.setContentText("Errore nella connessione con il server");
                alert.showAndWait();
                System.exit(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public MenuTokenController(GUIView context){
        this.context = context;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MenuTokenChoice.fxml"));
        loader.setController(this);
        try {
            Parent root = loader.load();
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        colorExecutorService = Executors.newSingleThreadScheduledExecutor();
        colorExecutorService.scheduleAtFixedRate(this::checkAvailableColors, 0, 1, TimeUnit.SECONDS);

        gameStatusExecutorService = Executors.newSingleThreadScheduledExecutor();
        gameStatusExecutorService.scheduleAtFixedRate(this::checkGameStatus, 0, 1, TimeUnit.SECONDS);

        turnExecutorService = Executors.newSingleThreadScheduledExecutor();
        turnExecutorService.scheduleAtFixedRate(this::checkTurn, 0, 1, TimeUnit.SECONDS);

    }

    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void checkAvailableColors() {
        try {
            List<TokenColour> availableColors = context.getClient().getGameContext().getColors();
            Platform.runLater(() -> updateButtonVisibility(availableColors));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateButtonVisibility(List<TokenColour> availableColors) {
        blueTokenButton.setVisible(availableColors.contains(TokenColour.BLUE));
        yellowTokenButton.setVisible(availableColors.contains(TokenColour.YELLOW));
        redTokenButton.setVisible(availableColors.contains(TokenColour.RED));
        greenTokenButton.setVisible(availableColors.contains(TokenColour.GREEN));
    }

    private void handleBlueTokenButton(ActionEvent actionEvent) {
        try {
            context.getClient().pickColor(TokenColour.BLUE);
        }catch (Exception e){
            System.out.println("unable to pick color");
        }
    }

    private void handleYellowTokenButton(ActionEvent actionEvent) {
        try {
            context.getClient().pickColor(TokenColour.YELLOW);
        }catch (Exception e){
            System.out.println("unable to pick color");
        }
    }

    private void handleRedTokenButton(ActionEvent actionEvent) {
        try {
            context.getClient().pickColor(TokenColour.RED);
        }catch (Exception e){
            System.out.println("unable to pick color");
        }
    }

    private void handleGreenTokenButton(ActionEvent actionEvent) {
        try {
            context.getClient().pickColor(TokenColour.GREEN);
        }catch (Exception e){
            System.out.println("unable to pick color");
        }
    }

    private void goToStarterCard() {
        //devo terminare l'esecuzione dei thread di aggiornamento di questa fase
        this.colorExecutorService.shutdown();
        this.gameStatusExecutorService.shutdown();
        this.turnExecutorService.shutdown();

        StarterCardController starterCardController = new StarterCardController(context);
        starterCardController.showScene();
    }

    private void checkGameStatus() {
        System.out.println("Checking game status");
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
        if(previousGameState == GameStateEnum.ChoosingStarterCard) {
            //passo alla scelta della carta iniziale
            System.out.println("Going to starter card");
            goToStarterCard();
        }
    }

    private void checkTurn(){
        for (int i = 0; i < myIndex; i++) {
            if (context.getClient().getGameContext().getGame().getPlayers().get(i).getColour() == null) {
                myTurn = false;
            }else {
                myTurn = true;
                waitText.setVisible(false);
                goText.setVisible(true);
            }
        }
    }

}
