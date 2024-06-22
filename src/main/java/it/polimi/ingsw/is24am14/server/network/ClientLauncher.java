package it.polimi.ingsw.is24am14.server.network;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.controller.Lobby;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.utils.GSONAdapters.InitGSON;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientLauncher {
    public static void main(String[] args) throws Exception {
        Gson gson = InitGSON.init();
        Corner corner = new Corner(CornerEnum.HIDDEN);

        String json = gson.toJson(corner);

        corner = gson.fromJson(json, Corner.class);

        System.out.println(corner.getType());
    }
}
