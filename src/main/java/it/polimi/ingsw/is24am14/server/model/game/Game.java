package it.polimi.ingsw.is24am14.server.model.game;

import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfFaceUpCardsReachedException;
import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfPlayersReachedException;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.card.*;

import java.util.ArrayList;

public class Game {
    public Game(int numPlayers) {
        this.numPlayers = numPlayers;
    }
    private ArrayList<Player> players;
    private int numPlayers;
    private Deck<ResourceCard> resourceDeck;
    private Deck<GoldCard> goldDeck;
    private ArrayList<PlayableCard> faceUpCards; //modificato da Card a PlayableCard
    private Deck<ObjectiveCard> objectiveDeck; //modificato da Card a ObjectiveCard
    private boolean isEndGame;
    private int indexActivePlayer;

    public void addPlayer(Player newPlayer) throws IllegalArgumentException, MaximumNumberOfPlayersReachedException {
        if (players.size() >= numPlayers) throw new MaximumNumberOfPlayersReachedException("Maximum number of players already reached");
        if (newPlayer == null) throw new IllegalArgumentException("The parameter must not be null");
        players.add(newPlayer);
    }
    public void removePlayer(int indexPlayer) throws IndexOutOfBoundsException {
        players.remove(indexPlayer);
    }
    public int getNumPlayers() {
        return this.numPlayers;
    }

    public void setNumPlayers(int numPlayers) throws IllegalArgumentException {
        if (numPlayers > 4) throw new IllegalArgumentException("The number of players must be less or equal to 4");
        if (numPlayers < 0) throw new IllegalArgumentException("The number of players must be greater than 0");
        this.numPlayers = numPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addFaceUpCard(PlayableCard newCard) throws MaximumNumberOfFaceUpCardsReachedException {
        if (newCard == null) throw new IllegalArgumentException("The parameter must be not null");
        if (faceUpCards.size() == 4) throw new MaximumNumberOfFaceUpCardsReachedException();
        faceUpCards.add(newCard);
    }
    public void removeFaceUpCard(int indexCard) {
        faceUpCards.remove(indexCard);
    }

    public int getIndexActivePlayer() {
        return indexActivePlayer;
    }

    public void changeActivePlayer() {
        indexActivePlayer = (indexActivePlayer + 1) % numPlayers;
    }

    public boolean isEndGame() {
        return isEndGame;
    }

    public void setEndGame() {
        isEndGame = true;
    }
    public Deck<ObjectiveCard> getObjectiveDeck() {
        return objectiveDeck;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setGoldDeck(Deck<GoldCard> goldDeck) {
        this.goldDeck = goldDeck;
    }

    public void setResourceDeck(Deck<ResourceCard> resourceDeck) {
        this.resourceDeck = resourceDeck;
    }

    public void setObjectiveDeck(Deck<ObjectiveCard> objectiveDeck) {
        this.objectiveDeck = objectiveDeck;
    }

    public void setFaceUpCards(ArrayList<PlayableCard> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }
}
