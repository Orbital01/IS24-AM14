package it.polimi.ingsw.is24am14.client.printer;

import it.polimi.ingsw.is24am14.client.view.printer.RenderBoard;
import it.polimi.ingsw.is24am14.server.model.card.Corner;
import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the RenderBoard class
 * @see RenderBoard
 * it tests the printBoard method in the worst case scenario (all card on the main diagonal)
 * it also tests the card rendering in the best case scenario (all cards have different corners and overlapped one)
 */
public class RenderBoardTest {

    /**
     * This method creates a game area with 4 cards on the main diagonal
     * @return the game area
     */
    private GameArea createGameArea() {
        GameArea gameArea = new GameArea();
        StarterCard starterCard;
        ResourceCard resourceCard1;
        ResourceCard resourceCard2;
        ResourceCard resourceCard3;
        ResourceCard resourceCard4;
        ResourceCard resourceCard5;

        ArrayList<Corner> starterFrontCorners = new ArrayList<>();
        starterFrontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        starterFrontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        starterFrontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        starterFrontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        ArrayList<Corner> starterBackCorners = new ArrayList<>();
        starterBackCorners.add(new Corner(CornerEnum.EMPTY));
        starterBackCorners.add(new Corner(CornerEnum.EMPTY));
        starterBackCorners.add(new Corner(CornerEnum.HIDDEN));
        starterBackCorners.add(new Corner(CornerEnum.HIDDEN));
        ArrayList<CornerEnum.ResourceEnum> starterResources = new ArrayList<>();
        starterResources.add(CornerEnum.ResourceEnum.PLANT);
        starterResources.add(CornerEnum.ResourceEnum.ANIMAL);
        starterResources.add(CornerEnum.ResourceEnum.FUNGI);
        starterCard = new StarterCard(starterFrontCorners, starterBackCorners, starterResources, "", "");

        gameArea.placeStarterCard(starterCard);

        ArrayList<Corner> resourceBackCorners = new ArrayList<>();
        resourceBackCorners.add(new Corner(CornerEnum.EMPTY));
        resourceBackCorners.add(new Corner(CornerEnum.EMPTY));
        resourceBackCorners.add(new Corner(CornerEnum.EMPTY));
        resourceBackCorners.add(new Corner(CornerEnum.EMPTY));

        ArrayList<Corner> resourceCard1FrontCorners = new ArrayList<>();
        resourceCard1FrontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        resourceCard1FrontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        resourceCard1FrontCorners.add(new Corner(CornerEnum.EMPTY));
        resourceCard1FrontCorners.add(new Corner(CornerEnum.HIDDEN));

        resourceCard1 = new ResourceCard(0, CornerEnum.ResourceEnum.ANIMAL ,resourceCard1FrontCorners, resourceBackCorners,"", "" );

        gameArea.addCard(starterCard, resourceCard1, 0);

        ArrayList<Corner> resourceCard2FrontCorners = new ArrayList<>();
        resourceCard2FrontCorners.add(new Corner(CornerEnum.EMPTY));
        resourceCard2FrontCorners.add(new Corner(CornerEnum.EMPTY));
        resourceCard2FrontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        resourceCard2FrontCorners.add(new Corner(CornerEnum.HIDDEN));
        resourceCard2 = new ResourceCard(1, CornerEnum.ResourceEnum.PLANT , resourceCard2FrontCorners, resourceBackCorners, "", "" );

        gameArea.addCard(resourceCard1, resourceCard2, 1);

        ArrayList<Corner> resourceCard3FrontCorners = new ArrayList<>();
        resourceCard3FrontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        resourceCard3FrontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        resourceCard3FrontCorners.add(new Corner(CornerEnum.HIDDEN));
        resourceCard3FrontCorners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        resourceCard3 = new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, resourceCard3FrontCorners, resourceBackCorners, "", "");
        gameArea.addCard(resourceCard2, resourceCard3, 1);

        ArrayList<Corner> resourceCard4FrontCorners = new ArrayList<>();
        resourceCard4FrontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        resourceCard4FrontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        resourceCard4FrontCorners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        resourceCard4FrontCorners.add(new Corner(CornerEnum.EMPTY));

        resourceCard4 = new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, resourceCard4FrontCorners, resourceBackCorners, "", "");
        gameArea.addCard(resourceCard3, resourceCard4, 0);

        ArrayList<Corner> resourceCard5FrontCorners = new ArrayList<>();
        resourceCard5FrontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        resourceCard5FrontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        resourceCard5FrontCorners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        resourceCard5FrontCorners.add(new Corner(CornerEnum.EMPTY));

        resourceCard5 = new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, resourceCard5FrontCorners, resourceBackCorners, "", "");
        gameArea.addCard(resourceCard3, resourceCard5, 1);

        return gameArea;
    }

    /**
     * This test checks if the printBoard method works correctly in the worst case scenario
     */
    @Test
    void printBoardTest() {
        GameArea gameArea = createGameArea();
        RenderBoard renderBoard = new RenderBoard(gameArea);
        renderBoard.printBoard();
    }


}
