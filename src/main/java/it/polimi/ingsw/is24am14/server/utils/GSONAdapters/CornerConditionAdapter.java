package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.*;
import it.polimi.ingsw.is24am14.server.model.card.CornerCondition;

import java.lang.reflect.Type;

public class CornerConditionAdapter implements JsonSerializer<CornerCondition>, JsonDeserializer<CornerCondition> {
    @Override
    public CornerCondition deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new CornerCondition();
    }

    @Override
    public JsonElement serialize(CornerCondition cornerCondition, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("CLASSNAME", cornerCondition.getClass().getSimpleName());

        return jsonObject;
    }
}
