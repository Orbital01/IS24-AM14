package it.polimi.ingsw.is24am14.server.model.card;

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

    public GoldCard(int points, Condition pointCondition, CornerEnum.ResourceEnum resource,
                    ResourceCondition placementCondition, ArrayList<Corner> frontCorners,
                    String frontImage, String backImage) {

        super(frontCorners, createBackCorners(), frontImage, backImage);
        this.points = points;
        this.pointCondition = pointCondition;
        this.resource = resource;
        this.placementCondition = placementCondition;

    }

    public static ArrayList<Corner> createBackCorners() {
        ArrayList<Corner> backCorners = new ArrayList<>();
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        return backCorners;
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
