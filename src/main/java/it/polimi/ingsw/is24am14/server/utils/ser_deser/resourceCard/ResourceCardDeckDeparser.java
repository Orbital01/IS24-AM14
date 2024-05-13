package it.polimi.ingsw.is24am14.server.utils.ser_deser.resourceCard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.is24am14.server.model.card.Deck;
import it.polimi.ingsw.is24am14.server.model.card.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ResourceCardDeckDeparser implements DeckDeparser {

    @Override
    public Deck<ResourceCard> deparse() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ResourceCard.class, new ResourceCardTypeAdapter())
                .create();

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("JSONs/resourceCards.json")));
            Type resourceCardListType = new TypeToken<ArrayList<ResourceCard>>() {}.getType();
            ArrayList<ResourceCard> resourceCards = gson.fromJson(jsonString, resourceCardListType);
            Deck<ResourceCard> deck = new Deck<ResourceCard>(resourceCards);
            return deck;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
