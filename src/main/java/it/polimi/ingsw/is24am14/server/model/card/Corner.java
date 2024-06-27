package it.polimi.ingsw.is24am14.server.model.card;

import java.io.Serializable;

/**
 * This class represents a corner of a card.
 * Each corner has a type and can be overlapped by another card.
 * The type of corner is defined by the CornerEnum enumeration.
 * The overlapping card is represented by a Card object.
 */

public class Corner implements Serializable {
    private CornerEnum type;
    private boolean isOverlapped;

    public Corner(CornerEnum type) {
        this.type = type;
        isOverlapped = false;
    }
    /**
     * Returns the type of the corner.
     * @return the type of the corner
     */
    public CornerEnum getType() {
        return type;
    }

    /**
     * Marks the corner as overlapped.
     */
    public void overlap() {
        isOverlapped = true;
    }
    /**
     * Checks if the corner is overlapped.
     * @return {@code true} if the corner is overlapped, otherwise {@code false}
     */
    public boolean isOverlapped() {
        return isOverlapped;
    }

    /**
     * these methods are used ONLY to serialize the object
     * they are not used in the game
     */
    //setters
    public void setType(CornerEnum type) {
        this.type = type;
    }
}
