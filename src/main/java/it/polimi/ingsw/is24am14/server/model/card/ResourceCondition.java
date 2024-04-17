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

        //  for each card on the board
        for (Map.Entry<Coordinates, Card> entry : board.getBoard().entrySet())
        {
            //  for each corner of the card
            for (int i = 0; i < 4; i++) {
                //  if there is an overlapped card do nothing
                //  else check for condition
                if (!entry.getValue().getCorners().get(i).isOverlapped()) {
                    toFind.remove(entry.getValue().getCornerEnums().get(i));
                }
            }
            //  if resource card
            if (entry.getValue().getCornerEnums().size() > 3) toFind.remove(entry.getValue().getCornerEnums().get(4));
        }

        return toFind.isEmpty();
    }
}
