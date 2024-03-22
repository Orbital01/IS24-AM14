package it.polimi.ingsw.is24am14.model.cardRelated;
import java.util.ArrayList;

public abstract class Card {

 private  final ArrayList<Corner> frontCorners;
 private final ArrayList<Corner>backCorners;
 private EnumSide enumSide;

 public Card(ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, EnumSide enumSide) {

     if (frontCorners.size() > 4 || backCorners.size() > 4) {
         throw new IllegalArgumentException("Each side of the card can have a maximum of 4 corners");
     }

   this.frontCorners = frontCorners;
   this.backCorners = backCorners;
   this.enumSide = enumSide;


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
  public void flipSide() {
    if (enumSide == EnumSide.FRONT) {
      enumSide = EnumSide.BACK;
    } else {
      enumSide = EnumSide.FRONT;
    }
  }
}
