package it.polimi.ingsw.is24am14.server.model.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CornerEnumTest {

    @Test
    void valueOfShouldReturnCorrectResourceEnum() {
        String resourceString = "Hidden";
        CornerEnum resourceEnum = CornerEnum.valueOf(resourceString);
        assertEquals(CornerEnum.HIDDEN, resourceEnum);
    }

    @Test
    void valueOfShouldThrowExceptionForInvalidResourceEnum() {
        String resourceString = "INVALID";
        assertThrows(IllegalArgumentException.class, () -> CornerEnum.valueOf(resourceString));
    }
}