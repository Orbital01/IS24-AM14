package it.polimi.ingsw.is24am14.server.controller.state;
import it.polimi.ingsw.is24am14.server.model.game.*;
import it.polimi.ingsw.is24am14.server.model.player.*;
import it.polimi.ingsw.is24am14.server.model.card.*;
import java.util.*;
import java.util.Random;

/**
 * Represents the initial game state.
 * This class is responsible for setting up the game, including shuffling and dealing cards, assigning player colors, and setting the first player.
 */
public class InitGameState implements GameState{
    private GameContext context;
    private List<TokenColour> TokenColours;
    private Deck starterCards; //must be assigned by Matteo's parser
    private Deck goldDeck; //must be assigned by Matteo's parser
    private Deck resourceDeck;
    private Deck objectiveDeck; //must be assigned by Matteo's parser
    private ArrayList<Card> faceUpCards;

    /**
     * Constructor for the InitGameState class.
     * Initializes all decks and sets them to the game class.
     *
     * @param context The game context.
     */
    public InitGameState(GameContext context){
        //Initialize all decks & get them from parser
        List<TokenColour> TokenColours = Arrays.asList(TokenColour.values());
        Deck<StarterCard> starterCards = new StarterCardDeckDeparser().deparse();
        Deck<GoldCard> goldDeck = new GoldCardDeckDeparser().deparse();
        Deck<ResourceCard> resourceDeck = new ResourceCardDeckDeparser().deparse();
        Deck<ObjectiveCard> objectiveDeck = new ObjectiveCardDeckDeparser().deparse();
        ArrayList<Card> faceUpCards = new ArrayList<Card>();

        //Set all decks to Game class
        context.getGame().setGoldDeck(goldDeck);
        context.getGame().setResourceDeck(resourceDeck);
        context.getGame().setObjectiveDeck(objectiveDeck);
        context.getGame().setFaceUpCards(faceUpCards);

    }

    /**
     * Executes the initial game state.
     * Shuffles all decks, assigns cards to players, and sets the first player.
     */
    public void execute(){
        //Shuffle all decks
        starterCards.shuffle();
        goldDeck.shuffle();
        resourceDeck.shuffle();
        objectiveDeck.shuffle();

        //FaceUpCards assignment: we need to take two cards from resourceDeck and two from goldDeck and insert them in the faceUpCards array list
        for (int i = 0; i < 2; i++){
            context.getGame().addFaceUpCard(resourceDeck.removeTop());
        }
        for (int i = 0; i < 2; i++){
            context.getGame().addFaceUpCard(goldDeck.removeTop());
        }

        //For each player in game, assign a player board, a player hand, a starter card, two resource cards and a gold card
        for (Player player : context.getGame().getPlayers()){
            //Initialize player's board, hand and starter card
            player.setPlayerBoard(new GameArea());
            player.setPlayerHand(new ArrayList<PlayableCard>());
            player.setStarterCard(starterCards.removeTop()); //POLYMORPHISM ERROR: to be fixed by Matteo by introducing Java generics types
            player.getPlayerBoard().placeStarterCard(player.getStarterCard());
            //Let each player choose his colour
            chooseAndRemoveColour(player, chosenColourIndex(player));
            //Let each player have his hand of cards
            player.addCardToHand(resourceDeck.removeTop());
            player.addCardToHand(resourceDeck.removeTop());
            player.addCardToHand(goldDeck.removeTop());
        }

        //Shuffle objectiveDeck and assign two common objectives for all players
        ArrayList<ObjectiveCard> commonObjectives = new ArrayList<ObjectiveCard>();
        for (int i = 0; i < 2; i++){
            commonObjectives.add(objectiveDeck.removeTop()); //POLYMORPHISM ERROR: to be fixed by Matteo by introducing Java generics types
        }

        //Let each player choose his secret objective (handled out from the first for-loop in order to keep the right order of rules)
        for (Player player : context.getGame().getPlayers()){
            chooseSecretObjective(player, chosenSecretIndex(player));
        }

        //First player is randomly chosen; after first player is chosen, he will have black token as well
        Random random = new Random();
        int firstPlayerIndex = random.nextInt(context.getGame().getNumPlayers());
        context.getGame().getPlayers().get(firstPlayerIndex).setFirstPlayer(true); //--> the VIEW will graphically assign the black token to the first player
    }

    /**
     * Allows a player to choose a secret objective.
     *
     * @param player The player who is choosing the secret objective.
     * @param chosenSecretIndex The index of the chosen secret objective.
     */
    public void chooseSecretObjective(Player player, int chosenSecretIndex){
        ArrayList<ObjectiveCard> chooseSecret = new ArrayList<ObjectiveCard>();
        for (int i = 0; i < 2; i++){
            chooseSecret.add(objectiveDeck.removeTop()); //POLYMORPHISM ERROR: to be fixed by Matteo by introducing Java generics types
        }
        player.setSecretObjective(chooseSecret.get(chosenSecretIndex));
    }

    /**
     * Allows a player to choose and remove a color.
     *
     * @param player The player who is choosing the color.
     * @param chosenColourIndex The index of the chosen color.
     */
    public void chooseAndRemoveColour(Player player, int chosenColourIndex){
        TokenColour chosenColour = TokenColours.get(chosenColourIndex);
        TokenColours.remove(chosenColour);
        player.setColour(chosenColour);
    }

    /**
     * Placeholder method (currently waiting for VIEW) for choosing a color index.
     *
     * @param player The player who is choosing the color.
     * @return The index of the chosen color.
     */
    public int chosenColourIndex(Player player){
        return 4; //--> this is just a placeholder, the VIEW will graphically assign the colour to the player
    }
    /**
     * Placeholder method (currently waiting for VIEW) for choosing a secret index.
     *
     * @param player The player who is choosing the secret objective.
     * @return The index of the chosen secret objective.
     */
    public int chosenSecretIndex(Player player){
        return 2; //--> this is just a placeholder, the VIEW will graphically assign the colour to the player
    }
}
