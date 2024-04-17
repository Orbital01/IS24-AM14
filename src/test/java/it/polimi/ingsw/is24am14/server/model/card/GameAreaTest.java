package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.util.ArrayList;


class GameAreaTest {
    private GameArea testBoard;
    private Card placedDownCard;
    private Card playedCard;

    private StarterCard starterCard;

    @Nested
    class TestsWithSetup {
        @BeforeEach
        public void testSetup() {
            testBoard = new GameArea();
            ArrayList<Corner> cardCorners = new ArrayList<>();
            cardCorners.add(new Corner(CornerEnum.EMPTY));
            cardCorners.add(new Corner(CornerEnum.HIDDEN));
            cardCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
            cardCorners.add(new Corner(CornerEnum.ObjectEnum.QUILL));

            ArrayList<CornerEnum.ResourceEnum> starterCardResources = new ArrayList<>();
            starterCardResources.add(CornerEnum.ResourceEnum.PLANT);
            starterCardResources.add(CornerEnum.ResourceEnum.FUNGI);


            placedDownCard = new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, cardCorners, cardCorners, "imgPath", "imgPath");
            playedCard = new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, cardCorners, cardCorners, "imgPath", "imgPath");
            Card secondPlacedCard = new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, cardCorners, cardCorners, "imgPath", "imgPath");
            StarterCard starterCard = new StarterCard(cardCorners, cardCorners, starterCardResources, "imgPath", "imgPath");

            Coordinates startingCoordinates = new Coordinates(0, 0);
            Coordinates secondCardCoordinates = new Coordinates(1, 1);
            Coordinates overlappedCoordinates = new Coordinates(-1, -1);

            testBoard.getBoard().put(startingCoordinates, placedDownCard);
            testBoard.getBoard().put(secondCardCoordinates, secondPlacedCard);
            testBoard.getBoard().put(overlappedCoordinates, secondPlacedCard);
        }


        /**
         * Tests getCard method in normal conditions
         */
        @Test
        void getCardTestExistingCoordinates() {
            Coordinates existingCoords = new Coordinates(0, 0);
            Card cardToFind = testBoard.getCard(existingCoords);
            assertNotNull(cardToFind);
            assertSame(placedDownCard, cardToFind);
        }

        /**
         * Tests getCard method when trying to fetch a card from coordinates not existing in the testBoard
         */
        @Test
        void getCardTestNotExistingCoordinates() {
            Coordinates notExistingCoords = new Coordinates(-1, 1);
            Card cardToFind = testBoard.getCard(notExistingCoords);
            assertNull(cardToFind);
        }

        //addCard tests

        /**
         * Tests addCard method in normal conditions
         */
        @Test
        void addCardTestDefaultCase() {
            testBoard.addCard(placedDownCard, playedCard, 0);
            Coordinates playedCardCoordinates = new Coordinates(1, -1);
            assertSame(playedCard, testBoard.getCard(playedCardCoordinates));
        }

        /**
         * Tests if addCard method throws exception when trying to place a card on top of a HIDDEN corner
         */
        @Test
        void addCardTestHiddenCornerPlacement() {
            assertThrows(IllegalStateException.class, () -> testBoard.addCard(placedDownCard, playedCard, 1));
        }

        /**
         * Tests if addCard method throws exception when trying to place a card to overlap another
         */
        @Test
        void addCardTestOverlappingPlacement() {
            assertThrows(IllegalStateException.class, () -> testBoard.addCard(placedDownCard, playedCard, 2));
        }
    }
    @Nested
    class IgnoreBeforeEach {
        GameArea testBoard = new GameArea();

        /**
         * Tests if setStarterCard method effectively places down card at starting coordinates
         */
        @Test
        void setStarterCardTest() {
            Coordinates startingCoordinates = new Coordinates(0, 0);
            testBoard.placeStarterCard(starterCard);
            assertEquals(starterCard, testBoard.getCard(startingCoordinates));
        }
    }

}
