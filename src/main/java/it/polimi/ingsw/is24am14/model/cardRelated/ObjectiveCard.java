package it.polimi.ingsw.is24am14.model.cardRelated;
import java.util.ArrayList;

public class ObjectiveCard extends Card{
    private final Condition condition;

    public ObjectiveCard(Condition condition, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, EnumSide enumSide) {
        super(frontCorners, backCorners, enumSide);
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }
}
