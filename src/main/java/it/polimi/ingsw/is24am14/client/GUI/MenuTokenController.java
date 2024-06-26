package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUIViewLauncher;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
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
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class is the controller for the MenuTokenChoice.fxml file.
 * It manages the choice of the token colour by the player.
 */
public class MenuTokenController {

    private GUIViewLauncher context;
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

    /**
     * This method is called when the FXML file is loaded.
     * It initializes the buttons and the scheduled executor services.
     */
    public void initialize() {
        blueTokenButton.setOnAction(this::handleBlueTokenButton);
        yellowTokenButton.setOnAction(this::handleYellowTokenButton);
        redTokenButton.setOnAction(this::handleRedTokenButton);
        greenTokenButton.setOnAction(this::handleGreenTokenButton);

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

    /**
     * This constructor initializes the controller with the GUIViewLauncher context.
     * It also loads the FXML file and sets the controller.
     * It also initializes the scheduled executor services.
     * @param context the GUIViewLauncher context
     */
    public MenuTokenController(GUIViewLauncher context){
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

    /**
     * This method shows the scene.
     */
    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * this method updates the available colours.
     */
    private void checkAvailableColors() {
        try {
            List<TokenColour> availableColors = context.getClient().getGameContext().getColors();
            Platform.runLater(() -> updateButtonVisibility(availableColors));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * this method updates the visibility of the buttons based on the available colours.
     */
    private void updateButtonVisibility(List<TokenColour> availableColors) {
        blueTokenButton.setVisible(availableColors.contains(TokenColour.BLUE));
        yellowTokenButton.setVisible(availableColors.contains(TokenColour.YELLOW));
        redTokenButton.setVisible(availableColors.contains(TokenColour.RED));
        greenTokenButton.setVisible(availableColors.contains(TokenColour.GREEN));
    }

    /**
     * This method handles the click on the blue token button.
     * It sends the choice to the server.
     * @param actionEvent the action event
     */
    private void handleBlueTokenButton(ActionEvent actionEvent) {
        try {
            context.getClient().pickColor(TokenColour.BLUE);
            yellowTokenButton.setDisable(true);
            redTokenButton.setDisable(true);
            greenTokenButton.setDisable(true);
        }catch (Exception e){
            System.out.println("unable to pick color");
        }
    }

    /**
     * This method handles the click on the yellow token button.
     * It sends the choice to the server.
     * @param actionEvent the action event
     */
    private void handleYellowTokenButton(ActionEvent actionEvent) {
        try {
            context.getClient().pickColor(TokenColour.YELLOW);
            blueTokenButton.setDisable(true);
            redTokenButton.setDisable(true);
            greenTokenButton.setDisable(true);
        }catch (Exception e){
            System.out.println("unable to pick color");
        }
    }

    /**
     * This method handles the click on the red token button.
     * It sends the choice to the server.
     * @param actionEvent the action event
     */
    private void handleRedTokenButton(ActionEvent actionEvent) {
        try {
            context.getClient().pickColor(TokenColour.RED);
            blueTokenButton.setDisable(true);
            yellowTokenButton.setDisable(true);
            greenTokenButton.setDisable(true);
        }catch (Exception e){
            System.out.println("unable to pick color");
        }
    }

    /**
     * This method handles the click on the green token button.
     * It sends the choice to the server.
     * @param actionEvent the action event
     */
    private void handleGreenTokenButton(ActionEvent actionEvent) {
        try {
            context.getClient().pickColor(TokenColour.GREEN);
            blueTokenButton.setDisable(true);
            yellowTokenButton.setDisable(true);
            redTokenButton.setDisable(true);
        }catch (Exception e){
            System.out.println("unable to pick color");
        }
    }

    /**
     * This method goes to the starter card selection scene.
     */
    private void goToStarterCard() {
        //devo terminare l'esecuzione dei thread di aggiornamento di questa fase
        this.colorExecutorService.shutdown();
        this.gameStatusExecutorService.shutdown();
        this.turnExecutorService.shutdown();

        StarterCardController starterCardController = new StarterCardController(context);
        starterCardController.showScene();
    }

    /**
     * This method checks the game status.
     */
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

    /**
     * This method updates the scene based on the game state.
     */
    private void updateSceneBasedOnGameState() {
        if(previousGameState == GameStateEnum.ChoosingStarterCard) {
            //passo alla scelta della carta iniziale
            System.out.println("Going to starter card");
            goToStarterCard();
        }
    }

    /**
     * This method checks if it's the player's turn.
     */
    private void checkTurn(){
        for (int i = 0; i < myIndex; i++) {
            try {
                if (context.getClient().getGameContext().getGame().getPlayers().get(i).getColour() == null) {
                    myTurn = false;
                    Platform.runLater(() -> {
                        waitText.setVisible(true);
                        goText.setVisible(false);
                    });
                }else {
                    myTurn = true;
                    Platform.runLater(() -> {
                        waitText.setVisible(false);
                        goText.setVisible(true);
                    });
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
