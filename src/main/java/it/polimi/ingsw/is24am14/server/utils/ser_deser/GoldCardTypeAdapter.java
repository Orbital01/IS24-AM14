package it.polimi.ingsw.is24am14.server.utils.ser_deser;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.is24am14.server.model.card.*;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;

public class GoldCardTypeAdapter extends TypeAdapter<GoldCard> {

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Corner.class, new CornerTypeAdapter())
            .registerTypeAdapter(CardCondition.class, new CardConditionTypeAdapter())
            .registerTypeAdapter(ObjectCondition.class, new ObjectConditionTypeAdapter())
            .registerTypeAdapter(ResourceCondition.class, new ResourceConditionTypeAdapter())
            .create();

    @Override
    public void write(JsonWriter out, GoldCard card) throws IOException {
        JsonObject jsonObject = new JsonObject();

        jsonObject.add("frontCorners", gson.toJsonTree(card.getFrontCorners()));
        jsonObject.addProperty("frontImage", card.getFrontImage());
        jsonObject.addProperty("backImage", card.getBackImage());

        jsonObject.addProperty("points", card.getPoints());
        jsonObject.add("pointCondition", gson.toJsonTree(card.getPointCondition()));
        jsonObject.add("resource", gson.toJsonTree(card.getResource()));
        jsonObject.add("placementCondition", gson.toJsonTree(card.getPlacementCondition()));
        gson.toJson(jsonObject, out);
    }

    @Override
    public GoldCard read(JsonReader in) throws IOException {
        JsonObject jsonObject = gson.fromJson(in, JsonObject.class);

        ArrayList<Corner> frontCorners = gson.fromJson(jsonObject.get("frontCorners"), new TypeToken<ArrayList<Corner>>(){}.getType());
        String frontImage = jsonObject.get("frontImage").getAsString();
        String backImage = jsonObject.get("backImage").getAsString();

        int points = jsonObject.get("points").getAsInt();
        Condition pointCondition = gson.fromJson(jsonObject.get("pointCondition"), Condition.class);
        CornerEnum.ResourceEnum resource = gson.fromJson(jsonObject.get("resource"), CornerEnum.ResourceEnum.class);
        Condition placementCondition = gson.fromJson(jsonObject.get("placementCondition"), Condition.class);
        return new GoldCard(points, pointCondition, resource, placementCondition, frontCorners, frontImage, backImage);
    }
}