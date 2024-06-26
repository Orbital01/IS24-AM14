package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.*;
import it.polimi.ingsw.is24am14.server.model.card.CardCondition;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;

import java.lang.reflect.Type;
import java.util.Map;

public class CardConditionSerializer implements JsonSerializer<CardCondition>, JsonDeserializer<CardCondition> {
    @Override
    public JsonElement serialize(CardCondition cardCondition, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("CLASSNAME", cardCondition.getClass().getSimpleName());
        jsonObject.add("DATA", jsonSerializationContext.serialize(cardCondition.getListCard()));

        return jsonObject;
    }

    @Override
    public CardCondition deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        CardCondition cardCondition = new CardCondition();

        for (Map.Entry<String, JsonElement> entry : jsonObject.get("DATA").getAsJsonObject().entrySet()) {
            JsonArray array = (JsonArray) JsonParser.parseString(entry.getKey());;
            Coordinates coordinates = new Coordinates(array.get(0).getAsInt(), array.get(1).getAsInt());
            cardCondition.addClause(coordinates, CornerEnum.ResourceEnum.valueOf(entry.getValue().getAsString()));
        }

        return cardCondition;
    }
}
