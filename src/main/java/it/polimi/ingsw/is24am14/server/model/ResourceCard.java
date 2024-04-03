package it.polimi.ingsw.is24am14.server.model;
import java.util.ArrayList;

/**
 * This class represents a resource card.
 * A resource card has a number of points and a resource.
 */

public class ResourceCard extends Card{

    private final int points;
    private final CornerEnum.ResourceEnum resource;

    public ResourceCard(int points, CornerEnum.ResourceEnum resource, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners) {
        super(frontCorners, backCorners);
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
