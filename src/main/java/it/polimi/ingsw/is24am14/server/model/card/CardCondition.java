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

    private CornerEnum.ResourceEnum getResourceByCoordinates(Coordinates coordinates) {
        for (Map.Entry<Coordinates, CornerEnum.ResourceEnum> entry : listCard.entrySet()) {
            if (entry.getKey().getRow() == coordinates.getRow() && entry.getKey().getColumn() == coordinates.getColumn()) return entry.getValue();
        }
        return null;
    }

    @Override
    public int numSatisfied(GameArea board) {
        return isSatisfied(board) ? 1 : 0;
    }

    /**
     * Checks the type of the card
     * @param boardCard card to be checked
     * @param type type to be checked
     * @return {@code true} if the card is typeof type, otherwise {@code false}
     */
    private boolean isSameType (PlayableCard boardCard, CornerEnum.ResourceEnum type) {
        return boardCard.getResource() == type;
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
