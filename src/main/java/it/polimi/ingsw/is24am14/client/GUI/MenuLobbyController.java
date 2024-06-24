package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.client.GUIViewLauncher;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuLobbyController {

    private GUIViewLauncher context;
    private Scene scene;
    private BorderPane layout = new BorderPane();

    public MenuLobbyController(GUIViewLauncher context){
        this.context = context;
        scene = new Scene(layout, 1920, 1080);
        Guifactory.setAutomaticBackground(layout);

        //aggiungo il logo
        VBox container = new VBox();
        container.setSpacing(20);
        container.setAlignment(javafx.geometry.Pos.CENTER);
        container.getChildren().add(Guifactory.displayLogo());

        //aggiungo i due bottoni per la creazione e la partecipazione a una lobby
        Button createButton = Guifactory.createButton("Crea Partita", 200, 50);
        createButton.setOnAction(this::handleCreateButtonAction);

        Button joinButton = Guifactory.createButton("Partecipa", 200, 50);
        joinButton.setOnAction(this::handleJoinButtonAction);

        container.getChildren().add(createButton);
        container.getChildren().add(joinButton);

        layout.setCenter(container);

    }


    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void goToJoinLobby(){
        JoinGameController joinController = new JoinGameController(context);
        joinController.showScene();
    }

    private void goToCreateLobby(){
        CreateGameController createGameController = new CreateGameController(context);
        createGameController.showScene();
    }

    private void handleCreateButtonAction(ActionEvent event) {
        goToCreateLobby();
    }

    private void handleJoinButtonAction(ActionEvent event) {
        goToJoinLobby();
    }

}
