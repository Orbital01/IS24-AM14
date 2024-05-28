package it.polimi.ingsw.is24am14.server.network;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.view.*;

public class RMIClient extends UnicastRemoteObject implements RMIClientInterface {
    private String username;
    private final VirtualView view;
    private final RMIServerInterface server;

    protected RMIClient() throws Exception {
        Registry registry;
        registry = LocateRegistry.getRegistry("127.0.0.1", 12345);
        this.server = (RMIServerInterface) registry.lookup("RMIServer");
        this.view = new TUIView();


        this.username = view.askForUsername();
      
        while (true) {
            try {
                server.acceptConnection(this, username);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                this.username = view.askForUsername();
            }
        }
    }

    @Override
    public void chooseOption(ArrayList<String> lobbiesNames) throws Exception {
        this.view.printLobbyOption(lobbiesNames);
        int choice = view.getLobbyOption();

        if (choice == 0) {
            int lobbyIndex = view.getLobbyIndex(lobbiesNames);
            this.server.joinExistingLobby(this, lobbiesNames.get(lobbyIndex));
        }
        else if (choice == 1) {
            int numPlayers = this.view.getLobbyNumPlayers();
            this.server.createNewLobby(this, numPlayers);
        }
    }

    @Override
    public String getUsername() throws Exception {
        return this.username;
    }

    @Override
    public void receivePlayersInLobby(ArrayList<String> players) throws Exception {
        this.view.printPlayersInLobby(players);
    }

    @Override
    public void selectColor(List<TokenColour> colors) throws Exception {
        this.view.printColors(colors);
        TokenColour color = this.view.chooseColor(colors);
        this.server.setColor(this, color);
    }

    @Override
    public void selectObjectiveCard(ObjectiveCard card1, ObjectiveCard card2) throws Exception {
        this.view.printSecretObjective(card1, card2);
        ObjectiveCard choice = null;
        if (this.view.chooseSecretObjective(card1, card2) == 0) {
            choice = card1;
        } else if (this.view.chooseSecretObjective(card1, card2) == 1) {
            choice = card2;
        }
        this.server.setSecretObjective(this, choice);
    }

    @Override
    public void printBlackToken() throws Exception {
        this.view.printBlackToken();
    }

    @Override

    public void chooseMove(ArrayList<PlayableCard> hand, GameArea board) throws Exception {
        this.view.printBoard(board);
        this.view.printHand(hand);

        int choice = this.view.moveChoice();
        if (choice == 0) this.flipCard(hand);
        else if (choice == 1) this.putCard(hand, board);
    }

    @Override
    public void flipCard(ArrayList<PlayableCard> hand) throws Exception {
        int choice = this.view.chooseCardToFlip(hand);
        this.server.flipCard(this, choice);
    }

    @Override
    public void putCard(ArrayList<PlayableCard> hand, GameArea board) throws Exception {
        int handCardIndex, cornerIndex;
        Coordinates boardCard;
        handCardIndex = this.view.chooseCardToPlay(hand);
        boardCard = this.view.chooseCardToOverlap(board);
        cornerIndex = this.view.chooseCornerIndex(board);
        this.server.playCard(this, hand.get(handCardIndex), boardCard, cornerIndex);
    }

    @Override
    public void pickChoice(Deck<GoldCard> goldDeck, Deck<ResourceCard> resourceDeck, ArrayList<PlayableCard> faceUpCards) throws Exception {
        int option = this.view.pickChoices(goldDeck, resourceDeck, faceUpCards);

        if (option == 0) this.server.drawFromGoldDeck(this);
        else if (option == 1) this.server.drawFromResourceDeck(this);
        else {
            this.view.showFaceUpCards(faceUpCards);
            int faceUpIndex = this.view.chooseFaceUpCard(faceUpCards);
            this.server.drawFromFaceUpCards(this, faceUpIndex);
        }
    }

    @Override
    public void receiveScore(int score) throws Exception {
        this.view.printScore(score);
    }

    @Override
    public void receiveWinner(String winner) throws Exception {
        this.view.printWinner(winner);
    }
}
