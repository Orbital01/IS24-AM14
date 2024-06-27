package it.polimi.ingsw.is24am14.server.network;

import java.util.ArrayList;

/**
 * Represents a response object used in socket communication.
 * Contains a code, message, and optional list of strings.
 */
public class SocketResponse {
    /** The response code indicating the status or type of response. */
    public int code;

    /** The message associated with the response. */
    public String message;

    /** A list of strings accompanying the response. */
    public ArrayList<String> strings;


    /**
     * Constructs a SocketResponse object with the given code and message.
     * Initializes the strings list.
     * @param code the response code
     * @param message the response message
     */
    public SocketResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.strings = new ArrayList<>();
    }

    /**
     * Constructs a SocketResponse object with the given code, message, and initial string.
     * Initializes the strings list and adds the initial string.
     * @param code the response code
     * @param message the response message
     * @param firstString the initial string to add to the strings list
     */
    public SocketResponse(int code, String message, String firstString) {
        this.code = code;
        this.message = message;
        this.strings = new ArrayList<>();
        this.strings.add(firstString);
    }

    /**
     * Constructs an empty SocketResponse object with an initialized strings list.
     */
    public SocketResponse() {
        this.strings = new ArrayList<>();
    }
}