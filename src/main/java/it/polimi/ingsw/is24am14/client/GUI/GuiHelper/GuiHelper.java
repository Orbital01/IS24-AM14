package it.polimi.ingsw.is24am14.client.GUI.GuiHelper;

import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.network.Message;
import it.polimi.ingsw.is24am14.server.view.GUIView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GuiHelper {

    private int lastMessageIndex=0;

    public Player getWinner(Game game){
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

    public void updateMessages(TextArea messageArea, GUIView context) {
        ArrayList<Message> messaggi;

        try {
            messaggi = context.getClient().getGameContext().getMessages();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = this.lastMessageIndex; i < messaggi.size(); i++) {
            Message message = messaggi.get(i);
            if(message.getReceiver().equals("")){
                messageArea.appendText(message.getSender() + ": " + message.getMessage() + "\n");
                this.lastMessageIndex++;
            }else {
                messageArea.appendText("private message from " + message.getSender() + ": " + message.getMessage() + "\n");
                this.lastMessageIndex++;
            }
        }
    }
}
