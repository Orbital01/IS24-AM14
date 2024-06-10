package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuTokenController {

    private GUIView context;
    private Scene scene;

    public void initialize() {

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
    }


    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }


}
