package it.polimi.ingsw.is24am14.server.utils.ser_deser.starterCards;

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

public class StarterCardDeckDeparser implements DeckDeparser {

    @Override
    public Deck<StarterCard> deparse() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(StarterCard.class, new StarterCardTypeAdapter())
                .create();

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("JSONs/starterCards.json")));
            Type starterCardListType = new TypeToken<ArrayList<StarterCard>>(){}.getType();
            ArrayList<StarterCard> starterCards = gson.fromJson(jsonString, starterCardListType);
            Deck<StarterCard> deck = new Deck<StarterCard>(starterCards);
            return deck;
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
            return null;
        }



    }

}
