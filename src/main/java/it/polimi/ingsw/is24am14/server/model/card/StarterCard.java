package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.client.view.printer.EmojiConverter;
import net.fellbaum.jemoji.Emoji;
import net.fellbaum.jemoji.Emojis;

import java.util.ArrayList;

/**
 * This class represents the starter card of the game.
 * it is the first card that the player will have on its board.
 * It has a list of resources that the player will have at the beginning of the game.
 */

public class StarterCard extends Card {

    private final ArrayList<CornerEnum.ResourceEnum> resources;

    public StarterCard(ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, ArrayList<CornerEnum.ResourceEnum> resources,
                       String frontImage, String backImage) {
        super(frontCorners, backCorners, frontImage, backImage);
        this.resources = resources;
    }

    public ArrayList<CornerEnum.ResourceEnum> getResources() {
        return resources;
    }

    public ArrayList<String> drawCard() {
        if (this.getSide().equals(EnumSide.FRONT)) {
            ArrayList<String> cardString = new ArrayList<>();

            ArrayList<String> corners;
            corners = EmojiConverter.drawCorners(this);
            ArrayList<String> resources = EmojiConverter.drawResource(this);

            //Each card has 7 rows
            //First Row
            cardString.add("|-----|------------|-----|");
            //Second Row
            cardString.add("| " + corners.get(0) + " |" + "             | " + corners.get(1) + " |");
            //Third Row
            cardString.add("|-----|		       |-----|");
            //Fourth Row
            cardString.add("|		");
            cardString.set(3, cardString.get(3) + "		         |");
            //Fifth Row
            cardString.add("|-----|		       |-----|");
            //Sixth Row
            cardString.add("| " + corners.get(2) + " |" + "             | " + corners.get(3) + " |");
            //Seventh Row
            cardString.add("|-----|------------|-----|");

            return cardString;
        }
        else{ //Card is played on the BACK side
            ArrayList<String> cardString = new ArrayList<>();

            ArrayList<String> corners = new ArrayList<String>();
            for (int i = 0; i < this.getCorners().size(); i++){
                corners.add(Emojis.WHITE_MEDIUM_SQUARE.getEmoji());
            }
            ArrayList<String> resources = EmojiConverter.drawResource(this);

            //Each card has 7 rows
            //First Row
            cardString.add("|-----|------------|-----|");
            //Second Row
            cardString.add("| " + corners.get(0) + " |" + "             | " + corners.get(1) + " |");
            //Third Row
            cardString.add("|-----|		       |-----|");
            //Fourth Row
            cardString.add("|		");
            for (String e : resources) {
                cardString.set(3, cardString.get(3) + e);
            }
            cardString.set(3, cardString.get(3) + "		         |");
            //Fifth Row
            cardString.add("|-----|		       |-----|");
            //Sixth Row
            cardString.add("| " + corners.get(2) + " |" + "             | " + corners.get(3) + " |");
            //Seventh Row
            cardString.add("|-----|------------|-----|");

            return cardString;
        }

    }

}
