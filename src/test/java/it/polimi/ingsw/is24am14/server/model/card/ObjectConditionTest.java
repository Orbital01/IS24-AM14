package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ObjectConditionTest {

    @Test
    void addNullClause() {
        ObjectCondition testing = new ObjectCondition();
        assertThrows(NullPointerException.class, () -> {
            testing.addClause(null);
        });
    }

    @Test
    void isNullSatisfied() {
        ObjectCondition testing = new ObjectCondition();
        assertThrows(NullPointerException.class, () -> {
            testing.isSatisfied(null);
        });
    }

    @Test
    void isOneObjectOneCardSatisfied() {
        ObjectCondition testingCondition = new ObjectCondition();
        testingCondition.addClause(CornerEnum.ObjectEnum.INKWELL);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL,corners, corners, "front.jpg", "back.jpg");

        gameBoard.board.put(new Coordinates(0, 0), testCard);

        assertTrue(testingCondition.isSatisfied(gameBoard));
    }

    @Test
    void isOneObjectOneCardNotSatisfied() {
        ObjectCondition testingCondition = new ObjectCondition();
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.ObjectEnum.EMPTY));
        corners.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL,corners, corners, "front.jpg", "back.jpg");

        gameBoard.board.put(new Coordinates(0, 0), testCard);

        assertFalse(testingCondition.isSatisfied(gameBoard));
    }

    @Test
    void isMoreObjectsOneCardSatisfied() {
        ObjectCondition testingCondition = new ObjectCondition();
        testingCondition.addClause(CornerEnum.ObjectEnum.INKWELL);
        testingCondition.addClause(CornerEnum.ObjectEnum.INKWELL);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL,corners, corners, "front.jpg", "back.jpg");

        gameBoard.board.put(new Coordinates(0, 0), testCard);

        assertTrue(testingCondition.isSatisfied(gameBoard));
    }

    @Test
    void isOneObjectMoreCardSatisfied() {
        ObjectCondition testingCondition = new ObjectCondition();
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners1 = new ArrayList<>();
        corners1.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners1.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners1.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners1.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard1 = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners1, corners1, "front.jpg", "back.jpg");

        ArrayList<Corner> corners2 = new ArrayList<>();
        corners2.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners2.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners2.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        corners2.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard2 = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners2, corners2, "front.jpg", "back.jpg");

        gameBoard.board.put(new Coordinates(0, 0), testCard1);
        gameBoard.board.put(new Coordinates(1, 1), testCard2);

        assertTrue(testingCondition.isSatisfied(gameBoard));
    }

    //  Not passed. There is an error in GameArea.getCard(Coordinates)
    @Test
    void isOneObjectOverlappedCardNotSatisfied() {
        ObjectCondition testingCondition = new ObjectCondition();
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners1 = new ArrayList<>();
        corners1.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners1.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        corners1.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners1.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard1 = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners1, corners1, "front.jpg", "back.jpg");

        ArrayList<Corner> corners2 = new ArrayList<>();
        corners2.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners2.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners2.add(new Corner(CornerEnum.ObjectEnum.EMPTY));
        corners2.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard2 = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners2, corners2, "front.jpg", "back.jpg");

        gameBoard.board.put(new Coordinates(0, 0), testCard1);
        gameBoard.board.put(new Coordinates(1, 1), testCard2);

        assertFalse(testingCondition.isSatisfied(gameBoard));
    }

    @Test
    void isMoreObjectsMoreCardSatisfied() {
        ObjectCondition testingCondition = new ObjectCondition();
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners1 = new ArrayList<>();
        corners1.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners1.add(new Corner(CornerEnum.ObjectEnum.EMPTY));
        corners1.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        corners1.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard1 = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners1, corners1, "front.jpg", "back.jpg");

        ArrayList<Corner> corners2 = new ArrayList<>();
        corners2.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners2.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners2.add(new Corner(CornerEnum.ObjectEnum.EMPTY));
        corners2.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));

        ResourceCard testCard2 = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners2, corners2, "front.jpg", "back.jpg");

        gameBoard.board.put(new Coordinates(0, 0), testCard1);
        gameBoard.board.put(new Coordinates(1, 1), testCard2);

        assertTrue(testingCondition.isSatisfied(gameBoard));
    }

    @Test
    void oneCardNumObjects() {
        ObjectCondition testingCondition = new ObjectCondition();
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        corners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        corners.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners, corners, "front.jpg", "back.jpg");

        gameBoard.board.put(new Coordinates(0, 0), testCard);

        assertEquals(2, testingCondition.numObjects(gameBoard));
    }

    @Test
    void oneCardNotObjects() {
        ObjectCondition testingCondition = new ObjectCondition();
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        corners.add(new Corner(CornerEnum.ObjectEnum.EMPTY));
        corners.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners, corners, "front.jpg", "back.jpg");

        gameBoard.board.put(new Coordinates(0, 0), testCard);

        assertNotEquals(2, testingCondition.numObjects(gameBoard));
    }

    @Test
    void moreCardNumObjects() {
        ObjectCondition testingCondition = new ObjectCondition();
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners1 = new ArrayList<>();
        corners1.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners1.add(new Corner(CornerEnum.ObjectEnum.EMPTY));
        corners1.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        corners1.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard1 = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners1, corners1, "front.jpg", "back.jpg");

        ArrayList<Corner> corners2 = new ArrayList<>();
        corners2.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners2.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners2.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        corners2.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));

        ResourceCard testCard2 = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners2, corners2, "front.jpg", "back.jpg");

        gameBoard.board.put(new Coordinates(0, 0), testCard1);
        gameBoard.board.put(new Coordinates(1, 1), testCard2);

        assertEquals(3, testingCondition.numObjects(gameBoard));
    }

    @Test
    void moreCardNotNumObjects() {
        ObjectCondition testingCondition = new ObjectCondition();
        testingCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners1 = new ArrayList<>();
        corners1.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners1.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        corners1.add(new Corner(CornerEnum.ObjectEnum.EMPTY));
        corners1.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard1 = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners1, corners1, "front.jpg", "back.jpg");

        ArrayList<Corner> corners2 = new ArrayList<>();
        corners2.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners2.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners2.add(new Corner(CornerEnum.ObjectEnum.EMPTY));
        corners2.add(new Corner(CornerEnum.ObjectEnum.HIDDEN));

        ResourceCard testCard2 = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners2, corners2, "front.jpg", "back.jpg");

        gameBoard.board.put(new Coordinates(0, 0), testCard1);
        gameBoard.board.put(new Coordinates(1, 1), testCard2);

        assertNotEquals(1, testingCondition.numObjects(gameBoard));
    }
}