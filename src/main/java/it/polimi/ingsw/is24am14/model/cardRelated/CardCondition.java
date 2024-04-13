package it.polimi.ingsw.is24am14.model.cardRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CardCondition implements Condition {
    HashMap<Coordinates, CornerEnum.ResourceEnum> listCard;

    public CardCondition() {
        listCard = new HashMap<Coordinates, CornerEnum.ResourceEnum>();
    }

    public void addClause(Coordinates clauseCoordinates, CornerEnum.ResourceEnum clauseResource) {
        listCard.put(clauseCoordinates, clauseResource);
    }
    @Override
    public boolean isSatisfied(HashMap<Coordinates, Card> board) {
        int listCardIndex = 0;

        for (Map.Entry<Coordinates, Card> entry : board) {
            //  if the board-card type is the same as the listCard type
            //  look at the next card of the condition
            CornerEnum.ResourceEnum type = new ArrayList<CornerEnum.ResourceEnum>(listCard.values()).get(listCardIndex);
            Card cardToCheck = entry.getValue();
            Coordinates coordinates = new ArrayList<Coordinates>(listCard.keySet()).get(listCardIndex);

            while (listCardIndex < listCard.size() && isSameType(cardToCheck, type) && cardToCheck != null) {
                listCardIndex = listCardIndex + 1;
                coordinates = new ArrayList<Coordinates>(listCard.keySet()).get(listCardIndex);
                cardToCheck = board.get(Coordinates.add(entry.getKey(), coordinates));
            }

            if (listCardIndex >= listCard.size()) return true;
        }

        return false;
    }

    private boolean isSameType (Card boardCard, CornerEnum.ResourceEnum type) {
        return false;
    }
    private boolean isSameType (ResourceCard boardCard, CornerEnum.ResourceEnum type) {
        return boardCard.getResource() == type;
    }

    private boolean isSameType (GoldCard boardCard, CornerEnum.ResourceEnum type) {
        return boardCard.getResource() == type;
    }
}
