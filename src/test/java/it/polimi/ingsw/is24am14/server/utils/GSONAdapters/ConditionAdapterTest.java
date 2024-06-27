package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConditionAdapterTest {
    Gson gson = InitGSON.init();
    @Test
    void resourceCondition() {
        Condition resourceCondition = new ResourceCondition();
        String json = gson.toJson(resourceCondition);
        Condition restored = gson.fromJson(json, Condition.class);
        assertEquals(resourceCondition.getClass(), restored.getClass());
        assertInstanceOf(ResourceCondition.class, restored);
    }

    @Test
    void objectCondition() {
        Condition objectCondition = new ObjectCondition();
        String json = gson.toJson(objectCondition);
        Condition restored = gson.fromJson(json, Condition.class);
        assertEquals(objectCondition.getClass(), restored.getClass());
        assertInstanceOf(ObjectCondition.class, restored);
    }

    @Test
    void cardCondition() {
        Condition cardCondition = new CardCondition();
        String json = gson.toJson(cardCondition);
        Condition restored = gson.fromJson(json, Condition.class);
        assertEquals(cardCondition.getClass(), restored.getClass());
        assertInstanceOf(CardCondition.class, restored);
    }

    @Test
    void cornerCondition() {
        Condition cornerCondition = new CornerCondition();
        String json = gson.toJson(cornerCondition);
        Condition restored = gson.fromJson(json, Condition.class);
        assertEquals(cornerCondition.getClass(), restored.getClass());
        assertInstanceOf(CornerCondition.class, restored);
    }
}