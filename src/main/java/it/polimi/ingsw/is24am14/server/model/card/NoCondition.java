package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;

public class NoCondition implements Condition{

    /**
     * Checks if the condition is satisfied on the game board.
     * As this is a NoCondition, it always returns true.
     * @param board the player's game board that is being checked
     * @return {@code true} as the condition is always satisfied
     */
    @Override
    public boolean isSatisfied(GameArea board){
        return true;
    }
    /**
     * Returns the number of conditions that are satisfied on the game board.
     * As this is a NoCondition, it always returns 1.
     * @param board the player's game board that is being checked
     * @return 1 as the condition is always satisfied
     */
    @Override
    public int numSatisfied(GameArea board){
        return 1;
    }
    /**
     * Returns a string representation of the NoCondition object.
     * @return a string representation of the NoCondition object
     */
    @Override
    public String toString() {
        return "NoCondition";
    }
}
