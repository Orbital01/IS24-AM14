package it.polimi.ingsw.is24am14.server.utils;

import it.polimi.ingsw.is24am14.server.model.card.*;

import java.util.ArrayList;

/**
 * This class is used to create the deck of objective cards.
 */

public class ObjectiveCardDeckCreator {

    public Deck<ObjectiveCard> createObjectiveCardDeck() {

        ArrayList<ObjectiveCard> content = new ArrayList<>();
        Deck<ObjectiveCard> objectiveCardDeck = new Deck<>(content);


        Coordinates topLeft = new Coordinates(1, -1);
        Coordinates topMid = new Coordinates(1, 0);
        Coordinates topRight = new Coordinates(1, 1);
        Coordinates midLeft = new Coordinates(0, -1);
        Coordinates centre = new Coordinates(0, 0);
        Coordinates midRight = new Coordinates(0, 1);
        Coordinates bottomLeft = new Coordinates(-1, -1);
        Coordinates bottomMid = new Coordinates(-1, 0);
        Coordinates bottomRight = new Coordinates(-1, 1);


        // The four diagonal card conditions

        CardCondition redDiag = new CardCondition();
        redDiag.addClause(topRight, CornerEnum.ResourceEnum.FUNGI);
        redDiag.addClause(centre, CornerEnum.ResourceEnum.FUNGI);
        redDiag.addClause(bottomLeft, CornerEnum.ResourceEnum.FUNGI);
        content.add(new ObjectiveCard(redDiag, "src/main/resources/images/cards/objective_cards/red_fronts/page_87.png", "src/main/resources/images/cards/resource_cards/red_backs/page_87.png", 2));


        CardCondition greenDiag = new CardCondition();
        greenDiag.addClause(topLeft, CornerEnum.ResourceEnum.PLANT);
        greenDiag.addClause(centre, CornerEnum.ResourceEnum.PLANT);
        greenDiag.addClause(bottomRight, CornerEnum.ResourceEnum.PLANT);

        content.add(new ObjectiveCard(greenDiag, "src/main/resources/images/cards/objective_cards/red_fronts/page_88.png", "src/main/resources/images/cards/resource_cards/red_backs/page_88.png", 2));


        CardCondition blueDiag = new CardCondition();
        blueDiag.addClause(topRight, CornerEnum.ResourceEnum.ANIMAL);
        blueDiag.addClause(centre, CornerEnum.ResourceEnum.ANIMAL);
        blueDiag.addClause(bottomLeft, CornerEnum.ResourceEnum.ANIMAL);
        content.add(new ObjectiveCard(blueDiag, "src/main/resources/images/cards/objective_cards/red_fronts/page_89.png", "src/main/resources/images/cards/resource_cards/red_backs/page_89.png", 2));


        CardCondition purpleDiag = new CardCondition();
        purpleDiag.addClause(topLeft, CornerEnum.ResourceEnum.INSECT);
        purpleDiag.addClause(centre, CornerEnum.ResourceEnum.INSECT);
        purpleDiag.addClause(bottomRight, CornerEnum.ResourceEnum.INSECT);
        content.add(new ObjectiveCard(purpleDiag, "src/main/resources/images/cards/objective_cards/red_fronts/page_90.png", "src/main/resources/images/cards/resource_cards/red_backs/page_90.png", 2));


        // The four L-shape card conditions

        CardCondition redRedGreenL = new CardCondition();
        redRedGreenL.addClause(topLeft, CornerEnum.ResourceEnum.FUNGI);
        redRedGreenL.addClause(midLeft, CornerEnum.ResourceEnum.FUNGI);
        redRedGreenL.addClause(bottomMid, CornerEnum.ResourceEnum.PLANT);
        content.add(new ObjectiveCard(redRedGreenL, "src/main/resources/images/cards/objective_cards/red_fronts/page_91.png", "src/main/resources/images/cards/resource_cards/red_backs/page_91.png", 3));

        CardCondition greenGreenPurpleL = new CardCondition();
        greenGreenPurpleL.addClause(topRight, CornerEnum.ResourceEnum.PLANT);
        greenGreenPurpleL.addClause(midRight, CornerEnum.ResourceEnum.PLANT);
        greenGreenPurpleL.addClause(bottomLeft, CornerEnum.ResourceEnum.INSECT);
        content.add(new ObjectiveCard(greenGreenPurpleL, "src/main/resources/images/cards/objective_cards/red_fronts/page_92.png", "src/main/resources/images/cards/resource_cards/red_backs/page_92.png", 3));

        CardCondition redBlueBlueL = new CardCondition();
        redRedGreenL.addClause(topRight, CornerEnum.ResourceEnum.FUNGI);
        redRedGreenL.addClause(centre, CornerEnum.ResourceEnum.ANIMAL);
        redRedGreenL.addClause(bottomMid, CornerEnum.ResourceEnum.ANIMAL);
        content.add(new ObjectiveCard(redBlueBlueL, "src/main/resources/images/cards/objective_cards/red_fronts/page_93.png", "src/main/resources/images/cards/resource_cards/red_backs/page_93.png", 3));

        CardCondition bluePurplePurpleL = new CardCondition();
        bluePurplePurpleL.addClause(topLeft, CornerEnum.ResourceEnum.ANIMAL);
        bluePurplePurpleL.addClause(centre, CornerEnum.ResourceEnum.INSECT);
        bluePurplePurpleL.addClause(bottomMid, CornerEnum.ResourceEnum.INSECT);
        content.add(new ObjectiveCard(bluePurplePurpleL, "src/main/resources/images/cards/objective_cards/red_fronts/page_94.png", "src/main/resources/images/cards/resource_cards/red_backs/page_94.png", 3));

        // The four resource conditions

        ResourceCondition threeFungi = new ResourceCondition();
        threeFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        content.add(new ObjectiveCard(threeFungi, "src/main/resources/images/cards/objective_cards/red_fronts/page_95.png", "src/main/resources/images/cards/resource_cards/red_backs/page_95.png", 2));


        ResourceCondition threePlant = new ResourceCondition();
        threePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        content.add(new ObjectiveCard(threePlant, "src/main/resources/images/cards/objective_cards/red_fronts/page_96.png", "src/main/resources/images/cards/resource_cards/red_backs/page_96.png", 2));

        ResourceCondition threeAnimal = new ResourceCondition();
        threeAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        threeAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        threeAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        content.add(new ObjectiveCard(threeAnimal, "src/main/resources/images/cards/objective_cards/red_fronts/page_97.png", "src/main/resources/images/cards/resource_cards/red_backs/page_97.png", 2));

        ResourceCondition threeInsect = new ResourceCondition();
        threeInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        content.add(new ObjectiveCard(threeInsect, "src/main/resources/images/cards/objective_cards/red_fronts/page_98.png", "src/main/resources/images/cards/resource_cards/red_backs/page_98.png", 2));

        // The four object conditions

        ObjectCondition quiInkMan = new ObjectCondition();
        quiInkMan.addClause(CornerEnum.ObjectEnum.QUILL);
        quiInkMan.addClause(CornerEnum.ObjectEnum.INKWELL);
        quiInkMan.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        content.add(new ObjectiveCard(quiInkMan,"src/main/resources/images/cards/objective_cards/red_fronts/page_99.png", "src/main/resources/images/cards/resource_cards/red_backs/page_99.png", 3));

        ObjectCondition twoMan = new ObjectCondition();
        twoMan.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        twoMan.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        content.add(new ObjectiveCard(twoMan,"src/main/resources/images/cards/objective_cards/red_fronts/page_100.png", "src/main/resources/images/cards/resource_cards/red_backs/page_100.png", 2));

        ObjectCondition twoInk = new ObjectCondition();
        twoInk.addClause(CornerEnum.ObjectEnum.INKWELL);
        twoInk.addClause(CornerEnum.ObjectEnum.INKWELL);
        content.add(new ObjectiveCard(twoInk,"src/main/resources/images/cards/objective_cards/red_fronts/page_101.png", "src/main/resources/images/cards/resource_cards/red_backs/page_101.png", 2));

        ObjectCondition twoQui = new ObjectCondition();
        twoQui.addClause(CornerEnum.ObjectEnum.QUILL);
        twoQui.addClause(CornerEnum.ObjectEnum.QUILL);
        content.add(new ObjectiveCard(twoQui,"src/main/resources/images/cards/objective_cards/red_fronts/page_102.png", "src/main/resources/images/cards/resource_cards/red_backs/page_102.png", 2));



        return objectiveCardDeck;

    }

}
