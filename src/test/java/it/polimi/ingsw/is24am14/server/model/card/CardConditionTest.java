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

        gameBoard.getBoard().put(new Coordinates(0, 0), testCard);

        assertTrue(testingCondition.isSatisfied(gameBoard));
    }

    @Test
    void isSingleCardPatternMultipleCardBoardSatisfied() {
        CardCondition testingCondition = new CardCondition();
        testingCondition.addClause(new Coordinates(0, 0), CornerEnum.ResourceEnum.PLANT);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners1 = new ArrayList<>();
        corners1.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners1.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners1.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners1.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard1 = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL,corners1, corners1, "front.jpg", "back.jpg");

        ArrayList<Corner> corners2 = new ArrayList<>();
        corners2.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners2.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners2.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners2.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard2 = new ResourceCard(1, CornerEnum.ResourceEnum.PLANT,corners2, corners2, "front.jpg", "back.jpg");

        gameBoard.getBoard().put(new Coordinates(0, 0), testCard1);
        gameBoard.getBoard().put(new Coordinates(1, 1), testCard2);

        assertTrue(testingCondition.isSatisfied(gameBoard));
    }

    @Test
    void isMultipleCardPatternMultipleCardBoardSatisfied() {
        CardCondition testingCondition = new CardCondition();
        testingCondition.addClause(new Coordinates(0, 0), CornerEnum.ResourceEnum.PLANT);
        testingCondition.addClause(new Coordinates(1, 1), CornerEnum.ResourceEnum.PLANT);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners1 = new ArrayList<>();
        corners1.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners1.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners1.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners1.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard1 = new ResourceCard(1, CornerEnum.ResourceEnum.PLANT,corners1, corners1, "front.jpg", "back.jpg");

        ArrayList<Corner> corners2 = new ArrayList<>();
        corners2.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners2.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners2.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners2.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard2 = new ResourceCard(1, CornerEnum.ResourceEnum.PLANT,corners2, corners2, "front.jpg", "back.jpg");

        gameBoard.getBoard().put(new Coordinates(0, 0), testCard1);
        gameBoard.getBoard().put(new Coordinates(1, 1), testCard2);

        assertTrue(testingCondition.isSatisfied(gameBoard));
    }
}