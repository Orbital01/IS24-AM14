package it.polimi.ingsw.is24am14.model.cardRelated;
import java.util.ArrayList;

public class GoldCard extends Card {
    private final int points;
    private final Condition pointCondition;
    private final CornerEnum.ResourceEnum resource;

    private final Condition placementCondition;

    public GoldCard(int points, Condition pointCondition, CornerEnum.ResourceEnum resource, Condition placementCondition, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, EnumSide enumSide) {
        super(frontCorners, backCorners, enumSide);
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
