package it.polimi.ingsw.is24am14.server.model.card;

import it.polimi.ingsw.is24am14.server.model.game.GameArea;

public class NoCondition implements Condition{
    @Override
    public boolean isSatisfied(GameArea board){
        return true;
    }
    @Override
    public int numSatisfied(GameArea board){
        return 1;
    }

    @Override
    public String toString() {
        return "NoCondition";
    }
}
