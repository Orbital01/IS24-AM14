package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.client.GUIViewLauncher;
import it.polimi.ingsw.is24am14.server.network.ClientInterface;
import it.polimi.ingsw.is24am14.server.network.RMIClient;
import it.polimi.ingsw.is24am14.server.network.SocketClient;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuConnectionController {

    private GUIViewLauncher context;
    private Scene scene;
    private ClientInterface client;
    private BorderPane layout = new BorderPane();

    /**
     * Constructor for the MenuConnectionController class.
     * It initializes the GUI elements and sets up the event handlers.
     *
     * @param context The GUIViewLauncher context.
     */
    public MenuConnectionController(GUIViewLauncher context){
        this.context = context;
        scene = new Scene(layout, 1920, 1080);
        Guifactory.setAutomaticBackground(layout);

        VBox container = new VBox();
        container.setSpacing(20);
        container.setAlignment(javafx.geometry.Pos.CENTER);
        //aggiungo l'immagine del logo
        container.getChildren().add(Guifactory.displayLogo());
        //aggiungo i due bottoni per la connessione
        Button rmiButton = Guifactory.createButton("Connessione RMI", 200, 50);
        rmiButton.setOnAction(this::handleRmiButtonAction);

        Button tcpButton = Guifactory.createButton("Connessione TCP", 200, 50);
        tcpButton.setOnAction(this::handleTcpButtonAction);

        HBox buttonContainer = new HBox();
        buttonContainer.setSpacing(20);
        buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);
        buttonContainer.getChildren().addAll(rmiButton, tcpButton);

        container.getChildren().add(buttonContainer);

        layout.setCenter(container);
    }

    /**
     * Handles the action of the RMI button.
     * It tries to establish an RMI connection and handles any exceptions that might occur.
     *
     * @param event The ActionEvent object.
     */
    private void handleRmiButtonAction(ActionEvent event) {
        //avvio la connessione RMI
        System.out.println("Connessione RMI");
        try {

            client = new RMIClient(); //dovrò poi aggiungere l'indirizzo IP
            context.setClient(client);
            //passo alla scena di selezione del nickname
            GoToUsername();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di connessione");
            alert.setHeaderText(null);
            alert.setContentText("Errore nella connessione RMI");
            alert.showAndWait();
            System.exit(1);
        }
    }

    /**
     * Handles the action of the TCP button.
     * It tries to establish a TCP connection and handles any exceptions that might occur.
     *
     * @param event The ActionEvent object.
     */
    private void handleTcpButtonAction(ActionEvent event) {
        System.out.println("Connessione TCP");
        try {

            client = new SocketClient(); //dovrò poi aggiungere l'indirizzo IP
            context.setClient(client);
            //passo alla scena di selezione del nickname
            GoToUsername();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di connessione");
            alert.setHeaderText(null);
            alert.setContentText("Errore nella connessione TCP");
            alert.showAndWait();
            System.exit(1);
        }
    }

    /**
     * Passes to the nickname selection scene.
     */
    public void GoToUsername(){
        //passo alla scena di selezione del nickname
        MenuNicknameController nicknameController = new MenuNicknameController(context, 0);
        nicknameController.showScene();
    }

    /**
     * Shows the scene.
     */
    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

}
