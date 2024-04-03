package it.polimi.ingsw.is24am14.server.model;
import java.util.ArrayList;

/**
 * This class represents a card in the game. Each card has a front and a back side, each side has a maximum of 4 corners.
 * Each corner has a color and a value.
 * The card can be flipped to show the other side.
 * The card has a side that is shown
 * The card is abstract because it is a generic card, there are different types of cards in the game.
 */

public abstract class Card {

 private  final ArrayList<Corner> frontCorners;
 private final ArrayList<Corner>backCorners;
 private EnumSide enumSide;

    /**
    * Constructor
    * @param frontCorners the corners of the front side of the card
    * @param backCorners the corners of the back side of the card
    * @throws IllegalArgumentException if the number of corners is greater than 4
     * it is always created in the front side
    */
 public Card(ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners) {

     if (frontCorners.size() > 4 || backCorners.size() > 4) {
         throw new IllegalArgumentException("Each side of the card can have a maximum of 4 corners");
     }

   this.frontCorners = frontCorners;
   this.backCorners = backCorners;
   this.enumSide = EnumSide.FRONT;


 }
 public ArrayList<Corner> getFrontCorners() {
   return frontCorners;
  }
public ArrayList<Corner> getBackCorners() {
    return backCorners;
  }
public EnumSide getSide() {
    return enumSide;
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
