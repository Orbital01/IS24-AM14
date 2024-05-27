package it.polimi.ingsw.is24am14.server.controller;
import it.polimi.ingsw.is24am14.server.model.game.*;
import it.polimi.ingsw.is24am14.server.model.game.exceptions.MaximumNumberOfFaceUpCardsReachedException;
import it.polimi.ingsw.is24am14.server.model.player.*;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.utils.GoldCardDeckCreator;
import it.polimi.ingsw.is24am14.server.utils.ObjectiveCardDeckCreator;
import it.polimi.ingsw.is24am14.server.utils.ResourceCardDeckCreator;
import it.polimi.ingsw.is24am14.server.utils.StarterCardDeckCreator;


import java.util.*;
import java.util.Random;

/**
 * Represents the initial game state.
 * This class is responsible for setting up the game, including shuffling and dealing cards, assigning player colors, and setting the first player.
 */
public class InitGameState implements GameState {
    private final GameContext context;
    private final List<TokenColour> TokenColours;
    private final Deck<StarterCard> starterCards;
    private final Deck<GoldCard> goldDeck;
    private final Deck<ResourceCard> resourceDeck;
    private final Deck<ObjectiveCard> objectiveDeck;

    /**
     * Constructor for the InitGameState class.
     * Initializes all decks and sets them to the game class.
     *
     * @param context The game context.
     */
    public InitGameState(GameContext context){
        //Initialize all decks & get them from parser
        this.context = context;

        TokenColours = new ArrayList<>(List.of(TokenColour.values()));
        TokenColours.remove(TokenColour.BLACK);

        this.starterCards = new StarterCardDeckCreator().createStarterCardDeck();
        this.goldDeck = new GoldCardDeckCreator().createGoldCardDeck();
        this.resourceDeck = new ResourceCardDeckCreator().createResourceCardDeck();
        this.objectiveDeck = new ObjectiveCardDeckCreator().createObjectiveCardDeck();

        ArrayList<PlayableCard> faceUpCards = new ArrayList<>();

        //Set all decks to Game class
        context.getGame().setGoldDeck(this.goldDeck);
        context.getGame().setResourceDeck(this.resourceDeck);
        context.getGame().setObjectiveDeck(this.objectiveDeck);
        context.getGame().setFaceUpCards(faceUpCards);
        context.getGame().setColors(TokenColours);
    }

    /**
     * Executes the initial game state.
     * Shuffles all decks, assigns cards to players, and sets the first player.
     */
    public void execute(){
        //Shuffle all decks
        this.starterCards.shuffle();
        this.goldDeck.shuffle();
        this.resourceDeck.shuffle();
        this.objectiveDeck.shuffle();

        //FaceUpCards assignment: we need to take two cards from resourceDeck and two from goldDeck and insert them in the faceUpCards array list
        for (int i = 0; i < 2; i++){
            try {
                context.getGame().addFaceUpCard(resourceDeck.removeTop());
            } catch (MaximumNumberOfFaceUpCardsReachedException e) {
                System.out.println(e.getMessage());
            }
        }
        for (int i = 0; i < 2; i++){
            try {
                context.getGame().addFaceUpCard(goldDeck.removeTop());
            } catch (MaximumNumberOfFaceUpCardsReachedException e) {
                System.out.println(e.getMessage());
            }
        }

        //For each player in game, assign a player board, a player hand, a starter card, two resource cards and a gold card
        for (Player player : context.getGame().getPlayers()){
            //Initialize player's board, hand and starter card
            player.setPlayerBoard(new GameArea());
            player.setPlayerHand(new ArrayList<>());
            player.setStarterCard(starterCards.removeTop()); //POLYMORPHISM ERROR: to be fixed by Matteo by introducing Java generics types
            player.getPlayerBoard().placeStarterCard(player.getStarterCard());
            //Let each player choose his colour
            try {
                //player.getConnection().assignColor(TokenColours, player);
                player.assignColor(TokenColours);
                System.out.println("Player " + player.getPlayerNickname() + " has chosen " + player.getColour());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //Let each player have his hand of cards
            player.addCardToHand(resourceDeck.removeTop());
            player.addCardToHand(resourceDeck.removeTop());
            player.addCardToHand(goldDeck.removeTop());
        }

        //Shuffle objectiveDeck and assign two common objectives for all players
        ArrayList<ObjectiveCard> commonObjectives = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            commonObjectives.add(objectiveDeck.removeTop()); //POLYMORPHISM ERROR: to be fixed by Matteo by introducing Java generics types
        }

        //Let each player choose his secret objective (handled out from the first for-loop in order to keep the right order of rules)
        for (int playerIndex = 0; playerIndex < context.getGame().getPlayers().size(); playerIndex++){
            Player player = context.getGame().getPlayers().get(playerIndex);
            try {
                player.askSecretObjective(objectiveDeck.removeTop(), objectiveDeck.removeTop());
                System.out.println("Player " + player.getPlayerNickname() + " has chosen " + player.getSecretObjective());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        //First player is randomly chosen; after first player is chosen, he will have black token as well
        Random random = new Random();
        int firstPlayerIndex = random.nextInt(context.getGame().getNumPlayers());
        context.getGame().setIndexActivePlayer(firstPlayerIndex);
        context.getGame().getPlayers().get(firstPlayerIndex).setFirstPlayer(true); //--> the VIEW will graphically assign the black token to the first player

        try {
            this.context.getGame().getPlayers().get(firstPlayerIndex).sendIsFirstPlayer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
