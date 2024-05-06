package it.polimi.ingsw.is24am14.server.model.card;

import java.util.ArrayList;

/**
 * this class is created to add a method to the Card class that will be only used by Gold and Resource cards
 */

public abstract class  PlayableCard extends Card{

    public PlayableCard (ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, String frontImage, String backImage){
        super(frontCorners, backCorners, frontImage, backImage);
    }
    /**
     * This method returns the resource of the card
     * @return the resource of the card
     */
    public abstract CornerEnum.ResourceEnum getResource();
}
