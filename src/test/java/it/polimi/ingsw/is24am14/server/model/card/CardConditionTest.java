package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.utils.ObjectiveCardDeckCreator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardConditionTest {
    public static StarterCard createStarterCard() {
        ArrayList<Corner> empEmpEmpEmp = new ArrayList<>();
        empEmpEmpEmp.add(new Corner(CornerEnum.EMPTY));
        empEmpEmpEmp.add(new Corner(CornerEnum.EMPTY));
        empEmpEmpEmp.add(new Corner(CornerEnum.EMPTY));
        empEmpEmpEmp.add(new Corner(CornerEnum.EMPTY));
        ArrayList<Corner> insAniFunPlan = new ArrayList<>();
        insAniFunPlan.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        insAniFunPlan.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        insAniFunPlan.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        insAniFunPlan.add(new Corner(CornerEnum.ResourceEnum.PLANT));

        ArrayList<CornerEnum.ResourceEnum> plaFun = new ArrayList<>();
        plaFun.add(CornerEnum.ResourceEnum.PLANT);
        plaFun.add(CornerEnum.ResourceEnum.FUNGI);
        return new StarterCard(insAniFunPlan, empEmpEmpEmp, plaFun, "/images/cards/starter_cards/starter_backs/page_83.png", "/images/cards/starter_cards/starter_fronts/page_83.png");
    }

    public static ResourceCard createResourceCard(CornerEnum.ResourceEnum resource) {
        ArrayList<Corner> backCorners = new ArrayList<>();
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));

        ArrayList<Corner> funEmpFunHid = new ArrayList<>();
        funEmpFunHid.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funEmpFunHid.add(new Corner(CornerEnum.EMPTY));
        funEmpFunHid.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funEmpFunHid.add(new Corner(CornerEnum.EMPTY));

        return new ResourceCard(0, resource, funEmpFunHid, backCorners, "/images/cards/resource_cards/red_fronts/page_1.png", "/images/cards/resource_cards/red_backs/page_1.png");

    }

    // These tests check all 8 card-placement related conditions
    @Test
    public void fungiDiagonalCondition() {
        ObjectiveCardDeckCreator creator = new ObjectiveCardDeckCreator();
        Deck<ObjectiveCard> deck = creator.createObjectiveCardDeck();
        ObjectiveCard objectiveCard = deck.removeTop();

        GameArea board = new GameArea();
        StarterCard starterCard = createStarterCard();
        board.placeStarterCard(starterCard);

        Card card1 = createResourceCard(CornerEnum.ResourceEnum.FUNGI);
        Card card2 = createResourceCard(CornerEnum.ResourceEnum.FUNGI);
        Card card3 = createResourceCard(CornerEnum.ResourceEnum.FUNGI);

        board.addCard(starterCard, card1, 1);
        board.addCard(card1, card2, 1);

        assertFalse(objectiveCard.getCondition().isSatisfied(board));

        board.addCard(card2, card3, 1);
        assertTrue(objectiveCard.getCondition().isSatisfied(board));
    }

    @Test
    public void plantDiagonalCondition() {
        ObjectiveCardDeckCreator creator = new ObjectiveCardDeckCreator();
        Deck<ObjectiveCard> deck = creator.createObjectiveCardDeck();
        ObjectiveCard objectiveCard = null;

        for (int i = 0; i < 2; i++) objectiveCard = deck.removeTop();

        GameArea board = new GameArea();
        StarterCard starterCard = createStarterCard();
        board.placeStarterCard(starterCard);

        Card card1 = createResourceCard(CornerEnum.ResourceEnum.PLANT);
        Card card2 = createResourceCard(CornerEnum.ResourceEnum.PLANT);
        Card card3 = createResourceCard(CornerEnum.ResourceEnum.PLANT);

        board.addCard(starterCard, card1, 0);
        board.addCard(card1, card2, 0);

        assertFalse(objectiveCard.getCondition().isSatisfied(board));

        board.addCard(card2, card3, 0);
        assertTrue(objectiveCard.getCondition().isSatisfied(board));
    }

    @Test
    public void animalDiagonalCondition(){
        ObjectiveCardDeckCreator creator = new ObjectiveCardDeckCreator();
        Deck<ObjectiveCard> deck = creator.createObjectiveCardDeck();
        ObjectiveCard objectiveCard = null;

        for (int i = 0; i < 3; i++) objectiveCard = deck.removeTop();

        GameArea board = new GameArea();
        StarterCard starterCard = createStarterCard();
        board.placeStarterCard(starterCard);

        Card card1 = createResourceCard(CornerEnum.ResourceEnum.ANIMAL);
        Card card2 = createResourceCard(CornerEnum.ResourceEnum.ANIMAL);
        Card card3 = createResourceCard(CornerEnum.ResourceEnum.ANIMAL);

        board.addCard(starterCard, card1, 1);
        board.addCard(card1, card2, 1);

        assertFalse(objectiveCard.getCondition().isSatisfied(board));

        board.addCard(card2, card3, 1);
        assertTrue(objectiveCard.getCondition().isSatisfied(board));
    }

    @Test
    public void insectDiagonalCondition(){
        ObjectiveCardDeckCreator creator = new ObjectiveCardDeckCreator();
        Deck<ObjectiveCard> deck = creator.createObjectiveCardDeck();
        ObjectiveCard objectiveCard = null;

        for (int i = 0; i < 4; i++) objectiveCard = deck.removeTop();

        GameArea board = new GameArea();
        StarterCard starterCard = createStarterCard();
        board.placeStarterCard(starterCard);

        Card card1 = createResourceCard(CornerEnum.ResourceEnum.INSECT);
        Card card2 = createResourceCard(CornerEnum.ResourceEnum.INSECT);
        Card card3 = createResourceCard(CornerEnum.ResourceEnum.INSECT);

        board.addCard(starterCard, card1, 0);
        board.addCard(card1, card2, 0);

        assertFalse(objectiveCard.getCondition().isSatisfied(board));

        board.addCard(card2, card3, 0);
        assertTrue(objectiveCard.getCondition().isSatisfied(board));

    }

    @Test
    public void redRedGreenLCondition() {
        ObjectiveCardDeckCreator creator = new ObjectiveCardDeckCreator();
        Deck<ObjectiveCard> deck = creator.createObjectiveCardDeck();
        ObjectiveCard objectiveCard = null;

        for (int i = 0; i < 5; i++) objectiveCard = deck.removeTop();

        GameArea board = new GameArea();
        StarterCard starterCard = createStarterCard();
        board.placeStarterCard(starterCard);

        Card card1 = createResourceCard(CornerEnum.ResourceEnum.FUNGI);
        Card card2 = createResourceCard(CornerEnum.ResourceEnum.FUNGI);
        Card card3 = createResourceCard(CornerEnum.ResourceEnum.PLANT);

        board.addCard(starterCard, card1, 3);
        board.addCard(starterCard, card2, 1);

        assertFalse(objectiveCard.getCondition().isSatisfied(board));

        board.addCard(card1, card3, 3);

        assertTrue(objectiveCard.getCondition().isSatisfied(board));
    }

    @Test
    public void greenGreenPurpleL(){
        ObjectiveCardDeckCreator creator = new ObjectiveCardDeckCreator();
        Deck<ObjectiveCard> deck = creator.createObjectiveCardDeck();
        ObjectiveCard objectiveCard = null;

        for (int i = 0; i < 6; i++) objectiveCard = deck.removeTop();

        GameArea board = new GameArea();
        StarterCard starterCard = createStarterCard();
        board.placeStarterCard(starterCard);

        Card card1 = createResourceCard(CornerEnum.ResourceEnum.PLANT);
        Card card2 = createResourceCard(CornerEnum.ResourceEnum.PLANT);
        Card card3 = createResourceCard(CornerEnum.ResourceEnum.INSECT);

        board.addCard(starterCard, card1, 3);
        board.addCard(starterCard, card2, 1);

        assertFalse(objectiveCard.getCondition().isSatisfied(board));

        board.addCard(card1, card3, 2);

        assertTrue(objectiveCard.getCondition().isSatisfied(board));
    }

    @Test
    public void redBlueBlue() {
        ObjectiveCardDeckCreator creator = new ObjectiveCardDeckCreator();
        Deck<ObjectiveCard> deck = creator.createObjectiveCardDeck();
        ObjectiveCard objectiveCard = null;

        for (int i = 0; i < 7; i++) objectiveCard = deck.removeTop();

        GameArea board = new GameArea();
        StarterCard starterCard = createStarterCard();
        board.placeStarterCard(starterCard);

        Card card1 = createResourceCard(CornerEnum.ResourceEnum.ANIMAL);
        Card card2 = createResourceCard(CornerEnum.ResourceEnum.ANIMAL);
        Card card3 = createResourceCard(CornerEnum.ResourceEnum.FUNGI);

        board.addCard(starterCard, card1, 1);
        board.addCard(card1, card3,1);

        assertFalse(objectiveCard.getCondition().isSatisfied(board));

        board.addCard(starterCard, card2, 3);

        assertTrue(objectiveCard.getCondition().isSatisfied(board));
    }

    @Test
    public void purplePurpleBlueL(){
        ObjectiveCardDeckCreator creator = new ObjectiveCardDeckCreator();
        Deck<ObjectiveCard> deck = creator.createObjectiveCardDeck();
        ObjectiveCard objectiveCard = null;

        for (int i = 0; i < 8; i++) objectiveCard = deck.removeTop();

        GameArea board = new GameArea();
        StarterCard starterCard = createStarterCard();
        board.placeStarterCard(starterCard);

        Card card1 = createResourceCard(CornerEnum.ResourceEnum.INSECT);
        Card card2 = createResourceCard(CornerEnum.ResourceEnum.INSECT);
        Card card3 = createResourceCard(CornerEnum.ResourceEnum.ANIMAL);

        board.addCard(starterCard, card1, 3);
        board.addCard(starterCard, card2, 1);

        assertFalse(objectiveCard.getCondition().isSatisfied(board));

        board.addCard(card2, card3, 0);

        assertTrue(objectiveCard.getCondition().isSatisfied(board));
    }
}