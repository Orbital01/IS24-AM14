package it.polimi.ingsw.is24am14.server.network;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.controller.Lobby;
import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.utils.GSONAdapters.InitGSON;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.Socket;

public class SocketMediator implements ClientHandler {
    private String buffer;
    public PrintWriter socketOut;
    public Scanner socketIn;
    private String username;
    private final LobbyList lobbyList;
    private final Gson gson;

    public SocketMediator(Socket socket, LobbyList lobbyList) {
        this.buffer = "";
        this.lobbyList = lobbyList;

        gson = InitGSON.init();

        try {
            this.socketIn = new Scanner(socket.getInputStream());
            this.socketOut = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(SocketResponse message) throws Exception {
        this.socketOut.println(new Gson().toJson(message));
    }

    public String receive() {
        return this.socketIn.nextLine();
    }

    public void manage() throws Exception {
        this.acceptConnection();
        this.askStartingOption(this.lobbyList.getLobbiesNames());

        if (this.buffer.equals("join")) this.joinExistingLobby();
        else if (this.buffer.equals("create")) this.createNewLobby();

        while (true) {
            SocketResponse context = new Gson().fromJson(this.buffer, SocketResponse.class);
        }
    }

    public void acceptConnection() throws Exception {
        String user = receive();
        while (lobbyList.getClientHandler(user) != null) {
            send(new SocketResponse(409, "username already existing"));
            user = receive();
        }
        send(new SocketResponse(200, "ok"));

        this.username = user;
        this.lobbyList.addClientHandler(this);
        System.out.println("Socket Client connected");
    }

    @Override
    public String getUsername() throws Exception {
        return username;
    }

    @Override
    public void askStartingOption(ArrayList<String> lobbiesNames) throws Exception {
        send(new SocketResponse(100, "askStartingOption", lobbiesNames));
        SocketResponse answer = new Gson().fromJson(this.receive(), SocketResponse.class);

        if (answer.message.equals("0")) this.buffer = "join";
        else if (answer.message.equals("1")) this.buffer = "create";
    }

    public void joinExistingLobby() throws Exception {
        send(new SocketResponse(100, "joinLobby", lobbyList.getLobbiesNames()));
        SocketResponse answer = new Gson().fromJson(this.receive(), SocketResponse.class);
        this.lobbyList.joinLobby(this.username, answer.message);

        sendPlayersInLobby(this.lobbyList.getLobbyByHost(this.username));
    }

    public void createNewLobby() throws Exception {
        send(new SocketResponse(100, "createLobby"));
        SocketResponse answer = new Gson().fromJson(this.receive(), SocketResponse.class);
        int numPlayer = Integer.parseInt(answer.message);
        this.lobbyList.createLobby(this.username, numPlayer);
        this.lobbyList.joinLobby(this.username, this.username);

        sendPlayersInLobby(this.lobbyList.getLobbyByHost(this.username));
    }

    public void sendPlayersInLobby(Lobby lobby) throws Exception {
        ArrayList<String> players = new ArrayList<>();
        for (ClientHandler player : lobby.getPlayers()) {
            players.add(player.getUsername());
        }

        send(new SocketResponse(100, "playersInLobby", players));
    }

    @Override
    public void assignColor(List<TokenColour> colors) throws Exception {
        ArrayList<String> colorsString = new ArrayList<>();
        for (TokenColour colour : colors) {
            colorsString.add(colour.toString());
        }

        //this.buffer = new Gson().toJson(new SocketResponse(100, "colors", colorsString));
        sendColors(colorsString);
        SocketResponse response = new Gson().fromJson(receive(), SocketResponse.class);

        Lobby lobby = this.lobbyList.getPlayersLobby(this.username);
        lobby.setColor(this.getUsername(), TokenColour.valueOf(response.message));
        lobby.getGameContext().getGame().removeColor(TokenColour.valueOf(response.message));
    }

    public void sendColors(ArrayList<String> colors) throws Exception {
        send(new SocketResponse(100, "chooseColor", colors));
    }

    @Override
    public void askSecretObjective(ObjectiveCard card1, ObjectiveCard card2) throws Exception {
        ArrayList<String> param = new ArrayList<>();
        param.add(gson.toJson(card1));
        param.add(gson.toJson(card2));
        param.add(gson.toJson(card1.getCondition()));

        send(new SocketResponse(100, "selectObjectiveCard", param));

        SocketResponse response = gson.fromJson(this.receive(), SocketResponse.class);
        int choice = Integer.parseInt(response.message);

        ObjectiveCard card = null;
        if (choice == 0) card = card1;
        else if (choice == 1) card = card2;

        Lobby lobby = this.lobbyList.getPlayersLobby(this.getUsername());
        lobby.getGameContext().getGame().getPlayer(this.getUsername()).setSecretObjective(card);
    }

    @Override
    public void sendIsFirstPlayer() throws Exception {
        send(new SocketResponse(200, "isFirstPlayer"));
    }

    @Override
    public void askForMove(ArrayList<PlayableCard> hand, GameArea board) throws Exception {
        ArrayList<String> param = new ArrayList<>();
        param.add(gson.toJson(board));
        param.add(gson.toJson(hand));
        send(new SocketResponse(100, "askForMove", param));

        SocketResponse response = gson.fromJson(this.receive(), SocketResponse.class);
        int choice = Integer.parseInt(response.message);
        if (choice == 0) flipCard(
                hand,
                Integer.parseInt(response.strings.getFirst())
        );
        else if (choice == 1) playCard(
                board,
                hand,
                Integer.parseInt(response.strings.getFirst()),
                gson.fromJson(response.strings.get(1), Coordinates.class),
                Integer.parseInt(response.strings.get(2))
        );
    }

    public void flipCard(ArrayList<PlayableCard> hand, int cardToFlip) throws Exception {
        hand.get(cardToFlip).flipSide();
        Lobby lobby = this.lobbyList.getPlayersLobby(this.username);

        for (PlayableCard card : lobby.getGameContext().getGame().getPlayer(this.username).getPlayerHand()) {
            System.out.println(card.getSide());
        }

        this.askForMove(hand, lobby.getGameContext().getGame().getActivePlayer().getPlayerBoard());
    }

    public void playCard(GameArea board, ArrayList<PlayableCard> hand, int cardToPlay, Coordinates cardToOverlap, int cornerToOverlap) throws Exception {
        board.addCard(board.getCard(cardToOverlap), hand.get(cardToPlay), cornerToOverlap);
        hand.remove(cardToPlay);
    }

    @Override
    public void askPickChoice(Deck<GoldCard> goldDeck, Deck<ResourceCard> resourceDeck, ArrayList<PlayableCard> faceUpCards) throws Exception {
        ArrayList<String> param = new ArrayList<>();
        param.add(String.valueOf(goldDeck.isEmpty()));
        param.add(String.valueOf(resourceDeck.isEmpty()));
        param.add(gson.toJson(faceUpCards));
        send(new SocketResponse(100, "askPickChoice", param));

        SocketResponse response = gson.fromJson(this.receive(), SocketResponse.class);
        Lobby lobby = this.lobbyList.getPlayersLobby(this.username);
        if (Integer.parseInt(response.message) == 0) {
            lobby.getGameContext().getGame().getActivePlayer().addCardToHand(lobby.getGameContext().getGame().popGoldDeck());
        } else if (Integer.parseInt(response.message) == 1) {
            lobby.getGameContext().getGame().getActivePlayer().addCardToHand(lobby.getGameContext().getGame().popResourceDeck());
        } else {
            lobby.getGameContext().getGame().getActivePlayer().addCardToHand(lobby.getGameContext().getGame().drawFaceUpCard(Integer.parseInt(response.strings.getFirst())));
        }
    }

    @Override
    public void sendScore(int score) throws Exception {
        send(new SocketResponse(200, String.valueOf(score)));
    }

    @Override
    public void sendWinner(String winner) throws Exception {
        send(new SocketResponse(200, "winner"));
    }
}
