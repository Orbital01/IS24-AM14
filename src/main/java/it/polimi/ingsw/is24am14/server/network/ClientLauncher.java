package it.polimi.ingsw.is24am14.server.network;


import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.utils.GSONAdapters.InitGSON;
import it.polimi.ingsw.is24am14.server.utils.ObjectiveCardDeckCreator;

import java.util.ArrayList;

public class ClientLauncher {
    public static void main(String[] args) throws Exception {
        ObjectiveCardDeckCreator creator = new ObjectiveCardDeckCreator();
        Deck<ObjectiveCard> deck = creator.createObjectiveCardDeck();

        GameArea board = new GameArea();
        StarterCard starterCard = createStarterCard();
        board.placeStarterCard(starterCard);

        Card card1 = createResourceCard(CornerEnum.ResourceEnum.FUNGI);
        Card card2 = createResourceCard(CornerEnum.ResourceEnum.FUNGI);
        Card card3 = createResourceCard(CornerEnum.ResourceEnum.FUNGI);

        board.addCard(starterCard, card1, 1);
        board.addCard(card1, card2, 1);
        board.addCard(card2, card3, 1);

        ObjectiveCard objectiveCard = deck.removeTop();
        objectiveCard.getCondition().isSatisfied(board);
    }

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
        funEmpFunHid.add(new Corner(CornerEnum.HIDDEN));

        return new ResourceCard(0, resource, funEmpFunHid, backCorners, "/images/cards/resource_cards/red_fronts/page_1.png", "/images/cards/resource_cards/red_backs/page_1.png");

    }
}
