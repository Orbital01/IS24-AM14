package it.polimi.ingsw.is24am14.server.controller;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.controller.GameContext;

import java.util.ArrayList;

public class PlayState implements GameState{
    private Game game;

    private final int playerIndex = game.getIndexActivePlayer();
    private PlayableCard playedCard;
    private final Player currentPlayer = game.getPlayers().get(playerIndex);
    private final GameArea gameArea = currentPlayer.getPlayerBoard();
    private final GameContext context;

    public boolean isEndGame;
    private PlayableCard lastPlayedCard;

    public PlayState(GameContext context) {
        this.context = context;
        this.game = context.game;
    }

    public PlayableCard returnPlayedCard(){
        return playedCard;
    }

    int cardIndex;
    Card alreadyPlacedCard;
    int cornerIndex;


    @Override
    public void execute(){
        // This goes in the View, not Controller: PlayableCard playedCard = chooseCard(currentPlayer.getPlayerHand(), cardIndex);
        playCard(playedCard, alreadyPlacedCard, cornerIndex);
        //set playedCard in GameContext
        context.setLastPlayedCard(playedCard);
    }


/* View method
    PlayableCard chooseCard(ArrayList<PlayableCard> playerHand, int cardIndex){
        return currentPlayer.getPlayerHand().get(cardIndex);
    }
 */

    /**
     * Method to check if a Gold Card placementCondition is satisfied and then proceed to play it
     * (same as the one in GameArea)
     *
     * @param playedCard        The card to place
     * @param alreadyPlacedCard The card already in the board
     * @param cornerIndex       The corner the new card will overlap
     */
    void playCard(GoldCard playedCard, Card alreadyPlacedCard, int cornerIndex) {
        // Fetches the placementCondition and checks if it's satisfied, throws exception if it isn't
        Condition placementCondition = playedCard.getPlacementCondition();
        boolean isLegal = placementCondition.isSatisfied(gameArea);
        if (!isLegal)
            throw new IllegalStateException("The placement condition is not satisfied");
        gameArea.addCard(playedCard, alreadyPlacedCard, cornerIndex);
        currentPlayer.getPlayerHand().remove(cardIndex);
    }

    void playCard(ResourceCard playedCard, Card alreadyPlacedCard, int cornerIndex) {
        gameArea.addCard(playedCard, alreadyPlacedCard, cornerIndex);
        currentPlayer.getPlayerHand().remove(cardIndex);
    }

    void playCard(PlayableCard playedCard, Card alreadyPlacedCard, int cornerIndex) {
    }

}

