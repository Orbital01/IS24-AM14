package it.polimi.ingsw.is24am14.server.controller;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.card.Deck;
import java.util.ArrayList;

public class DrawState implements GameState{
    private Game game;
    private final int playerIndex = game.getIndexActivePlayer();
    private final Player currentPlayer = game.getPlayers().get(playerIndex);

    public boolean isEndGame;

    public ArrayList<PlayableCard> faceUpCards;

    int cardIndex;
    public Deck<ResourceCard> resourceDeck;
    public Deck<GoldCard> goldDeck;

    //Obtain player, their gameArea, initialize isEndGame as false
    public DrawState(GameContext context) {
        this.game = context.game;

    }

    @Override
    public void execute(){
        drawAction(drawDeckIndex);
    }
    int drawDeckIndex;
    /**
     * After Player chooses where to draw from, perform draw from the right source
     * @param drawDeckIndex Auxiliary index (0 for Resource Deck, 1 for Gold, 2 for FaceUp)
     */
    //  da sistemare
    void drawAction(int drawDeckIndex){
        if(drawDeckIndex==0) {
            if (resourceDeck.isEmpty())
                throw new IllegalStateException("The chosen deck is empty!");
            drawResourceCard();
        }
        if(drawDeckIndex==1){
            if(goldDeck.isEmpty())
                throw new IllegalStateException("The chosen deck is empty!");
            drawGoldCard();
        }

        if(drawDeckIndex==2){
            drawFromFaceUp(chooseFaceUpCard());
        }
    }


    /**
     * Draws card from Resource Deck, adding it to player's hand and removing it from the top of the deck
     */
    void drawResourceCard() {
        currentPlayer.getPlayerHand().addLast(resourceDeck.removeTop());
        isEndGame = (resourceDeck.isEmpty() && goldDeck.isEmpty());
    }

    /**
     * Like drawResourceCard but for Gold cards
     */
    void drawGoldCard() {
        currentPlayer.getPlayerHand().addLast(goldDeck.removeTop());
        isEndGame = (resourceDeck.isEmpty() && goldDeck.isEmpty());
    }





    //Method to be replaced with player input
    int chooseFaceUpCard(){
        int faceUpIndex=0;
        return faceUpIndex;
    }

    /**
     * Draws FaceUp card at the given index position (0-3) and replaces it with a car of the same type
     * @param faceUpIndex position index of the FaceUp card
     */
    void drawFromFaceUp(int faceUpIndex) {
        PlayableCard drawnCard = faceUpCards.get(faceUpIndex);
        currentPlayer.getPlayerHand().add(cardIndex, drawnCard);
        addFaceUpCard(drawnCard, faceUpIndex);
    }

    //Methods to replace the drawn FaceUp card with another card of the same type
    void addFaceUpCard(PlayableCard card, int cardIndex){
    }
    void addFaceUpCard(GoldCard card, int cardIndex) {
        faceUpCards.add(cardIndex, goldDeck.removeTop());

    }

    void addFaceUpCard(ResourceCard card, int cardIndex) {
        faceUpCards.add(cardIndex, resourceDeck.removeTop());
    }
}



