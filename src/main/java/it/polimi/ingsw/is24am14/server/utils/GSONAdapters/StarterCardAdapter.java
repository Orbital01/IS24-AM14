package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.*;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;

import java.lang.reflect.Type;

public class StarterCardAdapter implements JsonSerializer<StarterCard> {

    @Override
    public JsonElement serialize(StarterCard starterCard, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new Gson().toJsonTree(starterCard).getAsJsonObject();
        jsonObject.addProperty("cardType", starterCard.getClass().getSimpleName());
        return jsonObject;
    }
}
