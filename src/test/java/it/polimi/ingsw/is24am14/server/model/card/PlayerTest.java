package it.polimi.ingsw.is24am14.server.model.card;

import org.junit.jupiter.api.*;

import it.polimi.ingsw.is24am14.server.model.player.*;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

/**
 * This class is used to test the Player class methods.
 */
class PlayerTest {
    /**
     * This test checks if the removeCardFromHand method throws an exception when the player's hand is empty.
     */
    @Test
    void emptyHandTest(){
        Player player = new Player("TestPlayer");
        ArrayList<PlayableCard> playerHand = new ArrayList<>();
        playerHand = player.getPlayerHand();

        //Emptying the hand
        playerHand.clear(); // ==> playerHand.size() == 0

        //Generating a random index number --> we don't care, the player hand is either way empty
        Random random = new Random();
        int index = random.nextInt(10);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            player.removeCardFromHand(index);
        });

        String expectedMessage = "The player's hand is empty, therefore it is not possible to remove a card from it.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * This test checks if the removeCardFromHand method throws an exception when the index is negative.
     */
    @Test
    void negativeIndexForHandTest() {
        //Creating a player
        Player player = new Player("TestPlayer");
        ArrayList<PlayableCard> playerHand = new ArrayList<>();
        playerHand = player.getPlayerHand();

        //Generating a test card to add to the player's hand
        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL,corners, corners, "front.jpg", "back.jpg");

        //Adding the test card to the player's hand
        player.addCardToHand(testCard);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            player.removeCardFromHand(-1);
        });

        String expectedMessage = "The index is out of bounds.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * This test checks if the removeCardFromHand method throws an exception when the index is bigger than the size of the player's hand.
     */
    @Test
    void outOfBoundsIndexForHandTest() {
        //Creating a player
        Player player = new Player("TestPlayer");
        ArrayList<PlayableCard> playerHand = new ArrayList<>();
        playerHand = player.getPlayerHand();

        //Generating a test card to add to the player's hand
        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.HIDDEN));

        ResourceCard testCard = new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL,corners, corners, "front.jpg", "back.jpg");

        //Adding the test card to the player's hand
        player.addCardToHand(testCard);
        int playerHandSize = playerHand.size();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            player.removeCardFromHand(playerHandSize + 1);
        });

        String expectedMessage = "The index is out of bounds.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /***********************************************************************************************/
    /* Tests for placeCard() method coincide with the tests for addCard() method in GameArea class */
    /***********************************************************************************************/

}
