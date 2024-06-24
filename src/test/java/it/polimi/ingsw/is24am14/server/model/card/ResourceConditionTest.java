package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResourceConditionTest {

    @Test
    void isCenterResourceSatisfied() {
        ResourceCondition testingCondition = new ResourceCondition();
        testingCondition.addClause(CornerEnum.ResourceEnum.ANIMAL);
        GameArea gameBoard = new GameArea();

        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, corners, corners, "", "");

        gameBoard.getBoard().put(new Coordinates(0, 0), testCard);

        assertFalse(testingCondition.isSatisfied(gameBoard));

        gameBoard.getCard(new Coordinates(0, 0)).flipSide();

        assertTrue(testingCondition.isSatisfied(gameBoard));
    }

    @Test
    void isMultipleResourceSatisfied() {
        ResourceCondition testingCondition = new ResourceCondition();
        testingCondition.addClause(CornerEnum.ResourceEnum.PLANT);
        testingCondition.addClause(CornerEnum.ResourceEnum.PLANT);
        testingCondition.addClause(CornerEnum.ResourceEnum.PLANT);
        testingCondition.addClause(CornerEnum.ResourceEnum.PLANT);
        testingCondition.addClause(CornerEnum.ResourceEnum.PLANT);

        GameArea gameBoard = new GameArea();

        ArrayList<Corner> cornersStarter = new ArrayList<>();
        cornersStarter.add(new Corner(CornerEnum.EMPTY));
        cornersStarter.add(new Corner(CornerEnum.EMPTY));
        cornersStarter.add(new Corner(CornerEnum.HIDDEN));
        cornersStarter.add(new Corner(CornerEnum.HIDDEN));

        ArrayList<CornerEnum.ResourceEnum> starterResources = new ArrayList<>();
        starterResources.add(CornerEnum.ResourceEnum.PLANT);
        starterResources.add(CornerEnum.ResourceEnum.ANIMAL);
        starterResources.add(CornerEnum.ResourceEnum.FUNGI);

        ArrayList<Corner> empty = new ArrayList<>();
        empty.add(new Corner(CornerEnum.EMPTY));
        empty.add(new Corner(CornerEnum.EMPTY));
        empty.add(new Corner(CornerEnum.EMPTY));
        empty.add(new Corner(CornerEnum.EMPTY));

        Card starterCard = new StarterCard(cornersStarter, empty, starterResources, "", "");

        gameBoard.getBoard().put(new Coordinates(0, 0), starterCard);

        assertFalse(testingCondition.isSatisfied(gameBoard));
    }
}