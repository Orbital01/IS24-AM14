package it.polimi.ingsw.is24am14.server.network;

import it.polimi.ingsw.is24am14.server.controller.GameStateEnum;
import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.PlayableCard;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientLauncher {
    public static void main(String[] args) throws Exception {
        String username, host;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username:");
        username = scanner.nextLine();
        int option;

        ClientInterface client = new SocketClient();

        client.connect(username);
        System.out.println("Option:");
        option = scanner.nextInt();
        if (option == 1) {
            client.createLobby(2);
            scanner.nextLine();// a cosa servono questi?
            scanner.nextLine();
            client.startGame();
        } else if (option == 2) {
            System.out.println("Host:");
            scanner.nextLine();
            host = scanner.nextLine();
            client.joinLobby(host);
        }

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
                        System.out.println("Available colors:");
                        for (TokenColour color : client.getGameContext().getColors()) {
                            System.out.println(color);
                        }
                        String colorChoice = scanner.nextLine();
                        client.pickColor(TokenColour.valueOf(colorChoice));
                    }

                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.ChoosingSecretObjective && client.getGameContext().getGame().getPlayer(username).getSecretObjective() == null) {
                    System.out.println("Secret objective");
                    System.out.println(client.getGameContext().getObjectiveCardChoices(username).getFirst());
                    System.out.println(client.getGameContext().getObjectiveCardChoices(username).get(1));
                    client.pickObjectiveCard(client.getGameContext().getObjectiveCardChoices(username).get(0));

                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.Move) {

                    if (client.getGameContext().getGame().getActivePlayer().getPlayerNickname().equals(username)) {
                        System.out.println("It's my turn");
                    } else {
                        System.out.println("It's not your turn");
                    }

                    client.flipCard(0);
                    client.putCard(0, new Coordinates(0, 0), 0);
                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.Draw) {
                    client.drawGoldCard();
                } else if (client.getGameContext().getGameStateEnum() == GameStateEnum.ChoosingStarterCard) {
                    client.setStarterCard(client.getGameContext().getGame().getPlayer(username).getStarterCard());
                } else {
                    System.out.println("Boh non so");
                }
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
