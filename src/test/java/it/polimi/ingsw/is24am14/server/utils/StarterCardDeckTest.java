package it.polimi.ingsw.is24am14.server.utils;

import it.polimi.ingsw.is24am14.server.model.card.Deck;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import org.junit.jupiter.api.Test;

public class StarterCardDeckTest {
        /**
         * Test if the deck is correctly created
         */
        @Test
        public void testStarterCardDeck() {
            StarterCardDeckCreator creator = new StarterCardDeckCreator();
            Deck<StarterCard> deck = creator.createStarterCardDeck();

            int i = 1;
            while (!deck.isEmpty()) {
                System.out.println(" ");
                System.out.println("Card number " + i++);
                StarterCard card = deck.removeTop();
                for (String s : card.drawCard()){
                    System.out.println(s);
                }
            }
        }
    }


