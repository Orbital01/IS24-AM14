package it.polimi.ingsw.is24am14.model.cardRelated;

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
