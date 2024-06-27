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
     *
     * @param backCorners  the corners of the back side of the card
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

    public ArrayList<CornerEnum> getCornerEnums() {
        ArrayList<CornerEnum> items = new ArrayList<>();
        ArrayList<Corner> corners = getCorners();

        for (int i = 0; i < 4; i++) {
            items.add(corners.get(i).getType());
        }
        return items;
    }

    /**
     * these methods are implemented in order to be able to serialize the card
     * they should not be used in the model or controller
     */
    //getters
    public ArrayList<Corner> getFrontCorners() {return frontCorners;}
    public ArrayList<Corner> getBackCorners() {return backCorners;}
    public String getFrontImage() {return frontImage;}
    public String getBackImage() {return backImage;}

    public ArrayList<String> drawCard() {
        return new ArrayList<>();
    }

    public Condition getPointCondition() {
        return new NoCondition();
    };

}
