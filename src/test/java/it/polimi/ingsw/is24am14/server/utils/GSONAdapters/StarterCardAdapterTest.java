package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.Deck;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.utils.StarterCardDeckCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StarterCardAdapterTest {
    @Test
    void test() {
        Gson gson = InitGSON.init();
        StarterCardDeckCreator starterCardDeckCreator = new StarterCardDeckCreator();
        Deck<StarterCard> deck = starterCardDeckCreator.createStarterCardDeck();

        while (!deck.isEmpty()) {
            StarterCard starterCard = deck.removeTop();
            String json = gson.toJson(starterCard);
            StarterCard actual = gson.fromJson(json, StarterCard.class);

            assertEquals(starterCard.getSide(), actual.getSide());
            assertEquals(starterCard.getFrontImage(), actual.getFrontImage());
            assertEquals(starterCard.getBackImage(), actual.getBackImage());
            assertEquals(starterCard.getCardType(), actual.getCardType());

            for (int i = 0; i < starterCard.getFrontCorners().size(); i++) {
                assertEquals(starterCard.getFrontCorners().get(i).getType(), actual.getFrontCorners().get(i).getType());
                assertEquals(starterCard.getFrontCorners().get(i).isOverlapped(), actual.getFrontCorners().get(i).isOverlapped());
            }

            for (int i = 0; i < starterCard.getBackCorners().size(); i++) {
                assertEquals(starterCard.getBackCorners().get(i).getType(), actual.getBackCorners().get(i).getType());
                assertEquals(starterCard.getBackCorners().get(i).isOverlapped(), actual.getBackCorners().get(i).isOverlapped());
            }

            for (int i = 0; i < starterCard.getResources().size(); i++) {
                assertEquals(starterCard.getResources().get(i), actual.getResources().get(i));
            }
        }
    }
}