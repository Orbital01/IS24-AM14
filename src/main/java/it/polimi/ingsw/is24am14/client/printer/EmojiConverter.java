package it.polimi.ingsw.is24am14.client.printer;

import it.polimi.ingsw.is24am14.server.model.card.*;
import javafx.beans.value.ObservableNumberValue;
import net.fellbaum.jemoji.Emoji;
import net.fellbaum.jemoji.Emojis;

import java.util.ArrayList;

public class EmojiConverter {
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
        for (i = 0; i < card.getCorners().size(); i++) {
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

    public static ArrayList<Emoji> drawObjectCondition(ObjectCondition objectCondition) {
        Emoji object;
        ArrayList<Emoji> objectEmojis = new ArrayList<Emoji>();
        for (int i = 0; i < objectCondition.getListObject().size(); i++) {
            switch (objectCondition.getListObject().get(i)) {
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

    public static ArrayList<Emoji> drawResourceCondition(ResourceCondition resourceCondition){
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

    public static ArrayList<Emoji> drawCardCondition(CardCondition cardCondition){
        Emoji card;
        ArrayList<Emoji> cardEmojis = new ArrayList<Emoji>();
        for (int i = 0; i < cardCondition.getListCard().size(); i++) {
            switch (cardCondition.getListCard().get(i)) { //Come fa a funzionare .get(i)? Il campo della chiave è di tipo Coordinates
                case CornerEnum.ResourceEnum.FUNGI:
                    //Fungi Card ==> Red Card
                    Emoji fungi = Emojis.RED_SQUARE;
                    card = fungi;
                    cardEmojis.add(card);
                    break;
                case CornerEnum.ResourceEnum.ANIMAL:
                    //Animal Card ==> Blue Card
                    Emoji animal = Emojis.BLUE_SQUARE;
                    card = animal;
                    cardEmojis.add(card);
                    break;
                case CornerEnum.ResourceEnum.PLANT:
                    //Plant Card ==> Green Card
                    Emoji plant = Emojis.GREEN_SQUARE;
                    card = plant;
                    cardEmojis.add(card);
                    break;
                case CornerEnum.ResourceEnum.INSECT:
                    //Insect Card ==> Purple Card
                    Emoji insect = Emojis.PURPLE_SQUARE;
                    card = insect;
                    cardEmojis.add(card);
                    break;
                default:
                    //throw new IllegalStateEcornerception("Unecornerpected value: " + corners.get(i).getType());
                    break;
            }
        }
        return cardEmojis;
    }

}