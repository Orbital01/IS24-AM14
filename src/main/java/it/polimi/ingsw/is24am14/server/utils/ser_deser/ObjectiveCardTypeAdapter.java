package it.polimi.ingsw.is24am14.server.utils.ser_deser;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.is24am14.server.model.card.*;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectiveCardTypeAdapter extends TypeAdapter<ObjectiveCard> {

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Corner.class, new CornerTypeAdapter())
            .registerTypeAdapter(CardCondition.class, new CardConditionTypeAdapter())
            .registerTypeAdapter(ObjectCondition.class, new ObjectConditionTypeAdapter())
            .registerTypeAdapter(ResourceCondition.class, new ResourceConditionTypeAdapter())
            .create();

    @Override
    public void write(JsonWriter out, ObjectiveCard card) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("condition", gson.toJsonTree(card.getCondition()));
        jsonObject.add("frontCorners", gson.toJsonTree(card.getFrontCorners()));
        jsonObject.addProperty("frontImage", card.getFrontImage());
        jsonObject.addProperty("backImage", card.getBackImage());
        jsonObject.addProperty("points", card.getPoints());
        gson.toJson(jsonObject, out);
    }

    @Override
    public ObjectiveCard read(JsonReader in) throws IOException {
        JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
        Condition condition = gson.fromJson(jsonObject.get("condition"), Condition.class);
        ArrayList<Corner> frontCorners = gson.fromJson(jsonObject.get("frontCorners"), new TypeToken<ArrayList<Corner>>(){}.getType());
        String frontImage = jsonObject.get("frontImage").getAsString();
        String backImage = jsonObject.get("backImage").getAsString();
        int points = jsonObject.get("points").getAsInt();
        return new ObjectiveCard(condition, frontCorners, frontImage, backImage, points);
    }
}