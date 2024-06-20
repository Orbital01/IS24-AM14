package it.polimi.ingsw.is24am14.server.network;

import java.util.ArrayList;

public class SocketResponse {
    public int code;
    public String message;
    public ArrayList<String> strings;


    public SocketResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.strings = new ArrayList<>();
    }

    public SocketResponse(int code, String message, String firstString) {
        this.code = code;
        this.message = message;
        this.strings = new ArrayList<>();
        this.strings.add(firstString);
    }

    public SocketResponse() {
        this.strings = new ArrayList<>();
    }
}