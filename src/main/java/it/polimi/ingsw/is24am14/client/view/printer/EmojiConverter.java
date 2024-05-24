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
    public static ArrayList<Emoji> drawCorners(PlayableCard card) {
        Emoji corner;
        int i = 0;
        ArrayList<Emoji> cornerEmojis = new ArrayList<Emoji>();
        if (card.getSide().equals(EnumSide.FRONT)) {
            for (i = 0; i < card.getCorners().size(); i++) {
                switch (card.getCorners().get(i).getType()) {
                    case CornerEnum.ResourceEnum.FUNGI:
                        Emoji fungi = Emojis.MUSHROOM;
                        corner = fungi;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.INKWELL:
                        Emoji inkwell = Emojis.BLACK_NIB;
                        corner = inkwell;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.MANUSCRIPT:
                        Emoji manuscript = Emojis.SCROLL;
                        corner = manuscript;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.QUILL:
                        Emoji quill = Emojis.FEATHER;
                        corner = quill;
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
                        corner = fungi;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.INKWELL:
                        Emoji inkwell = Emojis.BLACK_NIB;
                        corner = inkwell;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.MANUSCRIPT:
                        Emoji manuscript = Emojis.SCROLL;
                        corner = manuscript;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ObjectEnum.QUILL:
                        Emoji quill = Emojis.FEATHER;
                        corner = quill;
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

    /*public static Emoji drawCardType(PlayableCard card){
        Emoji resource = null;
        int i = 0;
        switch (card.getResource()){
                case CornerEnum.ResourceEnum.FUNGI:
                    Emoji fungi = Emojis.MUSHROOM;
                    resource = fungi;
                    break;
                case CornerEnum.ResourceEnum.ANIMAL:
                    Emoji animal = Emojis.WOLF;
                    resource = animal;
                    break;
                case CornerEnum.ResourceEnum.PLANT:
                    Emoji plant = Emojis.SHAMROCK;
                    resource = plant;
                    break;
                case CornerEnum.ResourceEnum.INSECT:
                    Emoji insect = Emojis.BUTTERFLY;
                    resource = insect;
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        return resource;
    }*/

    public static ArrayList<Emoji> drawResource(PlayableCard card) {
        Emoji resource;
        int i = 0;
        ArrayList<Emoji> resourceEmojis = new ArrayList<Emoji>();
        for (i = 0; i < card.getCorners().size(); i++) {
            switch (card.getResource()) {
                case CornerEnum.ResourceEnum.FUNGI:
                    Emoji fungi = Emojis.MUSHROOM;
                    resource = fungi;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.ANIMAL:
                    Emoji animal = Emojis.WOLF;
                    resource = animal;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.PLANT:
                    Emoji plant = Emojis.SHAMROCK;
                    resource = plant;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.INSECT:
                    Emoji insect = Emojis.BUTTERFLY;
                    resource = insect;
                    resourceEmojis.add(resource);
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        }
        return resourceEmojis;
    }

    public static ArrayList<Emoji> drawPlacementCondition(GoldCard goldCard){
        Emoji resource;
        int i = 0;
        ArrayList<Emoji> resourceEmojis = new ArrayList<Emoji>();
        for (i = 0; i < goldCard.getPlacementCondition().getListResource().size(); i++) {
            switch (goldCard.getPlacementCondition().getListResource().get(i)) {
                case CornerEnum.ResourceEnum.FUNGI:
                    Emoji fungi = Emojis.MUSHROOM;
                    resource = fungi;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.ANIMAL:
                    Emoji animal = Emojis.WOLF;
                    resource = animal;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.PLANT:
                    Emoji plant = Emojis.SHAMROCK;
                    resource = plant;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.INSECT:
                    Emoji insect = Emojis.BUTTERFLY;
                    resource = insect;
                    resourceEmojis.add(resource);
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        }
        return resourceEmojis;
    }



    /*************************************** STARTER CARD **************************************/
    public static ArrayList<Emoji> drawCorners(StarterCard card) {
        Emoji corner;
        int i = 0;
        ArrayList<Emoji> cornerEmojis = new ArrayList<Emoji>();
        if (card.getSide().equals(EnumSide.FRONT)) {
            for (i = 0; i < card.getCorners().size(); i++) {
                switch (card.getCorners().get(i).getType()) {
                    case CornerEnum.ResourceEnum.FUNGI:
                        Emoji fungi = Emojis.MUSHROOM;
                        corner = fungi;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty;
                        cornerEmojis.add(corner);
                        break;
                    case null:
                        Emoji nullEmoji = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = nullEmoji;
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
                        corner = fungi;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty;
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

    public static ArrayList<Emoji> drawResource(StarterCard card) {
        Emoji resource;
        int i = 0;
        ArrayList<Emoji> resourceEmojis = new ArrayList<Emoji>();
        for (i = 0; i < card.getResources().size(); i++) {
            switch (card.getResources().get(i)) {
                case CornerEnum.ResourceEnum.FUNGI:
                    Emoji fungi = Emojis.MUSHROOM;
                    resource = fungi;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.ANIMAL:
                    Emoji animal = Emojis.WOLF;
                    resource = animal;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.PLANT:
                    Emoji plant = Emojis.SHAMROCK;
                    resource = plant;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.INSECT:
                    Emoji insect = Emojis.BUTTERFLY;
                    resource = insect;
                    resourceEmojis.add(resource);
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        }
        return resourceEmojis;
    }

    /*************************************** OBJECTIVE CARD **************************************/
    public static ArrayList<Emoji> drawCorners(ObjectiveCard card) {
        Emoji corner;
        int i = 0;
        ArrayList<Emoji> cornerEmojis = new ArrayList<Emoji>();
        if (card.getSide().equals(EnumSide.FRONT)) {
            for (i = 0; i < card.getCorners().size(); i++) {
                switch (card.getCorners().get(i).getType()) {
                    case CornerEnum.ResourceEnum.FUNGI:
                        Emoji fungi = Emojis.MUSHROOM;
                        corner = fungi;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty;
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
                        corner = fungi;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.ANIMAL:
                        Emoji animal = Emojis.WOLF;
                        corner = animal;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.PLANT:
                        Emoji plant = Emojis.SHAMROCK;
                        corner = plant;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.ResourceEnum.INSECT:
                        Emoji insect = Emojis.BUTTERFLY;
                        corner = insect;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.HIDDEN:
                        Emoji hidden = Emojis.PROHIBITED;
                        corner = hidden;
                        cornerEmojis.add(corner);
                        break;
                    case CornerEnum.OthersEnum.EMPTY:
                        Emoji empty = Emojis.WHITE_MEDIUM_SQUARE;
                        corner = empty;
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

    public static ArrayList<Emoji> drawCondition(Condition condition) {
        if (condition.toString().equals("CornerCondition")){
            ArrayList<Emoji> conditionEmojis = new ArrayList<Emoji>();
            conditionEmojis.add(Emojis.TOP_ARROW);
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

//    public ArrayList<ArrayList<String>> drawCondition(Condition condition) {
//        if (condition.toString().equals("CardCondition")) {
//            return drawCondition((CardCondition) condition);
//        }
//        else {
//            return null;
//        }
//    }
    public static ArrayList<Emoji> drawCondition(ObjectCondition objectCondition) {
        Emoji object;
        ArrayList<Emoji> objectEmojis = new ArrayList<Emoji>();
        for (int i = 0; i < objectCondition.getListObject().size(); i++) {
            switch (objectCondition.getListObject().get(i)) {
                case null:
                    Emoji nullEmoji = Emojis.WHITE_MEDIUM_SQUARE;
                    object = nullEmoji;
                    objectEmojis.add(object);
                    break;
                case CornerEnum.ObjectEnum.INKWELL:
                    Emoji inkwell = Emojis.BLACK_NIB;
                    object = inkwell;
                    objectEmojis.add(object);
                    break;
                case CornerEnum.ObjectEnum.MANUSCRIPT:
                    Emoji manuscript = Emojis.SCROLL;
                    object = manuscript;
                    objectEmojis.add(object);
                    break;
                case CornerEnum.ObjectEnum.QUILL:
                    Emoji quill = Emojis.FEATHER;
                    object = quill;
                    objectEmojis.add(object);
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        }
        return objectEmojis;
    }

    public static ArrayList<Emoji> drawCondition(ResourceCondition resourceCondition){
        Emoji resource;
        ArrayList<Emoji> resourceEmojis = new ArrayList<Emoji>();
        for (int i = 0; i < resourceCondition.getListResource().size(); i++) {
            switch (resourceCondition.getListResource().get(i)) {
                case CornerEnum.ResourceEnum.FUNGI:
                    Emoji fungi = Emojis.MUSHROOM;
                    resource = fungi;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.ANIMAL:
                    Emoji animal = Emojis.WOLF;
                    resource = animal;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.PLANT:
                    Emoji plant = Emojis.SHAMROCK;
                    resource = plant;
                    resourceEmojis.add(resource);
                    break;
                case CornerEnum.ResourceEnum.INSECT:
                    Emoji insect = Emojis.BUTTERFLY;
                    resource = insect;
                    resourceEmojis.add(resource);
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        }
        return resourceEmojis;
    }

    public ArrayList<Emoji> drawCondition(CornerCondition objectCondition) {
        ArrayList<Emoji> objectEmojis = new ArrayList<Emoji>();
        objectEmojis.add(Emojis.TOP_ARROW);
        return objectEmojis;
    }

//    public ArrayList<Emoji> drawCardCondition(CardCondition cardCondition){
//        Emoji card;
//        ArrayList<Emoji> cardEmojis = new ArrayList<Emoji>();
//        for (int i = 0; i < cardCondition.getListCard().size(); i++) {
//            switch (cardCondition.getListCard().get(i)) { //Come fa a funzionare .get(i)? Il campo della chiave è di tipo Coordinates
//                case CornerEnum.ResourceEnum.FUNGI:
//                    //Fungi Card ==> Red Card
//                    Emoji fungi = Emojis.RED_SQUARE;
//                    card = fungi;
//                    cardEmojis.add(card);
//                    break;
//                case CornerEnum.ResourceEnum.ANIMAL:
//                    //Animal Card ==> Blue Card
//                    Emoji animal = Emojis.BLUE_SQUARE;
//                    card = animal;
//                    cardEmojis.add(card);
//                    break;
//                case CornerEnum.ResourceEnum.PLANT:
//                    //Plant Card ==> Green Card
//                    Emoji plant = Emojis.GREEN_SQUARE;
//                    card = plant;
//                    cardEmojis.add(card);
//                    break;
//                case CornerEnum.ResourceEnum.INSECT:
//                    //Insect Card ==> Purple Card
//                    Emoji insect = Emojis.PURPLE_SQUARE;
//                    card = insect;
//                    cardEmojis.add(card);
//                    break;
//                default:
//                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
//                    break;
//            }
//        }
//        return cardEmojis;
//    }

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
