package it.polimi.ingsw.is24am14.server.model.game;

import it.polimi.ingsw.is24am14.server.model.card.*;

import static it.polimi.ingsw.is24am14.server.model.card.Coordinates.newCoordinates;

import java.io.Serializable;
import java.util.*;


/**
 * Represents the game area where cards will be placed during the game
 */
public class GameArea implements Serializable {
    private final HashMap<Coordinates, Card> board;
    /**
     * Constructs a new empty GameArea
     */
    public GameArea() {
        board = new HashMap<>();
    }

    /**
     * Board getter
     * @return The board
     */
    public HashMap<Coordinates, Card> getBoard() {
        return board;
    }

    /**
     * Searches for a card placed in GameArea and retrieves its coordinates
     * @param C The card to find
     * @return The coordinates of the card
     * @throws NoSuchElementException If the card isn't found in the GameArea
     */
    public Coordinates getCoordinates(Card C) throws NoSuchElementException {
        if(board.containsValue(C)) {
            for (Map.Entry<Coordinates, Card> entry : board.entrySet()) {
                if (entry.getValue() == C) {
                    return entry.getKey();
                }
            }
        }
        throw new NoSuchElementException("Game Area doesn't contain that card");
    }
   /**
    * Retrieves the row index of a given Card
     * @param C The card to find the row for
     * @return The row index of the card
     */
    public int getRow(Card C) {
        Coordinates coordinates = getCoordinates(C);
        return coordinates.getRow();
    }

    /**
    * Retrieves the column index of a given Card
     * @param C The card to find the column for
     * @return The column index of the card
     */
    public int getColumn(Card C) {
        Coordinates coordinates = getCoordinates(C);
        return coordinates.getColumn();
    }

      /**
     * Retrieves a card given its coordinates, compares Row and Column with board entries
     * @param coordinates The coordinates to check
     * @return The card at the given coordinates or null if empty
     */
    public Card getCard(Coordinates coordinates) {
        int Row = coordinates.getRow();
        int Column = coordinates.getColumn();
        for(Map.Entry<Coordinates, Card> entry : board.entrySet()){
            if(entry.getKey().getRow()==Row && entry.getKey().getColumn()==Column)
                return entry.getValue();
        }
        return null;
    }

    /**
     * Adds a card to the GameArea, placing it on top of the corner of an existing card
     * @param playedCard The card already on the board
     * @param newCard The card about to be played
     * @param cornerIndex The index of the corner the newCard is being placed on
     * @throws IllegalStateException if the move is deemed illegal (e.g., placing on top of the playedCard)
     */
    public void addCard(Card playedCard, Card newCard, int cornerIndex) throws IllegalStateException {
        ArrayList<Corner> playedCardCorners = playedCard.getCorners();

        Coordinates newCardCoordinates = newCoordinates(getCoordinates(playedCard), cornerIndex);

        //  Cannot put a card on a hidden corner
        if (playedCardCorners.get(cornerIndex).getType() == CornerEnum.HIDDEN) throw new IllegalStateException("Illegal move. Cannot put a card on a hidden corner");

        //  Cannot put a card on another card
        if (getCard(newCardCoordinates) != null) throw new IllegalStateException("Illegal move. Cannot put a card on another card");

        board.put(newCardCoordinates, newCard);
        overlapCorners(playedCard);
    }

    /**
     * Adds starter card to player's game area
     * @param starterCard The starterCard
     */
    public void placeStarterCard(StarterCard starterCard) {
        Coordinates startingCoordinates = new Coordinates(0, 0);
        board.put(startingCoordinates, starterCard);
    }

    /**
     * Overlaps all 4 neighboring corners when playing a card
     * @param playedCard The played card
     */
    public void overlapCorners(Card playedCard){
        Coordinates cardCoordinates = getCoordinates(playedCard);
        int Row = cardCoordinates.getRow();
        int Column = cardCoordinates.getColumn();

        Coordinates TopLeftCard = new Coordinates (Row++, Column--);
        Coordinates TopRightCard = new Coordinates(Row++, Column++);
        Coordinates BottomLeftCard = new Coordinates(Row--, Column--);
        Coordinates BottomRightCard = new Coordinates(Row--, Column++);

        if(getCard(TopLeftCard)!=null)
            getCard(TopLeftCard).getCorners().get(CornerIndex.BOTTOM_RIGHT).overlap();
        if(getCard(TopRightCard)!=null)
            getCard(TopRightCard).getCorners().get(CornerIndex.BOTTOM_LEFT).overlap();
        if(getCard(BottomLeftCard)!=null)
            getCard(BottomLeftCard).getCorners().get(CornerIndex.TOP_RIGHT).overlap();
        if(getCard(BottomRightCard)!=null)
            getCard(BottomRightCard).getCorners().get(CornerIndex.TOP_LEFT).overlap();
    }

}
