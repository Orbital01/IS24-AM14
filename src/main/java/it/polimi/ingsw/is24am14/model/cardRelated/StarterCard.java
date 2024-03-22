package it.polimi.ingsw.is24am14.model.cardRelated;
import java.util.ArrayList;

public class StarterCard extends Card{

    private final ArrayList<CornerEnum.ResourceEnum> resources;

    public StarterCard(ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, EnumSide enumSide, ArrayList<CornerEnum.ResourceEnum> resources) {
        super(frontCorners, backCorners, enumSide);
        this.resources = resources;
    }

    public ArrayList<CornerEnum.ResourceEnum> getResources() {
        return resources;
    }
}
