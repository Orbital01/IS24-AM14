package it.polimi.ingsw.is24am14.server.network;

import java.util.ArrayList;

public class SocketResponse {
    public int code;
    public String message;
    public ArrayList<String> strings;

    public SocketResponse(int code, String message, ArrayList<String> strings) {
        this.code = code;
        this.message = message;
        this.strings = strings;
    }

    public SocketResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public SocketResponse() {}
}
