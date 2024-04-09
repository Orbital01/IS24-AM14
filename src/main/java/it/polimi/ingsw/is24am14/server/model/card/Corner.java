package it.polimi.ingsw.is24am14.server.model.card;

/**
 * This class represents a corner of a card.
 * Each corner has a type and can be overlapped by another card.
 * The type of a corner is defined by the CornerEnum enumeration.
 * The overlapping card is represented by a Card object.
 */

public class Corner {
    private final CornerEnum type;
    private Card overlappingCard;

public Corner(CornerEnum type) {
        this.type = type;
    }

    public Card getOverlappingCard() {
        return overlappingCard;
    }

    /**
     * Sets the overlapping card.
     * @param overlappingCard the card that overlaps the corner
     */
    public void setOverlappingCard(Card overlappingCard) {
        this.overlappingCard = overlappingCard;
    }

    public CornerEnum getType() {
        return type;
    }
}
