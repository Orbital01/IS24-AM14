package it.polimi.ingsw.is24am14.server.model.card;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class CardTest {

    private static class TestCard extends Card {
        public TestCard(ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, String frontImage, String backImage) {
            super(frontCorners, backCorners, frontImage, backImage);
        }
    }

    // this test is to check if the card is correctly generated in the front side and is correctly flipped
    @Test
    void testFlip() {
        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.EMPTY));
        corners.add(new Corner(CornerEnum.HIDDEN));

        TestCard card = new TestCard(corners, corners, "front.jpg", "back.jpg");

        assertEquals(EnumSide.FRONT, card.getSide());
        card.flipSide();
        assertEquals(EnumSide.BACK, card.getSide());
    }

    // this test is to check if the cards corner are correctly set and returned
    // i used two different arraylists for the front and back corners to test if they are correctly returned when the card is flipped
    @Test
    void testCorrectCorners() {
        ArrayList<Corner> frontCorners = new ArrayList<>();
        // use the same corners for both sides, testing all the enum values
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));

        ArrayList<Corner> backCorners = new ArrayList<>();
        backCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        backCorners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        backCorners.add(new Corner(CornerEnum.HIDDEN));
        backCorners.add(new Corner(CornerEnum.EMPTY));

        TestCard card = new TestCard(frontCorners, backCorners, "front.jpg", "back.jpg");


        assertEquals(frontCorners, card.getCorners());
        card.flipSide();
        assertEquals(backCorners, card.getCorners());

        //checking if the corners are still the same after flipping the card twice
        card.flipSide();
        assertEquals(frontCorners, card.getCorners());
    }

    // this test is to check if the card throws an exception when the number of corners is greater than 4
    @Test
    void testTooManyCorners() {
        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.EMPTY));
        corners.add(new Corner(CornerEnum.HIDDEN));
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        assertThrows(IllegalArgumentException.class, () -> new TestCard(corners, corners, "front.jpg", "back.jpg"));
    }

    //test to check if the card correctly returns the image
    @Test
    void testGetImage() {
        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.EMPTY));
        corners.add(new Corner(CornerEnum.HIDDEN));

        TestCard card = new TestCard(corners, corners, "front.jpg", "back.jpg");

        assertEquals("front.jpg", card.getImage());
        card.flipSide();
        assertEquals("back.jpg", card.getImage());
    }



    @Test
    void testGetCornerEnums() {
        ArrayList<Corner> corners = new ArrayList<>();
        corners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        corners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        corners.add(new Corner(CornerEnum.EMPTY));
        corners.add(new Corner(CornerEnum.HIDDEN));

        TestCard card = new TestCard(corners, corners, "front.jpg", "back.jpg");

        ArrayList<CornerEnum> cornerEnums = card.getCornerEnums();

        assertEquals(4, cornerEnums.size());
        assertEquals(CornerEnum.ResourceEnum.INSECT, cornerEnums.get(0));
        assertEquals(CornerEnum.ObjectEnum.INKWELL, cornerEnums.get(1));
        assertEquals(CornerEnum.EMPTY, cornerEnums.get(2));
        assertEquals(CornerEnum.HIDDEN, cornerEnums.get(3));
    }

}
