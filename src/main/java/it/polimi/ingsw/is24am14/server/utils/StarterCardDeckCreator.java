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


        ArrayList<Corner> empPlaInsEmp = new ArrayList<>();
        empPlaInsEmp.add(new Corner(CornerEnum.EMPTY));
        empPlaInsEmp.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        empPlaInsEmp.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        empPlaInsEmp.add(new Corner(CornerEnum.EMPTY));

        ArrayList<Corner> funPlaInsAni = new ArrayList<>();
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        ArrayList<CornerEnum.ResourceEnum> insect = new ArrayList<>();
        insect.add(CornerEnum.ResourceEnum.INSECT);

        content.add(new StarterCard(funPlaInsAni, empPlaInsEmp, insect, "src/main/resources/images/cards/starter_cards/starter_fronts/page_81.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_81.png"));




        ArrayList<Corner> aniEmpEmpFun = new ArrayList<>();
        aniEmpEmpFun.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        aniEmpEmpFun.add(new Corner(CornerEnum.EMPTY));
        aniEmpEmpFun.add(new Corner(CornerEnum.EMPTY));
        aniEmpEmpFun.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        ArrayList<Corner> plaAniFunIns = new ArrayList<>();
        plaAniFunIns.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        plaAniFunIns.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        plaAniFunIns.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        plaAniFunIns.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        ArrayList<CornerEnum.ResourceEnum> fungi = new ArrayList<>();
        fungi.add(CornerEnum.ResourceEnum.FUNGI);

        content.add(new StarterCard(plaAniFunIns, aniEmpEmpFun, fungi, "src/main/resources/images/cards/starter_cards/starter_fronts/page_82.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_82.png"));




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
        content.add(new StarterCard(insAniFunPlan, empEmpEmpEmp, plaFun, "src/main/resources/images/cards/starter_cards/starter_fronts/page_83.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_83.png"));





        ArrayList<Corner> plaInsAniFun = new ArrayList<>();
        plaInsAniFun.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        plaInsAniFun.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        plaInsAniFun.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        plaInsAniFun.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        ArrayList<CornerEnum.ResourceEnum> aniIns = new ArrayList<>();
        aniIns.add(CornerEnum.ResourceEnum.ANIMAL);
        aniIns.add(CornerEnum.ResourceEnum.INSECT);
        content.add(new StarterCard(plaInsAniFun, empEmpEmpEmp, aniIns, "src/main/resources/images/cards/starter_cards/starter_fronts/page_84.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_84.png"));




        ArrayList<Corner> empEmpHidHid = new ArrayList<>();
        empEmpHidHid.add(new Corner(CornerEnum.EMPTY));
        empEmpHidHid.add(new Corner(CornerEnum.EMPTY));
        empEmpHidHid.add(new Corner(CornerEnum.HIDDEN));
        empEmpHidHid.add(new Corner(CornerEnum.HIDDEN));

        ArrayList<Corner> insFunPlaAni = new ArrayList<>();
        insFunPlaAni.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        insFunPlaAni.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        insFunPlaAni.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        insFunPlaAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        ArrayList<CornerEnum.ResourceEnum> aniInsPLa = new ArrayList<>();
        aniInsPLa.add(CornerEnum.ResourceEnum.ANIMAL);
        aniInsPLa.add(CornerEnum.ResourceEnum.INSECT);
        aniInsPLa.add(CornerEnum.ResourceEnum.PLANT);
        content.add(new StarterCard(insFunPlaAni, empEmpHidHid, aniInsPLa, "src/main/resources/images/cards/starter_cards/starter_fronts/page_85.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_85.png"));




        ArrayList<Corner> funAniPlaIns = new ArrayList<>();
        funAniPlaIns.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funAniPlaIns.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        funAniPlaIns.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        funAniPlaIns.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        ArrayList<CornerEnum.ResourceEnum> plaAniFun = new ArrayList<>();
        plaAniFun.add(CornerEnum.ResourceEnum.PLANT);
        plaAniFun.add(CornerEnum.ResourceEnum.ANIMAL);
        plaAniFun.add(CornerEnum.ResourceEnum.FUNGI);
        content.add(new StarterCard(funAniPlaIns, empEmpHidHid, plaAniFun, "src/main/resources/images/cards/starter_cards/starter_fronts/page_86.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_86.png"));

        content.get(0).flipSide();
        content.get(1).flipSide();
        content.get(2).flipSide();
        content.get(3).flipSide();
        content.get(4).flipSide();
        content.get(5).flipSide();

        return new Deck<>(content);
    }

}
