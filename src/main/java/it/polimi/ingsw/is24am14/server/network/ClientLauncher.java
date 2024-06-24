package it.polimi.ingsw.is24am14.server.network;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.controller.Lobby;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.utils.GSONAdapters.InitGSON;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientLauncher {
    public static void main(String[] args) throws Exception {
        ArrayList<Corner> empEmpHidEmp = new ArrayList<>();
        CornerCondition cornerCondition = new CornerCondition();

        empEmpHidEmp.add(new Corner(CornerEnum.EMPTY));
        empEmpHidEmp.add(new Corner(CornerEnum.EMPTY));
        empEmpHidEmp.add(new Corner(CornerEnum.HIDDEN));
        empEmpHidEmp.add(new Corner(CornerEnum.EMPTY));


        ResourceCondition threeFungiOneAnimal = new ResourceCondition();
        threeFungiOneAnimal.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOneAnimal.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOneAnimal.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOneAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        Card goldCard = new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.FUNGI, threeFungiOneAnimal, empEmpHidEmp, "src/main/resources/images/cards/gold_cards/red_fronts/page_44.png", "src/main/resources/images/cards/gold_cards/red_backs/page_44.png");
        goldCard.flipSide();

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
        plaFun.add(CornerEnum.ResourceEnum.FUNGI);
        plaFun.add(CornerEnum.ResourceEnum.FUNGI);
        StarterCard card2 = new StarterCard(insAniFunPlan, empEmpEmpEmp, plaFun, "src/main/resources/images/cards/starter_cards/starter_backs/page_83.png", "src/main/resources/images/cards/starter_cards/starter_fronts/page_83.png");

        GameArea board = new GameArea();
        board.placeStarterCard(card2);
        board.addCard(card2, goldCard, 0);

        for (Map.Entry<Coordinates, Card> entry : board.getBoard().entrySet()) {
            for (CornerEnum cornerEnum : entry.getValue().getCornerEnums()) {
                System.out.println(cornerEnum);
            }
            System.out.println("____");
        }
    }
}
