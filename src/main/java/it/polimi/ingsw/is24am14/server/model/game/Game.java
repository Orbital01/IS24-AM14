package it.polimi.ingsw.is24am14.server.model.game;

import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfFaceUpCardsReachedException;
import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfPlayersReachedException;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
    public Game(int numPlayers) {
        this.players = new ArrayList<>();
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
    private List<TokenColour> colors;

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

    public GoldCard popGoldDeck() {
        return goldDeck.removeTop();
    }

    public ResourceCard popResourceDeck() {
        return resourceDeck.removeTop();
    }

    public Deck<GoldCard> getGoldDeck() {
        return goldDeck;
    }

    public Deck<ResourceCard> getResourceDeck() {
        return resourceDeck;
    }

    public ArrayList<PlayableCard> getFaceUpCards() {
        return faceUpCards;
    }

    public boolean areDecksEmpty() {
        return resourceDeck.isEmpty() && goldDeck.isEmpty();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public Player getPlayer(String playerName) {
        for (Player player : players) {
            if (player.getPlayerNickname().equals(playerName)) return player;
        }
        return null;
    }

    public Player getActivePlayer() {
        return players.get(indexActivePlayer);
    }

    public void addFaceUpCard(PlayableCard newCard) throws MaximumNumberOfFaceUpCardsReachedException {
        if (newCard == null) throw new IllegalArgumentException("The parameter must be not null");
        if (faceUpCards.size() == 4) throw new MaximumNumberOfFaceUpCardsReachedException();
        faceUpCards.add(newCard);
    }

    public PlayableCard drawFaceUpCard(int indexCard) {
        if (indexCard < 0 || indexCard >= faceUpCards.size()) throw new IndexOutOfBoundsException();
        PlayableCard card = faceUpCards.remove(indexCard);

        if (indexCard < 2 ) faceUpCards.add(popGoldDeck());
        else faceUpCards.add(popResourceDeck());

        return card;
    }

    public int getIndexActivePlayer() {
        return indexActivePlayer;
    }

    public void setIndexActivePlayer(int indexActivePlayer) {
        this.indexActivePlayer = indexActivePlayer;
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

    public void setColors(List<TokenColour> colors) {
        this.colors = colors;
    }

    public void removeColor(TokenColour color) {
        this.colors.remove(color);
    }
}
