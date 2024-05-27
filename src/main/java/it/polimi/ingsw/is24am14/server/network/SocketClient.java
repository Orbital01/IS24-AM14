package it.polimi.ingsw.is24am14.server.network;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.utils.GSONAdapters.InitGSON;
import it.polimi.ingsw.is24am14.server.view.TUIView;
import it.polimi.ingsw.is24am14.server.view.VirtualView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class SocketClient {
    private final Socket socket;
    private String username;
    private final PrintWriter socketOut;
    private final BufferedReader socketIn;
    private final VirtualView view;
    private final Gson gson;


    public SocketClient() throws Exception {
        String hostName = "127.0.0.1";
        int portNumber = 12346;
        this.socket = new Socket(hostName, portNumber);
        this.socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socketOut = new PrintWriter(socket.getOutputStream(), true);
        this.view = new TUIView();

        gson = InitGSON.init();

        int code_response;
        do {
            this.username = this.view.askForUsername();
            this.socketOut.println(this.username);
            code_response = new Gson().fromJson(this.socketIn.readLine(), SocketResponse.class).code;
        } while (code_response != 200);

        while (true) {
            String line = socketIn.readLine();
            SocketResponse response = new Gson().fromJson(line, SocketResponse.class);

            switch (response.message) {
                case "askStartingOption":
                    this.chooseStartingOption(response.strings);
                    break;
                case "createLobby":
                    this.createLobby();
                    break;
                case "joinLobby":
                    this.joinLobby(response);
                    break;
                case "playersInLobby":
                    this.receivePlayersInLobby(response);
                    break;
                case "chooseColor":
                    List<TokenColour> colors = new ArrayList<>();
                    for (String colour : response.strings) {
                        colors.add(TokenColour.valueOf(colour));
                    }

                    this.selectColor(colors);
                    break;
                case "selectObjectiveCard":
                    this.selectObjectiveCard(response);
                    break;
                case "isFirstPlayer":
                    this.view.printBlackToken();
                    break;
                case "askForMove":
                    this.chooseMove(response);
                    break;
                case "askPickChoice":
                    this.pickChoice(response);
                    break;
                default:
            }
        }
    }

    public void chooseStartingOption(ArrayList<String> lobbies) throws Exception {
        int lobby_option;

        this.view.printLobbyOption(lobbies);
        lobby_option = this.view.getLobbyOption();

        send(new SocketResponse(200, String.valueOf(lobby_option)));
    }

    public void joinLobby(SocketResponse response) throws Exception {
        int lobbyIndex;

        this.view.printLobbyOption(response.strings);
        lobbyIndex = this.view.getLobbyIndex(response.strings);

        send(new SocketResponse(200, response.strings.get(lobbyIndex)));
    }

    public void createLobby() throws Exception {
        int numPlayers = this.view.getLobbyNumPlayers();
        send(new SocketResponse(200, String.valueOf(numPlayers)));
    }

    public void receivePlayersInLobby(SocketResponse response) throws Exception {
        this.view.printPlayersInLobby(response.strings);
    }

    public void selectColor(List<TokenColour> colors) throws Exception {
        this.view.printColors(colors);
        TokenColour color = this.view.chooseColor(colors);
        send(new SocketResponse(200, color.toString()));
    }

    public void selectObjectiveCard(SocketResponse response) throws Exception {
        ObjectiveCard card1 = gson.fromJson(response.strings.get(0), ObjectiveCard.class);
        ObjectiveCard card2 = gson.fromJson(response.strings.get(1), ObjectiveCard.class);

        this.view.printSecretObjective(card1, card2);
        int choice = this.view.chooseSecretObjective(card1, card2);
        send(new SocketResponse(200, String.valueOf(choice)));
    }

    public void chooseMove(SocketResponse response) throws Exception {
        GameArea board = gson.fromJson(response.strings.getFirst(), GameArea.class);
        ArrayList<PlayableCard> hand;

        this.view.printBoard(board);
        PlayableCard[] handArray = gson.fromJson(response.strings.get(1), PlayableCard[].class);
        hand = new ArrayList<>(Arrays.asList(handArray));
        this.view.printHand(hand);

        int choice = this.view.moveChoice();
        ArrayList<String> param = new ArrayList<>();

        if (choice == 0) {
            int indexFlipCard = this.view.chooseCardToFlip(hand);
            param.add(String.valueOf(indexFlipCard));
        } else if (choice == 1) {
            int indexCardToPlay = this.view.chooseCardToPlay(hand);
            Coordinates coordinatesToOverlap = this.view.chooseCardToOverlap(board);
            int indexToOverlap = this.view.chooseCornerIndex(board);

            param.add(String.valueOf(indexCardToPlay));
            param.add(gson.toJson(coordinatesToOverlap));
            param.add(String.valueOf(indexToOverlap));
        }
        send(new SocketResponse(200, String.valueOf(choice), param));
    }

    public void pickChoice(SocketResponse response) throws Exception {
        boolean goldDeckEmpty = Boolean.parseBoolean(response.strings.getFirst());
        boolean resourceDeckEmpty = Boolean.parseBoolean(response.strings.get(1));
        PlayableCard[] faceUpArray = gson.fromJson(response.strings.get(2), PlayableCard[].class);
        ArrayList<PlayableCard> faceUpCards = new ArrayList<>(Arrays.asList(faceUpArray));
        int option = this.view.pickChoices(goldDeckEmpty, resourceDeckEmpty, faceUpCards);

        ArrayList<String> param = new ArrayList<>();
        if (option > 1) {
            this.view.showFaceUpCards(faceUpCards);
            int faceUpIndex = this.view.chooseFaceUpCard(faceUpCards);
            param.add(String.valueOf(faceUpIndex));
        }

        send(new SocketResponse(200, String.valueOf(option), param));
    }

    public void send(SocketResponse message) throws Exception {
        this.socketOut.println(new Gson().toJson(message));
    }
}
