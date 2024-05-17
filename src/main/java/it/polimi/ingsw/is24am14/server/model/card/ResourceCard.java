package it.polimi.ingsw.is24am14.server.model.card;

import java.util.ArrayList;

/**
 * This class represents a resource card.
 * A resource card has a number of points and a resource.
 */

public class ResourceCard extends PlayableCard {

    private final int points;
    private final CornerEnum.ResourceEnum resource;

    public ResourceCard(int points, CornerEnum.ResourceEnum resource, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
                        String frontImage, String backImage) {
        super(frontCorners, backCorners, frontImage, backImage);
        this.points = points;
        this.resource = resource;
    }

    public ArrayList<CornerEnum> getCornerEnums() {
        ArrayList<CornerEnum> items = new ArrayList<>();
        ArrayList<Corner> corners = getCorners();

        for (int i = 0; i < 4; i++) {
            items.add(corners.get(i).getType());
        }

        if (getSide() == EnumSide.FRONT) items.add(getResource());
        return items;
    }

    public int getPoints() {
        return points;
    }

    public CornerEnum.ResourceEnum getResource() {
        return resource;
    }
}
