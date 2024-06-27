package it.polimi.ingsw.is24am14.server.model.card;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a card in the game. Each card has a front and a back side, each side has a maximum of 4 corners.
 * Each corner has a color and a value.
 * The card can be flipped to show the other side.
 * The card has a side that is shown
 * The card is abstract because it is a generic card, there are different types of cards in the game.
 */

public abstract class Card implements Serializable {

    private final ArrayList<Corner> frontCorners;
    private final ArrayList<Corner> backCorners;
    private EnumSide enumSide;
    private final String frontImage;
    private final String backImage;


    /**
     * Constructor
     *
     * @param frontCorners the corners of the front side of the card
     * @param backCorners  the corners of the back side of the card
     * @param frontImage   the image of the front side of the card
     * @param backImage    the image of the back side of the card
     * @throws IllegalArgumentException if the number of corners is greater than 4
     * it is always created in the front side
     */

    public Card(ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, String frontImage, String backImage) {

        if (frontCorners.size() > 4 || backCorners.size() > 4) {
            throw new IllegalArgumentException("Each side of the card can have a maximum of 4 corners");
        }

        this.frontCorners = frontCorners;
        this.backCorners = backCorners;
        this.enumSide = EnumSide.FRONT;

        this.frontImage = frontImage;
        this.backImage = backImage;

    }

    /**
     * This method returns the corners of the card based on the side
     * If the side is FRONT, it returns the front corners, otherwise it returns the back corners
     *
     * @return the corners of the card
     */
    public ArrayList<Corner> getCorners() {
        if (enumSide == EnumSide.FRONT) {
            return frontCorners;
        } else {
            return backCorners;
        }
    }

    /**
     * Retrieves the type of resource associated with the type of the card.
     *
     * @return the type of resource as a {@link CornerEnum.ResourceEnum}, or {@code null} if no type is specified
     */
    public CornerEnum.ResourceEnum getCardType() {
        return null;
    }

    /**
     * This method returns the side of the card
     *
     * @return the side of the card
     */
    public EnumSide getSide() {
        return enumSide;
    }


    /**
     * This method returns the image of the card
     *
     * @return the image of the card
     */
  
    public String getImage() {
        if (enumSide == EnumSide.FRONT) {
            return frontImage;
        } else {
            return backImage;
        }
    }

    /**
     * This method flips the card to show the other side
     */
    public void flipSide() {
        if (enumSide == EnumSide.FRONT) {
            enumSide = EnumSide.BACK;
        } else {
            enumSide = EnumSide.FRONT;
        }
    }

    /**
     * This method returns the types of the corners of the card
     *
     * @return the types of the corners of the card
     */
    public ArrayList<CornerEnum> getCornerEnums() {
        ArrayList<CornerEnum> items = new ArrayList<>();
        ArrayList<Corner> corners = getCorners();

        for (int i = 0; i < 4; i++) {
            items.add(corners.get(i).getType());
        }
        return items;
    }

    /**
     * Retrieves the corners of the front side of the card.
     *
     * @return an {@code ArrayList} of {@link Corner} objects representing the corners of the front side
     */
    public ArrayList<Corner> getFrontCorners() {return frontCorners;}

    /**
     * Retrieves the corners of the back side of the card.
     *
     * @return an {@code ArrayList} of {@link Corner} objects representing the corners of the back side
     */
    public ArrayList<Corner> getBackCorners() {return backCorners;}

    /**
     * Retrieves the image associated with the front side of the card.
     *
     * @return a {@code String} representing the image file path or identifier of the front side image
     */
    public String getFrontImage() {return frontImage;}

    /**
     * Retrieves the image associated with the back side of the card.
     *
     * @return a {@code String} representing the image file path or identifier of the back side image
     */
    public String getBackImage() {return backImage;}


    public ArrayList<String> drawCard() {
        return new ArrayList<>();
    }

    /**
     * Retrieves the point condition associated with the card.
     *
     * @return a {@link Condition} representing the point condition, defaulting to {@link NoCondition} if none is specified
     */
    public Condition getPointCondition() {
        return new NoCondition();
    }

}
