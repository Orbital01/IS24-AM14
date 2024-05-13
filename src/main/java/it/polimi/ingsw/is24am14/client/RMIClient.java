package it.polimi.ingsw.is24am14.client;

import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.network.RMIServerConnection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RMIClient extends UnicastRemoteObject implements ClientConnection {
    private RMIServerConnection server;

    protected RMIClient() throws RemoteException {
    }

    public void makeMove() throws Exception {
        //  Choose if you want to place a card or flip a card
        Scanner in = new Scanner(System.in);

        System.out.print("Select 0 to flip a card, 1 to put a card on the game board: ");
        int choice = in.nextInt();

        while (choice != 0 && choice != 1) {
            System.out.print("Not valid number. Select 0 to flip a card, 1 to put a card: ");
            choice = in.nextInt();
        }

        if (choice == 0) {
            //  flip a card
            System.out.println("Select a card to flip");
            int cardToFlip = in.nextInt();
            server.flipCard(cardToFlip);
        } else if (choice == 1) {
            //  Da mettere input utente poi
            //  choose a card from the hand
            int handIndex = 0;
            //  choose a card on the board
            Coordinates boardCoordinates = new Coordinates(0, 0);
            //  choose a corner index
            int cornerIndex = 0;

            server.playCard(server.getPlayerHand().get(handIndex), server.getGameBoard().get(boardCoordinates), cornerIndex);
        }
    }

    @Override
    public int sendInt() throws Exception {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    @Override
    public void chooseColor(List<TokenColour> colors, Player player) throws Exception {
        int color_index = 0;
        player.setColour(colors.get(color_index));
        colors.remove(color_index);
    }

    @Override
    public void pickObjective(ArrayList<ObjectiveCard> secrets, Player player) throws Exception {
        int secret_index = 0;
        player.setSecretObjective(secrets.get(secret_index));
    }

    @Override
    public void drawCard() throws Exception {
        int drawDeckIndex;
        Scanner in = new Scanner(System.in);
        drawDeckIndex = in.nextInt();
        if(drawDeckIndex==0) {
            server.drawResourceCard();
        }
        if(drawDeckIndex==1){
            server.drawGoldCard();
        }

        if(drawDeckIndex==2){
            int faceUpIndex = 0;
            server.drawFromFaceUp(faceUpIndex);
        }
    }

    @Override
    public void joinLobby(LobbyList lobby) throws Exception {
        int option, lobby_index, num_players;
        System.out.println("Digita: \n0 per unirti ad una lobby già esistente\n1 per creare una nuova lobby");
        Scanner in = new Scanner(System.in);
        option = in.nextInt();
        if (option == 0) {
            System.out.println("Scegli l'indice della lobby");
            lobby_index = in.nextInt();
            server.joinExistingLobby(lobby.getLobbies().get(lobby_index));
        } else if (option == 1) {
            System.out.println("Hai scelto di creare una nuova lobby");
            System.out.println("Scegli un numero di giocatori (metti tra 0 o 4, non c'è ancora il check se è legale)");
            num_players = in.nextInt();
            server.joinNewLobby(num_players);
        }
    }


    @Override
    public void execute() throws Exception {
        Registry registry;
        registry = LocateRegistry.getRegistry("127.0.0.1", 12345);

        this.server = (RMIServerConnection) registry.lookup("RMIServer");
        this.server.acceptConnection(this);
        System.out.println("Client connected");
    }

    @Override
    public void send(String message) throws Exception {
        //  TODO
    }


}
