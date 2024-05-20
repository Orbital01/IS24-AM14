package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RMIClient extends UnicastRemoteObject implements ClientConnection {
    private RMIServerInterface server;
    private String username;

    protected RMIClient() throws RemoteException {}

    @Override
    public void joinLobby(LobbyList lobbyList) throws Exception {
        Scanner in = new Scanner(System.in);
        int option, lobby_index, num_players;
        System.out.println("There are " + lobbyList.getLobbies().size() + " lobbies");
        for (int i = 0; i < lobbyList.getLobbies().size(); i++) {
            System.out.println(i + ") " + lobbyList.getLobbies().get(i).getHost());
        }

        System.out.println("Digit: \n0 to join an existing lobby\n1 to create a new lobby");
        option = in.nextInt();

        while (option < 0 || option > 1) {
            //  there's going to be the chat option here
            System.out.println("Invalid option");
            option = in.nextInt();
        }

        if (option == 0) {
            System.out.println("Choose a lobby");
            lobby_index = in.nextInt();
            server.joinExistingLobby(this, lobbyList.getLobbies().get(lobby_index));
        } else if (option == 1) {
            System.out.println("You've decided to create a new lobby");
            System.out.println("How many players do you want to join? (Must be a number between 0 and 4)");
            num_players = in.nextInt();
            while (num_players < 0 || num_players > 4) {
                System.out.println("Invalid players number");
                num_players = in.nextInt();
            }
            server.joinNewLobby(this, num_players);
        }
    }

    @Override
    public void execute() throws Exception {
        Registry registry;
        registry = LocateRegistry.getRegistry("127.0.0.1", 12345);

        System.out.println("Choose a username");
        Scanner in = new Scanner(System.in);
        this.username = in.nextLine();

        this.server = (RMIServerInterface) registry.lookup("RMIServer");

        while (true) {
            try {
                this.server.acceptConnection(this);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                this.username = in.nextLine();
            }
        }

        System.out.println("Client connected");
    }


    @Override
    public String getUsername() throws Exception {
        return this.username;
    }

    @Override
    public int pickColor(List<TokenColour> colours) throws Exception {
        System.out.println("Choose a colour");
        for (int i = 1; i < colours.size(); i++) {
            System.out.println(i + ")" + colours.get(i));
        }

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        while (choice < 0 || choice > colours.size()) {
            System.out.println("Invalid choice");
            choice = in.nextInt();
        }

        return choice;
    }

    @Override
    public void pickSecretObjective(int playerIndex, ArrayList<ObjectiveCard> objectiveCards) throws Exception {
        System.out.println("Pick one objective card");
        for (ObjectiveCard card : objectiveCards) {
            System.out.println(card.getCondition());
        }
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        while (choice < 0 || choice > objectiveCards.size()) {
            System.out.println("Invalid choice");
            choice = in.nextInt();
        }

        this.server.assignSecretObjective(this, playerIndex, objectiveCards.get(choice));
    }

    @Override
    public void receiveIsFirstPlayer(boolean isFirstPlayer) throws Exception {
        if (!isFirstPlayer) System.out.println("The game has started. Please wait for your turn");
    }

    @Override
    public void makeMove(Player player) throws Exception {
        System.out.println("It's your turn");
        System.out.println("Select:\n0 -> flip a card\n1 -> play a card");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        while (choice < 0 || choice > 1) {
            System.out.println("Invalid choice");
            choice = in.nextInt();
        }

        if (choice == 0) {
            System.out.println("Which card would you like to flip?");
            int cardIndex = in.nextInt();
            while (cardIndex < 0 || cardIndex > player.getPlayerHand().size()) {
                System.out.println("Invalid choice");
                cardIndex = in.nextInt();
            }

            this.server.flipCard(this, cardIndex);
        } else {
            int handCardIndex, boardX, boardY, cornerIndex;
            while (true) {
                try {
                    System.out.println("Select a card to place on the board");
                    for (int i = 0; i < player.getPlayerHand().size(); i++) {
                        System.out.println(i + ") " + player.getPlayerHand().get(i));
                    }
                    handCardIndex = in.nextInt();
                    while (handCardIndex < 0 || handCardIndex > player.getPlayerHand().size()) {
                        System.out.println("Invalid choice");
                        handCardIndex = in.nextInt();
                    }

                    System.out.println("Select a cart on the board to overlap");
                    for (Coordinates coordinates : player.getPlayerBoard().getBoard().keySet())
                    {
                        System.out.println("(" + coordinates.getRow() + ", " + coordinates.getColumn() + ")");
                    }
                    System.out.println("Insert the row");
                    boardX = in.nextInt();
                    System.out.println("Insert the column");
                    boardY = in.nextInt();
                    Coordinates cardToOverlap = new Coordinates(boardX, boardY);
                    System.out.println("Select the corner to overlap");
                    cornerIndex = in.nextInt();
                    this.server.putCard(this, handCardIndex, cardToOverlap, cornerIndex);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void pickChoice(Deck<GoldCard> goldCardDeck, Deck<ResourceCard> resourceCardDeck, ArrayList<PlayableCard> faceUpCards) throws Exception {
        int deckChoice;
        Scanner in = new Scanner(System.in);
        System.out.println("You have to draw");
        if (!goldCardDeck.isEmpty()) System.out.println("1) Draw from the Gold Deck");
        if (!resourceCardDeck.isEmpty()) System.out.println("2) Draw from the Resource Deck");
        System.out.println("3) Draw from the Face Up Cards");

        deckChoice = in.nextInt();
        while (deckChoice < 1 || deckChoice > 3) {
            System.out.println("Invalid choice");
            deckChoice = in.nextInt();
        }

        if (deckChoice == 1 && !goldCardDeck.isEmpty()) this.server.drawGoldDeck(this);
        else if (deckChoice == 2 && !resourceCardDeck.isEmpty()) this.server.drawResourceDeck(this);
        else {
            System.out.println("Which card from the Face Up Cards?");
            deckChoice = in.nextInt();
            while (deckChoice < 0 || deckChoice > 3) {
                System.out.println("Invalid choice");
                deckChoice = in.nextInt();
            }

            this.server.drawFaceUpCard(this, deckChoice);
        }
    }

    @Override
    public void printScore(int score) throws Exception {
        System.out.println("Your score is: " + score);
    }

    @Override
    public void printWinner(String winner) throws Exception {
        System.out.println("The winner is " + winner + "!!!");
    }
}
