package it.polimi.ingsw.is24am14.server.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents the deck of cards used in the game. It contains a list of cards and methods to manipulate them.
 */
public class Deck {
    private ArrayList<Card> content;

    public Deck() {
        content = new ArrayList<Card>();
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }

    public Card removeTop(){
        //launch an exception if the deck is empty
        if (content.isEmpty()) {
            throw new IllegalStateException("The deck is empty");
        }
        //removes the card in the first position of the deck and deletes it from the deck
        return content.removeFirst();
    }

    public void shuffle(){
        //shuffles the deck
        Collections.shuffle(content);
    }

}
