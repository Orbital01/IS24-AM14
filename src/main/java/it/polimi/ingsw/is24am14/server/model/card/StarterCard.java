package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.client.printer.EmojiConverter;
import net.fellbaum.jemoji.Emoji;

import java.util.ArrayList;

/**
 * This class represents the starter card of the game.
 * it is the first card that the player will have on its board.
 * It has a list of resources that the player will have at the beginning of the game.
 */

public class StarterCard extends Card {

    private final ArrayList<CornerEnum.ResourceEnum> resources;

    public StarterCard(ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, ArrayList<CornerEnum.ResourceEnum> resources,
                       String frontImage, String backImage) {
        super(frontCorners, backCorners, frontImage, backImage);
        this.resources = resources;
    }

    public ArrayList<CornerEnum.ResourceEnum> getResources() {
        return resources;
    }

    public ArrayList<String> drawCard() {
        ArrayList<String> cardString = new ArrayList<>();

        ArrayList<Emoji> corners;
        corners = EmojiConverter.drawCorners(this);
        ArrayList<Emoji> resources = EmojiConverter.drawResource(this);

        cardString.add("upper side");
        cardString.add("spacing");
        cardString.add("upper corners: " + corners.get(0).getEmoji() + corners.get(1).getEmoji());
        cardString.add("resources: ");
        for (Emoji resource : resources) {
            cardString.add(""+resource.getEmoji());
        }
        cardString.add("bottom corners: " + corners.get(2).getEmoji() + corners.get(3).getEmoji());
        cardString.add("spacing");
        cardString.add("bottom side");

        return cardString;
    }

}
