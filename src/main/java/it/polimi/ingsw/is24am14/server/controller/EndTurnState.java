package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.game.*;
import it.polimi.ingsw.is24am14.server.model.card.ObjectCondition.*;
import it.polimi.ingsw.is24am14.server.controller.PlayState;

public class EndTurnState implements GameState{
    private Game game;
    private final int playerIndex = game.getIndexActivePlayer();
    private final Player currentPlayer = game.getPlayers().get(playerIndex);

    private final PlayableCard lastPlayedCard;


    public boolean isEndGame;

    //Not necessary if they get passed from TurnState
    public EndTurnState(GameContext context){
        this.lastPlayedCard = context.lastPlayedCard;
        this.game = context.game;
    }


    @Override
    public void execute(){
        updateScore(lastPlayedCard, currentPlayer);
    }


    /**
     * Updates the player's score with the number of points on the resource card
     * @param lastPlayedCard The played resource card
     * @param currentPlayer The player who just played the card
     */
    void updateScore(ResourceCard lastPlayedCard, Player currentPlayer){
        currentPlayer.setScore(lastPlayedCard.getPoints()+currentPlayer.getScore());
        if(currentPlayer.getScore()>=20)
            isEndGame=true;
    }

    /**
     * Updates the player's score with the number of points given by the gold card
     * @param lastPlayedCard The played gold card
     * @param currentPlayer The player who just played the card
     */
    void updateScore(GoldCard lastPlayedCard, Player currentPlayer){
        Condition pointsCondition = lastPlayedCard.getPointCondition();
        int earnedPoints;
        if(lastPlayedCard.getPointCondition()!=null)
            earnedPoints = lastPlayedCard.getPoints() * lastPlayedCard.getPointCondition().numSatisfied();
        else
            earnedPoints = lastPlayedCard.getPoints();
        //Sets player score to his old score + the points given by the satisfied condition on the gold card
        currentPlayer.setScore(currentPlayer.getScore()+ earnedPoints);
    }

    void updateScore(PlayableCard lastPlayedCard, Player currentPlayer){};
}

