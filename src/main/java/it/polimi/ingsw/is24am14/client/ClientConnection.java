package it.polimi.ingsw.is24am14.client;

public interface ClientConnection {
    void execute() throws Exception;
    void send(String message) throws Exception; //    message send by the client to the server
    void makeMove() throws Exception;
    int sendInt() throws Exception;
}
