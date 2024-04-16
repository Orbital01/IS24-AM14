package it.polimi.ingsw.is24am14.server.model.player;


import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.*;

import java.util.ArrayList;


public class Player {
    private String nickname;
    private int points;
    private final TokenColour colour;
    private Card starterCard;
    private ArrayList<Card> playerHand;
    private ObjectiveCard secretObjective;
    private GameArea playerBoard;

    //Constructor
    /**
     * Constructs a Player object with the given nickname and colour.
     * Initializes the player's points to 0, their hand to an empty list of cards,
     * their secret objective to null, and their game area to a new GameArea.
     *
     * @param nickname The nickname of the player.
     * @param colour The colour of the player's token.
     */
    public Player(String nickname, TokenColour colour) {
        this.nickname = nickname;
        this.points = 0;
        this.colour = colour;
        this.playerHand = new ArrayList<Card>();
        this.secretObjective = null;
        this.playerBoard = new GameArea();
    }

    //Getters
    /**
     * Retrieves the player's nickname.
     *
     * @return The nickname of the player.
     */
    public String getPlayerNickname() {
        return this.nickname;
    }

    /**
     * Retrieves the player's score.
     *
     * @return The score of the player.
     */
    public int getScore() {
        return this.points;
    }

    /**
     * Retrieves the player's starter card.
     *
     * @return The starter card of the player.
     */
    public Card getStarterCard() {
        return this.starterCard;
    }

    /**
     * Retrieves the player's hand of cards.
     *
     * @return The hand of cards of the player.
     */
    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    /**
     * Sets the player's nickname.
     *
     * @param nickname The new nickname of the player.
     */
    public void setPlayerNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Sets the player's score.
     *
     * @param points The new score of the player.
     */
    public void setScore(int points) {
        this.points = points;
    }

    /**
     * Sets the player's starter card.
     *
     * @param starterCard The (new) starter card of the player.
     */
    public void setStarterCard(Card starterCard) {
        this.starterCard = starterCard;
    }

    //Methods
    /**
     * Adds a card to the player's hand.
     *
     * @param card The card that is going to be added to the player's hand.
     */
    public void addCardToHand(Card card) {
        this.playerHand.add(card);
    }

    /**
     * Removes a card from the player's hand.
     *
     * @param index The index of the card in the player's hand that is going to be removed.
     */
    public void removeCardFromHand(int index) throws IllegalArgumentException, IllegalStateException {
        if (index < 0 || index > this.playerHand.size()) throw new IllegalArgumentException("The index is out of bounds.");
        if (this.playerHand.isEmpty()) throw new IllegalStateException("The player's hand is empty, therefore it is not possible to remove a card from it.");
        this.playerHand.remove(index);
    }

    /**
     * Places a card on the player's board.
     *
     * @param cardToOverlap The card that is going to be overlapped.
     * @param cardToPlace The new card that the player wants to place on the board.
     * @param cornerIndex The index of the corner where the new card is goin to be placed upon the old card.
     */
    public void placeCard(Card cardToOverlap, Card cardToPlace, int cornerIndex) {
        playerBoard.addCard(cardToOverlap, cardToPlace, cornerIndex);
    }


}
