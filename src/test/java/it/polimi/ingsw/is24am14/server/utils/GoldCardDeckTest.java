package it.polimi.ingsw.is24am14.server.utils;

import it.polimi.ingsw.is24am14.server.model.card.Deck;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;
import org.junit.jupiter.api.Test;

public class GoldCardDeckTest {

    /**
     * Test if the deck is correctly created
     */
    @Test
    public void testGoldCardDeck() {
        GoldCardDeckCreator creator = new GoldCardDeckCreator();
        Deck<GoldCard> deck = creator.createGoldCardDeck();

        int i = 1;
        while (!deck.isEmpty()) {
            System.out.println(" ");
            System.out.println("Card number " + i++);
            GoldCard card = deck.removeTop();
            for (String s : card.drawCard()){
                System.out.println(s);
            }
        }
    }
}
