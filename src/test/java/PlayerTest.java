import org.junit.jupiter.api.Test;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.player.*;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;



class PlayerTest {
    @Test
    void emptyHandTest(){
        Player player = new Player("TestPlayer", TokenColour.GREEN);
        ArrayList<Card> playerHand = new ArrayList<>();
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

    @Test
    void illegalIndexForHandTest() {
        Player player = new Player("TestPlayer", TokenColour.GREEN);
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand = player.getPlayerHand();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            player.removeCardFromHand(-1);
        });

        String expectedMessage = "The index is out of bounds.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /***********************************************************************************************/
    /* Tests for placeCard() method coincide with the tests for addCard() method in GameArea class */
    /***********************************************************************************************/

}
