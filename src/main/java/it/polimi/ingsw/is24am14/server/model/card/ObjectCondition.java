package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.util.ArrayList;
import java.util.Map;


/**
 *  A condition implementation to check whether the player has a certain amount of objects in their game board.
 *  Implements the {@link Condition} interface.
 */
public class ObjectCondition implements Condition {
    private final ArrayList<CornerEnum.ObjectEnum> listObject;

    public ObjectCondition() {
        listObject = new ArrayList<>();
    }

    /**
     * @param clause the object that is added to the list
     * Adds a clause (object in this case) to the list of clauses (object)
     * that a player must have on his game-board to satisfy the condition
     */
    public void addClause(CornerEnum.ObjectEnum clause) throws NullPointerException {
        if (clause == null) {throw new NullPointerException(); }
        listObject.add(clause);
    }

    /**
     * Checks if the player's game board meets the condition by having the required number of objects.
     * @param board the player's game board that is being checked
     * @return {@code true} if the player's game board has the required number of objects, otherwise {@code false}
     */
    @Override
    public boolean isSatisfied(GameArea board) throws NullPointerException {
        if (board == null) {throw new NullPointerException(); }
        ArrayList<CornerEnum.ObjectEnum> toFind = new ArrayList<>(listObject);

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
        }

        return toFind.isEmpty();
    }

    /**
     * @param board the player's game board that is being checked
     * @return the number of objects on the game-board that satisfies the condition
     */
    public int numObjects(GameArea board) {
        CornerEnum.ObjectEnum toFind = listObject.getFirst();
        int objectsFound = 0;

        //  for each card on the board
        for (Map.Entry<Coordinates, Card> entry : board.getBoard().entrySet())
        {
            //  for each corner of the card
            for (int i = 0; i < 4; i++) {
                //  if there is an overlapped card do nothing
                //  else check for condition
                if (!entry.getValue().getCorners().get(i).isOverlapped() && entry.getValue().getCorners().get(i).getType() == toFind) {
                    objectsFound++;
                }
            }
        }

        return objectsFound;
    }

    /**
     * these methods are used to serialize the object condition
     */
    public ArrayList<CornerEnum.ObjectEnum> getListObject() {
        return listObject;
    }

}
