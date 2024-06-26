package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.client.GUIViewLauncher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is responsible for managing the menu where the user can enter their nickname.
 */
public class MenuNicknameController {

    @FXML
    TextField usernameField;

    @FXML
    Button confirmButton;

    private GUIViewLauncher context;
    private Scene scene;
    private BorderPane layout = new BorderPane();

    /**
     * Constructor for the MenuNicknameController class.
     * It initializes the scene and the layout of the menu.
     * @param context The context of the GUI view.
     */
    public MenuNicknameController(GUIViewLauncher context, int tries){
        this.context = context;
        scene = new Scene(layout, 1920, 1080);
        Guifactory.setAutomaticBackground(layout);

        //metto il logo
        VBox container = new VBox();
        container.setSpacing(20);
        container.setAlignment(javafx.geometry.Pos.CENTER);
        container.getChildren().add(Guifactory.displayLogo());

        //aggiungo una etichetta sopra il campo di inserimento
        if(tries==0) {
            Label label = Guifactory.printLabel("Inserisci il tuo username", 20);
            container.getChildren().add(label);
        }else{
            Label label = Guifactory.printLabel("Username non valido, riprova", 20);
            container.getChildren().add(label);
        }

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

    /**
     * This method is responsible for showing the scene of the menu.
     */
    public void showScene() {
        Stage stage = context.getStage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is responsible for going to the scene where the user can choose what lobby to join or create.
     */
    public void goToLobbyChoice(){
        //passo alla scena di decisione sulla lobby
        MenuLobbyController lobbyController = new MenuLobbyController(context);
        lobbyController.showScene();
    }

    /**
     * This method is responsible for handling the action of the confirm button.
     * It sets the username and goes to the scene where the user can choose what lobby to join or create.
     * @param event The event of the confirm button.
     */
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
                    MenuNicknameController menuNicknameController = new MenuNicknameController(context, 1);
                    menuNicknameController.showScene();
                }
            }
    }

}
