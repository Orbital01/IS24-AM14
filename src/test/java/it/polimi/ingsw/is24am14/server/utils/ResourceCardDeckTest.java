package it.polimi.ingsw.is24am14.server.utils;

import it.polimi.ingsw.is24am14.server.model.card.*;

import it.polimi.ingsw.is24am14.server.model.card.Deck;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import org.junit.jupiter.api.Test;

public class ResourceCardDeckTest {
    /**
     * Test if the deck is correctly created
     */
    @Test
    public void testResourceCardDeck() {
        ResourceCardDeckCreator creator = new ResourceCardDeckCreator();
        Deck<ResourceCard> deck = creator.createResourceCardDeck();

        int i = 1;
        while (!deck.isEmpty()) {
            System.out.println(" ");
            System.out.println("Card number " + i++);
            ResourceCard card = deck.removeTop();
            for (String s : card.drawCard()){
                System.out.println(s);
            }
        }
    }
}