package it.polimi.ingsw.is24am14.server.network;


import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.utils.GSONAdapters.InitGSON;

import java.util.ArrayList;

public class ClientLauncher {
    public static void main(String[] args) throws Exception {
        Coordinates topMid = new Coordinates(2, 0);
        Coordinates centre = new Coordinates(0, 0);
        Coordinates bottomRight = new Coordinates(-1, 1);


        // The four diagonal card conditions

        CardCondition redRedGreenL = new CardCondition();
        redRedGreenL.addClause(centre, CornerEnum.ResourceEnum.FUNGI);
        redRedGreenL.addClause(topMid, CornerEnum.ResourceEnum.FUNGI);
        redRedGreenL.addClause(bottomRight, CornerEnum.ResourceEnum.PLANT);
        ObjectiveCard objectiveCard = new ObjectiveCard(redRedGreenL, "/images/cards/objective_cards/objective_fronts/page_91.png", "/images/cards/resource_cards/objective_backs/page_91.png", 3);
        Condition condition = objectiveCard.getCondition();



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
        StarterCard starterCard = new StarterCard(insAniFunPlan, empEmpEmpEmp, plaFun, "/images/cards/starter_cards/starter_backs/page_83.png", "/images/cards/starter_cards/starter_fronts/page_83.png");


        ArrayList<Corner> funEmpFunHid = new ArrayList<>();
        funEmpFunHid.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funEmpFunHid.add(new Corner(CornerEnum.EMPTY));
        funEmpFunHid.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funEmpFunHid.add(new Corner(CornerEnum.EMPTY));
        ArrayList<Corner> backCorners = new ArrayList<>();
        CornerEnum.ResourceEnum resource = CornerEnum.ResourceEnum.FUNGI;
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));

        ResourceCard resourceCard1 = new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, funEmpFunHid, backCorners, "/images/cards/resource_cards/red_fronts/page_1.png", "/images/cards/resource_cards/red_backs/page_1.png");
        ResourceCard resourceCard2 = new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, funEmpFunHid, backCorners, "/images/cards/resource_cards/red_fronts/page_1.png", "/images/cards/resource_cards/red_backs/page_1.png");
        ResourceCard resourceCard3 = new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, funEmpFunHid, backCorners, "/images/cards/resource_cards/red_fronts/page_1.png", "/images/cards/resource_cards/red_backs/page_1.png");


        GameArea gameArea = new GameArea();
        gameArea.placeStarterCard(starterCard);
        gameArea.addCard(starterCard, resourceCard1, 3);
        gameArea.addCard(starterCard, resourceCard2, 1);
        //gameArea.addCard(resourceCard1, resourceCard3, 3);

        System.out.println(condition.isSatisfied(gameArea));

        String resourceJson = InitGSON.init().toJson(resourceCard1);
        System.out.println(resourceJson);
    }
}
