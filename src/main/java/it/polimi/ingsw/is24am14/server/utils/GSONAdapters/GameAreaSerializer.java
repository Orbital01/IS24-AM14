package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.*;
import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GameAreaSerializer implements JsonSerializer<GameArea>, JsonDeserializer<GameArea> {

    @Override
    public JsonElement serialize(GameArea src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        for (Map.Entry<Coordinates, Card> entry : src.getBoard().entrySet()) {
            JsonObject entryObject = new JsonObject();
            entryObject.add("coordinates", context.serialize(entry.getKey()));
            entryObject.add("card", context.serialize(entry.getValue()));
            jsonArray.add(entryObject);
        }

        jsonObject.add("board", jsonArray);
        return jsonObject;
    }

    @Override
    public GameArea deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("board");

        HashMap<Coordinates, Card> board = new HashMap<>();
        for (JsonElement element : jsonArray) {
            JsonObject entryObject = element.getAsJsonObject();
            Coordinates coordinates = context.deserialize(entryObject.get("coordinates"), Coordinates.class);
            Card card = InitGSON.init().fromJson(entryObject.get("card"), Card.class);
            board.put(coordinates, card);
        }

        GameArea gameArea = new GameArea();
        gameArea.getBoard().putAll(board);
        return gameArea;
    }
}