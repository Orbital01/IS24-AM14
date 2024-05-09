package it.polimi.ingsw.is24am14.server.model.card;

/**
 * This interface is used to define the possible types of corners that a card can have.
 * It is used in the Card class to define the corners of the card.
 */

public interface CornerEnum {
    OthersEnum HIDDEN = OthersEnum.HIDDEN;
    OthersEnum EMPTY = OthersEnum.EMPTY;

    /**
     * this enum rapresents the possible types of resources that a corner can have
     */
    public enum ResourceEnum implements CornerEnum {
       PLANT, ANIMAL, INSECT, FUNGI
    }
    /**
     * this enum rapresents the possible types of objects that a corner can have
     */
    public enum ObjectEnum implements CornerEnum{
        INKWELL, MANUSCRIPT, QUILL
    }
    /**
     * this enum rapresents the last two possibile states of a corner
     * HIDDEN is used to represent a corner that is not visible to the player
     * EMPTY is used to represent a corner that has no object or resource
     */
    public enum OthersEnum implements CornerEnum {
        HIDDEN, EMPTY
    }

    /**
     * this method is used to get the CornerEnum value from a string
     * IT IS ONLY USED TO DESERIALIZE THE OBJECT
     */

    public static CornerEnum valueOf(String name) {
        String trimmedName = name.trim();

        for (ResourceEnum value : ResourceEnum.values()) {
            if (value.name().equalsIgnoreCase(trimmedName)) {
                return value;
            }
        }

        for (ObjectEnum value : ObjectEnum.values()) {
            if (value.name().equalsIgnoreCase(trimmedName)) {
                return value;
            }
        }

        for (OthersEnum value : OthersEnum.values()) {
            if (value.name().equalsIgnoreCase(trimmedName)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid CornerEnum name");
    }

}
