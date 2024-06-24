package it.polimi.ingsw.is24am14.client.view.printer;

import it.polimi.ingsw.is24am14.server.model.card.*;
import javafx.beans.value.ObservableNumberValue;
import net.fellbaum.jemoji.Emoji;
import net.fellbaum.jemoji.Emojis;

import java.util.ArrayList;
import java.util.HashMap;

public class EmojiConverter {
    //Constructor
    public EmojiConverter() {}

/*************************************** GOLD AND RESOURCE **************************************/
    public static ArrayList<String> drawCorners(PlayableCard card) {
        String corner;
        int i = 0;
        ArrayList<String> cornerEmojis = new ArrayList<String>();
        if (card.getSide().equals(EnumSide.FRONT)) {
            for (i = 0; i < card.getCorners().size(); i++) {
                switch (card.getCorners().get(i).getType()) {
                    case CornerEnum.ResourceEnum.FUNGI:
                        Emoji fungi = Emojis.MUSHROOM;
                        corner = fungi.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.INKWELL:
                        Emoji inkwell = Emojis.BLACK_NIB;
                        corner = inkwell.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.MANUSCRIPT:
                        Emoji manuscript = Emojis.SCROLL;
                        corner = manuscript.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.QUILL:
                        Emoji quill = Emojis.FEATHER;
                        corner = quill.getEmoji();
                        cornerEmojis.add(corner);
                        break;

                    default:
                        //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                        break;
                }
            }

        } else {
            for (i = 0; i < card.getCorners().size(); i++) {
                switch (card.getCorners().get(i).getType()) {
                    case CornerEnum.ResourceEnum.FUNGI:
                        Emoji fungi = Emojis.MUSHROOM;
                        corner = fungi.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.INKWELL:
                        Emoji inkwell = Emojis.BLACK_NIB;
                        corner = inkwell.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.MANUSCRIPT:
                        Emoji manuscript = Emojis.SCROLL;
                        corner = manuscript.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.QUILL:
                        Emoji quill = Emojis.FEATHER;
                        corner = quill.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    default:
                        //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                        break;
                }
            }
        }
        return cornerEmojis;
    }

    public static ArrayList<String> drawResource(PlayableCard card) {
        String resource;
        int i = 0;
        ArrayList<String> resourceEmojis = new ArrayList<String>();
        for (i = 0; i < card.getCorners().size(); i++) {
            switch (card.getResource()) {
                case CornerEnum.ResourceEnum.FUNGI:
                    Emoji fungi = Emojis.MUSHROOM;
                    resource = fungi.getEmoji();
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.ANIMAL:
                    Emoji animal = Emojis.WOLF;
                    resource = animal.getEmoji();
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.PLANT:
                    Emoji plant = Emojis.SHAMROCK;
                    resource = plant.getEmoji();
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.INSECT:
                    Emoji insect = Emojis.BUTTERFLY;
                    resource = insect.getEmoji();
                    resourceEmojis.add(resource);
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        }
        return resourceEmojis;
    }

    public static ArrayList<String> drawPlacementCondition(GoldCard goldCard){
        String resource;
        ArrayList<String> resourceEmojis = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            resourceEmojis.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        }
        for (int i = 0; i < goldCard.getPlacementCondition().getListResource().size(); i++) {
            switch (goldCard.getPlacementCondition().getListResource().get(i)) {
                case CornerEnum.ResourceEnum.FUNGI:
                    Emoji fungi = Emojis.MUSHROOM;
                    resource = fungi.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                case CornerEnum.ResourceEnum.ANIMAL:
                    Emoji animal = Emojis.WOLF;
                    resource = animal.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                case CornerEnum.ResourceEnum.PLANT:
                    Emoji plant = Emojis.SHAMROCK;
                    resource = plant.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                case CornerEnum.ResourceEnum.INSECT:
                    Emoji insect = Emojis.BUTTERFLY;
                    resource = insect.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        }
        return resourceEmojis;
    }



    /*************************************** STARTER CARD **************************************/
    public static ArrayList<String> drawCorners(StarterCard card) {
        String corner;
        int i = 0;
        ArrayList<String> cornerEmojis = new ArrayList<String>();
        if (card.getSide().equals(EnumSide.FRONT)) {
            for (i = 0; i < card.getCorners().size(); i++) {
                switch (card.getCorners().get(i).getType()) {
                    case CornerEnum.ResourceEnum.FUNGI:
                        Emoji fungi = Emojis.MUSHROOM;
                        corner = fungi.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case null:
                        Emoji nullEmoji = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = nullEmoji.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    default:
                        //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                        break;
                }
            }

        } else {
            for (i = 0; i < card.getCorners().size(); i++) {
                switch (card.getCorners().get(i).getType()) {
                    case CornerEnum.ResourceEnum.FUNGI:
                        Emoji fungi = Emojis.MUSHROOM;
                        corner = fungi.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    default:
                        //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                        break;
                }
            }
        }
        return cornerEmojis;
    }

    public static ArrayList<String> drawResource(StarterCard card) {
        String resource;
        ArrayList<String> resourceEmojis = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            resourceEmojis.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        }
        for (int i = 0; i < card.getResources().size(); i++) {
            switch (card.getResources().get(i)) {
                case CornerEnum.ResourceEnum.FUNGI:
                    Emoji fungi = Emojis.MUSHROOM;
                    resource = fungi.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                case CornerEnum.ResourceEnum.ANIMAL:
                    Emoji animal = Emojis.WOLF;
                    resource = animal.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                case CornerEnum.ResourceEnum.PLANT:
                    Emoji plant = Emojis.SHAMROCK;
                    resource = plant.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                case CornerEnum.ResourceEnum.INSECT:
                    Emoji insect = Emojis.BUTTERFLY;
                    resource = insect.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        }
        return resourceEmojis;
    }

    /*************************************** OBJECTIVE CARD **************************************/
    public static ArrayList<String> drawCorners(ObjectiveCard card) {
        String corner;
        int i = 0;
        ArrayList<String> cornerEmojis = new ArrayList<String>();
        if (card.getSide().equals(EnumSide.FRONT)) {
            for (i = 0; i < card.getCorners().size(); i++) {
                switch (card.getCorners().get(i).getType()) {
                    case CornerEnum.ResourceEnum.FUNGI:
                        Emoji fungi = Emojis.MUSHROOM;
                        corner = fungi.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    default:
                        //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                        break;
                }
            }

        } else {
            for (i = 0; i < card.getCorners().size(); i++) {
                switch (card.getCorners().get(i).getType()) {
                    case CornerEnum.ResourceEnum.FUNGI:
                        Emoji fungi = Emojis.MUSHROOM;
                        corner = fungi.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty.getEmoji();
                        cornerEmojis.add(corner);
                        break;
                    default:
                        //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                        break;
                }
            }
        }
        return cornerEmojis;
    }

    public static ArrayList<String> drawCondition(Condition condition) {
        if (condition.toString().equals("CornerCondition")){
            ArrayList<String> conditionEmojis = new ArrayList<String>();
            conditionEmojis.add(Emojis.TOP_ARROW.getEmoji());
            return conditionEmojis;
        }
        else if (condition.toString().equals("ResourceCondition")){
            return drawCondition((ResourceCondition) condition);
        }
        /*else if (condition.toString().equals("CardCondition")){
            return drawCondition((CardCondition) condition);
        }*/
        else if (condition.toString().equals("ObjectCondition")){
            return drawCondition((ObjectCondition) condition);
        }
        else if (condition.toString().equals("NoCondition")){
            ArrayList<String> emptyString = new ArrayList<>();
            emptyString.add("  ");
            return emptyString;
        }
        else {
            return null;
        }

    }

    public ArrayList<ArrayList<String>> drawCardCondition(Condition condition) {
        if (condition.toString().equals("CardCondition")){
            return drawCondition((CardCondition) condition);
        }
        else {
            return null;
        }

    }

    public static ArrayList<String> drawCondition(ObjectCondition objectCondition) {
        String object;
        ArrayList<String> objectEmojis = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            objectEmojis.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        }
        for (int i = 0; i < objectCondition.getListObject().size(); i++) {
            switch (objectCondition.getListObject().get(i)) {
                case null:
                    Emoji nullEmoji = Emojis.WHITE_MEDIUM_SQUARE;
                    object = nullEmoji.getEmoji();
                    objectEmojis.set(i, object);
                    break;
                case CornerEnum.ObjectEnum.INKWELL:
                    Emoji inkwell = Emojis.BLACK_NIB;
                    object = inkwell.getEmoji();
                    objectEmojis.set(i, object);
                    break;
                case CornerEnum.ObjectEnum.MANUSCRIPT:
                    Emoji manuscript = Emojis.SCROLL;
                    object = manuscript.getEmoji();
                    objectEmojis.set(i, object);
                    break;
                case CornerEnum.ObjectEnum.QUILL:
                    Emoji quill = Emojis.FEATHER;
                    object = quill.getEmoji();
                    objectEmojis.set(i, object);
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        }
        return objectEmojis;
    }

    public static ArrayList<String> drawCondition(ResourceCondition resourceCondition){
        String resource;
        ArrayList<String> resourceEmojis = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            resourceEmojis.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        }
        for (int i = 0; i < resourceCondition.getListResource().size(); i++) {
            switch (resourceCondition.getListResource().get(i)) {
                case CornerEnum.ResourceEnum.FUNGI:
                    Emoji fungi = Emojis.MUSHROOM;
                    resource = fungi.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                case CornerEnum.ResourceEnum.ANIMAL:
                    Emoji animal = Emojis.WOLF;
                    resource = animal.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                case CornerEnum.ResourceEnum.PLANT:
                    Emoji plant = Emojis.SHAMROCK;
                    resource = plant.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                case CornerEnum.ResourceEnum.INSECT:
                    Emoji insect = Emojis.BUTTERFLY;
                    resource = insect.getEmoji();
                    resourceEmojis.set(i, resource);
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        }
        return resourceEmojis;
    }

    public ArrayList<String> drawCondition(CornerCondition objectCondition) {
        ArrayList<String> objectEmojis = new ArrayList<String>();
        objectEmojis.add(Emojis.TOP_ARROW.getEmoji());
        return objectEmojis;
    }

    public ArrayList<ArrayList<String>> drawCondition(CardCondition cardCondition) {
        ArrayList<String> row1 = new ArrayList<>(3);
        row1.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        row1.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        row1.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        ArrayList<String> row2 = new ArrayList<>(3);
        row2.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        row2.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        row2.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        ArrayList<String> row3 = new ArrayList<>(3);
        row3.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        row3.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        row3.add(Emojis.WHITE_LARGE_SQUARE.getEmoji());
        HashMap<Coordinates, CornerEnum.ResourceEnum> listCard = cardCondition.getListCard();


        for (HashMap.Entry<Coordinates, CornerEnum.ResourceEnum> entry : listCard.entrySet()) {
            Emoji emoji;
            if (entry.getValue() == CornerEnum.ResourceEnum.FUNGI)
                emoji = Emojis.RED_SQUARE;
            else if (entry.getValue() == CornerEnum.ResourceEnum.PLANT)
                emoji = Emojis.GREEN_SQUARE;
            else if (entry.getValue() == CornerEnum.ResourceEnum.ANIMAL)
                emoji = Emojis.BLUE_SQUARE;
            else
                emoji = Emojis.PURPLE_SQUARE;

            if (entry.getKey().getRow() == 1) {
                if (entry.getKey().getColumn() == -1)
                    row1.set(0, emoji.getEmoji());
                else if (entry.getKey().getColumn() == 0)
                    row1.set(1, emoji.getEmoji());
                else
                    row1.set(2, emoji.getEmoji());
            } else if (entry.getKey().getRow() == 0) {
                if (entry.getKey().getColumn() == -1)
                    row2.set(0, emoji.getEmoji());
                else if (entry.getKey().getColumn() == 0)
                    row2.set(1, emoji.getEmoji());
                else
                    row2.set(2, emoji.getEmoji());
            } else {
                if (entry.getKey().getColumn() == -1)
                    row3.set(0, emoji.getEmoji());
                else if (entry.getKey().getColumn() == 0)
                    row3.set(1, emoji.getEmoji());
                else
                    row3.set(2, emoji.getEmoji());
            }

        }
        ArrayList<ArrayList<String>> cardConditionMatrix = new ArrayList<>(3);
        cardConditionMatrix.add(row1);
        cardConditionMatrix.add(row2);
        cardConditionMatrix.add(row3);

        return cardConditionMatrix;

    }
}
