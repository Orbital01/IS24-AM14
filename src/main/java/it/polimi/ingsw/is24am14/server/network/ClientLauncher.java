package it.polimi.ingsw.is24am14.server.network;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.utils.GSONAdapters.InitGSON;

import java.util.ArrayList;

public class ClientLauncher {
    public static void main(String[] args) {
        try {
            //RMIClientInterface client = new RMIClient();
            SocketClient client = new SocketClient();
            //tmp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void tmp() {
        Gson gson = InitGSON.init();

        ResourceCondition condition = new ResourceCondition();
        condition.addClause(CornerEnum.ResourceEnum.FUNGI);
        condition.addClause(CornerEnum.ResourceEnum.FUNGI);
        condition.addClause(CornerEnum.ResourceEnum.FUNGI);

        System.out.println(gson.toJson(condition));

    }
}
