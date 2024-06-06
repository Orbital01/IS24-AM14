package it.polimi.ingsw.is24am14.server.view;


import it.polimi.ingsw.is24am14.client.view.printer.RenderBoard;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TUIView implements VirtualView{
    @Override
    public String askForUsername() {
        System.out.println("Choose a username");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    @Override
    public void printLobbyOption(ArrayList<String> lobbiesNames) {
        System.out.println("There are " + lobbiesNames.size() + " lobbies");
        for (int i = 0; i < lobbiesNames.size(); i++) {
            System.out.println(i + ") " + lobbiesNames.get(i));
        }
    }

    @Override
    public int getLobbyOption() {
        int option;
        Scanner in = new Scanner(System.in);
        System.out.println("Digit:\n0 to join an existing lobby.\n1 to create a new lobby.");

        option = in.nextInt();
        while (option < 0 || option > 1) {
            System.out.println("Invalid option. Try again.");
            option = in.nextInt();
        }

        return option;
    }

    @Override
    public int getLobbyIndex(ArrayList<String> lobbiesNames) {
        int index;
        Scanner in = new Scanner(System.in);
        System.out.println("Choose which lobby you would like to join");

        index = in.nextInt();
        while (index < 0 || index > lobbiesNames.size() - 1) {
            System.out.println("Invalid index. Try again.");
            index = in.nextInt();
        }

        return index;
    }

    @Override
    public int getLobbyNumPlayers() {
        System.out.println("Enter number of players (must be between 1 and 4)");
        Scanner in = new Scanner(System.in);
        int numPlayers = in.nextInt();
        while (numPlayers < 1 || numPlayers > 4) {
            System.out.println("Invalid number. Try again.");
            numPlayers = in.nextInt();
        }
        return numPlayers;
    }

    @Override
    public void printPlayersInLobby(ArrayList<String> players) {
        System.out.println("Players in the lobby:");
        for (String player : players) {
            System.out.println(player);
        }
    }

    @Override
    public void printColors(List<TokenColour> colors) {
        System.out.println("The available colors are:");
        for (int i = 0; i < colors.size(); i++) {
            System.out.println(i + ") " + colors.get(i).toString());
        }
    }

    @Override
    public TokenColour chooseColor(List<TokenColour> colors) {
        int colorIndex;
        Scanner in = new Scanner(System.in);
        System.out.println("Choose which color would you like to be");

        colorIndex = in.nextInt();
        while (colorIndex < 0 || colorIndex > colors.size() - 1) {
            System.out.println("Invalid index. Try again.");
            colorIndex = in.nextInt();
        }

        return colors.get(colorIndex);
    }

    @Override
    public void printSecretObjective(ObjectiveCard card1, ObjectiveCard card2) {
        System.out.println("0) " + card1.toString());
        System.out.println("1) " + card2.toString());
    }

    @Override
    public int chooseSecretObjective(ObjectiveCard card1, ObjectiveCard card2) {

        int secretObjectiveChoice;
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a secret objective");

        secretObjectiveChoice = in.nextInt();
        while (secretObjectiveChoice < 0 || secretObjectiveChoice > 1) {
            System.out.println("Invalid index. Try again.");
            secretObjectiveChoice = in.nextInt();
        }

        return secretObjectiveChoice;
    }

    @Override
    public void printBlackToken() {
        System.out.println("You have the black token, that means that you're the first player");
    }

    @Override
    public void printBoard(GameArea board) {
        System.out.println("Your board");
        RenderBoard render = new RenderBoard(board);
        render.printBoard();
    }

    @Override
    public void printHand(ArrayList<PlayableCard> hand) {
        System.out.println("Your hand");
        for (PlayableCard card : hand) {
            for (String s : card.drawCard()) {
                System.out.println(s);
            }
        }
    }

    @Override
    public int moveChoice() {
        int choice;
        Scanner in = new Scanner(System.in);
        System.out.println("It's your turn!");
        System.out.println("Digit:\n0 to flip a Card in your hand.\n1 to put a card on the board.");

        choice = in.nextInt();
        while (choice < 0 || choice > 1) {
            System.out.println("Invalid choice. Try again.");
            choice = in.nextInt();
        }

        return choice;
    }

    @Override
    public int chooseCardToFlip(ArrayList<PlayableCard> hand) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a card to flip");

        choice = scanner.nextInt();
        while (choice < 0 || choice >= hand.size()) {
            System.out.println("Invalid choice");
            choice = scanner.nextInt();
        }

        return choice;
    }

    @Override
    public int chooseCardToPlay(ArrayList<PlayableCard> hand) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a card to play");
        choice = scanner.nextInt();
        while (choice < 0 || choice >= hand.size()) {
            System.out.println("Invalid choice");
            choice = scanner.nextInt();
        }
        return choice;
    }

    @Override
    public Coordinates chooseCardToOverlap(GameArea board) {
        int x, y;
        Coordinates coordinates;
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a card to overlap");
        x = in.nextInt();
        y = in.nextInt();
        coordinates = new Coordinates(x, y);
        while (board.getCard(coordinates) == null) {
            System.out.println("Invalid coordinates. Try again.");
            x = in.nextInt();
            y = in.nextInt();
            coordinates = new Coordinates(x, y);
        }
        return coordinates;
    }

    @Override
    public int chooseCornerIndex(GameArea board) {
        int choice;
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a corner index");
        choice = in.nextInt();
        while (choice < 0 || choice >= 4) {
            System.out.println("Invalid choice");
            choice = in.nextInt();
        }
        return choice;
    }

    @Override
    public int pickChoices(Deck<GoldCard> goldDeck, Deck<ResourceCard> resourceDeck, ArrayList<PlayableCard> faceUpCards) {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("Digit:");
        if (goldDeck.isEmpty()) {
            System.out.println("The gold deck is empty");
        } else {
            System.out.println("0) to draw from the Gold Deck");
        }

        if (resourceDeck.isEmpty()) {
            System.out.println("The resource deck is empty");
        } else {
            System.out.println("1) to draw from the Resource Deck");
        }

        System.out.println("2) to pick one of the Face Up Cards");

        option = scanner.nextInt();
        while (option < 0 || option >= 3 || (option == 0 && goldDeck.isEmpty()) || (option == 1 && resourceDeck.isEmpty())) {
            System.out.println("Invalid option. Try again.");
            option = scanner.nextInt();
        }
        return option;
    }

    @Override
    public int pickChoices(boolean goldDeckEmpty, boolean resourceDeckEmpty, ArrayList<PlayableCard> faceUpCards) {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("Digit:");
        if (goldDeckEmpty) {
            System.out.println("The gold deck is empty");
        } else {
            System.out.println("0) to draw from the Gold Deck");
        }

        if (resourceDeckEmpty) {
            System.out.println("The resource deck is empty");
        } else {
            System.out.println("1) to draw from the Resource Deck");
        }

        System.out.println("2) to pick one of the Face Up Cards");

        option = scanner.nextInt();
        while (option < 0 || option >= 3 || (option == 0 && goldDeckEmpty) || (option == 1 && resourceDeckEmpty)) {
            System.out.println("Invalid option. Try again.");
            option = scanner.nextInt();
        }
        return option;
    }

    @Override
    public int chooseFaceUpCard(ArrayList<PlayableCard> faceUpCards) {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        while (choice < 0 || choice >= faceUpCards.size()) {
            System.out.println("Invalid choice. Try again.");
            choice = scanner.nextInt();
        }
        return choice;
    }

    @Override
    public void showFaceUpCards(ArrayList<PlayableCard> faceUpCards) {
        for (PlayableCard card : faceUpCards) {
            System.out.println(card.toString());
        }
    }

    @Override
    public void printScore(int score) {
        System.out.println("Your score is " + score);
    }

    @Override
    public void printWinner(String winner) {
        System.out.println("The winner is " + winner + "!!!");
    }
}