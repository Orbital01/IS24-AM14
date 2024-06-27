package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.util.Map;

public class CornerCondition implements Condition {

    /**
     * Checks if the condition is satisfied on the game board.
     * The condition is satisfied if there is at least one corner that meets the condition.
     * @param board the player's game board that is being checked
     * @return {@code true} if the condition is satisfied, otherwise {@code false}
     */
    @Override
    public boolean isSatisfied(GameArea board) {
        return numSatisfied(board) > 0;
    }

    /**
     * Returns the number of corners that satisfy the condition on the game board.
     * @param board the player's game board that is being checked
     * @return the number of corners that satisfy the condition
     */
    @Override
    public int numSatisfied(GameArea board) {
        int num = 0;
        Coordinates tmpCoordinates;
        Coordinates coordinates = getCoordinates(board);

        if (coordinates == null) throw new IllegalArgumentException("Corner condition null");
        //  top left
        tmpCoordinates = Coordinates.newCoordinates(coordinates, 0);
        if (board.getCard(tmpCoordinates) != null && board.getCard(tmpCoordinates).getCorners().get(3).isOverlapped()) {
            num++;
        }

        //  top right
        tmpCoordinates = Coordinates.newCoordinates(coordinates, 1);
        if (board.getCard(tmpCoordinates) != null && board.getCard(tmpCoordinates).getCorners().get(2).isOverlapped()) {
            num++;
        }

        //  bottom left
        tmpCoordinates = Coordinates.newCoordinates(coordinates, 2);
        if (board.getCard(tmpCoordinates) != null && board.getCard(tmpCoordinates).getCorners().get(1).isOverlapped()) {
            num++;
        }

        //  bottom right
        tmpCoordinates = Coordinates.newCoordinates(coordinates, 3);
        if (board.getCard(tmpCoordinates) != null && board.getCard(tmpCoordinates).getCorners().get(0).isOverlapped()) {
            num++;
        }

        return num;
    }
    /**
     * Returns a string representation of the CornerCondition object.
     * @return a string representation of the CornerCondition object
     */
    @Override
    public String toString() {
        return "CornerCondition";
    }

    /**
     * Returns the coordinates of the card that has this corner condition.
     * @param board the player's game board that is being checked
     * @return the coordinates of the card that has this corner condition, or null if no such card exists
     */
    public Coordinates getCoordinates(GameArea board) {
        for (Map.Entry<Coordinates, Card> entry : board.getBoard().entrySet()) {
            if (entry.getValue().getPointCondition() == this) return entry.getKey();
        }
        return null;
    }
}
