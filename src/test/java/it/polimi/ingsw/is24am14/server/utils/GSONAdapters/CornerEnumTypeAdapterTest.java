package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CornerEnumTypeAdapterTest {
    Gson gson = InitGSON.init();
    @Test
    void empty() {
        CornerEnum empty = CornerEnum.EMPTY;
        String json = gson.toJson(empty);
        CornerEnum actual = gson.fromJson(json, CornerEnum.class);
        assertEquals(empty, actual);
    }

    @Test
    void hidden() {
        CornerEnum hidden = CornerEnum.HIDDEN;
        String json = gson.toJson(hidden);
        CornerEnum actual = gson.fromJson(json, CornerEnum.class);
        assertEquals(hidden, actual);
    }

    @Test
    void inkwell() {
        CornerEnum inkwell = CornerEnum.ObjectEnum.INKWELL;
        String json = gson.toJson(inkwell);
        CornerEnum actual = gson.fromJson(json, CornerEnum.class);
        assertEquals(inkwell, actual);
    }

    @Test
    void manuscript() {
        CornerEnum manuscript = CornerEnum.ObjectEnum.MANUSCRIPT;
        String json = gson.toJson(manuscript);
        CornerEnum actual = gson.fromJson(json, CornerEnum.class);
        assertEquals(manuscript, actual);
    }

    @Test
    void quill() {
        CornerEnum quill = CornerEnum.ObjectEnum.QUILL;
        String json = gson.toJson(quill);
        CornerEnum actual = gson.fromJson(json, CornerEnum.class);
        assertEquals(quill, actual);
    }

    @Test
    void plant() {
        CornerEnum plant = CornerEnum.ResourceEnum.PLANT;
        String json = gson.toJson(plant);
        CornerEnum actual = gson.fromJson(json, CornerEnum.class);
        assertEquals(plant, actual);
    }

    @Test
    void animal() {
        CornerEnum animal = CornerEnum.ResourceEnum.ANIMAL;
        String json = gson.toJson(animal);
        CornerEnum actual = gson.fromJson(json, CornerEnum.class);
        assertEquals(animal, actual);
    }

    @Test
    void insect() {
        CornerEnum insect = CornerEnum.ResourceEnum.INSECT;
        String json = gson.toJson(insect);
        CornerEnum actual = gson.fromJson(json, CornerEnum.class);
        assertEquals(insect, actual);
    }

    @Test
    void fungi() {
        CornerEnum fungi = CornerEnum.ResourceEnum.FUNGI;
        String json = gson.toJson(fungi);
        CornerEnum actual = gson.fromJson(json, CornerEnum.class);
        assertEquals(fungi, actual);
    }
}