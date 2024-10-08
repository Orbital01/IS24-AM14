package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.client.view.printer.EmojiConverter;
import net.fellbaum.jemoji.Emoji;

import java.util.ArrayList;

/**
 * This class represents the objective cards in the game.
 * Objective cards are used to gain a certain amount of points.
 * Each objective card has a condition that must be satisfied in order to gain those points
 */

public class ObjectiveCard extends Card {
    private final Condition condition;
    private final int points;

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

    /**
     * This method is used to pass an ArrayList strings of the ObjectiveCard content to the method that will draw it in the TUI
     * @return an ArrayList of strings that represent the objective card
     */
    public ArrayList<String> drawCard() {
        ArrayList<String> cardString = new ArrayList<>();
        if (this.getCondition().toString().equals("ObjectCondition") || this.getCondition().toString().equals("ResourceCondition")){
            ArrayList<String> corners;
            corners = EmojiConverter.drawCorners(this);

            EmojiConverter conditionConverter = new EmojiConverter();
            ArrayList<String> condition = conditionConverter.drawCondition(this.getCondition());
            int points = this.getPoints();

            //Each card has 7 rows
            //First Row
            cardString.add("|------------------------|");
            //Second Row
            cardString.add("|            " + points + "           |");
            //Third Row
            cardString.add("|                        |");
            //Fourth Row
            cardString.add("|       ");
//            for (String e : condition) {
//                cardString.set(3, cardString.get(3) + e);
//            }
//            cardString.set(3, cardString.get(3) + "		 |");
            for (String e : condition){
                cardString.set(3, cardString.get(3) + e);
            }
            cardString.set(3, cardString.get(3) + "       |");
            //Fifth Row
            cardString.add("|                        |");
            //Sixth Row
            cardString.add("|                        |");
            //Seventh Row
            cardString.add("|------------------------|");

            return cardString;
        }
        else if (this.getCondition().toString().equals("CardCondition")){
            int points = this.getPoints();

            EmojiConverter conditionConverter = new EmojiConverter();
            ArrayList<ArrayList<String>> cardCondition = conditionConverter.drawCardCondition(this.getCondition());

            cardString.add("|------------------------|");
            cardString.add("|            "  + points +  "           |");

            cardString.add("|         ");
            int i = 0;
            for (String e : cardCondition.get(0)) {
                cardString.set(2, cardString.get(2) + e);
            }
            cardString.set(2, cardString.get(2) + "      |");

            cardString.add("|         ");
            for (String e : cardCondition.get(1)) {
                cardString.set(3, cardString.get(3) + e);
            }
            cardString.set(3, cardString.get(3) + "      |");

            cardString.add("|         ");
            for (String e : cardCondition.get(2)) {
                cardString.set(4, cardString.get(4) + e);
            }
            cardString.set(4, cardString.get(4) + "      |");

            cardString.add("|                        |");
            cardString.add("|------------------------|");
        }
        return cardString;
    }
}
