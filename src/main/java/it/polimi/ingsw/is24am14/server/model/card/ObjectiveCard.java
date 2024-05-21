package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.client.printer.EmojiConverter;
import net.fellbaum.jemoji.Emoji;

import java.util.ArrayList;

/**
 * This class represents the objective cards in the game.
 * Objective cards are used to gain a certain amount of points.
 * Each objective card has a condition that must be satisfied in order to gain those points
 */

public class ObjectiveCard extends Card {
    private final Condition condition;
    private int points;

    public ObjectiveCard(Condition condition, String frontImage, String backImage, int points) {

        super(createFakeCorners(), createFakeCorners(), frontImage, backImage);
        this.condition = condition;
        this.points = points;
    }
    /**
     * This method creates the back corners of the objective card
     * @return an array list of 4 hidden corners
     */
    private static ArrayList<Corner> createFakeCorners() {
        return new ArrayList<>();
    }

    public Condition getCondition() {
        return condition;
    }

    public int getPoints() {return points;}

    public ArrayList<String> drawCard() {
        ArrayList<String> cardString = new ArrayList<>();

        ArrayList<Emoji> corners;
        corners = EmojiConverter.drawCorners(this);
        ArrayList<Emoji> condition = EmojiConverter.drawCondition(this.getCondition());
        int points = this.getPoints();

        //Each card has 7 rows
        //First Row
        cardString.add("|------------------------|");
        //Second Row
        cardString.add("|            "  + points +  "           |");
        //Third Row
        cardString.add("|                        |");
        //Fourth Row
        cardString.add("|		  ");
        for (Emoji e : condition) {
            cardString.set(3, cardString.get(3) + e.getEmoji());
        }
        cardString.set(3, cardString.get(3) + "		 |");
        //Fifth Row
        cardString.add("|                        |");
        //Sixth Row
        cardString.add("|                        |");
        //Seventh Row
        cardString.add("|------------------------|");

        return cardString;

    }
}
