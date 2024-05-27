package it.polimi.ingsw.is24am14.server.model.card;

import java.util.ArrayList;

/**
 * This class represents the objective cards in the game.
 * Objective cards are used to gain a certain amount of points.
 * Each objective card has a condition that must be satisfied in order to gain those points
 */

public class ObjectiveCard extends Card {
    private final Condition condition;
    private final int points;

    public ObjectiveCard(Condition condition, String frontImage, String backImage, int points) {

        super(createFakeCorners(), createFakeCorners(), frontImage, backImage);
        this.condition = condition;
        this.points = points;
    }
    /**
     * This method creates the back corners of the objective card
     * @return an array list of 4 hidden corners
     */
    private static ArrayList<Corner> createFakeCorners() {
        return new ArrayList<>();
    }

    public Condition getCondition() {
        return condition;
    }

    public int getPoints() {return points;}
}
