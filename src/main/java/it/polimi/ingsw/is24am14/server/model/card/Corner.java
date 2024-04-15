package it.polimi.ingsw.is24am14.server.model.card;

/**
 * This class represents a corner of a card.
 * Each corner has a type and can be overlapped by another card.
 * The type of corner is defined by the CornerEnum enumeration.
 * The overlapping card is represented by a Card object.
 */

public class Corner {
    private final CornerEnum type;

public Corner(CornerEnum type) {
        this.type = type;
    }


    /**
     * Returns the type of the corner.
     * @return the type of the corner
     */
    public CornerEnum getType() {
        return type;
    }

}
