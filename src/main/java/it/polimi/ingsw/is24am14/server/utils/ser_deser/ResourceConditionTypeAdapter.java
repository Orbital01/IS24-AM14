package it.polimi.ingsw.is24am14.server.utils.ser_deser;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.is24am14.server.model.card.*;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;

public class ResourceConditionTypeAdapter extends TypeAdapter<ResourceCondition> {
    private final Gson gson = new Gson();

    @Override
    public void write(JsonWriter out, ResourceCondition condition) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("listResource", gson.toJsonTree(condition.getListResource()));
        gson.toJson(jsonObject, out);
    }

    @Override
    public ResourceCondition read(JsonReader in) throws IOException {
        JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
        ArrayList<CornerEnum.ResourceEnum> listResource = gson.fromJson(jsonObject.get("listResource"), new TypeToken<ArrayList<CornerEnum.ResourceEnum>>(){}.getType());
        ResourceCondition condition = new ResourceCondition();
        for (CornerEnum.ResourceEnum resource : listResource) {
            condition.addClause(resource);
        }
        return condition;
    }
}