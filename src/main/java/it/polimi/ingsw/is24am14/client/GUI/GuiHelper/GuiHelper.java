package it.polimi.ingsw.is24am14.client.GUI.GuiHelper;

import it.polimi.ingsw.is24am14.server.model.card.Deck;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.network.Message;
import it.polimi.ingsw.is24am14.client.GUIViewLauncher;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;

/**
 * This class provides helper methods for the GUI game controller.

 */
public class GuiHelper {

    private int lastMessageIndex=0;

    /**
     * Determines the winner of the game based on the highest score.
     *
     * @param game The game to determine the winner of.
     * @return winner The player with the highest score.
     */
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

    /**
     * Updates the messages in the message area.
     * it retrieves the messages from the game context and appends them to the message area.
     * It also updates the lastMessageIndex to keep track of the last message displayed.
     *
     * @param messageArea The TextArea to update.
     * @param context The GUIViewLauncher context.
     */
    public void updateMessages(TextArea messageArea, GUIViewLauncher context) {
        ArrayList<Message> messaggi;
        String user;
        try {
            messaggi = context.getClient().getGameContext().getMessages();
            user = context.getClient().getUsername();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = this.lastMessageIndex; i < messaggi.size(); i++) {
            Message message = messaggi.get(i);
            if(message.getReceiver().equals("")){
                messageArea.appendText(message.getSender() + ": " + message.getMessage() + "\n");
                this.lastMessageIndex++;
            }else if (message.getReceiver().equals(user)) {
                messageArea.appendText("private message from " + message.getSender() + ": " + message.getMessage() + "\n");
                this.lastMessageIndex++;
            }
        }
    }

    /**
     * Gets the point board as a StackPane.
     * it uses the PointBoardController to create the point board.
     * It retrieves the token images and scores from the game.
     * It returns the point board as a StackPane.
     *
     * @param game The game to get the point board of.
     * @return The point board as a StackPane.
     */
    public StackPane getPointBoard(Game game){
        ArrayList<Image> tokenImages = new ArrayList<>();
        ArrayList<Integer> scores = new ArrayList<>();

        for(Player player : game.getPlayers()){

            Image image = new Image(PointBoardController.class.getResource("/images.tokens/" + player.getColour().toString().toLowerCase() +"_token.png").toExternalForm());
            tokenImages.add(image);
            scores.add(player.getScore());
        }
        return PointBoardController.getPointBoardStackPane(tokenImages, scores);
    }

    /**
     * Gets the common objectives from the game context.
     *
     * @param context The GUIViewLauncher context.
     * @return The common objectives as an ArrayList.
     */
    public ArrayList<ObjectiveCard> getCommonObjectives(GUIViewLauncher context) {

        ArrayList<ObjectiveCard> commonObjectivesList;

        try {
            commonObjectivesList = context.getClient().getGameContext().getGame().getCommonObjective();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return commonObjectivesList;
    }

}
