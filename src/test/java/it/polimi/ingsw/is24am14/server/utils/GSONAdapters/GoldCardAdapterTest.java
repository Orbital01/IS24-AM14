package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;
import it.polimi.ingsw.is24am14.server.model.card.Deck;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;
import it.polimi.ingsw.is24am14.server.utils.GoldCardDeckCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldCardAdapterTest {
    @Test
    void test() {
        Gson gson = InitGSON.init();
        GoldCardDeckCreator goldCardDeckCreator = new GoldCardDeckCreator();
        Deck<GoldCard> deck = goldCardDeckCreator.createGoldCardDeck();

        while (!deck.isEmpty()) {
            GoldCard goldCard = deck.removeTop();
            String json = gson.toJson(goldCard);
            GoldCard actual = gson.fromJson(json, GoldCard.class);

            assertEquals(goldCard.getCardType(), actual.getCardType());
            assertEquals(goldCard.getResource(), actual.getResource());
            assertEquals(goldCard.getPoints(), actual.getPoints());
            assertEquals(goldCard.getImage(), actual.getImage());
            assertEquals(goldCard.getBackImage(), actual.getBackImage());
            assertEquals(goldCard.getSide(), actual.getSide());

            for (int i = 0; i < goldCard.getFrontCorners().size(); i++) assertEquals(goldCard.getFrontCorners().get(i).getType(), actual.getFrontCorners().get(i).getType());

            for (int i = 0; i < goldCard.getBackCorners().size(); i++) assertEquals(goldCard.getBackCorners().get(i).getType(), actual.getBackCorners().get(i).getType());

            for (int i = 0; i < goldCard.getPlacementCondition().getListResource().size(); i++) assertEquals(goldCard.getPlacementCondition().getListResource().get(i), actual.getPlacementCondition().getListResource().get(i));
        }
    }
}