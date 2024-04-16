package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardConditionTest {

    @Test
    void addNullNotNullClause() {
        CardCondition testingCondition = new CardCondition();
        assertThrows(NullPointerException.class, () -> {
            testingCondition.addClause(null, CornerEnum.ResourceEnum.PLANT);
        });
    }

    @Test
    void addNotNullNullClause() {
        CardCondition testingCondition = new CardCondition();
        assertThrows(NullPointerException.class, () -> {
            testingCondition.addClause(new Coordinates(0, 0), null);
        });
    }

    @Test
    void addNullNullClause() {
        CardCondition testingCondition = new CardCondition();
        assertThrows(NullPointerException.class, () -> {
            testingCondition.addClause(null, null);
        });
    }

    @Test
    void isSingleCardPatternSingleCardBoardSatisfied() {
        CardCondition testingCondition = new CardCondition();
        testingCondition.addClause(new Coordinates(0, 0), CornerEnum.ResourceEnum.PLANT);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard = new ResourceCard(1, CornerEnum.ResourceEnum.PLANT,corners, corners, "front.jpg", "back.jpg");

        gameBoard.board.put(new Coordinates(0, 0), testCard);

        assertTrue(testingCondition.isSatisfied(gameBoard));
    }
}