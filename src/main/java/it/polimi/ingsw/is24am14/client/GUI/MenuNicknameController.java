package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.naming.NameAlreadyBoundException;
import java.io.IOException;

public class MenuNicknameController {

    @FXML
    TextField usernameField;

    @FXML
    Button confirmButton;

    private GUIView context;
    private Scene scene;
    private BorderPane layout = new BorderPane();


    public MenuNicknameController(GUIView context){
        this.context = context;
        scene = new Scene(layout, 1920, 1080);
        Guifactory.setAutomaticBackground(layout);

        //metto il logo
        VBox container = new VBox();
        container.setSpacing(20);
        container.setAlignment(javafx.geometry.Pos.CENTER);
        container.getChildren().add(Guifactory.displayLogo());

        //aggiungo una etichetta sopra il campo di inserimento
        Label label = Guifactory.printLabel("Inserisci il tuo username", 20);
        container.getChildren().add(label);

        //metto il campo per l'inserimento dell'username
        usernameField = new TextField();
        usernameField.setPromptText("Inserisci il tuo username");
        usernameField.setMaxWidth(200);
        container.getChildren().add(usernameField);

        //metto il bottone per confermare l'username
        confirmButton = Guifactory.createButton("Conferma", 200,50);
        confirmButton.setOnAction(this::handleConfirmButtonAction);

        container.getChildren().add(confirmButton);
        layout.setCenter(container);

    }


    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    public void goToLobbyChoice(){
        //passo alla scena di decisione sulla lobby
        MenuLobbyController lobbyController = new MenuLobbyController(context);
        lobbyController.showScene();
    }

    private void handleConfirmButtonAction(ActionEvent event) {
        //setto lo username e passo alla scena di decisione sulla lobby
        String username = usernameField.getText();
        //verifico he l'username sia valido
        int ok = -1;
            if (username.length() > 0) {
                try{
                    context.getClient().connect(username);
                    ok = 1;
                    //vado alla decisione sulla lobby
                    goToLobbyChoice();
                } catch (Exception e) {
                    e.printStackTrace();
                    MenuNicknameController menuNicknameController = new MenuNicknameController(context);
                    menuNicknameController.showScene();
                }
            }
    }

}
