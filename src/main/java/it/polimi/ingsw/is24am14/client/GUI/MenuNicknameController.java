package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuNicknameController {

    @FXML
    TextField usernameField;

    @FXML
    Button confirmButton;

    private GUIView context;
    private Scene scene;

    public void initialize() {
        confirmButton.setOnAction(this::handleConfirmButtonAction);
    }

    public MenuNicknameController(GUIView context){
        this.context = context;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MenuNicknameChoice.fxml"));
        loader.setController(this);
        try {
            Parent root = loader.load();
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        while(ok == -1) {
            if (username.length() > 0) {
                try{
                    context.getClient().connect(username);
                    ok = 1;
                    //vado alla decisione sulla lobby
                    goToLobbyChoice();
                } catch (Exception e) {
                    //username non valido
                    //mostro un messaggio di errore
                    //e chiedo di inserire un nuovo username
                    usernameField.clear();
                    username = usernameField.getText();
                    ok = -1;
                }
            }
        }
    }

}
