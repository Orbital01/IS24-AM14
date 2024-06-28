package it.polimi.ingsw.is24am14.client.TUIFactory;


import it.polimi.ingsw.is24am14.client.view.printer.RenderBoard;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.network.ClientInterface;
import it.polimi.ingsw.is24am14.server.network.Message;
import net.fellbaum.jemoji.Emojis;

import java.util.*;
import java.util.regex.Pattern;

/**
 * This class is the Text User Interface Factory, it is used to print game assets and to get input from the user
 */
public class TUIFactory {

    /**
     * This method prints the start screen of the game
     */
    public void startScreen(){
        String green = "\033[32m";
        String reset = "\033[0m";

        System.out.println(green + "                                                                                                                                                                                ");
        System.out.println("      * ***                    **                                     ***** *     **                                                               ***                        ");
        System.out.println("    *  ****  *                  **                                 ******  **    **** *                *                                            ***      *                ");
        System.out.println("   *  *  ****                   **                                **   *  * **    ****                **                                             **     ***               ");
        System.out.println("  *  **   **                    **                               *    *  *  **    * *                 **                                             **      *                ");
        System.out.println(" *  ***           ****          **              ***    ***           *  *    **   *                 ******** **   ****     ***  ****                 **               ****    ");
        System.out.println("**   **          * ***  *   *** **      ***    * ***  **** *        ** **    **   *        ****    ********   **    ***  *  **** **** *    ****      **    ***       * **** * ");
        System.out.println("**   **         *   ****   *********   * ***      *** *****         ** **     **  *       * ***  *    **      **     ****    **   ****    * ***  *   **     ***     **  ****  ");
        System.out.println("**   **        **    **   **   ****   *   ***      ***  **          ** **     **  *      *   ****     **      **      **     **          *   ****    **      **    ****       ");
        System.out.println("**   **        **    **   **    **   **    ***      ***             ** **      ** *     **    **      **      **      **     **         **    **     **      **      ***      ");
        System.out.println("**   **        **    **   **    **   ********      * ***            ** **      ** *     **    **      **      **      **     **         **    **     **      **        ***    ");
        System.out.println(" **  **        **    **   **    **   *******      *   ***           *  **       ***     **    **      **      **      **     **         **    **     **      **          ***  ");
        System.out.println("  ** *      *  **    **   **    **   **          *     ***             *        ***     **    **      **      **      **     **         **    **     **      **     ****  **  ");
        System.out.println("   ***     *    ******    **    **   ****    *  *       *** *      ****          **     **    **      **       ******* **    ***        **    **     **      **    * **** *   ");
        System.out.println("    *******      ****      *****      *******  *         ***      *  *****               ***** **      **       *****   **    ***        ***** **    *** *   *** *    ****    ");
        System.out.println("      ***                   ***        *****                     *     **                 ***   **                                        ***   **    ***     ***             ");
        System.out.println("                                                         *                                                                                                            ");
        System.out.println("                                                          **"+ reset);
    }

    /**
     * This method prints the legend of the game
     */
    public void printLegend(){
        String fungi = Emojis.MUSHROOM.getEmoji();
        String plant = Emojis.SHAMROCK.getEmoji();
        String animal = Emojis.WOLF.getEmoji();
        String insect = Emojis.BUTTERFLY.getEmoji();
        String hidden = Emojis.PROHIBITED.getEmoji();
        String empty = Emojis.WHITE_MEDIUM_SQUARE.getEmoji();
        String overlapped = Emojis.REPEAT_BUTTON.getEmoji();
        String noElement = Emojis.WHITE_LARGE_SQUARE.getEmoji();
        String inkwell = Emojis.BLACK_NIB.getEmoji();
        String manuscript = Emojis.SCROLL.getEmoji();
        String quill = Emojis.FEATHER.getEmoji();
        System.out.println("############### LEGEND ##################");
        //System.out.println("The following is the legend of the game");
        System.out.println("Description of types&corners:");
        System.out.println(fungi + ": Fungi");
        System.out.println(plant + ": Plant");
        System.out.println(animal + ": Animal");
        System.out.println(insect + ": Insect");
        System.out.println(hidden + ": Hidden corner");
        System.out.println(empty + ": Empty corner");
        System.out.println(noElement + ": No element");
        System.out.println(overlapped + ": Overlapped corner");
        System.out.println("Description of the objects:");
        System.out.println(inkwell + ": Inkwell");
        System.out.println(manuscript + ": Manuscript");
        System.out.println(quill + ": Quill");
        System.out.println("#######################################");
    }

    /**
     * This method prints the main menu of the game
     */
    public String askForUsername() {
        Scanner in = new Scanner(System.in);
        String username;
        do {
            System.out.println("Choose a username");
            username = in.nextLine();
            if (username.trim().isEmpty()) {
                System.out.println("Invalid input. Username cannot be empty. Try again.");
            }
        } while (username.trim().isEmpty());
        return username;
    }

    /**
     * This method waits for the user input for the connection method
     */
    public int connectionIndex(){
        int index;
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a connection method:\n0) Socket\n1) RMI");
        String input;
        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            input = in.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches() || Integer.parseInt(input) < 0 || Integer.parseInt(input) > 1);

        index = Integer.parseInt(input);

        while (index < 0 || index > 1) {
            System.out.println("Invalid index. Try again.");
            index = in.nextInt();
        }
        return index;
    }

    /**
     * This method prints the lobby available
     */
    public void printLobbyOption(ArrayList<String> lobbiesNames) {
        System.out.println("There are " + lobbiesNames.size() + " lobbies");
        for (int i = 0; i < lobbiesNames.size(); i++) {
            System.out.println(i + ") " + lobbiesNames.get(i));
        }
    }

    /**
     * This method waits for the user input for creating or joining a lobby
     */
    public int getLobbyOption() {
        int option;
        Scanner in = new Scanner(System.in);
        System.out.println("Digit:\n0 to join an existing lobby.\n1 to create a new lobby.");

        String input;
        Pattern pattern = Pattern.compile("^0(\\.\\d+)?|1(\\.0+)?$");

        do {
            input = in.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches());

        option = Integer.parseInt(input);
        while (option < 0 || option > 1) {
            System.out.println("Invalid option. Try again.");
            option = in.nextInt();
        }

        return option;
    }

    /**
     * This method waits for the user input for the lobby he wants to join
     */
    public int getLobbyIndex(ArrayList<String> lobbiesNames) {
        int index;
        Scanner in = new Scanner(System.in);
        System.out.println("Choose which lobby you would like to join");

        String input;
        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            input = in.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches() || Integer.parseInt(input) < 0 || Integer.parseInt(input) > lobbiesNames.size() - 1);

        index = Integer.parseInt(input);

        return index;
    }

    /**
     * This method gets the number of players for the lobby
     */
    public int getLobbyNumPlayers() {
        System.out.println("Enter number of players (must be between 2 and 4)");
        Scanner in = new Scanner(System.in);
        int numPlayers;
        String input;
        Pattern pattern = Pattern.compile("^[234]$");

        do {
            input = in.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches());

        numPlayers = Integer.parseInt(input);
        while (numPlayers < 1 || numPlayers > 4) {
            System.out.println("Invalid number. Try again.");
            numPlayers = in.nextInt();
        }
        return numPlayers;
    }

    /**
     * This method prints the number of players in the lobby
     */
    public void printPlayersInLobby(ArrayList<String> players) {
        System.out.println("Players in the lobby:");
        for (String player : players) {
            System.out.println(player);
        }
    }

    /**
     * This method prints the available token colours
     */
    public void printColors(List<TokenColour> colors) {
        System.out.println("The available colors are:");
        for (int i = 0; i < colors.size(); i++) {
            System.out.println(i + ") " + colors.get(i).toString());
        }
    }

    /**
     * This method waits for the user input for the colour he wants to be
     */
    public TokenColour chooseColor(List<TokenColour> colors) {
        int colorIndex;
        Scanner in = new Scanner(System.in);
        System.out.println("Choose which color would you like to be");

        String input;
        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            input = in.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches() || Integer.parseInt(input) < 0 || Integer.parseInt(input) > colors.size() - 1);

        colorIndex = Integer.parseInt(input);

        while (colorIndex < 0 || colorIndex > colors.size() - 1) {
            System.out.println("Invalid index. Try again.");
            colorIndex = in.nextInt();
        }

        return colors.get(colorIndex);
    }

    /**
     * This method prints the secret objectives for the player
     */
    public void printSecretObjective(ObjectiveCard card1, ObjectiveCard card2) {
        System.out.println("First Objective Card (0):");
        for (String s : card1.drawCard()) {
            System.out.println(s);
        }
        System.out.println("Second Objective Card (1):");
        for (String s : card2.drawCard()) {
            System.out.println(s);
        }
    }

    /**
     * This method waits for the user input for the secret objective he wants to choose
     */
    public String chooseSecretObjective(ObjectiveCard card1, ObjectiveCard card2) {

        String secretObjectiveChoice;
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a secret objective");

        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            secretObjectiveChoice = in.nextLine();
            if (!pattern.matcher(secretObjectiveChoice).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(secretObjectiveChoice).matches());

        while (!secretObjectiveChoice.equals("0") && !secretObjectiveChoice.equals("1")) {
            System.out.println("Invalid index. Try again.");
            secretObjectiveChoice = in.nextLine();
        }

        return secretObjectiveChoice;
    }

    /**
     * This method warns the player that he has the black token
     */
    public void printBlackToken() {
        System.out.println("You have the black token, that means that you're the first player");
    }

    /**
     * This method prints the game board
     */
    public void printBoard(GameArea board) {
        System.out.println("Your board:");
        RenderBoard render = new RenderBoard(board);
        render.printBoard();
    }

    /**
     * This method prints the player's hand
     */
    public void printHand(ArrayList<PlayableCard> hand) {
        System.out.println("Your hand:");
        for (PlayableCard card : hand) {
            System.out.println(card.getSide());
            for (String s : card.drawCard()) {
                System.out.println(s);
            }
        }
    }

    /**
     * This method prints the player menu during his turn
     */
    public int moveChoice() {
        int choice;
        Scanner in = new Scanner(System.in);
        System.out.println("It's your turn!");
        System.out.println("Digit:\n0 to flip a Card in your hand.\n1 to put a card on the board. \n2 to print the legend. " +
                "\n3 to see the chat. \n4 to send a message. \n5 punteggi. \n6 board degli altri player \n7 see all the objective cards");

        String input;
        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            input = in.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches() || Integer.parseInt(input) < 0 || Integer.parseInt(input) > 7);

        choice = Integer.parseInt(input);

        while (choice < 0 || choice > 7) {
            System.out.println("Invalid choice. Try again.");
            choice = in.nextInt();
        }

        return choice;
    }

    /**
     * This method prints the common and secret objective cards for the player
     */
    public void getObjectiveCards(ClientInterface context) {

        System.out.println("Common Objective Cards:");
        try {
            ArrayList<ObjectiveCard> common = context.getGameContext().getGame().getCommonObjective();
            for (ObjectiveCard card : common) {
                for (String s : card.drawCard()) {
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            System.out.println("Error getting common objective cards");
        }

        System.out.println("Secret Objective Cards:");

        try {
            ObjectiveCard priv = context.getGameContext().getGame().getPlayer(context.getUsername()).getSecretObjective();
            for (String s : priv.drawCard()) {
                System.out.println(s);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public int chooseCardToFlip(ArrayList<PlayableCard> hand) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a card to flip");

        String input;
        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            input = scanner.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches() || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= hand.size());

        choice = Integer.parseInt(input);

        while (choice < 0 || choice >= hand.size()) {
            System.out.println("Invalid choice");
            choice = scanner.nextInt();
        }

        return choice;
    }

    public int chooseCardToPlay(ArrayList<PlayableCard> hand) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a card to play");

        String input;
        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            input = scanner.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches() || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= hand.size());

        choice = Integer.parseInt(input);

        while (choice < 0 || choice >= hand.size()) {
            System.out.println("Invalid choice");
            choice = scanner.nextInt();
        }
        return choice;
    }

    public Coordinates chooseCardToOverlap(GameArea board) {
        int x, y;
        Coordinates coordinates;
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a card to overlap:");

        String input;
        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            System.out.println("-> Insert the index of the row");
            input = in.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches());

        x = Integer.parseInt(input);

        do {
            System.out.println("-> Insert the index of the column");
            input = in.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches());

        y = Integer.parseInt(input);

        coordinates = new Coordinates(x, y);
        while (board.getCard(coordinates) == null) {
            System.out.println("Invalid coordinates. Try again.");
            x = in.nextInt();
            y = in.nextInt();
            coordinates = new Coordinates(x, y);
        }
        return coordinates;
    }

    public int chooseCornerIndex(GameArea board) {
        int choice;
        Scanner in = new Scanner(System.in);

        System.out.println("Choose a corner index");

        String input;
        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            input = in.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches() || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= 4);

        choice = Integer.parseInt(input);

        while (choice < 0 || choice >= 4) {
            System.out.println("Invalid choice");
            choice = in.nextInt();
        }
        return choice;
    }

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

        String input;
        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            input = scanner.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches() || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= 3 || (Integer.parseInt(input) == 0 && goldDeck.isEmpty()) || (Integer.parseInt(input) == 1 && resourceDeck.isEmpty()));

        option = Integer.parseInt(input);

        while (option < 0 || option >= 3 || (option == 0 && goldDeck.isEmpty()) || (option == 1 && resourceDeck.isEmpty())) {
            System.out.println("Invalid option. Try again.");
            option = scanner.nextInt();
        }
        return option;
    }

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

        String input;
        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            input =scanner.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches());

        option = Integer.parseInt(input);

        while (option < 0 || option >= 3 || (option == 0 && goldDeckEmpty) || (option == 1 && resourceDeckEmpty)) {
            System.out.println("Invalid option. Try again.");
            option = scanner.nextInt();
        }
        return option;
    }

    public int chooseFaceUpCard(ArrayList<PlayableCard> faceUpCards) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        String input;
        Pattern pattern = Pattern.compile("^-?\\d+$");

        do {
            input = scanner.nextLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input: insert an integer value");
            }
        } while (!pattern.matcher(input).matches() || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= faceUpCards.size());

        choice = Integer.parseInt(input);

        while (choice < 0 || choice >= faceUpCards.size()) {
            System.out.println("Invalid choice. Try again.");
            choice = scanner.nextInt();
        }
        return choice;
    }
    
    public void showFaceUpCards(ArrayList<PlayableCard> faceUpCards) {
        int i = 0;
        for (PlayableCard card : faceUpCards) {
            System.out.println(i + ")");
            for (String line : card.drawCard()) {
                System.out.println(line);
            }
            i++;
        }
    }
    
    public void printScore(int score) {
        System.out.println("Your score is " + score);
    }

    public void printWinner(String winner) {
        System.out.println("The winner is " + winner + "!!!");
    }

    public String getWinner(ClientInterface client) throws Exception {
        ArrayList<Player> gamePlayers = new ArrayList<>();
        gamePlayers = client.getGameContext().getGame().getPlayers();
        Player winner = gamePlayers.stream()
                .max(Comparator.comparing(Player::getScore))
                .orElse(null);
        return winner.getPlayerNickname();
    }

    public int printChat(ClientInterface client, int index){
        ArrayList<Message> messages;
        String user;

        try {
            messages = client.getGameContext().getMessages();
            user = client.getUsername();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("############### Last Messages Received ##################");

        for (int i = index; i < messages.size(); i++) {
            Message message = messages.get(i);

            if(message.getReceiver().equals("")){
                System.out.println(message.getSender() + ": " + message.getMessage());
            }else if(message.getReceiver().equals(user)) {
                System.out.println("private message from " + message.getSender() + ": " + message.getMessage());
            }
        }

        return messages.size();
    }

    public void askForMessage(ClientInterface client) throws Exception {
        System.out.println("type the number corresponding to the receiver of the message and type the massage or 5 to exit");

        int counter = 1;
        System.out.println("0 -> all players");
        for (Player player : client.getGameContext().getGame().getPlayers()){
            System.out.println(counter + " -> " + player.getPlayerNickname());
            counter++;
        }

        System.out.println("Type the message");

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.isEmpty() || input.isBlank() || input.length()<2){
            System.out.println("Invalid input");
            return;
        }

        String[] parts = input.split(" ",2);

        int receiver = Integer.parseInt(parts[0]);
        String message = parts[1];

        if(receiver == 5){
            return;
        }else if (receiver!=0) {
            client.sendMessage(client.getGameContext().getGame().getPlayer(receiver-1).getPlayerNickname(), message);
            System.out.println("Message sent");
        }else if(receiver==0) {
            client.sendMessage("", message);
            System.out.println("Message sent");
        }

    }

    public void getScores(ClientInterface client) throws Exception {
        ArrayList<Player> gamePlayers;
        gamePlayers = client.getGameContext().getGame().getPlayers();
        System.out.println("############### Scores ##################");
        for (Player player : gamePlayers) {
            System.out.println(player.getPlayerNickname() + " -> " + player.getScore());
        }
    }

    public void getOtherBoard(ClientInterface client) throws Exception {
        //select the player
        ArrayList<Player> gamePlayers = client.getGameContext().getGame().getPlayers();
        int counter = 1;
        for (Player player : gamePlayers){
            System.out.println(counter + " -> " + player.getPlayerNickname());
            counter++;
        }

        System.out.println("select the player to see their board");
        Scanner in = new Scanner(System.in);
        int playerIndex;
        try {
            playerIndex = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: insert an integer value");
            playerIndex = in.nextInt();
        }
        GameArea board = gamePlayers.get(playerIndex-1).getPlayerBoard();
        RenderBoard render = new RenderBoard(board);
        render.printBoard();
    }

}
