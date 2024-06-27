package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  A condition implementation to check whether the player has a certain pattern of cards in their game board.
 *  Implements the {@link Condition} interface.
 */
public class CardCondition implements Condition {
    private final LinkedHashMap<Coordinates, CornerEnum.ResourceEnum> listCard;

    /**
     * Constructor for CardCondition class.
     * Initializes the listCard map.
     */
    public CardCondition() {
        listCard = new LinkedHashMap<>();
    }

    /**
     * Adds a clause to the condition.
     * @param clauseCoordinates the position of the card in the pattern.
     * @param clauseResource the type of the card in the pattern.
     * @throws NullPointerException if clauseCoordinates or clauseResource is null
     */
    public void addClause(Coordinates clauseCoordinates, CornerEnum.ResourceEnum clauseResource) throws NullPointerException {
        if (clauseCoordinates == null || clauseResource == null) throw new NullPointerException();
        listCard.put(clauseCoordinates, clauseResource);
    }

    /**
     * Checks if the player's game board meets the condition by having the required pattern of cards.
     * @param board the player's game board that is being checked
     * @return {@code true} if the player's game board has the required pattern of cards, otherwise {@code false}
     */
    //  Not working
    @Override
    public boolean isSatisfied(GameArea board) {

        int listCardIndex;
        boolean satisfied;
        ArrayList<Coordinates> listCardCoordinates = new ArrayList<>(listCard.keySet());
        ArrayList<CornerEnum.ResourceEnum> listCardResources = new ArrayList<>(listCard.values());

        //  for each card on the board
        for (Map.Entry<Coordinates, Card> entry : board.getBoard().entrySet()) {
            listCardIndex = 0;
            satisfied = true;

            Coordinates coordinatesImOn = entry.getKey();
            while (listCardIndex < listCard.size() && satisfied) {
                Coordinates toCheck = Coordinates.add(coordinatesImOn, listCardCoordinates.get(listCardIndex));
                CornerEnum.ResourceEnum toCheckResource = listCardResources.get(listCardIndex);

                if (board.getCard(toCheck) == null || board.getCard(toCheck).getCardType() == null || !board.getCard(toCheck).getCardType().equals(toCheckResource)) satisfied = false;
                listCardIndex++;
            }
            if (satisfied) return true;
        }


        return false;
    }

    /**
     * Calculates the number of times the condition is satisfied on the given game board.
     *
     * @param board the {@link GameArea} representing the game board to evaluate
     * @return {@code 1} if the condition is satisfied on the board, {@code 0} otherwise
     */
    @Override
    public int numSatisfied(GameArea board) {
        return isSatisfied(board) ? 1 : 0;
    }

    /**
     * these methods are ONLY used for Serializing the CardCondition object
     */
    public HashMap<Coordinates, CornerEnum.ResourceEnum> getListCard() {
        return listCard;
    }

    @Override
    public String toString() {
        return "CardCondition";
    }
}
