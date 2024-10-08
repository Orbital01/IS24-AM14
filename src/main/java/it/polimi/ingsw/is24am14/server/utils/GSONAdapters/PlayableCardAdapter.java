package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.*;
import it.polimi.ingsw.is24am14.server.model.card.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayableCardAdapter implements JsonSerializer<PlayableCard>, JsonDeserializer<PlayableCard> {
    @Override
    public JsonElement serialize(PlayableCard playableCard, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = jsonSerializationContext.serialize(playableCard).getAsJsonObject();
        jsonObject.addProperty("cardType", playableCard.getClass().getSimpleName());
        return jsonObject;
    }

    @Override
    public PlayableCard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String cardType;
        try {
            cardType = jsonObject.get("cardType").getAsString();
        } catch (NullPointerException e) {
            System.out.println(jsonElement);
            throw new JsonParseException(e.getMessage());
        }

        if ("GoldCard".equals(cardType)) {
            ArrayList<Corner> frontCorners = new ArrayList<>(Arrays.asList(jsonDeserializationContext.deserialize(jsonObject.get("frontCorners"), Corner[].class)));
            ArrayList<Corner> backCorners = new ArrayList<>(Arrays.asList(jsonDeserializationContext.deserialize(jsonObject.get("backCorners"), Corner[].class)));

            GoldCard goldCard = new GoldCard(
                    jsonDeserializationContext.deserialize(jsonObject.get("points"), Integer.class),
                    jsonDeserializationContext.deserialize(jsonObject.get("pointCondition"), Condition.class),
                    jsonDeserializationContext.deserialize(jsonObject.get("resource"), CornerEnum.class),
                    jsonDeserializationContext.deserialize(jsonObject.get("placementCondition"), Condition.class),
                    frontCorners,
                    jsonObject.get("frontImage").getAsString(),
                    jsonObject.get("backImage").getAsString());

            if (jsonObject.get("enumSide").getAsString().equals("BACK")) goldCard.flipSide();

            for (int i = 0; i < backCorners.size(); i++) {
                if (backCorners.get(i).isOverlapped()) goldCard.getBackCorners().get(i).overlap();
            }

            return goldCard;
        } else if ("ResourceCard".equals(cardType)) {
            ArrayList<Corner> frontCorners = new ArrayList<>(Arrays.asList(jsonDeserializationContext.deserialize(jsonObject.get("frontCorners"), Corner[].class)));
            ArrayList<Corner> backCorners = new ArrayList<>(Arrays.asList(jsonDeserializationContext.deserialize(jsonObject.get("backCorners"), Corner[].class)));
            ResourceCard resourceCard = new ResourceCard(
                    jsonDeserializationContext.deserialize(jsonObject.get("points"), Integer.class),
                    jsonDeserializationContext.deserialize(jsonObject.get("resource"), CornerEnum.class),
                    frontCorners,
                    backCorners,
                    jsonObject.get("frontImage").getAsString(),
                    jsonObject.get("backImage").getAsString());

            if (jsonObject.get("enumSide").getAsString().equals("BACK")) resourceCard.flipSide();

            for (int i = 0; i < backCorners.size(); i++) {
                if (backCorners.get(i).isOverlapped()) resourceCard.getBackCorners().get(i).overlap();
            }

            return resourceCard;
        }
        throw new RuntimeException("Error when deserializing playable card");
    }
}
