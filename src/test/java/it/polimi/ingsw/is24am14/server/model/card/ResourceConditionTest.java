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

        gameBoard.board.put(new Coordinates(0, 0), testCard);

        assertTrue(testingCondition.isSatisfied(gameBoard));
    }
}