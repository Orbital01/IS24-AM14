package it.polimi.ingsw.is24am14.server.model.card;

import java.util.ArrayList;

/**
 * This class represents a Gold Card.
 * A Gold Card is a card that gives points based on a condition or a resource.
 * It also has a condition that must be satisfied in order to place it on the board.
 */

public class GoldCard extends Card {
    private final int points;
    private final Condition pointCondition;
    private final CornerEnum.ResourceEnum resource;

    private final Condition placementCondition;

    public GoldCard(int points, Condition pointCondition, CornerEnum.ResourceEnum resource,
                    Condition placementCondition, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
                    String frontImage, String backImage) {

        super(frontCorners, backCorners, frontImage, backImage);
        this.points = points;
        this.pointCondition = pointCondition;
        this.resource = resource;
        this.placementCondition = placementCondition;

    }

    public int getPoints() {
        return points;
    }

    public Condition getPointCondition() {
        return pointCondition;
    }

    public CornerEnum.ResourceEnum getResource() {
        return resource;
    }

    public Condition getPlacementCondition() {
        return placementCondition;
    }
}
