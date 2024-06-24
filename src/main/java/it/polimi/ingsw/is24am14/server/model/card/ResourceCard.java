package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.client.view.printer.EmojiConverter;
import net.fellbaum.jemoji.Emoji;
import net.fellbaum.jemoji.Emojis;

import java.util.ArrayList;

/**
 * This class represents a resource card.
 * A resource card has a number of points and a resource.
 */

public class ResourceCard extends PlayableCard {

    private final int points;
    private final CornerEnum.ResourceEnum resource;
    private final Condition pointCondition;

    public ResourceCard(int points, CornerEnum.ResourceEnum resource, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
                        String frontImage, String backImage) {
        super(frontCorners, backCorners, frontImage, backImage);
        this.points = points;
        this.resource = resource;
        this.pointCondition = new NoCondition();
    }

    public ArrayList<CornerEnum> getCornerEnums() {
        ArrayList<CornerEnum> items = new ArrayList<>();
        ArrayList<Corner> corners = getCorners();

        for (int i = 0; i < 4; i++) {
            if (!corners.get(i).isOverlapped()) items.add(corners.get(i).getType());
        }

        if (getSide() == EnumSide.BACK) items.add(getResource());
        return items;
    }

    public int getPoints() {
        return points;
    }

    public CornerEnum.ResourceEnum getResource() {
        return resource;
    }

    @Override
    public Condition getPointCondition() {
        return this.pointCondition;
    }

    @Override
    public Condition getPlacementCondition() {
        return new NoCondition();
    }

    public ArrayList<String> drawCard() {
        ArrayList<String> cardString = new ArrayList<>();

        if (this.getSide().equals(EnumSide.FRONT)) {

            ArrayList<String> corners;
            corners = EmojiConverter.drawCorners(this);
            ArrayList<String> resource = EmojiConverter.drawResource(this);
            int points = this.getPoints();

            //Each card has 7 rows
            //First Row
            cardString.add("|-----|------------|-----|");
            //Second Row
            String NW = this.getCorners().get(0).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(0);
            String NE = this.getCorners().get(1).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(1);
            cardString.add("| " + NW + " |      " + points + "      | " + NE + " |");
            //Third Row
            cardString.add("|-----|		       |-----|");
            //Fourth Row
            cardString.add("|		    " + resource.get(0) + "		     |");
            //Fifth Row
            cardString.add("|-----|		       |-----|");
            //Sixth Row
            String SW = this.getCorners().get(2).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(2);
            String SE = this.getCorners().get(3).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(3);
            cardString.add("| " + SW + " |" + "             | " + SE + " |");
            //Seventh Row
            cardString.add("|-----|------------|-----|");
        }
        else{
            ArrayList<String> corners = new ArrayList<String>();
            for (int i = 0; i < this.getCorners().size(); i++){
                corners.add(Emojis.WHITE_MEDIUM_SQUARE.getEmoji());
            }
            ArrayList<String> resource = EmojiConverter.drawResource(this);


            //Each card has 7 rows
            //First Row
            cardString.add("|-----|------------|-----|");
            //Second Row
            String NW = this.getCorners().get(0).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(0);
            String NE = this.getCorners().get(1).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(1);
            cardString.add("| " + NW + " |         " + "    | " + NE + " |");
            //Third Row
            cardString.add("|-----|		       |-----|");
            //Fourth Row
            cardString.add("|		    " + resource.get(0) + "		     |");
            //Fifth Row
            cardString.add("|-----|		       |-----|");
            //Sixth Row
            String SW = this.getCorners().get(2).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(2);
            String SE = this.getCorners().get(3).isOverlapped() ? Emojis.REPEAT_BUTTON.getEmoji() : corners.get(3);
            cardString.add("| " + SW + " |" + "             | " + SE + " |");
            //Seventh Row
            cardString.add("|-----|------------|-----|");
        }
        return cardString;
    }
}
