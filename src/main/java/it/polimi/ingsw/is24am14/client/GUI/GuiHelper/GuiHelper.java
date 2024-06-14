package it.polimi.ingsw.is24am14.client.GUI.GuiHelper;

import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GuiHelper {

    public static Player getWinner(Game game){
        Player winner = null;
        int max = 0;
        for(Player player : game.getPlayers()){
            if(player.getScore() > max){
                max = player.getScore();
                winner = player;
            }
        }
        return winner;
    }

//    public static VBox updateChat(GUIView context){
//        //aggiorno l'array contenenete i messaggi
//        ArrayList<String> messages = context.getClient().getGameContext().getChat().updateChat(); //in attesa di stef
//        //creo il Vbox che conterrà i messaggi
//        VBox chat = new VBox();
//        chat.setAlignment(Pos.TOP_RIGHT);
//        //aggiungo uno sfondo
//        chat.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
//        chat.setPrefSize(300, 300);
//        //aggiungo i messaggi al Vbox
//        for(String message : messages){
//
//        }
//        return chat;
//    }


}
