package it.polimi.ingsw.is24am14.server.model.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * This class represents the deck of cards used in the game. It contains a list of cards and methods to manipulate them.
 */
public class Deck<T extends Card> implements Serializable, Iterable<T> {
    private final ArrayList<T> content;


    /**
     * Creates a deck with the given cards
     * @param cards the cards to be added to the deck
     */
    public Deck(ArrayList<T> cards) {
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

    public T removeTop(){
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

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }
}
