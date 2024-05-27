package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.*;
import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;

import java.lang.reflect.Type;

public class CornerEnumTypeAdapter implements JsonSerializer<CornerEnum>, JsonDeserializer<CornerEnum> {

    @Override
    public JsonElement serialize(CornerEnum cornerEnum, Type type, JsonSerializationContext jsonSerializationContext) {
        if (cornerEnum == CornerEnum.EMPTY) return new JsonPrimitive("EMPTY");
        else if (cornerEnum == CornerEnum.HIDDEN) return new JsonPrimitive("HIDDEN");
        else if (cornerEnum == CornerEnum.ObjectEnum.INKWELL) return new JsonPrimitive("INKWELL");
        else if (cornerEnum == CornerEnum.ObjectEnum.MANUSCRIPT) return new JsonPrimitive("MANUSCRIPT");
        else if (cornerEnum == CornerEnum.ObjectEnum.QUILL) return new JsonPrimitive("QUILL");
        else if (cornerEnum == CornerEnum.ResourceEnum.PLANT) return new JsonPrimitive("PLANT");
        else if (cornerEnum == CornerEnum.ResourceEnum.ANIMAL) return new JsonPrimitive("ANIMAL");
        else if (cornerEnum == CornerEnum.ResourceEnum.INSECT) return new JsonPrimitive("INSECT");
        else if (cornerEnum == CornerEnum.ResourceEnum.FUNGI) return new JsonPrimitive("FUNGI");
        return null;
    }
    @Override
    public CornerEnum deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.getAsString().equals("EMPTY")) return CornerEnum.EMPTY;
        else if (jsonElement.getAsString().equals("HIDDEN")) return CornerEnum.HIDDEN;
        else if (jsonElement.getAsString().equals("INKWELL")) return CornerEnum.ObjectEnum.INKWELL;
        else if (jsonElement.getAsString().equals("MANUSCRIPT")) return CornerEnum.ObjectEnum.MANUSCRIPT;
        else if (jsonElement.getAsString().equals("QUILL")) return CornerEnum.ObjectEnum.QUILL;
        else if (jsonElement.getAsString().equals("PLANT")) return CornerEnum.ResourceEnum.PLANT;
        else if (jsonElement.getAsString().equals("ANIMAL")) return CornerEnum.ResourceEnum.ANIMAL;
        else if (jsonElement.getAsString().equals("INSECT")) return CornerEnum.ResourceEnum.INSECT;
        else if (jsonElement.getAsString().equals("FUNGI")) return CornerEnum.ResourceEnum.FUNGI;
        return null;
    }
}
