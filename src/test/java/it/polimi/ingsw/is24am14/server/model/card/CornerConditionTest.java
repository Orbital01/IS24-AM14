package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CornerConditionTest {

    Card createGoldCardCornerCondition() {
        ArrayList<Corner> corners = new ArrayList<>();
        CornerCondition cornerCondition = new CornerCondition();

        corners.add(new Corner(CornerEnum.EMPTY));
        corners.add(new Corner(CornerEnum.EMPTY));
        corners.add(new Corner(CornerEnum.EMPTY));
        corners.add(new Corner(CornerEnum.EMPTY));
        return new GoldCard(3, cornerCondition, CornerEnum.ResourceEnum.FUNGI, new ResourceCondition(), corners, "/images/cards/gold_cards/red_fronts/page_49.png", "/images/cards/gold_cards/red_backs/page_49.png");
    }

    @Test
    void oneCornerCovered() {
        GameArea board = new GameArea();
        StarterCard starterCard = CardConditionTest.createStarterCard();
        board.placeStarterCard(starterCard);

        Card card = createGoldCardCornerCondition();
        board.addCard(starterCard, card, 0);

        assertEquals(1, card.getPointCondition().numSatisfied(board));
    }

    @Test
    void twoCornerCovered() {
        GameArea board = new GameArea();
        StarterCard starterCard = CardConditionTest.createStarterCard();
        board.placeStarterCard(starterCard);

        Card card1 = createGoldCardCornerCondition();
        board.addCard(starterCard, card1, 0);

        Card card2 = createGoldCardCornerCondition();
        board.addCard(starterCard, card2, 1);

        Card card3 = createGoldCardCornerCondition();
        board.addCard(card1, card3, 1);

        assertEquals(2, card3.getPointCondition().numSatisfied(board));
    }

    @Test
    void threeCornerCovered() {
        GameArea board = new GameArea();
        Card card1 = CardConditionTest.createStarterCard();
        Card card2 = CardConditionTest.createStarterCard();
        Card card3 = CardConditionTest.createStarterCard();
        Card card4 = createGoldCardCornerCondition();

        board.getBoard().put(new Coordinates(1, 1), card1);
        board.getBoard().put(new Coordinates(-1, 1), card2);
        board.getBoard().put(new Coordinates(-1, -1), card3);

        board.addCard(card1, card4, 2);

        assertEquals(3, card4.getPointCondition().numSatisfied(board));
    }

    @Test
    void fourCornerCovered() {
        GameArea board = new GameArea();
        Card card1 = CardConditionTest.createStarterCard();
        Card card2 = CardConditionTest.createStarterCard();
        Card card3 = CardConditionTest.createStarterCard();
        Card card4 = CardConditionTest.createStarterCard();
        Card card5 = createGoldCardCornerCondition();

        board.getBoard().put(new Coordinates(1, 1), card1);
        board.getBoard().put(new Coordinates(-1, 1), card2);
        board.getBoard().put(new Coordinates(-1, -1), card3);
        board.getBoard().put(new Coordinates(1, -1), card4);

        board.addCard(card1, card5, 2);

        assertEquals(4, card5.getPointCondition().numSatisfied(board));
    }
}