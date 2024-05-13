package it.polimi.ingsw.is24am14.client.printer;

import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;
import it.polimi.ingsw.is24am14.server.model.card.EnumSide;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
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



}