package it.polimi.ingsw.is24am14.model.cardRelated;

public class Corner {
    private final CornerEnum type;
    private Card overlappingCard;

public Corner(CornerEnum type) {
        this.type = type;
    }

    public Card getOverlappingCard() {
        return overlappingCard;
    }

    public void setOverlappingCard(Card overlappingCard) {
        this.overlappingCard = overlappingCard;
    }

    public CornerEnum getType() {
        return type;
    }
}
