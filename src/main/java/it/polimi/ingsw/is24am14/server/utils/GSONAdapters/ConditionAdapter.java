package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.*;
import it.polimi.ingsw.is24am14.server.model.card.*;

import java.lang.reflect.Type;

public class ConditionAdapter implements JsonDeserializer<Condition> {
    @Override
    public Condition deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.getAsJsonObject().has("listResource")) return jsonDeserializationContext.deserialize(jsonElement.getAsJsonObject(), ResourceCondition.class);
        if (jsonElement.getAsJsonObject().has("CLASSNAME")) return jsonDeserializationContext.deserialize(jsonElement.getAsJsonObject(), CardCondition.class);
        if (jsonElement.getAsJsonObject().has("listObject")) return jsonDeserializationContext.deserialize(jsonElement.getAsJsonObject(), ObjectCondition.class);
        if (jsonElement.getAsJsonObject().has("coordinates")) return jsonDeserializationContext.deserialize(jsonElement.getAsJsonObject(), CornerCondition.class);
        return new NoCondition();
    }
}
