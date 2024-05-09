package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.game.Game;

public class TCPMediator implements ServerConnection {
    @Override
    public void askForMove() throws Exception {
        //  send a request-move message to the client
    }

    @Override
    public void flipCard(int cardIndex) throws Exception {

    }

    @Override
    public PlayableCard chooseCardFromHand() throws Exception {
        return null;
    }

    @Override
    public Card alreadyPlacedCard() throws Exception {
        return null;
    }

    @Override
    public int getCornerIndex() throws Exception {
        return 0;
    }

    @Override
    public int getDrawDeckIndex() throws Exception {
        return 0;
    }

    @Override
    public int getFaceUpCardIndex() throws Exception {
        return 0;
    }
}
