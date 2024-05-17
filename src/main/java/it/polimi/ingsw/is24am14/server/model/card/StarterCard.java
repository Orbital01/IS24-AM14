package it.polimi.ingsw.is24am14.server.model.card;

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

}
