package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.utils.GoldCardDeckCreator;
import it.polimi.ingsw.is24am14.server.utils.ResourceCardDeckCreator;
import it.polimi.ingsw.is24am14.server.utils.StarterCardDeckCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTypeAdapterTest {
    @Test
    void goldCardTest() {
        Gson gson = InitGSON.init();
        GoldCardDeckCreator goldCardDeckCreator = new GoldCardDeckCreator();
        Deck<GoldCard> goldDeck = goldCardDeckCreator.createGoldCardDeck();

        while (!goldDeck.isEmpty()) {
            Card card = goldDeck.removeTop();
            String json = gson.toJson(card);
            Card cardFromJson = gson.fromJson(json, Card.class);

            //  assert attributes are equal
            assertEquals(card.getCardType(), cardFromJson.getCardType());
            assertEquals(card.getImage(), cardFromJson.getImage());
            assertEquals(card.getFrontImage(), cardFromJson.getFrontImage());
            assertEquals(card.getBackImage(), cardFromJson.getBackImage());

            //  assert front corners are equal
            for (int i = 0; i < card.getFrontCorners().size(); i++) {
                assertEquals(card.getFrontCorners().get(i).getType(), cardFromJson.getFrontCorners().get(i).getType());
                assertEquals(card.getFrontCorners().get(i).isOverlapped(), cardFromJson.getFrontCorners().get(i).isOverlapped());

                card.getFrontCorners().get(i).overlap();
                cardFromJson = gson.fromJson(gson.toJson(card), Card.class);
                assertEquals(card.getFrontCorners().get(i).isOverlapped(), cardFromJson.getFrontCorners().get(i).isOverlapped());
            }

            card.flipSide();
            cardFromJson = gson.fromJson(gson.toJson(card), Card.class);
            assertEquals(card.getSide(), cardFromJson.getSide());

            //  assert back corners are equal
            for (int i = 0; i < card.getBackCorners().size(); i++) {
                assertEquals(card.getBackCorners().get(i).getType(), cardFromJson.getBackCorners().get(i).getType());
                assertEquals(card.getBackCorners().get(i).isOverlapped(), cardFromJson.getBackCorners().get(i).isOverlapped());

                card.getBackCorners().get(i).overlap();
                cardFromJson = gson.fromJson(gson.toJson(card), Card.class);
                assertEquals(card.getBackCorners().get(i).isOverlapped(), cardFromJson.getBackCorners().get(i).isOverlapped());
            }
        }
    }

    @Test
    void resourceCardTest() {
        Gson gson = InitGSON.init();
        ResourceCardDeckCreator resourceCardDeckCreator = new ResourceCardDeckCreator();
        Deck<ResourceCard> resourceDeck = resourceCardDeckCreator.createResourceCardDeck();

        while (!resourceDeck.isEmpty()) {
            Card card = resourceDeck.removeTop();
            String json = gson.toJson(card);
            Card cardFromJson = gson.fromJson(json, Card.class);

            //  assert attributes are equal
            assertEquals(card.getCardType(), cardFromJson.getCardType());
            assertEquals(card.getImage(), cardFromJson.getImage());
            assertEquals(card.getFrontImage(), cardFromJson.getFrontImage());
            assertEquals(card.getBackImage(), cardFromJson.getBackImage());

            //  assert front corners are equal
            for (int i = 0; i < card.getFrontCorners().size(); i++) {
                assertEquals(card.getFrontCorners().get(i).getType(), cardFromJson.getFrontCorners().get(i).getType());
                assertEquals(card.getFrontCorners().get(i).isOverlapped(), cardFromJson.getFrontCorners().get(i).isOverlapped());

                card.getFrontCorners().get(i).overlap();
                cardFromJson = gson.fromJson(gson.toJson(card), Card.class);
                assertEquals(card.getFrontCorners().get(i).isOverlapped(), cardFromJson.getFrontCorners().get(i).isOverlapped());
            }

            card.flipSide();
            cardFromJson = gson.fromJson(gson.toJson(card), Card.class);
            assertEquals(card.getSide(), cardFromJson.getSide());

            //  assert back corners are equal
            for (int i = 0; i < card.getBackCorners().size(); i++) {
                assertEquals(card.getBackCorners().get(i).getType(), cardFromJson.getBackCorners().get(i).getType());
                assertEquals(card.getBackCorners().get(i).isOverlapped(), cardFromJson.getBackCorners().get(i).isOverlapped());

                card.getBackCorners().get(i).overlap();
                cardFromJson = gson.fromJson(gson.toJson(card), Card.class);
                assertEquals(card.getBackCorners().get(i).isOverlapped(), cardFromJson.getBackCorners().get(i).isOverlapped());
            }
        }
    }

    @Test
    void starterCardTest() {
        Gson gson = InitGSON.init();
        StarterCardDeckCreator starterCardDeckCreator = new StarterCardDeckCreator();
        Deck<StarterCard> starterCardDeck = starterCardDeckCreator.createStarterCardDeck();

        while (!starterCardDeck.isEmpty()) {
            Card card = starterCardDeck.removeTop();
            String json = gson.toJson(card);
            Card cardFromJson = gson.fromJson(json, Card.class);

            //  assert attributes are equal
            assertEquals(card.getCardType(), cardFromJson.getCardType());
            assertEquals(card.getImage(), cardFromJson.getImage());
            assertEquals(card.getFrontImage(), cardFromJson.getFrontImage());
            assertEquals(card.getBackImage(), cardFromJson.getBackImage());

            //  assert front corners are equal
            for (int i = 0; i < card.getFrontCorners().size(); i++) {
                assertEquals(card.getFrontCorners().get(i).getType(), cardFromJson.getFrontCorners().get(i).getType());
                assertEquals(card.getFrontCorners().get(i).isOverlapped(), cardFromJson.getFrontCorners().get(i).isOverlapped());

                card.getFrontCorners().get(i).overlap();
                cardFromJson = gson.fromJson(gson.toJson(card), Card.class);
                assertEquals(card.getFrontCorners().get(i).isOverlapped(), cardFromJson.getFrontCorners().get(i).isOverlapped());
            }

            card.flipSide();
            cardFromJson = gson.fromJson(gson.toJson(card), Card.class);
            assertEquals(card.getSide(), cardFromJson.getSide());

            //  assert back corners are equal
            for (int i = 0; i < card.getBackCorners().size(); i++) {
                assertEquals(card.getBackCorners().get(i).getType(), cardFromJson.getBackCorners().get(i).getType());
                assertEquals(card.getBackCorners().get(i).isOverlapped(), cardFromJson.getBackCorners().get(i).isOverlapped());

                card.getBackCorners().get(i).overlap();
                cardFromJson = gson.fromJson(gson.toJson(card), Card.class);
                assertEquals(card.getBackCorners().get(i).isOverlapped(), cardFromJson.getBackCorners().get(i).isOverlapped());
            }
        }
    }
}