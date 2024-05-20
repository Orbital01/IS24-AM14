package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.client.printer.EmojiConverter;
import net.fellbaum.jemoji.Emoji;
import net.fellbaum.jemoji.Emojis;

import java.util.ArrayList;

/**
 * This class represents a Gold Card.
 * A Gold Card is a card that gives points based on a condition or a resource.
 * It also has a condition that must be satisfied in order to place it on the board.
 */

public class GoldCard extends PlayableCard {
    private final int points;
    private final Condition pointCondition;
    private final CornerEnum.ResourceEnum resource;

    private final ResourceCondition placementCondition;

    public GoldCard(int points, Condition pointCondition, CornerEnum.ResourceEnum resource,
                    ResourceCondition placementCondition, ArrayList<Corner> frontCorners,
                    String frontImage, String backImage) {

        super(frontCorners, createBackCorners(), frontImage, backImage);
        this.points = points;
        this.pointCondition = pointCondition;
        this.resource = resource;
        this.placementCondition = placementCondition;

    }

    public static ArrayList<Corner> createBackCorners() {
        ArrayList<Corner> backCorners = new ArrayList<>();
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        return backCorners;
    }

    public int getPoints() {
        return points;
    }

    public Condition getPointCondition() {
        return pointCondition;
    }

    public CornerEnum.ResourceEnum getResource() {
        return resource;
    }

    public ResourceCondition getPlacementCondition() {
        return placementCondition;
    }

    public ArrayList<String> drawCard() {
        ArrayList<String> cardString = new ArrayList<>();

        if (this.getSide().equals(EnumSide.FRONT)){
            ArrayList<Emoji> corners;
            corners = EmojiConverter.drawCorners(this);
            ArrayList<Emoji> resource = EmojiConverter.drawResource(this);
            ArrayList<Emoji> plaecementCondition = EmojiConverter.drawPlacementCondition(this);
            int points = this.getPoints();
            ArrayList<Emoji> pointCondition = EmojiConverter.drawCondition(this.getPointCondition());

            //Each card has 7 rows
            //First Row
            cardString.add("|-----|------------|-----|");
            //Second Row
            cardString.add("| " + corners.get(0).getEmoji() + " |      " + points + pointCondition.get(0).getEmoji() + "    | " + corners.get(1).getEmoji() + " |");
            //Third Row
            cardString.add("|-----|		       |-----|");
            //Fourth Row
            cardString.add("|		    " + resource.get(0).getEmoji() + "		     |");
            //Fifth Row
            cardString.add("|-----|		       |-----|");
            //Sixth Row
            //cardString.add("| " + corners.get(2).getEmoji() + " | " + plaecementCondition.get(0).getEmoji()+plaecementCondition.get(0).getEmoji()+plaecementCondition.get(1).getEmoji()+plaecementCondition.get(2).getEmoji()+plaecementCondition.get(3).getEmoji() + " | " + corners.get(3).getEmoji() + " |");
            cardString.add("| " + corners.get(2).getEmoji() + " | ");
            for (Emoji e : plaecementCondition) {
                cardString.set(5, cardString.get(5) + e.getEmoji());
            }
            cardString.set(5, cardString.get(5) + " | " + corners.get(3).getEmoji() + " |");
            //Seventh Row
            cardString.add("|-----|------------|-----|");
        }
        else{
            ArrayList<Emoji> corners = new ArrayList<Emoji>();
            for (int i = 0; i < this.getCorners().size(); i++){
                corners.add(Emojis.WHITE_MEDIUM_SQUARE);
            }
            ArrayList<Emoji> resource = EmojiConverter.drawResource(this);


            //Each card has 7 rows
            //First Row
            cardString.add("|-----|------------|-----|");
            //Second Row
            cardString.add("| " + corners.get(0).getEmoji() + " |         " + "    | " + corners.get(1).getEmoji() + " |");
            //Third Row
            cardString.add("|-----|		       |-----|");
            //Fourth Row
            cardString.add("|		    " + resource.get(0).getEmoji() + "		     |");
            //Fifth Row
            cardString.add("|-----|		       |-----|");
            //Sixth Row
            cardString.add("| " + corners.get(2).getEmoji() + " |" + "             | " + corners.get(3).getEmoji() + " |");
            //Seventh Row
            cardString.add("|-----|------------|-----|");
        }
        return cardString;
    }

}
