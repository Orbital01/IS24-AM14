package it.polimi.ingsw.is24am14.server.utils.ser_deser;

import com.google.gson.GsonBuilder;
import it.polimi.ingsw.is24am14.server.model.card.*;

import java.util.ArrayList;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SerializeGoldCard {

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(GoldCard.class, new GoldCardTypeAdapter())
            .setPrettyPrinting()
            .create();

    public void serializeGoldCards(ArrayList<GoldCard> cards, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(cards, writer);
        } catch (IOException e) {
            System.out.println("Error while serializing the cards");
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        //creo una prima carta doppia per capire come scrive JSON,
        //le altre le scrivo a mano nel JSON
        ArrayList<Corner> frontCorners = new ArrayList<>();

        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));

        int points = 6;

        //da mettere qua le ipotesi con le diverse condition, parlare con Bog

        GoldCard goldCard = new GoldCard(points, null, CornerEnum.ResourceEnum.ANIMAL,
                null, frontCorners, "frontImage", "backImage");

        ArrayList<GoldCard> cards = new ArrayList<>();
        cards.add(goldCard);


        //provo a creare il Json do questo array
        SerializeGoldCard serializeGoldCard = new SerializeGoldCard();
        serializeGoldCard.serializeGoldCards(cards, "goldCards.json");
    }
}
