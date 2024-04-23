package it.polimi.ingsw.is24am14.server.model.card;

import java.util.ArrayList;

/**
 * This class represents the objective cards in the game.
 * Objective cards are used to gain a certain amount of points.
 * Each objective card has a condition that must be satisfied in order to gain those points
 */

public class ObjectiveCard extends Card {
    private final Condition condition;
    private int points;

    public ObjectiveCard(Condition condition, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
                         String frontImage, String backImage, int points) {
        super(frontCorners, backCorners, frontImage, backImage);
        this.condition = condition;
        this.points = points;
    }

    public Condition getCondition() {
        return condition;
    }

    public int getPoints() {return points;}
}
