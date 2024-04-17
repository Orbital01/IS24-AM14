package it.polimi.ingsw.is24am14.server.model.card;

import java.util.ArrayList;

/**
 * this class is created to add a method to the Card class that will be only used by Gold and Resource cards
 */

public interface PlayableCard{

    /**
     * This method returns the resource of the card
     * @return the resource of the card
     */
    public CornerEnum.ResourceEnum getResource();
}
