package it.polimi.ingsw.is24am14.server.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class ClientHandlerList implements Iterable<ClientHandler>, Serializable {
    private final ArrayList<ClientHandler> clientHandlers;
    public ClientHandlerList() {
        clientHandlers = new ArrayList<>();
    }

    public void add(ClientHandler clientHandler) {
        clientHandlers.add(clientHandler);
    }

    public int size() {
        return clientHandlers.size();
    }


    /**
     * @param name the username to search in the list
     * @return the clientHandler if present, null otherwise
     */
    public ClientHandler getClientHandler(String name) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (clientHandler.getUsername().equals(name)) return clientHandler;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public Iterator<ClientHandler> iterator() {
        return clientHandlers.iterator();
    }
}