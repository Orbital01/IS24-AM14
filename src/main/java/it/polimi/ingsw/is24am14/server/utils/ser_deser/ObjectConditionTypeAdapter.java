package it.polimi.ingsw.is24am14.server.utils.ser_deser;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.is24am14.server.model.card.*;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectConditionTypeAdapter extends TypeAdapter<ObjectCondition> {
    private final Gson gson = new Gson();

    @Override
    public void write(JsonWriter out, ObjectCondition condition) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("listObject", gson.toJsonTree(condition.getListObject()));
        gson.toJson(jsonObject, out);
    }

    @Override
    public ObjectCondition read(JsonReader in) throws IOException {
        JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
        ArrayList<CornerEnum.ObjectEnum> listObject = gson.fromJson(jsonObject.get("listObject"), new TypeToken<ArrayList<CornerEnum.ObjectEnum>>(){}.getType());
        ObjectCondition condition = new ObjectCondition();
        for (CornerEnum.ObjectEnum object : listObject) {
            condition.addClause(object);
        }
        return condition;
    }
}