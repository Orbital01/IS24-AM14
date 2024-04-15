package it.polimi.ingsw.is24am14.server.model.card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents the deck of cards used in the game. It contains a list of cards and methods to manipulate them.
 */
public class Deck {
    private ArrayList<Card> content;


    /**
     * Creates a deck with the given cards
     * @param cards the cards to be added to the deck
     */
    public Deck(ArrayList<Card> cards) {
        content = new ArrayList<>(cards);
    }

    /**
     * returns true if the deck is empty, false otherwise
     */
    public boolean isEmpty() {
        return content.isEmpty();
    }

    /**
     * it draws the first card of the deck, the card that is on top of the deck
     * @return Card
     */

    public Card removeTop(){
        //launch an exception if the deck is empty
        if (content.isEmpty()) {
            throw new IllegalStateException("The deck is empty");
        }
        //removes the card in the first position of the deck and deletes it from the deck
        return content.removeFirst();
    }


    /**
     * it shuffles the deck
     */

    public void shuffle(){
        //shuffles the deck
        Collections.shuffle(content);
    }

}
