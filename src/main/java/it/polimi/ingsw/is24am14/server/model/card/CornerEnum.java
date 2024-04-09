package it.polimi.ingsw.is24am14.server.model.card;

/**
 * This interface is used to define the possible types of corners that a card can have.
 * It is used in the Card class to define the corners of the card.
 */

public interface CornerEnum {
    OthersEnum HIDDEN = OthersEnum.HIDDEN;
    OthersEnum EMPTY = OthersEnum.EMPTY;

    public enum ResourceEnum implements CornerEnum {
       PLANT, ANIMAL, INSECT, FUNGI
    }
    public enum ObjectEnum implements CornerEnum{
        INKWELL, MANUSCRIPT, QUILL
    }
    public enum OthersEnum implements CornerEnum {
        HIDDEN, EMPTY
    }

}
