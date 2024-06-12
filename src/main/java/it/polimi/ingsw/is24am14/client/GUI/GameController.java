package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameController {

    private GUIView context;
    private Scene scene;

    public GameController(GUIView context){
        this.context = context;

        VBox layout = new VBox();

        scene = new Scene(layout, 600, 400);
    }

    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }


}
