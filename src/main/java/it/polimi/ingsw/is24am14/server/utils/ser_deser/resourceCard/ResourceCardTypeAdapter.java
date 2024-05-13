package it.polimi.ingsw.is24am14.server.utils.ser_deser.resourceCard;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw.is24am14.server.model.card.*;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.is24am14.server.utils.ser_deser.CornerTypeAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class ResourceCardTypeAdapter extends TypeAdapter<ResourceCard> {

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Corner.class, new CornerTypeAdapter())
            .create();

    @Override
    public void write(JsonWriter out, ResourceCard card) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("points", card.getPoints());
        jsonObject.add("resource", gson.toJsonTree(card.getResource()));
        jsonObject.add("frontCorners", gson.toJsonTree(card.getFrontCorners()));
        jsonObject.add("backCorners", gson.toJsonTree(card.getBackCorners()));
        jsonObject.addProperty("frontImage", card.getFrontImage());
        jsonObject.addProperty("backImage", card.getBackImage());
        gson.toJson(jsonObject, out);
    }

    @Override
    public ResourceCard read(JsonReader in) throws IOException {
        JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
        int points = jsonObject.get("points").getAsInt();
        CornerEnum.ResourceEnum resource = gson.fromJson(jsonObject.get("resource"), CornerEnum.ResourceEnum.class);
        ArrayList<Corner> frontCorners = gson.fromJson(jsonObject.get("frontCorners"), new TypeToken<ArrayList<Corner>>(){}.getType());
        ArrayList<Corner> backCorners = gson.fromJson(jsonObject.get("backCorners"), new TypeToken<ArrayList<Corner>>(){}.getType());
        String frontImage = jsonObject.get("frontImage").getAsString();
        String backImage = jsonObject.get("backImage").getAsString();
        return new ResourceCard(points, resource, frontCorners, backCorners, frontImage, backImage);
    }
}