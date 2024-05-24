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

    public ResourceCard(int points, CornerEnum.ResourceEnum resource, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
                        String frontImage, String backImage) {
        super(frontCorners, backCorners, frontImage, backImage);
        this.points = points;
        this.resource = resource;
    }

    public ArrayList<CornerEnum> getCornerEnums() {
        ArrayList<CornerEnum> items = new ArrayList<>();
        ArrayList<Corner> corners = getCorners();

        for (int i = 0; i < 4; i++) {
            items.add(corners.get(i).getType());
        }

        if (getSide() == EnumSide.FRONT) items.add(getResource());
        return items;
    }

    public int getPoints() {
        return points;
    }

    public CornerEnum.ResourceEnum getResource() {
        return resource;
    }

    public ArrayList<String> drawCard() {
        ArrayList<String> cardString = new ArrayList<>();

        if (this.getSide().equals(EnumSide.FRONT)) {

            ArrayList<Emoji> corners;
            corners = EmojiConverter.drawCorners(this);
            ArrayList<Emoji> resource = EmojiConverter.drawResource(this);
            int points = this.getPoints();

            //Each card has 7 rows
            //First Row
            cardString.add("|-----|------------|-----|");
            //Second Row
            cardString.add("| " + corners.get(0).getEmoji() + " |      " + points + "      | " + corners.get(1).getEmoji() + " |");
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
