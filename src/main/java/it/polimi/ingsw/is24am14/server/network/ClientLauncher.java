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
        Coordinates topLeft = new Coordinates(1, -1);
        Coordinates midLeft = new Coordinates(0, -1);
        Coordinates bottomMid = new Coordinates(-1, 0);

        CardCondition redRedGreenL = new CardCondition();
        redRedGreenL.addClause(topLeft, CornerEnum.ResourceEnum.FUNGI);
        redRedGreenL.addClause(midLeft, CornerEnum.ResourceEnum.FUNGI);
        redRedGreenL.addClause(bottomMid, CornerEnum.ResourceEnum.PLANT);
        ObjectiveCard objectiveCard = new ObjectiveCard(redRedGreenL, "/images/cards/objective_cards/objective_fronts/page_91.png", "/images/cards/resource_cards/objective_backs/page_91.png", 3);

        String json = InitGSON.init().toJson(objectiveCard);

        ObjectiveCard newObjectiveCard = InitGSON.init().fromJson(json, ObjectiveCard.class);

        ResourceCondition resourceCondition = new ResourceCondition();
        resourceCondition.addClause(CornerEnum.ResourceEnum.FUNGI);

        String jsonResource = InitGSON.init().toJson(resourceCondition);
        ResourceCondition newResourceCondition = InitGSON.init().fromJson(jsonResource, ResourceCondition.class);
    }
}
