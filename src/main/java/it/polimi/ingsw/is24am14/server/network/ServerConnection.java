package it.polimi.ingsw.is24am14.server.network;

import java.rmi.Remote;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.game.*;

public interface ServerConnection extends Remote {
    void askForMove() throws Exception;
    void flipCard(int cardIndex) throws Exception;
    PlayableCard chooseCardFromHand() throws Exception;
    Card alreadyPlacedCard() throws Exception;
    int getCornerIndex() throws Exception;
    int getDrawDeckIndex() throws Exception;
    int getFaceUpCardIndex() throws Exception;
}
