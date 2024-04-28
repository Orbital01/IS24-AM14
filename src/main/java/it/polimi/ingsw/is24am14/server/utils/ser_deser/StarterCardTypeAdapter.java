package it.polimi.ingsw.is24am14.server.utils.ser_deser;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.is24am14.server.model.card.*;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;

public class StarterCardTypeAdapter extends TypeAdapter<StarterCard> {

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Corner.class, new CornerTypeAdapter())
            .create();

    @Override
    public void write(JsonWriter out, StarterCard card) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("frontCorners", gson.toJsonTree(card.getFrontCorners()));
        jsonObject.add("backCorners", gson.toJsonTree(card.getBackCorners()));
        jsonObject.addProperty("frontImage", card.getFrontImage());
        jsonObject.addProperty("backImage", card.getBackImage());
        jsonObject.add("resources", gson.toJsonTree(card.getResources()));
        gson.toJson(jsonObject, out);
    }

    @Override
    public StarterCard read(JsonReader in) throws IOException {
        JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
        ArrayList<Corner> frontCorners = gson.fromJson(jsonObject.get("frontCorners"), new TypeToken<ArrayList<Corner>>(){}.getType());
        ArrayList<Corner> backCorners = gson.fromJson(jsonObject.get("backCorners"), new TypeToken<ArrayList<Corner>>(){}.getType());
        String frontImage = jsonObject.get("frontImage").getAsString();
        String backImage = jsonObject.get("backImage").getAsString();
        ArrayList<CornerEnum.ResourceEnum> resources = gson.fromJson(jsonObject.get("resources"), new TypeToken<ArrayList<CornerEnum.ResourceEnum>>(){}.getType());
        return new StarterCard(frontCorners, backCorners, resources, frontImage, backImage);
    }
}