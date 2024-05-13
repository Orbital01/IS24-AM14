package it.polimi.ingsw.is24am14.client;

import it.polimi.ingsw.is24am14.server.controller.LobbyList;
import it.polimi.ingsw.is24am14.server.model.card.ObjectiveCard;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPClient implements ClientConnection {
    private Socket socket;

    @Override
    public void send(String msg) throws Exception {
        PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);
        out.println(msg);
    }

    @Override
    public void makeMove() throws Exception {

    }

    @Override
    public int sendInt() throws Exception {
        return 0;
    }

    @Override
    public void chooseColor(List<TokenColour> colors, Player player) throws Exception {

    }

    @Override
    public void pickObjective(ArrayList<ObjectiveCard> secrets, Player player) throws Exception {

    }

    @Override
    public void drawCard() throws Exception {

    }

    @Override
    public void joinLobby(LobbyList lobby) throws Exception {

    }

    public void receive() throws Exception {
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

        String s = "";
        while ((s = in.readLine()) != null) {
            System.out.println("Il client ha ricevuto: " + s);
        }
    }

    public void connect(String address,int portNumber) throws Exception {
        socket = new Socket(address, portNumber);
    }

    public void execute() throws Exception {
        this.connect("127.0.0.1", 12346);
        this.send("Messaggino");
        System.out.println("aspetto...");
        this.receive();
    }

}
