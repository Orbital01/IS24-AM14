package it.polimi.ingsw.is24am14.model.cardRelated;

import it.polimi.ingsw.is24am14.model.cardRelated.Condition;
import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *  A condition implementation to check whether the player has a certain amount of objects in their game board.
 *  Implements the {@link Condition} interface.
 */
public class ObjectCondition<GameArea, Coordinates> implements Condition {
    ArrayList<CornerEnum.ObjectEnum> listObject;

    public ObjectCondition() {
        listObject = new ArrayList<>();
    }

    /**
     * @param clause the object that is added to the list
     * Adds a clause (object in this case) to the list of clauses (object)
     * that a player must have on his game-board to satisfy the condition
     */
    public void addClause(CornerEnum.ObjectEnum clause) {
        listObject.add(clause);
    }

    /**
     * Checks if the player's game board meets the condition by having the required number of objects.
     * @param board the player's game board that is being checked
     * @return {@code true} if the player's game board has the required number of objects, otherwise {@code false}
     */
    @Override
    public <Coordinates> boolean isSatisfied(HashMap<Coordinates, Card> board) {

        ArrayList<CornerEnum.ObjectEnum> toFind = new ArrayList<>(listObject);

        //  for each card on the board
        for (Map.Entry<Coordinates, Card> entry : board.entrySet())
        {
            //  for each corner of the card
            for (int i = 0; i < 5; i++) {
                //  if there is an overlapped card do nothing
                //  else check for condition
                if (!board.containsKey(Coordinates.newCoordinates(entry.getKey(), i))) {
                    toFind.remove(getCornerEnums(entry.getValue()).get(i));
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
        for (Map.Entry<Coordinates, Card> entry : board)
        {
            //  for each corner of the card
            for (int i = 0; i < 5; i++) {
                //  if there is an overlapped card do nothing
                //  else check for condition
                if (!board.containsKey(Coordinates.newCoordinates(entry.getKey(), i))) {
                    objectsFound++;
                }
            }
        }

        return objectsFound;
    }

    /**
     * @param card the card whose corners-elements are getting returned
     * @return each CornerEnums of the corners
     */
    public ArrayList<CornerEnum> getCornerEnums(Card card) {
        ArrayList<CornerEnum> items = new ArrayList<>();
        ArrayList<Corner> corners = card.getSide() == EnumSide.FRONT ? card.getFrontCorners() : card.getBackCorners();

        for (int i = 0; i < 5; i++) {
            items.add(corners.get(i).getType());
        }
        return items;
    }
}
