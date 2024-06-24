package it.polimi.ingsw.is24am14.client;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.network.ClientInterface;
import it.polimi.ingsw.is24am14.server.network.RMIClient;
import it.polimi.ingsw.is24am14.server.network.SocketClient;
import it.polimi.ingsw.is24am14.server.view.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TUIViewLauncher {
    public static void main(String[] args) throws Exception {
        TUIView tui = new TUIView();
        //Let the client choose the type of connection
        ClientInterface client;
        int connectionIndex = tui.connectionIndex();
        if (connectionIndex == 0){
            client = new SocketClient();
        }
        else {
            client = new RMIClient();
        }

        String username, host;
        Scanner scanner = new Scanner(System.in);

        //Ask for username & connect
        username = tui.askForUsername();
        client.connect(username);

        //Ask for the option
        int option;
        option = tui.getLobbyOption();
        if (option == 0){
            //Show available lobbies
            tui.printLobbyOption(client.getLobbyList());
            //Get Lobby index and join it
            int lobbbyIndex = tui.getLobbyIndex(client.getLobbyList());
            System.out.println("Players in lobby: " + client.getLobbyClients(client.getLobbyList().get(lobbbyIndex)));
            tui.printPlayersInLobby(client.getLobbyClients(client.getLobbyList().get(lobbbyIndex)));
            client.joinLobby(client.getLobbyList().get(lobbbyIndex));
        }
        else{
            //Create a new lobby and start the game
            int numPlayers = tui.getLobbyNumPlayers();
            client.createLobby(numPlayers);
            client.updateGameContext();
            while (client.getLobbyClients(username).size() < numPlayers) {
                System.out.println("Waiting for players to join...");
                TimeUnit.SECONDS.sleep(4);
                client.updateGameContext();
            }
            client.startGame();
        }

        //Visualize the lobbies
        //tui.printLobbyOption(client.getLobbyList());

        while (true) {
            client.updateGameContext();
            if (client.getGameContext() != null) {
                if (client.getGameContext().getGameStateEnum() == GameStateEnum.DeckInit) {
                    System.out.println("Initializing");
                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.ChoosingColor) {

                    int myIndex = client.getGameContext().getGame().getPlayers().indexOf(client.getGameContext().getGame().getPlayer(client.getUsername()));
                    if (myIndex == -1) {throw new RuntimeException("Player not found");}

                    boolean myTurn = true;
                    for (int i = 0; i < myIndex; i++) {
                        if (client.getGameContext().getGame().getPlayers().get(i).getColour() == null) {
                            myTurn = false;
                        }
                    }

                    if (myTurn && client.getGameContext().getGame().getPlayers().get(myIndex).getColour() == null) {
//                        System.out.println("Available colors:");
//                        for (TokenColour color : client.getGameContext().getColors()) {
//                            System.out.println(color);
//                        }
//                        String colorChoice = scanner.nextLine();
                        tui.printColors(client.getGameContext().getColors());
                        TokenColour colorChoice = tui.chooseColor(client.getGameContext().getColors());
                        //client.pickColor(TokenColour.valueOf(colorChoice));
                        client.pickColor(colorChoice);
                    }

                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.ChoosingSecretObjective && client.getGameContext().getGame().getPlayer(username).getSecretObjective() == null) {
                    System.out.println("Secret objective");
                    ObjectiveCard firstObjective = client.getGameContext().getObjectiveCardChoices(username).get(0);
                    ObjectiveCard secondObjective = client.getGameContext().getObjectiveCardChoices(username).get(1);
//                    System.out.println(client.getGameContext().getObjectiveCardChoices(username).getFirst());
//                    System.out.println(client.getGameContext().getObjectiveCardChoices(username).get(1));
                    tui.printSecretObjective(firstObjective, secondObjective);
                    //client.pickObjectiveCard(client.getGameContext().getObjectiveCardChoices(username).get(0));
                    client.pickObjectiveCard(client.getGameContext().getObjectiveCardChoices(username).get(tui.chooseSecretObjective(firstObjective, secondObjective)));

                    //After all clients have chosen their secret objectives, the black token for the first player is assigned
//                    if (client.getGameContext().getGame().getPlayer(username).isFirstPlayer()){
//                        tui.printBlackToken();
//                    }

                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.Move) {

                    int counter = 0;
                    while (counter < 1){
                        if (client.getGameContext().getGame().getPlayer(username).isFirstPlayer()){
                            tui.printBlackToken();
                        }
                        counter++;
                    }

                    if (client.getGameContext().getGame().getActivePlayer().getPlayerNickname().equals(username)) {
                        tui.printBoard(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                        int moveChoice = tui.moveChoice();
                        if (moveChoice == 0){
                            tui.printHand(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                            int cardToFlip = tui.chooseCardToFlip(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                            client.flipCard(cardToFlip);
                        }
                        else{
                            tui.printHand(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                            int cardToPlay = tui.chooseCardToPlay(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                            Coordinates cardToOverlap = tui.chooseCardToOverlap(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                            int cornerIndex = tui.chooseCornerIndex(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                            client.putCard(cardToPlay, cardToOverlap, cornerIndex);
                            tui.printBoard(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                        }

                        //Show points
                        tui.printScore(client.getGameContext().getGame().getPlayer(username).getScore());
                    } else {
                        System.out.println("It's not your turn");
                    }

                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.Draw) {
                    int drawChoice = tui.pickChoices(client.getGameContext().getGame().getGoldDeck(), client.getGameContext().getGame().getResourceDeck(), client.getGameContext().getGame().getFaceUpCards());
                    if (drawChoice == 0){ //draw from GoldDeck
                        client.drawGoldCard();
                    }
                    else if (drawChoice == 1){ //draw from ResourceDeck
                        client.drawResourceCard();
                    }
                    else{ //draw from FaceUpCards
                        System.out.println("Face up cards:");
                        tui.showFaceUpCards(client.getGameContext().getGame().getFaceUpCards());
                        int faceUpChoice = tui.chooseFaceUpCard(client.getGameContext().getGame().getFaceUpCards());
                        client.drawFaceUpCard(faceUpChoice);
                    }
                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.ChoosingStarterCard) {
                    StarterCard starterCard = client.getGameContext().getGame().getPlayer(username).getStarterCard();
                    System.out.println("Your starter card:");
                    for (String line : starterCard.drawCard()) {
                        System.out.println(line);
                    }
                    System.out.println("What side of the starter card would you like to use?");
                    System.out.println("Digit: \n 0 for the front side \n 1 for the back side");
                    int side = scanner.nextInt();
                    if (side == 1){
                        starterCard.flipSide();
                    }
                    client.setStarterCard(starterCard);
                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.EndGame) {
                    String winner = tui.getWinner(client);
                    tui.printWinner(winner);
                    //devo far terminare il gioco
                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.LastMove){
                    System.out.println("Last move:");
                    if (client.getGameContext().getGame().getActivePlayer().getPlayerNickname().equals(username)) {
                        int moveChoice = tui.moveChoice();
                        if (moveChoice == 0){
                            System.out.println("Your hand:");
                            tui.printHand(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                            int cardToFlip = tui.chooseCardToFlip(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                            client.flipCard(cardToFlip);
                        }
                        else{
                            System.out.println("Your hand:");
                            tui.printHand(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                            int cardToPlay = tui.chooseCardToPlay(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                            Coordinates cardToOverlap = tui.chooseCardToOverlap(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                            int cornerIndex = tui.chooseCornerIndex(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                            client.putCard(cardToPlay, cardToOverlap, cornerIndex);
                            tui.printBoard(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                        }

                        //Show points
                        tui.printScore(client.getGameContext().getGame().getPlayer(username).getScore());
                    } else {
                        System.out.println("It's not your turn");
                    }

                }
            }
            TimeUnit.SECONDS.sleep(1);
        }


    }






}
