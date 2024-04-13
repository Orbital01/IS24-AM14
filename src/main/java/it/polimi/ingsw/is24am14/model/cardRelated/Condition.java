package it.polimi.ingsw.is24am14.model.cardRelated;

import java.util.HashMap;

public interface Condition {
    public boolean isSatisfied(HashMap<Coordinates, Card> board);

}
