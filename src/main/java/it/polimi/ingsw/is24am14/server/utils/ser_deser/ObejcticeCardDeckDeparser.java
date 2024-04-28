package it.polimi.ingsw.is24am14.server.utils.ser_deser;

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

public class ObejcticeCardDeckDeparser implements DeckDeparser{

    @Override
    public Deck<ObjectiveCard> deparse() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ObjectiveCard.class, new ObjectiveCardTypeAdapter())
                .create();

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("JSONs/objectiveCards.json")));
            Type objectiveCardListType = new TypeToken<ArrayList<ObjectiveCard>>(){}.getType();
            ArrayList<ObjectiveCard> objectiveCards = gson.fromJson(jsonString, objectiveCardListType);
            Deck<ObjectiveCard> deck = new Deck<ObjectiveCard>(objectiveCards);
            return deck;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
