package it.polimi.ingsw.is24am14.model.cardRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ObjectCondition implements Condition {
    ArrayList<CornerEnum.ObjectEnum> listObject;

    public ObjectCondition() {
        listObject = new ArrayList<>();
    }

    public void addClause(CornerEnum.ObjectEnum clause) {
        listObject.add(clause);
    }
    @Override
    public boolean isSatisfied(HashMap<Coordinates, Card> board) {

        ArrayList<CornerEnum.ObjectEnum> toFind = new ArrayList<>(listObject);

        //  for each card on the board
        for (Map.Entry<Coordinates, Card> entry : board)
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

    public ArrayList<CornerEnum> getCornerEnums(Card card) {
        ArrayList<CornerEnum> items = new ArrayList<>();
        ArrayList<Corner> corners = card.getSide() == EnumSide.FRONT ? card.getFrontCorners() : card.getBackCorners();

        for (int i = 0; i < 5; i++) {
            items.add(corners.get(i).getType());
        }
        return items;
    }
}
