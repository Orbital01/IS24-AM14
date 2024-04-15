import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import it.polimi.ingsw.is24am14.server.model.card.* ;
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

}
