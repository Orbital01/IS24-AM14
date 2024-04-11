package is24am14.model.gameRelated;

import is24am14.model.cardRelated.*;

import java.util.*;

import static is24am14.model.cardRelated.Coordinates.newCoordinates;


public class GameArea {
    public HashMap<Coordinates, Card> board;

    public GameArea() {
        board = new HashMap<>();
    }

    // Receives Card as parameter and checks for the card in the GameArea map, throws exception if not found
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

    // Uses getCoordinates to obtain the Card coordinates and returns its row
    public int getRow(Card C) {
        Coordinates coordinates = getCoordinates(C);
        return coordinates.getRow();
    }

    // Same as getRow but returns its column
    public int getColumn(Card C) {
        Coordinates coordinates = getCoordinates(C);
        return coordinates.getColumn();
    }

    // Returns Card if the given coordinates contain a card, null if empty
    public Card getCard(Coordinates coordinates) {
        return board.getOrDefault(coordinates, null);
    }

    public void addCard(Card playedCard, Card newCard, int cornerIndex) throws IllegalStateException {
        ArrayList<Corner> playedCardCorners = playedCard.getSide() == EnumSide.FRONT ? playedCard.getFrontCorners() : playedCard.getBackCorners();
        Coordinates newCardCoordinates = newCoordinates(getCoordinates(playedCard), cornerIndex);

        //  cannot put a card on a hidden corner
        if (playedCardCorners.get(cornerIndex).getType() == CornerEnum.HIDDEN) throw new IllegalStateException("Illegal move. Cannot put a card on a hidden corner");

        //  cannot put a card on another card
        if (getCard(newCardCoordinates) != null) throw new IllegalStateException("Illegal move. Cannot put a card on another card");

        board.put(newCardCoordinates, newCard);
    }


}
