package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.*;
import it.polimi.ingsw.is24am14.server.model.card.ResourceCard;

import java.lang.reflect.Type;

public class ResourceCardAdapter implements JsonSerializer<ResourceCard> {
    @Override
    public JsonElement serialize(ResourceCard resourceCard, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new Gson().toJsonTree(resourceCard).getAsJsonObject();
        jsonObject.addProperty("cardType", resourceCard.getClass().getSimpleName());
        return jsonObject;
    }
}
