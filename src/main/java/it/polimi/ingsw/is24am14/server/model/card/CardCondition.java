package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *  A condition implementation to check whether the player has a certain pattern of cards in their game board.
 *  Implements the {@link Condition} interface.
 */
public class CardCondition implements Condition {
    private HashMap<Coordinates, CornerEnum.ResourceEnum> listCard;

    public CardCondition() {
        listCard = new HashMap<>();
    }

    /**
     * @param clauseCoordinates the position of the card in the pattern.
     * @param clauseResource the type of the card in the pattern.
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
        int listCardIndex = 0;

        for (Map.Entry<Coordinates, Card> entry : board.getBoard().entrySet()) {
            //  if the board-card type is the same as the listCard type
            //  look at the next card of the condition
            CornerEnum.ResourceEnum type = new ArrayList<>(listCard.values()).get(listCardIndex);
            Card cardToCheck = entry.getValue();
            Coordinates coordinates;
/*
            while (listCardIndex < listCard.size() && isSameType(entry.getValue(), type) && cardToCheck != null) {
                coordinates = new ArrayList<>(listCard.keySet()).get(listCardIndex);
                cardToCheck = board.getBoard().get(Coordinates.add(entry.getKey(), coordinates));
                listCardIndex = listCardIndex + 1;
            }
            */

            if (listCardIndex >= listCard.size()) return true;
        }

        return false;
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
}
