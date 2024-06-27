package it.polimi.ingsw.is24am14.client;
import it.polimi.ingsw.is24am14.client.TUIFactory.TUIFactory;
import it.polimi.ingsw.is24am14.server.controller.GameContext;
import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.network.ClientInterface;
import it.polimi.ingsw.is24am14.server.network.NotYourColorTurnException;
import it.polimi.ingsw.is24am14.server.network.RMIClient;
import it.polimi.ingsw.is24am14.server.network.SocketClient;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TUIViewLauncher {
    public static void main(String[] args) throws Exception {
        TUIFactory tui = new TUIFactory();

        //Print the start screen
        tui.startScreen();

        //Let the client choose the type of connection
        ClientInterface client = null;
        int connectionIndex = tui.connectionIndex();
        if (connectionIndex == 0){
            try {
                client = new SocketClient();
            } catch (Exception e) {
                System.out.println("Error while connecting to the server via Socket connection");
                System.exit(0);
            }
        }
        else {
            try {
                client = new RMIClient();
            } catch (Exception e) {
                System.out.println("Error while connecting to the server via RMI connection");
                System.exit(0);
            }
        }

        String username, host;

        Scanner scanner = new Scanner(System.in);

        //Ask for username & connect
        username = tui.askForUsername();
        while (username.isEmpty()){
            System.out.println("Invalid username");
            username = tui.askForUsername();
        }

        try{
            client.connect(username);
        } catch (Exception e){
            System.out.println("Error while connecting to the server");
        }

        //Ask for the option
        int option;
        option = tui.getLobbyOption();
        try {
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

                while (numPlayers < 2 || numPlayers > 4){
                    System.out.println("Invalid number of players");
                    numPlayers = tui.getLobbyNumPlayers();
                }

                client.createLobby(numPlayers);
                client.updateGameContext();
                while (client.getLobbyClients(username).size() < numPlayers) {
                    System.out.println("Waiting for players to join...");
                    TimeUnit.SECONDS.sleep(4);
                    client.updateGameContext();
                }
                client.startGame();
            }
        } catch (Exception e) {
            System.out.println("Error while arranging the game");
        }

        //Game loop
        int counter = 0;
        int lastMessageIndex = 0;

        //Print the legend
        tui.printLegend();

        while (true) {
            client.updateGameContext();

            if (client.getGameContext() != null) {

                boolean myTurn = client.getGameContext().getGame().getActivePlayer().getPlayerNickname().equals(username);

                if (client.getGameContext().getGameStateEnum() == GameStateEnum.DeckInit) {

                    System.out.println("Initializing");

                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.ChoosingColor) {
                    String clientUsername;
                    try {
                        clientUsername = client.getUsername();
                    } catch (Exception e) {
                        System.out.println("Error while getting the username");
                        break;
                    }

                    int myIndex = client.getGameContext().getGame().getPlayers().indexOf(client.getGameContext().getGame().getPlayer(clientUsername));
                    myTurn = true;

                    for (int i = 0; i < myIndex; i++) {
                        if (client.getGameContext().getGame().getPlayers().get(i).getColour() == null) myTurn = false;
                    }

                    if (myIndex == -1) {throw new RuntimeException("Player not found");}
                    if (myTurn && client.getGameContext().getGame().getPlayers().get(myIndex).getColour() == null) {
                        try {
                            tui.printColors(client.getGameContext().getColors());
                            TokenColour colorChoice = tui.chooseColor(client.getGameContext().getColors());
                            client.pickColor(colorChoice);
                        } catch (Exception e) {
                            System.out.println("Invalid color choice");
                        }
                    }

                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.ChoosingSecretObjective && client.getGameContext().getGame().getPlayer(username).getSecretObjective() == null) {

                    System.out.println("Secret objective");
                    ObjectiveCard firstObjective = client.getGameContext().getObjectiveCardChoices(username).get(0);
                    ObjectiveCard secondObjective = client.getGameContext().getObjectiveCardChoices(username).get(1);
                    tui.printSecretObjective(firstObjective, secondObjective);
                    int objectiveChoice = Integer.parseInt(tui.chooseSecretObjective(firstObjective, secondObjective));
                    try {
                        client.pickObjectiveCard(client.getGameContext().getObjectiveCardChoices(username).get(objectiveChoice));
                    } catch (Exception e) {
                        System.out.println("Invalid objective choice");
                    }

                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.Move) {
                    //flush the terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    //let the player know if he is the first player
                    while (counter < 1){
                        if (client.getGameContext().getGame().getPlayer(username).isFirstPlayer()){
                            tui.printBlackToken();
                        }
                        counter++;
                    }

                    //if i'm the active player i can play
                    if (client.getGameContext().getGame().getActivePlayer().getPlayerNickname().equals(username)) {

                        //print the board
                        tui.printBoard(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                        //print the player hand
                        tui.printHand(client.getGameContext().getGame().getPlayer(username).getPlayerHand());


                        int moveChoice = tui.moveChoice();
                        if (moveChoice == 0){

                            int cardToFlip = tui.chooseCardToFlip(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                            try {
                                client.flipCard(cardToFlip);
                            } catch (Exception e) {
                                System.out.println("Error while flipping the card");
                            }

                        } else if(moveChoice == 1){

                            int cardToPlay = tui.chooseCardToPlay(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                            Coordinates cardToOverlap = tui.chooseCardToOverlap(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                            int cornerIndex = tui.chooseCornerIndex(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());

                            try {
                                client.putCard(cardToPlay, cardToOverlap, cornerIndex);
                            } catch (Exception e) {
                                System.out.println("Invalid move");
                            }

                            //print the updated board
                            client.updateGameContext();
                            tui.printBoard(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());

                        }else if(moveChoice == 2){
                            tui.printLegend();
                        } else if (moveChoice == 3) {
                            //flush the terminal
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            //print the chat
                            lastMessageIndex = tui.printChat(client, lastMessageIndex);
                        } else if (moveChoice == 4) {
                            try {
                                tui.askForMessage(client);
                            } catch (Exception e) {
                                System.out.println("Error while sending the message");
                            }
                        } else if (moveChoice == 5){
                            try {
                                tui.getScores(client);
                            } catch (Exception e) {
                                System.out.println("Error while getting the scores");
                            }
                        } else if (moveChoice == 6){
                            try {
                                tui.getOtherBoard(client);
                            } catch (Exception e) {
                                System.out.println("Error while getting the other player's board");
                            }
                        } else if (moveChoice == 7){
                            //Get all the objective cards
                            tui.getObjectiveCards(client);
                        }

                    } else {
                        System.out.println("It's not your turn");
                    }

                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.Draw) {
                    if (myTurn) {
                        int drawChoice = tui.pickChoices(client.getGameContext().getGame().getGoldDeck(), client.getGameContext().getGame().getResourceDeck(), client.getGameContext().getGame().getFaceUpCards());
                        if (drawChoice == 0) { //draw from GoldDeck
                            try {
                                client.drawGoldCard();
                            } catch (Exception e) {
                                System.out.println("Error while drawing the gold card");
                            }
                        } else if (drawChoice == 1) { //draw from ResourceDeck
                            try {
                                client.drawResourceCard();
                            } catch (Exception e) {
                                System.out.println("Error while drawing the resource card");
                            }
                        } else { //draw from FaceUpCards
                            System.out.println("Face up cards:");
                            tui.showFaceUpCards(client.getGameContext().getGame().getFaceUpCards());
                            int faceUpChoice = tui.chooseFaceUpCard(client.getGameContext().getGame().getFaceUpCards());
                            try {
                                client.drawFaceUpCard(faceUpChoice);
                            } catch (Exception e) {
                                System.out.println("Error while drawing the face up card");
                            }
                        }
                    }

                    //Show points
                    tui.printScore(client.getGameContext().getGame().getPlayer(username).getScore());


                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.ChoosingStarterCard && client.getGameContext().getGame().getPlayer(client.getUsername()).getPlayerBoard().getBoard().isEmpty()) {
                    StarterCard starterCard = client.getGameContext().getGame().getPlayer(username).getStarterCard();
                    System.out.println("Your starter card:");
                    for (String line : starterCard.drawCard()) {
                        System.out.println(line);
                    }

                    System.out.println("What side of the starter card would you like to use?");
                    System.out.println("Digit: \n 0 to keep this side \n 1 to flip the card");

                    //Let the player choose the side of the starter card
                    int side = scanner.nextInt();
                    while (side != 0){
                        starterCard.flipSide();
                        for (String line : starterCard.drawCard()) {
                                System.out.println(line);
                            }
                        System.out.println("Digit: \n 0 to keep this side \n 1 to flip the card");
                        side = scanner.nextInt();
                    }

                    try {
                        client.setStarterCard(starterCard);
                    } catch (Exception e) {
                        System.out.println("Error while setting the starter card");
                    }
                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.EndGame) {
                    try {
                        String winner = tui.getWinner(client);
                        tui.printWinner(winner);
                        System.exit(0);
                    } catch (Exception e) {
                        System.out.println("Error while getting the winner");
                    }

                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.LastMove){
                    System.out.println("Last move:");
                    if (client.getGameContext().getGame().getActivePlayer().getPlayerNickname().equals(username)) {
                        int moveChoice = tui.moveChoice();
                        if (moveChoice == 0){
                            try {
                                System.out.println("Your hand:");
                                tui.printHand(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                                int cardToFlip = tui.chooseCardToFlip(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                                client.flipCard(cardToFlip);
                            } catch (Exception e) {
                                System.out.println("Invalid move");
                            }
                        }
                        else{
                            try {
                                System.out.println("Your hand:");
                                tui.printHand(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                                int cardToPlay = tui.chooseCardToPlay(client.getGameContext().getGame().getPlayer(username).getPlayerHand());
                                Coordinates cardToOverlap = tui.chooseCardToOverlap(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                                int cornerIndex = tui.chooseCornerIndex(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                                client.putCard(cardToPlay, cardToOverlap, cornerIndex);
                                tui.printBoard(client.getGameContext().getGame().getPlayer(username).getPlayerBoard());
                            } catch (Exception e) {
                                System.out.println("Invalid move");
                            }
                        }

                        //Show points
                        tui.printScore(client.getGameContext().getGame().getPlayer(username).getScore());
                    } else {
                        System.out.println("It's not your turn");
                    }

                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Error in thread execution");
            }
        }


    }






}
