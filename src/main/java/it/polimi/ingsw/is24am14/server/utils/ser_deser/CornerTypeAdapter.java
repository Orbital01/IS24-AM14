package it.polimi.ingsw.is24am14.server.utils.ser_deser;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw.is24am14.server.model.card.Corner;
import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;
import java.io.IOException;

public class CornerTypeAdapter extends TypeAdapter<Corner> {

    @Override
    public void write(JsonWriter out, Corner corner) throws IOException {
        out.beginObject();
        out.name("type").value(corner.getType().toString());
        out.name("isOverlapped").value(corner.isOverlapped());
        out.endObject();
    }

    @Override
    public Corner read(JsonReader in) throws IOException {
        CornerEnum type = null;
        boolean isOverlapped = false;

        //non mi piace molto questa parte, ma non so come fare di meglio, anche perché non conosco a pieno JSON
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "type":
                    type = CornerEnum.valueOf(in.nextString());
                    //if an exception is generated here, the deserialization will stop
                    break;
                case "isOverlapped":
                    isOverlapped = in.nextBoolean();
                    break;
            }
        }
        in.endObject();

        Corner corner = new Corner(type);
        if (isOverlapped) {
            corner.overlap();
        }

        return corner;
    }
}