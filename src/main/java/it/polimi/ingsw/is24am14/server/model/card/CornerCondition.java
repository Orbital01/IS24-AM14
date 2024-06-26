package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.util.Map;

public class CornerCondition implements Condition {

    @Override
    public boolean isSatisfied(GameArea board) {
        return numSatisfied(board) > 0;
    }

    @Override
    public int numSatisfied(GameArea board) {
        int num = 0;
        Coordinates tmpCoordinates;
        Coordinates coordinates = getCoordinates(board);

        if (coordinates == null) throw new IllegalArgumentException("Corner condition null");
        //  top left
        tmpCoordinates = Coordinates.newCoordinates(coordinates, 0);
        if (board.getCard(tmpCoordinates) != null && board.getCard(tmpCoordinates).getCorners().get(3).isOverlapped()) {
            num++;
        }

        //  top right
        tmpCoordinates = Coordinates.newCoordinates(coordinates, 1);
        if (board.getCard(tmpCoordinates) != null && board.getCard(tmpCoordinates).getCorners().get(2).isOverlapped()) {
            num++;
        }

        //  bottom left
        tmpCoordinates = Coordinates.newCoordinates(coordinates, 2);
        if (board.getCard(tmpCoordinates) != null && board.getCard(tmpCoordinates).getCorners().get(1).isOverlapped()) {
            num++;
        }

        //  bottom right
        tmpCoordinates = Coordinates.newCoordinates(coordinates, 3);
        if (board.getCard(tmpCoordinates) != null && board.getCard(tmpCoordinates).getCorners().get(0).isOverlapped()) {
            num++;
        }

        return num;
    }
    @Override
    public String toString() {
        return "CornerCondition";
    }

    public Coordinates getCoordinates(GameArea board) {
        for (Map.Entry<Coordinates, Card> entry : board.getBoard().entrySet()) {
            if (entry.getValue().getPointCondition() == this) return entry.getKey();
        }
        return null;
    }
}
