package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.server.network.ClientInterface;
import it.polimi.ingsw.is24am14.server.network.RMIClient;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuConnectionController {

    private GUIView context;
    private Scene scene;
    private RMIClient rmiClient;

    @FXML
    private Button rmiButton;

    @FXML
    private Button tcpButton;

    @FXML
    public void initialize() {
        rmiButton.setOnAction(this::handleRmiButtonAction);
        tcpButton.setOnAction(this::handleTcpButtonAction);
    }

    public MenuConnectionController(GUIView context){
        this.context = context;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MenuConnectionChoice.fxml"));
        loader.setController(this);
        try {
            Parent root = loader.load();
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRmiButtonAction(ActionEvent event) {
        //avvio la connessione RMI
        System.out.println("Connessione RMI");
        try {

            rmiClient = new RMIClient(); //dovrò poi aggiungere l'indirizzo IP
            context.setClient(rmiClient);
            //passo alla scena di selezione del nickname
            GoToUsername();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di connessione");
            alert.setHeaderText(null);
            alert.setContentText("Errore nella connessione RMI");
            alert.showAndWait();
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void handleTcpButtonAction(ActionEvent event) {
        Text testo = Guifactory.printMessage("TCP non ancora implementato");
        VBox vbox = new VBox();
        vbox.getChildren().add(testo);
        scene.setRoot(vbox);
        context.getStage().setScene(scene);
        context.getStage().show();

    }

    public void GoToUsername(){
        //passo alla scena di selezione del nickname
        MenuNicknameController nicknameController = new MenuNicknameController(context);
        nicknameController.showScene();
    }

    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

}
