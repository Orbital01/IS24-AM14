package it.polimi.ingsw.is24am14.server.utils;

import it.polimi.ingsw.is24am14.server.model.card.Deck;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import org.junit.jupiter.api.Test;

public class ObjectiveCardDeckTest {
    /**
     * Test if the deck is correctly created
     */
    @Test
    public void testObjectiveCardDeck() {
        ObjectiveCardDeckCreator creator = new ObjectiveCardDeckCreator();
        Deck<ObjectiveCard> deck = creator.createObjectiveCardDeck();

        int i = 1;
        while (!deck.isEmpty()) {
            System.out.println(" ");
            System.out.println("Card number " + i++);
            ObjectiveCard card = deck.removeTop();
            for (String s : card.drawCard()){
                System.out.println(s);
            }
        }
    }
}
