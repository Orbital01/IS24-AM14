package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.client.view.printer.EmojiConverter;
import net.fellbaum.jemoji.Emoji;
import net.fellbaum.jemoji.Emojis;

import java.util.ArrayList;

/**
 * This class represents a Gold Card.
 * A Gold Card is a card that gives points based on a condition or a resource.
 * It also has a condition that must be satisfied in order to place it on the board.
 */

public class GoldCard extends PlayableCard {
    private final int points;
    private final Condition pointCondition;
    private final CornerEnum.ResourceEnum resource;

    private final ResourceCondition placementCondition;

    /**
     * Constructs a new GoldCard with the given properties.
     *
     * @param points The number of points the card gives when the pointCondition is met.
     * @param pointCondition The condition that needs to be met for the card to give points.
     * @param resource The resource type of the card.
     * @param placementCondition The condition that needs to be met for the card to be placed on the board.
     * @param frontCorners The corners on the front side of the card.
     * @param frontImage The image on the front side of the card.
     * @param backImage The image on the back side of the card.
     */
    public GoldCard(int points, Condition pointCondition, CornerEnum.ResourceEnum resource,
                    ResourceCondition placementCondition, ArrayList<Corner> frontCorners,
                    String frontImage, String backImage) {

        super(frontCorners, createBackCorners(), frontImage, backImage);
        this.points = points;
        this.pointCondition = pointCondition;
        this.resource = resource;
        this.placementCondition = placementCondition;

    }

    /**
     * Creates the corners for the back side of the card.
     *
     * @return An ArrayList of Corners for the back side of the card.
     */
    public static ArrayList<Corner> createBackCorners() {
        ArrayList<Corner> backCorners = new ArrayList<>();
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        return backCorners;
    }
    /**
     * Returns the points of the card.
     * @return The points of the card.
     */
    public int getPoints() {
        return points;
    }
    /**
     * Returns the point condition of the card.
     * @return The point condition of the card.
     */
    public Condition getPointCondition() {
        return pointCondition;
    }

    /**
     * Returns the resource of the card.
     * @return The resource of the card.
     */
    public CornerEnum.ResourceEnum getResource() {
        return resource;
    }

    /**
     * Returns the CornerEnum values of the card.
     * @return An ArrayList of CornerEnum values of the card.
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
     * Returns the placement condition of the card.
     *
     * @return The placement condition of the card.
     */
    public ResourceCondition getPlacementCondition() {
        return placementCondition;
    }

    /**
     * This method is used to pass an ArrayList strings of the GoldCard content to the method that will draw it in the TUI
     * @return an ArrayList of strings that represent the gold card
     */
    public ArrayList<String> drawCard() {
        ArrayList<String> cardString = new ArrayList<>();

        if (this.getSide().equals(EnumSide.FRONT)){
            ArrayList<String> corners;
            corners = EmojiConverter.drawCorners(this);
            ArrayList<String> resource = EmojiConverter.drawResource(this);
            ArrayList<String> plaecementCondition = EmojiConverter.drawPlacementCondition(this);
            int points = this.getPoints();
            ArrayList<String> pointCondition = EmojiConverter.drawCondition(this.getPointCondition());

            //Each card has 7 rows
            //First Row
            cardString.add("|-----|------------|-----|");
            //Second Row
            String NW = this.getCorners().get(0).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(0);
            String NE = this.getCorners().get(1).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(1);
            cardString.add("| " + NW + " |      " + points + pointCondition.get(0) + "    | " + NE + " |");
            //Third Row
            cardString.add("|-----|		       |-----|");
            //Fourth Row
            cardString.add("|		    " + resource.get(0) + "		     |");
            //Fifth Row
            cardString.add("|-----|		       |-----|");
            //Sixth Row
            String SW = this.getCorners().get(2).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(2);
            String SE = this.getCorners().get(3).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(3);
            cardString.add("| " + SW + " | ");
            for (String e : plaecementCondition) {
                cardString.set(5, cardString.get(5) + e);
            }
            cardString.set(5, cardString.get(5) + " | " + SE + " |");
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
            cardString.add("|		    " + resource.get(0) + "		     |");
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
