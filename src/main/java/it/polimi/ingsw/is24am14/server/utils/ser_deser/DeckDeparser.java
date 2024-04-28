package it.polimi.ingsw.is24am14.server.utils.ser_deser;

import it.polimi.ingsw.is24am14.server.model.card.Deck;

import java.io.IOException;

public interface DeckDeparser {
    Deck deparse();
}