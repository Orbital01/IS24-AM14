package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.io.Serializable;

/**
 *  The Condition interface serves as an abstraction for all conditions within the game.
 *  Conditions represent various states or criteria that affect gameplay, progression, or outcomes.
 *  Implementations of this interface can define specific conditions such as number-of-resources condition,
 *  number-of-objects condition or pattern-of-cards-on-the-board condition.
 *  Implementing classes should provide methods to check if a particular condition is met,
 *  as well as any additional functionality necessary for managing or updating the condition state.
 */
public interface Condition extends Serializable {
    boolean isSatisfied(GameArea board);
    int numSatisfied(GameArea board);
    String toString();
}
