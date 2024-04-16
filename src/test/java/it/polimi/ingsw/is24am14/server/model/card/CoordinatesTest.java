package it.polimi.ingsw.is24am14.server.model.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.is24am14.server.model.card.Coordinates.newCoordinates;
import static it.polimi.ingsw.is24am14.server.model.card.Coordinates.add;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

//Batch of newCoordinates tests for all 4 corners
    /**
     * Tests the newCoordinates method when placing a card on the TOP_LEFT corner of the alreadyPlacedCard
     */
    @Test
    void newCoordinatesTestTopLeft(){
        int index=0;
        Coordinates startingCoordinates = new Coordinates(1, 2);
        Coordinates expectedCoordinates = new Coordinates(2, 1);
        assertEquals(expectedCoordinates.getRow(), newCoordinates(startingCoordinates, index).getRow());
        assertEquals(expectedCoordinates.getColumn(), newCoordinates(startingCoordinates, index).getColumn());
    }
    /**
     * Tests the newCoordinates method when placing a card on the TOP_RIGHT corner of the alreadyPlacedCard
     */
    @Test
    void newCoordinatesTestTopRight(){
        int index=1;
        Coordinates startingCoordinates = new Coordinates(1, 2);
        Coordinates expectedCoordinates = new Coordinates(2, 3);
        assertEquals(expectedCoordinates.getRow(), newCoordinates(startingCoordinates, index).getRow());
        assertEquals(expectedCoordinates.getColumn(), newCoordinates(startingCoordinates, index).getColumn());
    }
    /**
     * Tests the newCoordinates method when placing a card on the BOTTOM_LEFT corner of the alreadyPlacedCard
     */
    @Test
    void newCoordinatesTestBottomLeft(){
        int index=2;
        Coordinates startingCoordinates = new Coordinates(1, 2);
        Coordinates expectedCoordinates = new Coordinates(0, 1);
        assertEquals(expectedCoordinates.getRow(), newCoordinates(startingCoordinates, index).getRow());
        assertEquals(expectedCoordinates.getColumn(), newCoordinates(startingCoordinates, index).getColumn());
    }
    /**
     * Tests the newCoordinates method when placing a card on the BOTTOM_RIGHT corner of the alreadyPlacedCard
     */
    @Test
    void newCoordinatesTestBottomRight(){
        int index=3;
        Coordinates startingCoordinates = new Coordinates(1, 2);
        Coordinates expectedCoordinates = new Coordinates(0, 3);
        assertEquals(expectedCoordinates.getRow(), newCoordinates(startingCoordinates, index).getRow());
        assertEquals(expectedCoordinates.getColumn(), newCoordinates(startingCoordinates, index).getColumn());
    }

    /**
     * Tests if the sum of row and column index of two given coordinates is right
     */
    @Test
    void newAddTest(){
        Coordinates coordinates1 = new Coordinates(3, 5);
        Coordinates coordinates2 = new Coordinates(7, 5);
        Coordinates expectedSum = new Coordinates(10, 10);
        assertEquals(expectedSum.getRow(), add(coordinates1, coordinates2).getRow());
        assertEquals(expectedSum.getColumn(), add(coordinates1, coordinates2).getColumn());
    }

    /**
     * Another sum test but with negative coordinates
     */
    @Test
    void newAddTestNegative(){
        Coordinates coordinates1 = new Coordinates(-3, -5);
        Coordinates coordinates2 = new Coordinates(-7, -5);
        Coordinates expectedSum = new Coordinates(-10, -10);
        assertEquals(expectedSum.getRow(), add(coordinates1, coordinates2).getRow());
        assertEquals(expectedSum.getColumn(), add(coordinates1, coordinates2).getColumn());
    }

}