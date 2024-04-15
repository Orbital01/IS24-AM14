package it.polimi.ingsw.is24am14.model.cardRelated;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Corner;
import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;
import it.polimi.ingsw.is24am14.server.model.card.EnumSide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResourceCondition<Coordinates> implements Condition {
    ArrayList<CornerEnum.ResourceEnum> listResource;

    public ResourceCondition () {
        listResource = new ArrayList<>();
    }

    public void addClause (CornerEnum.ResourceEnum clause) {
        listResource.add(clause);
    }
    @Override
    public boolean isSatisfied(HashMap<Coordinates, Card> board) {
        ArrayList<CornerEnum.ResourceEnum> toFind = new ArrayList<>(listResource);

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
            //  if resource card
            if (getCornerEnums(entry.getValue()).size() > 4) toFind.remove(getCornerEnums(entry.getValue()).get(5));
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
    public ArrayList<CornerEnum> getCornerEnums(ResourceCard card) {
        ArrayList<CornerEnum> items = new ArrayList<>();
        ArrayList<Corner> corners = card.getSide() == EnumSide.FRONT ? card.getFrontCorners() : card.getBackCorners();

        for (int i = 0; i < 5; i++) {
            items.add(corners.get(i).getType());
        }

        if (card.getSide() == EnumSide.FRONT) items.add(card.getResource());

        return items;
    }
}
