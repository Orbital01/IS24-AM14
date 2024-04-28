package it.polimi.ingsw.is24am14.server.utils.ser_deser;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.is24am14.server.model.card.*;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CardConditionTypeAdapter extends TypeAdapter<CardCondition> {
    private final Gson gson = new Gson();

    @Override
    public void write(JsonWriter out, CardCondition condition) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("listCard", gson.toJsonTree(condition.getListCard()));
        gson.toJson(jsonObject, out);
    }

    @Override
    public CardCondition read(JsonReader in) throws IOException {
        JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
        HashMap<Coordinates, CornerEnum.ResourceEnum> listCard = gson.fromJson(jsonObject.get("listCard"), new TypeToken<HashMap<Coordinates, CornerEnum.ResourceEnum>>(){}.getType());
        CardCondition condition = new CardCondition();
        for (Map.Entry<Coordinates, CornerEnum.ResourceEnum> entry : listCard.entrySet()) {
            condition.addClause(entry.getKey(), entry.getValue());
        }
        return condition;
    }
}