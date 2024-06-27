package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.utils.StarterCardDeckCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameAreaSerializerTest {
    Gson gson = InitGSON.init();
    @Test
    void emptyGameArea() {
        GameArea gameArea = new GameArea();
        String json = gson.toJson(gameArea);
        GameArea restored = gson.fromJson(json, GameArea.class);
        assertEquals(gameArea.getBoard().size(), restored.getBoard().size());
    }

    @Test
    void gameAreaWithStarterCard() {
        GameArea gameArea = new GameArea();
        StarterCardDeckCreator starterCardDeckCreator = new StarterCardDeckCreator();
        StarterCard starterCard = starterCardDeckCreator.createStarterCardDeck().removeTop();
        gameArea.placeStarterCard(starterCard);
        String json = gson.toJson(gameArea);
        GameArea restored = gson.fromJson(json, GameArea.class);

        assertEquals(gameArea.getBoard().size(), restored.getBoard().size());

        assertTrue(sameStarterCardResources(gameArea.getCard(new Coordinates(0, 0)), restored.getCard(new Coordinates(0, 0))));

        assertTrue(sameCorners(gameArea.getCard(new Coordinates(0, 0)), restored.getCard(new Coordinates(0, 0))));
    }

    boolean sameStarterCardResources(Card card1, Card card2) {
        for (int i = 0; i < card1.getCornerEnums().size(); i++) {
            if (!card1.getCornerEnums().get(i).equals(card2.getCornerEnums().get(i))) return false;
        }

        return true;
    }

    boolean sameCorners(Card card1, Card card2) {
        for (int i = 0; i < card1.getFrontCorners().size(); i++) {
            if (!card1.getFrontCorners().get(i).getType().equals(card2.getFrontCorners().get(i).getType())) return false;
            if (card1.getFrontCorners().get(i).isOverlapped() != card2.getFrontCorners().get(i).isOverlapped()) return false;
        }

        for (int i = 0; i < card1.getBackCorners().size(); i++) {
            if (!card1.getBackCorners().get(i).getType().equals(card2.getBackCorners().get(i).getType())) return false;
            if (card1.getBackCorners().get(i).isOverlapped() != card2.getBackCorners().get(i).isOverlapped()) return false;
        }

        return true;
    }
}