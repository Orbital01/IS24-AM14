package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.network.RMIClient;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LobbyController {

    private GUIView context;
    private Scene scene;
    private int AP;

    @FXML
    private Button startButton;

    @FXML
    private Text connectedPlayers;

    private ScheduledExecutorService executorService;
    private int previousSize = 0;
    private int numberOfConnectedPlayers = 0;

    private ScheduledExecutorService gameStatusExecutorService;
    private GameStateEnum previousGameState = null;

    public void initialize() {
        startButton.setVisible(AP == 1);
        startButton.setOnAction(this::handleStartButtonAction);
    }

    public LobbyController(GUIView context, int accesPoint){
        this.context = context;
        this.AP = accesPoint;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("lobby.fxml"));
        loader.setController(this);
        try {
            Parent root = loader.load();
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        executorService = Executors.newSingleThreadScheduledExecutor();
        gameStatusExecutorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(this::checkConnectedClients, 0, 1, TimeUnit.SECONDS);
        gameStatusExecutorService.scheduleAtFixedRate(this::checkGameStatus, 0, 1, TimeUnit.SECONDS);

    }


    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void goToToken(){
        //termino l'esecuzione dei thread di controllo
        executorService.shutdown();
        gameStatusExecutorService.shutdown();

        MenuTokenController tokenController = new MenuTokenController(context);
        tokenController.showScene();
    }

    private void handleStartButtonAction(ActionEvent event) {
        try {
            context.getClient().startGame();
        } catch (Exception e) {
            //da gestire il caso in cui ci siano ancora meno giocatori rispetto al numero settato
            //è già gestito dal server, poteri aggiungere un messaggio di errore
            e.printStackTrace(); // non deve lanciare l'eccezione, ma attendere
        }
    }

    private void updateConnectedPlayersText() {
        if(AP==1) {
            try {
                ArrayList<String> giocatori = context.getClient().getLobbyClients(context.getClient().getUsername());
                numberOfConnectedPlayers = giocatori.size();
                connectedPlayers.setText("Connected players: " + numberOfConnectedPlayers);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            connectedPlayers.setText("waiting for the host to start the game");
        }
    }

    private void checkConnectedClients() {
        System.out.println("Checking connected clients");
        try {
            ArrayList<String> clients = context.getClient().getLobbyClients(context.getClient().getUsername());
            if (clients.size() != previousSize) {
                previousSize = clients.size();
                Platform.runLater(this::updateConnectedPlayersText);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        if(previousGameState == GameStateEnum.ChoosingColor) {
            System.out.println("Going to token page");
            goToToken();
        }
    }

}
