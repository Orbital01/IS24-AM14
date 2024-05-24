package it.polimi.ingsw.is24am14.client.printer;

import it.polimi.ingsw.is24am14.server.controller.InitGameState;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import org.junit.jupiter.api.*;

import it.polimi.ingsw.is24am14.server.model.player.*;
import it.polimi.ingsw.is24am14.server.controller.*;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class DrawCardTest {
    @Test
    void drawStarterCard() {
        //creo una starter card e la disegno
        Card card;

        ArrayList<Corner> empPlaInsEmp = new ArrayList<>();
        empPlaInsEmp.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        empPlaInsEmp.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        empPlaInsEmp.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        empPlaInsEmp.add(new Corner(CornerEnum.ResourceEnum.PLANT));

        ArrayList<Corner> funPlaInsAni = new ArrayList<>();
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        ArrayList<CornerEnum.ResourceEnum> insect = new ArrayList<>();
        insect.add(CornerEnum.ResourceEnum.INSECT);
        insect.add(CornerEnum.ResourceEnum.INSECT);

        card = new StarterCard(empPlaInsEmp, funPlaInsAni, insect, "src/main/resources/images/cards/starter_cards/starter_fronts/page_81.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_81.png");

        ArrayList<String> carta = card.drawCard();

        for (String s : carta) {
            System.out.println(s);
        }
    }

    @Test
    void drawGoldCard() {
        Card card;
        CornerCondition cornerCondition = new CornerCondition();
        ArrayList<Corner> empHidEmpEmp = new ArrayList<>();
        empHidEmpEmp.add(new Corner(CornerEnum.EMPTY));
        empHidEmpEmp.add(new Corner(CornerEnum.HIDDEN));
        empHidEmpEmp.add(new Corner(CornerEnum.EMPTY));
        empHidEmpEmp.add(new Corner(CornerEnum.EMPTY));

        ResourceCondition threeFunOneIns = new ResourceCondition();
        threeFunOneIns.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFunOneIns.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFunOneIns.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFunOneIns.addClause(CornerEnum.ResourceEnum.INSECT);
        //threeFunOneIns.addClause(CornerEnum.ResourceEnum.PLANT);
        card = new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.FUNGI, threeFunOneIns, empHidEmpEmp,  "src/main/resources/images/cards/gold_cards/red_fronts/page_46.png", "src/main/resources/images/cards/gold_cards/red_back/page_46.png");

        ArrayList<String> carta = card.drawCard();

        for (String s : carta) {
            System.out.println(s);
        }
    }

    @Test
    void drawStarterCardFront(){
        ArrayList<Corner> starterCorners = new ArrayList<>();
        ArrayList<Corner> starterBackCorners = new ArrayList<>();
        starterCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        starterCorners.add(new Corner(CornerEnum.EMPTY));
        starterCorners.add(new Corner(CornerEnum.EMPTY));
        starterCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        starterBackCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        starterBackCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        starterBackCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        starterBackCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        ArrayList<CornerEnum.ResourceEnum> starterResource = new ArrayList<>();
        starterResource.add(CornerEnum.ResourceEnum.FUNGI);

        StarterCard starterCardTest = new StarterCard(starterCorners, starterBackCorners, starterResource, "Foggia", "Foggia" );

        ArrayList<String> carta = starterCardTest.drawCard();

        for (String s : carta) {
            System.out.println(s);
        }
    }

    @Test
    void drawStarterCardBack(){
        ArrayList<Corner> starterCorners = new ArrayList<>();
        ArrayList<Corner> starterBackCorners = new ArrayList<>();
        starterCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        starterCorners.add(new Corner(CornerEnum.EMPTY));
        starterCorners.add(new Corner(CornerEnum.EMPTY));
        starterCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        starterBackCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        starterBackCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        starterBackCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        starterBackCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        ArrayList<CornerEnum.ResourceEnum> starterResource = new ArrayList<>();
        starterResource.add(CornerEnum.ResourceEnum.FUNGI);

        StarterCard starterCardTest = new StarterCard(starterCorners, starterBackCorners, starterResource, "Foggia", "Foggia" );
        starterCardTest.flipSide();

        ArrayList<String> carta = starterCardTest.drawCard();

        for (String s : carta) {
            System.out.println(s);
        }
    }

    @Test
    void drawGoldCard2(){
        ResourceCondition placementCondition = new ResourceCondition();
        placementCondition.addClause(CornerEnum.ResourceEnum.PLANT);
        placementCondition.addClause(CornerEnum.ResourceEnum.PLANT);
        placementCondition.addClause(CornerEnum.ResourceEnum.ANIMAL);
        ArrayList<Corner> goldCorners = new ArrayList<>();
        goldCorners.add(new Corner(CornerEnum.EMPTY));
        goldCorners.add(new Corner(CornerEnum.HIDDEN));
        goldCorners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        goldCorners.add(new Corner(CornerEnum.EMPTY));
        ObjectCondition inkwellConditionTest = new ObjectCondition();
        inkwellConditionTest.addClause(CornerEnum.ObjectEnum.INKWELL);

        GoldCard goldCardTest = new GoldCard(1, inkwellConditionTest, CornerEnum.ResourceEnum.PLANT , placementCondition, goldCorners, "Foggia", "Foggia" );
        goldCardTest.flipSide();

        ArrayList<String> carta = goldCardTest.drawCard();

        for (String s : carta) {
            System.out.println(s);
        }
    }

    @Test
    void drawObjectObjectiveCard(){ //ObjectiveCard with ObjectCondition
        ObjectCondition objectiveObjectCondition = new ObjectCondition();
        objectiveObjectCondition.addClause(CornerEnum.ObjectEnum.QUILL);
        objectiveObjectCondition.addClause(CornerEnum.ObjectEnum.INKWELL);
        objectiveObjectCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);

        ObjectiveCard objectiveCardTest = new ObjectiveCard(objectiveObjectCondition, "Foggia","Foggia",3);
        //objectiveCardTest.flipSide();

        ArrayList<String> carta = objectiveCardTest.drawCard();

        for (String s : carta) {
            System.out.println(s);
        }
    }

    @Test
    void drawCardConditionObjectiveCard1(){ //ObjectiveCard with CardCondition
        Coordinates animTopLeft = new Coordinates(1, -1);
        Coordinates insMid = new Coordinates(0, 0);
        Coordinates insBot = new Coordinates(-1, 0);
        CardCondition cardConditionTest = new CardCondition();
        cardConditionTest.addClause(animTopLeft, CornerEnum.ResourceEnum.ANIMAL);
        cardConditionTest.addClause(insMid, CornerEnum.ResourceEnum.INSECT);
        cardConditionTest.addClause(insBot, CornerEnum.ResourceEnum.INSECT);

        ObjectiveCard cardTest = new ObjectiveCard(cardConditionTest, "", "", 3);
        //objectiveCardTest.flipSide();

        ArrayList<String> carta = cardTest.drawCard();

        for (String s : carta) {
            System.out.println(s);
        }
    }

    @Test
    void drawCardConditionObjectiveCard2(){ //ObjectiveCard with CardCondition
        Coordinates animTopLeft = new Coordinates(1, -1);
        Coordinates insMid = new Coordinates(0, 0);
        Coordinates insBot = new Coordinates(-1, 1);
        CardCondition cardConditionTest = new CardCondition();
        cardConditionTest.addClause(animTopLeft, CornerEnum.ResourceEnum.ANIMAL);
        cardConditionTest.addClause(insMid, CornerEnum.ResourceEnum.INSECT);
        cardConditionTest.addClause(insBot, CornerEnum.ResourceEnum.INSECT);

        ObjectiveCard cardTest = new ObjectiveCard(cardConditionTest, "", "", 3);
        //objectiveCardTest.flipSide();

        ArrayList<String> carta = cardTest.drawCard();

        for (String s : carta) {
            System.out.println(s);
        }
    }

    @Test
    void drawResourceCard(){
        ArrayList<Corner> backCorners = new ArrayList<>();
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        ArrayList<Corner> frontCorners = new ArrayList<>();
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        ResourceCard resourceCardTest = new ResourceCard(0, CornerEnum.ResourceEnum.INSECT,frontCorners, backCorners,"","");
        //resourceCardTest.flipSide();

        ArrayList<String> carta = resourceCardTest.drawCard();

        for (String s : carta) {
            System.out.println(s);
        }
    }

    @Test
    public void drawResourceCard3(){
        ResourceCard resourceCard3;

        ArrayList<Corner> resourceCard3FrontCorners = new ArrayList<>();
        resourceCard3FrontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        resourceCard3FrontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        resourceCard3FrontCorners.add(new Corner(CornerEnum.HIDDEN));
        resourceCard3FrontCorners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));

        resourceCard3 = new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, resourceCard3FrontCorners, resourceCard3FrontCorners, "", "");

        ArrayList<String> carta = resourceCard3.drawCard();

        for (String s : carta) {
            System.out.println(s);
        }
    }
}
