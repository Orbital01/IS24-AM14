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
        Gson gson = InitGSON.init();
        ArrayList<Corner> funFunHidEmp = new ArrayList<>();
        funFunHidEmp.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funFunHidEmp.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funFunHidEmp.add(new Corner(CornerEnum.HIDDEN));
        funFunHidEmp.add(new Corner(CornerEnum.EMPTY));
        ArrayList<Corner> backCorners = new ArrayList<>();
        CornerEnum.ResourceEnum resource = CornerEnum.ResourceEnum.FUNGI;
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));

        ResourceCard resourceCard = new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, funFunHidEmp, backCorners, "/images/cards/resource_cards/red_fronts/page_2.png", "/images/cards/resource_cards/red_backs/page_2.png");

        ArrayList<Corner> hidEmpEmpQui = new ArrayList<>();
        ObjectCondition quillCondition = new ObjectCondition();
        quillCondition.addClause(CornerEnum.ObjectEnum.QUILL);
        hidEmpEmpQui.add(new Corner(CornerEnum.HIDDEN));
        hidEmpEmpQui.add(new Corner(CornerEnum.EMPTY));
        hidEmpEmpQui.add(new Corner(CornerEnum.EMPTY));
        hidEmpEmpQui.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        ResourceCondition twoFungiOneAnimal = new ResourceCondition();
        twoFungiOneAnimal.addClause(CornerEnum.ResourceEnum.FUNGI);
        twoFungiOneAnimal.addClause(CornerEnum.ResourceEnum.FUNGI);
        twoFungiOneAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        GoldCard goldCard = new GoldCard(1, quillCondition, CornerEnum.ResourceEnum.FUNGI, twoFungiOneAnimal, hidEmpEmpQui, "/images/cards/gold_cards/red_fronts/page_41.png", "/images/cards/gold_cards/red_backs/page_41.png");

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


        Player player = new Player("boh");
        player.setFirstPlayer(true);
        player.setScore(2);
        player.setColour(TokenColour.BLUE);
        player.addCardToHand(goldCard);
        player.addCardToHand(resourceCard);

        String goldCardJson = gson.toJson(goldCard);
        String playerJson = gson.toJson(player);
        System.out.println(playerJson);

        Player newPlayer = gson.fromJson(playerJson, Player.class);
    }
}
