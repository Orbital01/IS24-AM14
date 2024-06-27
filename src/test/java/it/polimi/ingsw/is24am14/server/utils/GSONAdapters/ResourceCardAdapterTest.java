package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.Deck;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;
import it.polimi.ingsw.is24am14.server.model.card.ResourceCard;
import it.polimi.ingsw.is24am14.server.utils.GoldCardDeckCreator;
import it.polimi.ingsw.is24am14.server.utils.ResourceCardDeckCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceCardAdapterTest {
    @Test
    void test() {
        Gson gson = InitGSON.init();
        ResourceCardDeckCreator resourceCardDeckCreator = new ResourceCardDeckCreator();
        Deck<ResourceCard> deck = resourceCardDeckCreator.createResourceCardDeck();

        while (!deck.isEmpty()) {
            ResourceCard resourceCard = deck.removeTop();
            String json = gson.toJson(resourceCard);
            ResourceCard actual = gson.fromJson(json, ResourceCard.class);

            assertEquals(resourceCard.getCardType(), actual.getCardType());
            assertEquals(resourceCard.getResource(), actual.getResource());
            assertEquals(resourceCard.getPoints(), actual.getPoints());
            assertEquals(resourceCard.getImage(), actual.getImage());
            assertEquals(resourceCard.getBackImage(), actual.getBackImage());
            assertEquals(resourceCard.getSide(), actual.getSide());

            for (int i = 0; i < resourceCard.getFrontCorners().size(); i++) assertEquals(resourceCard.getFrontCorners().get(i).getType(), actual.getFrontCorners().get(i).getType());

            for (int i = 0; i < resourceCard.getBackCorners().size(); i++) assertEquals(resourceCard.getBackCorners().get(i).getType(), actual.getBackCorners().get(i).getType());

            resourceCard.flipSide();

            actual = gson.fromJson(gson.toJson(resourceCard), ResourceCard.class);
            assertEquals(resourceCard.getSide(), actual.getSide());

        }
    }
}