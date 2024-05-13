package it.polimi.ingsw.is24am14.server.utils.ser_deser.starterCards;

import com.google.gson.GsonBuilder;
import it.polimi.ingsw.is24am14.server.model.card.*;

import java.util.ArrayList;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SerializeStarterCard {

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(StarterCard.class, new StarterCardTypeAdapter())
            .setPrettyPrinting()
            .create();

    public void serializeStarterCards(ArrayList<StarterCard> cards, String filePath) {
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
        backCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        backCorners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));

        ArrayList<CornerEnum.ResourceEnum> resources = new ArrayList<>();
        resources.add(CornerEnum.ResourceEnum.ANIMAL);
        resources.add(CornerEnum.ResourceEnum.FUNGI);
        resources.add(CornerEnum.ResourceEnum.INSECT);

        ArrayList<StarterCard> cards = new ArrayList<>();
        cards.add(new StarterCard(frontCorners, backCorners, resources,"frontImage", "backImage"));
        cards.add(new StarterCard(frontCorners, backCorners, resources,"frontImage", "backImage"));

        //provo a creare il Json do questo array
        SerializeStarterCard serializeStarterCard = new SerializeStarterCard();
        serializeStarterCard.serializeStarterCards(cards, "starterCards.json");
    }
}
