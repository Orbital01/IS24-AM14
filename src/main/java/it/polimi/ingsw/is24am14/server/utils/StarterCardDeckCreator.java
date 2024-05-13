package it.polimi.ingsw.is24am14.server.utils;

import it.polimi.ingsw.is24am14.server.model.card.Corner;
import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;
import it.polimi.ingsw.is24am14.server.model.card.Deck;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import java.util.ArrayList;

/**
 * This class is used to create the starter card deck for the game.
 */

public class StarterCardDeckCreator {

    public Deck<StarterCard> createStarterCardDeck() {
        // Create each one of the 6 starter cards
        ArrayList<StarterCard> content = new ArrayList<>();
        Deck<StarterCard> starterCardDeck = new Deck<>(content);
        ArrayList<Corner> frontCorners = new ArrayList<>();
        ArrayList<Corner> backCorners = new ArrayList<>();
        ArrayList<CornerEnum.ResourceEnum> resources = new ArrayList<>();

        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        resources.add(CornerEnum.ResourceEnum.INSECT);
        content.addFirst(new StarterCard(frontCorners, backCorners, resources, "src/main/resources/images/cards/starter_cards/starter_fronts/page_81.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_81.png"));

        frontCorners.clear();
        backCorners.clear();
        resources.clear();



        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        resources.add(CornerEnum.ResourceEnum.FUNGI);
        content.addFirst(new StarterCard(frontCorners, backCorners, resources, "src/main/resources/images/cards/starter_cards/starter_fronts/page_82.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_82.png"));

        frontCorners.clear();
        backCorners.clear();
        resources.clear();



        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        resources.add(CornerEnum.ResourceEnum.PLANT);
        resources.add(CornerEnum.ResourceEnum.FUNGI);
        content.addFirst(new StarterCard(frontCorners, backCorners, resources, "src/main/resources/images/cards/starter_cards/starter_fronts/page_83.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_83.png"));

        frontCorners.clear();
        backCorners.clear();
        resources.clear();



        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        resources.add(CornerEnum.ResourceEnum.ANIMAL);
        resources.add(CornerEnum.ResourceEnum.INSECT);
        content.addFirst(new StarterCard(frontCorners, backCorners, resources, "src/main/resources/images/cards/starter_cards/starter_fronts/page_84.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_84.png"));

        frontCorners.clear();
        backCorners.clear();
        resources.clear();



        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        resources.add(CornerEnum.ResourceEnum.ANIMAL);
        resources.add(CornerEnum.ResourceEnum.INSECT);
        resources.add(CornerEnum.ResourceEnum.PLANT);
        content.addFirst(new StarterCard(frontCorners, backCorners, resources, "src/main/resources/images/cards/starter_cards/starter_fronts/page_85.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_85.png"));

        frontCorners.clear();
        backCorners.clear();
        resources.clear();



        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        resources.add(CornerEnum.ResourceEnum.PLANT);
        resources.add(CornerEnum.ResourceEnum.ANIMAL);
        resources.add(CornerEnum.ResourceEnum.FUNGI);
        content.addFirst(new StarterCard(frontCorners, backCorners, resources, "src/main/resources/images/cards/starter_cards/starter_fronts/page_86.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_86.png"));

        frontCorners.clear();
        backCorners.clear();
        resources.clear();


        return starterCardDeck;
    }

}
