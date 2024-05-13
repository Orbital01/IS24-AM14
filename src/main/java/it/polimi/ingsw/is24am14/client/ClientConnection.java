package it.polimi.ingsw.is24am14.client;

import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.util.ArrayList;
import java.util.List;

public interface ClientConnection {
    void execute() throws Exception;
    void send(String message) throws Exception; //    message send by the client to the server
    void makeMove() throws Exception;
    int sendInt() throws Exception;
    void chooseColor(List<TokenColour> colors, Player player) throws Exception;
    void pickObjective(ArrayList<ObjectiveCard> secrets, Player player) throws Exception;
    void drawCard() throws Exception;
    void joinLobby(LobbyList lobby) throws Exception;
}
