package it.polimi.ingsw.is24am14.server.utils.ser_deser.resourceCard;

import com.google.gson.GsonBuilder;
import it.polimi.ingsw.is24am14.server.model.card.*;

import java.util.ArrayList;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ResourceCardSerializer {

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(ResourceCard.class, new ResourceCardTypeAdapter())
            .setPrettyPrinting()
            .create();

    public void serializeStarterCards(ArrayList<ResourceCard> cards, String filePath) {
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
        ArrayList<Corner> backCorners = new ArrayList<>();

        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));

        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.HIDDEN));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.HIDDEN));
        backCorners.add(new Corner(CornerEnum.ObjectEnum.EMPTY));

        ArrayList<ResourceCard> cards = new ArrayList<>();
        cards.add(new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, frontCorners, backCorners, "frontImage", "backImage"));
        cards.add(new ResourceCard(2, CornerEnum.ResourceEnum.PLANT, frontCorners, backCorners, "frontImage", "backImage"));

        //provo a creare il Json do questo array
        ResourceCardSerializer serializeStarterCard = new ResourceCardSerializer();
        serializeStarterCard.serializeStarterCards(cards, "resourceCards.json");
    }

}
