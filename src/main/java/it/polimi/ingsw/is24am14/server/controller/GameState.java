package it.polimi.ingsw.is24am14.server.controller;

import java.io.Serializable;

public interface GameState extends Serializable {
    void execute();
}
