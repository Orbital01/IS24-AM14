package it.polimi.ingsw.is24am14.server.model.card;

import java.util.ArrayList;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class represents a card in the game. Each card has a front and a back side, each side has a maximum of 4 corners.
 * Each corner has a color and a value.
 * The card can be flipped to show the other side.
 * The card has a side that is shown
 * The card is abstract because it is a generic card, there are different types of cards in the game.
 */

public abstract class Card {

    private final ArrayList<Corner> frontCorners;
    private final ArrayList<Corner> backCorners;
    private EnumSide enumSide;
    private Image frontImage;
    private Image backImage;


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

        try {
            this.frontImage = new Image(new FileInputStream(frontImage));
            this.backImage = new Image(new FileInputStream(backImage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
  
    public Image getImage() {
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
}
