package it.polimi.ingsw.is24am14.server.model.card;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DeckTest {
    /**
     * Test of isEmpty method, of class Deck.
     */
    @Test
    public void testIsEmpty() {
        Deck instance = new Deck(new ArrayList<>()); //instantiate a deck with an empty list of cards
        boolean expectedResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expectedResult, result);
    }

    //create a Testcard class that extends Card to test the removeTop method
    private static class TestCard extends Card {
        public TestCard(ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, String frontImage, String backImage) {
            super(frontCorners, backCorners, frontImage, backImage);
        }
    }

    /**
     * Test of removeTop method, of class Deck.
     */
    @Test
    public void testRemoveTop() {
        ArrayList<Card> cards = new ArrayList<>();
        Deck instance = new Deck(cards);
        //check if the exception is thrown when the deck is empty
        assertThrows(IllegalStateException.class, instance::removeTop);

        //now add a card to the deck and check if it is correctly removed
        Card card = new TestCard(new ArrayList<>(), new ArrayList<>(), "front.jpg", "back.jpg");
        cards.add(card);
        instance = new Deck(cards);
        Card result = instance.removeTop();
        assertEquals(card, result);
    }
}


