package it.polimi.ingsw.is24am14.client.GUI.Menu1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuNicknameController {
    @FXML
    TextField usernameField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setUsername(ActionEvent event) throws IOException {
        String username = usernameField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuNicknameChoice.fxml"));
        root = loader.load();

        MenuTokenController menuTokenController = loader.getController();
        //menuTokenController.setUsername(username);

        stage = (Stage) usernameField.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

    }
}
