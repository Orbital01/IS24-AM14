package it.polimi.ingsw.is24am14.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
