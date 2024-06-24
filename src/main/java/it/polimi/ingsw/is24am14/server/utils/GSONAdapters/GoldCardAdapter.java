package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.*;
import it.polimi.ingsw.is24am14.server.model.card.GoldCard;

import java.lang.reflect.Type;

public class GoldCardAdapter implements JsonSerializer<GoldCard>{
    @Override
    public JsonElement serialize(GoldCard goldCard, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new Gson().toJsonTree(goldCard).getAsJsonObject();
        jsonObject.addProperty("cardType", goldCard.getClass().getSimpleName());
        return jsonObject;
    }
}
