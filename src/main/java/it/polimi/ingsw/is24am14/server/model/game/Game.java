package it.polimi.ingsw.is24am14.server.model.game;

import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfFaceUpCardsReachedException;
import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfPlayersReachedException;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.utils.GoldCardDeckCreator;
import it.polimi.ingsw.is24am14.server.utils.ObjectiveCardDeckCreator;
import it.polimi.ingsw.is24am14.server.utils.ResourceCardDeckCreator;
import it.polimi.ingsw.is24am14.server.utils.StarterCardDeckCreator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * This class represents a game in the server model.
 * It contains all the necessary information and methods to manage a game, including the players, the decks of cards, and the game state.
 * It implements the Serializable interface to allow the game state to be saved and loaded.
 */
public class Game implements Serializable {
    private ArrayList<Player> players;
    private int numPlayers;

    private Deck<ResourceCard> resourceDeck;
    private Deck<GoldCard> goldDeck;
    private ArrayList<PlayableCard> faceUpCards;
    private Deck<ObjectiveCard> objectiveDeck;
    private final Deck<StarterCard> starterCards;
    private final ArrayList<ObjectiveCard> commonObjective;

    private boolean isEndGame;
    private int indexActivePlayer;
    private List<TokenColour> colors;
    /**
     * Constructs a new Game with the specified number of players.
     * It initializes the decks of cards and the list of players.
     *
     * @param numPlayers the number of players in the game
     */
    public Game(int numPlayers) {
        this.players = new ArrayList<>();
        this.numPlayers = numPlayers;

        this.starterCards = new StarterCardDeckCreator().createStarterCardDeck();
        this.goldDeck = new GoldCardDeckCreator().createGoldCardDeck();
        this.resourceDeck = new ResourceCardDeckCreator().createResourceCardDeck();
        this.objectiveDeck = new ObjectiveCardDeckCreator().createObjectiveCardDeck();

        this.commonObjective = new ArrayList<>();

        this.faceUpCards = new ArrayList<>();
    }
    /**
     * Initializes the game.
     * It shuffles all the decks of cards and assigns the face up cards.
     * It also initializes the player's board and hand.
     */
    public void init() {
        //Shuffle all decks
        this.starterCards.shuffle();
        this.goldDeck.shuffle();
        this.resourceDeck.shuffle();
        this.objectiveDeck.shuffle();

        //FaceUpCards assignment: we need to take two cards from resourceDeck and two from goldDeck and insert them in the faceUpCards array list
        for (int i = 0; i < 2; i++){
            this.addFaceUpCard(resourceDeck.removeTop());
        }
        for (int i = 0; i < 2; i++){
            this.addFaceUpCard(goldDeck.removeTop());
        }

        for (Player player : this.players) {
            //Initialize player's board and hand
            player.setPlayerBoard(new GameArea());
            player.setPlayerHand(new ArrayList<>());
        }
    }

    /**
     * Adds a new player to the game.
     * It throws an exception if the maximum number of players has been reached or if the new player is null.
     *
     * @param newPlayer the new player to be added
     * @throws IllegalArgumentException if the new player is null
     * @throws MaximumNumberOfPlayersReachedException if the maximum number of players has been reached
     */
    public void addPlayer(Player newPlayer) throws IllegalArgumentException, MaximumNumberOfPlayersReachedException {
        if (players.size() >= numPlayers) throw new MaximumNumberOfPlayersReachedException("Maximum number of players already reached");
        if (newPlayer == null) throw new IllegalArgumentException("The parameter must not be null");
        players.add(newPlayer);
    }
    /**
     * Removes a player from the game at the specified index.
     *
     * @param indexPlayer the index of the player to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void removePlayer(int indexPlayer) throws IndexOutOfBoundsException {
        players.remove(indexPlayer);
    }
    public int getNumPlayers() {
        return this.numPlayers;
    }
    /**
     * Sets the number of players in the game.
     *
     * @param numPlayers the number of players
     * @throws IllegalArgumentException if the number of players is less than 0 or greater than 4
     */
    public void setNumPlayers(int numPlayers) throws IllegalArgumentException {
        if (numPlayers > 4) throw new IllegalArgumentException("The number of players must be less or equal to 4");
        if (numPlayers < 0) throw new IllegalArgumentException("The number of players must be greater than 0");
        this.numPlayers = numPlayers;
    }

    /**
     * Removes the top card from the gold deck.
     *
     * @return the top card from the gold deck
     */
    public GoldCard popGoldDeck() {
        return goldDeck.removeTop();
    }

    /**
     * Removes the top card from the resource deck.
     *
     * @return the top card from the resource deck
     */
    public ResourceCard popResourceDeck() {
        return resourceDeck.removeTop();
    }

    public Deck<GoldCard> getGoldDeck() {
        return goldDeck;
    }

    public Deck<ResourceCard> getResourceDeck() {
        return resourceDeck;
    }

    public Deck<StarterCard> getStarterCards() {
        return starterCards;
    }

    public ArrayList<PlayableCard> getFaceUpCards() {
        return faceUpCards;
    }
    /**
     * Checks if both the resource deck and the gold deck are empty.
     *
     * @return true if both decks are empty, false otherwise
    */
    public boolean areDecksEmpty() {
        return resourceDeck.isEmpty() && goldDeck.isEmpty();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the player at the specified index.
     *
     * @param index the index of the player
     * @return the player at the specified index
     */
    public Player getPlayer(int index) {
        return players.get(index);
    }
    /**
    * Returns the player with the specified name.
    *
    * @param playerName the name of the player
    */
    public Player getPlayer(String playerName) {
        for (Player player : players) {
            if (player.getPlayerNickname().equals(playerName)) return player;
        }
        return null;
    }

    public Player getActivePlayer() {
        return players.get(indexActivePlayer);
    }
    /**
     * Adds a face up card to the game.
     * It throws an exception if the maximum number of face up cards has been reached or if the new card is null.
     *
     * @param newCard the new card to be added
     * @throws IllegalArgumentException if the new card is null
     * @throws MaximumNumberOfFaceUpCardsReachedException if the maximum number of face up cards has been reached
     */
    public void addFaceUpCard(PlayableCard newCard) throws MaximumNumberOfFaceUpCardsReachedException {
        if (newCard == null) throw new IllegalArgumentException("The parameter must be not null");
        if (faceUpCards.size() == 4) throw new MaximumNumberOfFaceUpCardsReachedException();
        faceUpCards.add(newCard);
    }

    /**
     * Draws a face up card from the face-up cards.
     * @param indexCard Index of the card to be drawn
     * @return the drawn face-up card
     */
    public PlayableCard drawFaceUpCard(int indexCard) {
        if (indexCard < 0 || indexCard >= faceUpCards.size()) throw new IndexOutOfBoundsException();
        PlayableCard card = faceUpCards.remove(indexCard);

        if (indexCard < 2 ) faceUpCards.add(popResourceDeck());
        else faceUpCards.add(popGoldDeck());

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

    public void addCommonObjective(ObjectiveCard card) {
        this.commonObjective.add(card);
    }

    public ArrayList<ObjectiveCard> getCommonObjective() {
        return this.commonObjective;
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
