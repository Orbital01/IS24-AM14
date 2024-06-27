package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.client.view.printer.EmojiConverter;
import net.fellbaum.jemoji.Emoji;
import net.fellbaum.jemoji.Emojis;

import java.util.ArrayList;

/**
 * This class represents a resource card.
 * A resource card has a number of points and a resource.
 */

public class ResourceCard extends PlayableCard {

    private final int points;
    private final CornerEnum.ResourceEnum resource;
    private final Condition pointCondition;

    /**
     * Constructs a new ResourceCard with the given parameters.
     *
     * @param points the number of points this card provides
     * @param resource the resource this card provides
     * @param frontCorners the corners on the front of the card
     * @param backCorners the corners on the back of the card
     * @param frontImage the image on the front of the card
     * @param backImage the image on the back of the card
     */
    public ResourceCard(int points, CornerEnum.ResourceEnum resource, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
                        String frontImage, String backImage) {
        super(frontCorners, backCorners, frontImage, backImage);
        this.points = points;
        this.resource = resource;
        this.pointCondition = new NoCondition();
    }

    /**
     * Returns the CornerEnum values of the corners of this card.
     *
     * @return an ArrayList of CornerEnum values
     */
    public ArrayList<CornerEnum> getCornerEnums() {
        ArrayList<CornerEnum> items = new ArrayList<>();
        ArrayList<Corner> corners = getCorners();

        for (int i = 0; i < 4; i++) {
            if (!corners.get(i).isOverlapped()) items.add(corners.get(i).getType());
        }

        if (getSide() == EnumSide.BACK) items.add(getResource());
        return items;
    }

    /**
     * Returns the number of points this card provides.
     *
     * @return the number of points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the resource this card provides.
     *
     * @return the resource
     */
    public CornerEnum.ResourceEnum getResource() {
        return resource;
    }

    public CornerEnum.ResourceEnum getCardType() {
        return resource;
    }


    /**
     * Returns the condition for the points this card provides.
     *
     * @return the point condition
     */
    @Override
    public Condition getPointCondition() {
        return this.pointCondition;
    }

    /**
     * Returns the condition for the placement of this card.
     * @return a NoCondition object
     */
    @Override
    public Condition getPlacementCondition() {
        return new NoCondition();
    }

    /**
     * This method is used to pass an ArrayList strings of the Resource content to the method that will draw it in the TUI
     * @return an ArrayList of strings that represent the resource card
     */
    public ArrayList<String> drawCard() {
        ArrayList<String> cardString = new ArrayList<>();

        if (this.getSide().equals(EnumSide.FRONT)) {

            ArrayList<String> corners;
            corners = EmojiConverter.drawCorners(this);
            ArrayList<String> resource = EmojiConverter.drawResource(this);
            int points = this.getPoints();

            //Each card has 7 rows
            //First Row
            cardString.add("|-----|------------|-----|");
            //Second Row
            String NW = this.getCorners().get(0).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(0);
            String NE = this.getCorners().get(1).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(1);
            cardString.add("| " + NW + " |      " + points + "      | " + NE + " |");
            //Third Row
            cardString.add("|-----|            |-----|");
            //Fourth Row
            cardString.add("|           " + resource.get(0) + "          |");
            //cardString.add("|		");
            //cardString.set(3, cardString.get(3) + "		         |");
            //Fifth Row
            cardString.add("|-----|            |-----|");
            //Sixth Row
            String SW = this.getCorners().get(2).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(2);
            String SE = this.getCorners().get(3).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(3);
            cardString.add("| " + SW + " |" + "             | " + SE + " |");
            //Seventh Row
            cardString.add("|-----|------------|-----|");
        }
        else{
            ArrayList<String> corners = new ArrayList<String>();
            for (int i = 0; i < this.getCorners().size(); i++){
                corners.add(Emojis.WHITE_MEDIUM_SQUARE.getEmoji());
            }
            ArrayList<String> resource = EmojiConverter.drawResource(this);


            //Each card has 7 rows
            //First Row
            cardString.add("|-----|------------|-----|");
            //Second Row
            String NW = this.getCorners().get(0).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(0);
            String NE = this.getCorners().get(1).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(1);
            cardString.add("| " + NW + " |         " + "    | " + NE + " |");
            //Third Row
            cardString.add("|-----|		       |-----|");
            //Fourth Row
            cardString.add("|           " + resource.get(0) + "          |");
            //cardString.add("|		");
            //cardString.set(3, cardString.get(3) + "		         |");
            //Fifth Row
            cardString.add("|-----|		       |-----|");
            //Sixth Row
            String SW = this.getCorners().get(2).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(2);
            String SE = this.getCorners().get(3).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(3);
            cardString.add("| " + SW + " |" + "             | " + SE + " |");
            //Seventh Row
            cardString.add("|-----|------------|-----|");
        }
        return cardString;
    }
}
