package it.polimi.ingsw.is24am14.model.cardRelated;
import java.util.ArrayList;

public class ResourceCard extends Card{

    private final int points;
    private final CornerEnum.ResourceEnum resource;

    public ResourceCard(int points, CornerEnum.ResourceEnum resource, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, EnumSide enumSide) {
        super(frontCorners, backCorners, enumSide);
        this.points = points;
        this.resource = resource;
    }

    public int getPoints() {
        return points;
    }

    public CornerEnum.ResourceEnum getResource() {
        return resource;
    }
}
