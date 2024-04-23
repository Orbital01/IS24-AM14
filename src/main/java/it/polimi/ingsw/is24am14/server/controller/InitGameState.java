package it.polimi.ingsw.is24am14.server.controller.state;
import it.polimi.ingsw.is24am14.server.model.game.*;
import it.polimi.ingsw.is24am14.server.model.player.*;
import it.polimi.ingsw.is24am14.server.model.card.*;
import java.util.*;
import java.util.Random;

public class InitGameState implements GameState{
    private GameContext context;
    private List<TokenColour> TokenColours;
    private Deck starterCards; //must be assigned by Matteo's parser
    private Deck goldDeck; //must be assigned by Matteo's parser
    private Deck resourceDeck;
    private Deck objectiveDeck; //must be assigned by Matteo's parser
    private ArrayList<Card> faceUpCards;
    public InitGameState(GameContext context){
        //Initialize all decks
        List<TokenColour> TokenColours = Arrays.asList(TokenColour.values());
        Deck starterCards = new Deck(new ArrayList<StarterCard>()); //must be assigned by Matteo's parser
        Deck goldDeck = new Deck(new ArrayList<GoldCard>()); //must be assigned by Matteo's parser
        Deck resourceDeck = new Deck(new ArrayList<ResourceCard>()); //must be assigned by Matteo's parser
        Deck objectiveDeck = new Deck(new ArrayList<ObjectiveCard>()); //must be assigned by Matteo's parser
        ArrayList<Card> faceUpCards = new ArrayList<Card>();

        //Get all decks from parser
        starterCards = parser.getStarterDeck(); //must be assigned by Matteo's parser
        goldDeck = parser.getGoldDeck(); //must be assigned by Matteo's parser
        resourceDeck = parser.getResourceDeck(); //must be assigned by Matteo's parser
        objectiveDeck = parser.getObjectiveDeck(); //objectiveDeck must be declared as ArrayList<Card> or as Deck?

        //Set all decks to Game class
        context.getGame().setGoldDeck(goldDeck);
        context.getGame().setResourceDeck(resourceDeck);
        context.getGame().setObjectiveDeck(objectiveDeck);
        context.getGame().setFaceUpCards(faceUpCards);

    }
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
            player.setPlayerHand(new ArrayList<Card>());
            player.setStarterCard(starterCards.removeTop()); //POLYMORPHISM ERROR: to be fixed by Matteo by introducing Java generics types
            player.getPlayerBoard().placeStarterCard(player.getStarterCard());
            //Let each player choose his colour
            chooseAndRemoveColour(player, /* chosenColourIndex that will be passed as a parameter by VIEW */);
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
            chooseSecretObjective(player, /* chosenSecretIndex that will be passed as a parameter by VIEW */);
        }

        //First player is randomly chosen; after first player is chosen, he will have black token as well
        Random random = new Random();
        int firstPlayerIndex = random.nextInt(context.getGame().getNumPlayers());
        context.getGame().getPlayers().get(firstPlayerIndex).setFirstPlayer(true); //--> the VIEW will graphically assign the black token to the first player
    }

    public void chooseSecretObjective(Player player, int chosenSecretIndex){
        ArrayList<ObjectiveCard> chooseSecret = new ArrayList<ObjectiveCard>();
        for (int i = 0; i < 2; i++){
            chooseSecret.add(objectiveDeck.removeTop()); //POLYMORPHISM ERROR: to be fixed by Matteo by introducing Java generics types
        }
        player.setObjectiveCard(chooseSecret.get(chosenSecretIndex));
    }

    public void chooseAndRemoveColour(Player player, int chosenColourIndex){
        TokenColour chosenColour = TokenColours.get(chosenColourIndex);
        TokenColours.remove(chosenColour);
        player.setColour(chosenColour);
    }
}
