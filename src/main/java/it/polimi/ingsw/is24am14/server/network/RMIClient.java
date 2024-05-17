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

    protected RMIClient() throws RemoteException {
    }

    @Override
    public void joinLobby(LobbyList lobbyList) throws Exception {
        Scanner in = new Scanner(System.in);
        int option, lobby_index, num_players;
        System.out.println("There are " + lobbyList.getLobbies().size() + " lobbies");
        for (int i = 0; i < lobbyList.getLobbies().size(); i++) {
            System.out.println(i + ")" + lobbyList.getLobbies().get(i).getHost());
        }

        System.out.println("Digita: \n0 per unirti ad una lobby già esistente\n1 per creare una nuova lobby");
        option = in.nextInt();

        if (option == 0) {
            System.out.println("Scegli l'indice della lobby");
            lobby_index = in.nextInt();
            server.joinExistingLobby(this, lobbyList.getLobbies().get(lobby_index));
        } else if (option == 1) {
            System.out.println("Hai scelto di creare una nuova lobby");
            System.out.println("Scegli un numero di giocatori (metti tra 0 o 4, non c'è ancora il check se è legale)");
            num_players = in.nextInt();
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
        this.server.acceptConnection(this);
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
        return in.nextInt();
    }

    @Override
    public void pickSecretObjective(int playerIndex, ArrayList<ObjectiveCard> objectiveCards) throws Exception {
        System.out.println("Pick one objective card");
        for (ObjectiveCard card : objectiveCards) {
            System.out.println(card.getCondition());
        }
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        this.server.assignSecretObjective(this, playerIndex, objectiveCards.get(choice));
    }

    @Override
    public void receiveIsFirstPlayer(boolean isFirstPlayer) throws Exception {
        if (isFirstPlayer) System.out.println("I'm the first player!");
        else System.out.println("I'm not the first player!");
    }

    @Override
    public void makeMove(Player player) throws Exception {
        System.out.println("It's your turn");
        System.out.println("Select:\n0 -> flip a card\n1 -> play a card");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        while (choice < 0 || choice > 1) choice = in.nextInt();

        if (choice == 0) {
            System.out.println("Which card would you like to flip?");
            int cardIndex = in.nextInt();
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

        if (deckChoice == 1 && !goldCardDeck.isEmpty()) this.server.drawGoldDeck(this);
        else if (deckChoice == 2 && !resourceCardDeck.isEmpty()) this.server.drawResourceDeck(this);
        else {
            System.out.println("Which card from the Face Up Cards?");
            deckChoice = in.nextInt();
            this.server.drawFaceUpCard(this, deckChoice);
        }
    }

    @Override
    public void printScore(int score) throws Exception {
        System.out.println("Your score is: " + score);
    }
}
