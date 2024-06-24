package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.util.ArrayList;
import java.util.Map;

/**
 *  A condition implementation to check whether the player has a certain amount of resources in their game board.
 *  Implements the {@link Condition} interface.
 */
public class ResourceCondition implements Condition {
    private final ArrayList<CornerEnum.ResourceEnum> listResource;

    public ResourceCondition () {
        listResource = new ArrayList<>();
    }

    /**
     * @param clause the resource that is added to the list
     * Adds a clause (resource in this case) to the list of clauses (resource)
     * that a player must have on his game-board to satisfy the condition
     */
    public void addClause (CornerEnum.ResourceEnum clause) throws NullPointerException {
        if (clause == null) throw new NullPointerException();
        listResource.add(clause);
    }

    /**
     * Checks if the player's game board meets the condition by having the required number of resources.
     * @param board the player's game board that is being checked
     * @return {@code true} if the player's game board has the required number of resources, otherwise {@code false}
     */
    @Override
    public boolean isSatisfied(GameArea board) {
        ArrayList<CornerEnum.ResourceEnum> toFind = new ArrayList<>(listResource);
        System.out.println("To find:");
        for (CornerEnum.ResourceEnum clause : listResource) {
            System.out.println(clause);
        }
        System.out.println("Removing:");

        //  for each card on the board
        for (Map.Entry<Coordinates, Card> entry : board.getBoard().entrySet())
        {
            System.out.println("ROW: " + entry.getKey().getRow() + "COL: " + entry.getKey().getColumn());
            for (CornerEnum cornerEnum : entry.getValue().getCornerEnums()) {
                System.out.println(cornerEnum);
                toFind.remove(cornerEnum);
            }
        }

        return toFind.isEmpty();
    }
    /**
     * these methods are ONLY used to serialize the class
     */
    public ArrayList<CornerEnum.ResourceEnum> getListResource() {
        return listResource;
    }

    @Override
    public int numSatisfied(GameArea board) {
        return isSatisfied(board) ? 1 : 0;
    }

    @Override
    public String toString() {
        return "ResourceCondition";
    }
}
