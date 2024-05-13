package it.polimi.ingsw.is24am14.server.utils.ser_deser.goldCard;
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

public class GoldCardDeckDeparser implements DeckDeparser{

        @Override
        public Deck<GoldCard> deparse() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(GoldCard.class, new GoldCardTypeAdapter())
                    .create();

            try {
                String jsonString = new String(Files.readAllBytes(Paths.get("JSONs/goldCards.json")));
                Type goldCardListType = new TypeToken<ArrayList<GoldCard>>(){}.getType();
                ArrayList<GoldCard> goldCards = gson.fromJson(jsonString, goldCardListType);
                Deck<GoldCard> deck = new Deck<GoldCard>(goldCards);
                return deck;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
}
