package it.polimi.ingsw.is24am14.server.network;

import java.rmi.Remote;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.game.*;

public interface ServerConnection extends Remote {
    void askForMove() throws Exception;
    void flipCard(int cardIndex) throws Exception;
    String getClientNickname() throws Exception;
}
