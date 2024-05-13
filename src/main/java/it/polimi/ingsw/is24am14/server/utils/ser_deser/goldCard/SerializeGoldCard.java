package it.polimi.ingsw.is24am14.server.utils.ser_deser.goldCard;

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

        //la placement condition è un ResourceCondition
        ResourceCondition placementCondition = new ResourceCondition();
        placementCondition.addClause(CornerEnum.ResourceEnum.ANIMAL);
        placementCondition.addClause(CornerEnum.ResourceEnum.FUNGI);
        placementCondition.addClause(CornerEnum.ResourceEnum.PLANT);

        //caso 1 -> object condition
        ObjectCondition pointCondition = new ObjectCondition();
        pointCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        pointCondition.addClause(CornerEnum.ObjectEnum.INKWELL);

        //caso2 -> card condition
        CardCondition pointCondition2 = new CardCondition();
        pointCondition2.addClause(new Coordinates(1, 2), CornerEnum.ResourceEnum.ANIMAL);
        pointCondition2.addClause(new Coordinates(3, 4), CornerEnum.ResourceEnum.FUNGI);


        //sono due card identiche, con qualche modifica per distinguerle nel JSON
        GoldCard goldCard = new GoldCard(points, pointCondition, CornerEnum.ResourceEnum.ANIMAL,
                placementCondition, frontCorners, "frontImage", "backImage");
        GoldCard goldCard2 = new GoldCard(points, pointCondition2, CornerEnum.ResourceEnum.PLANT,
                placementCondition, frontCorners, "frontImage", "backImage");

        ArrayList<GoldCard> cards = new ArrayList<>();
        cards.add(goldCard);
        cards.add(goldCard2);

        //provo a creare il Json di questo array
        SerializeGoldCard serializeGoldCard = new SerializeGoldCard();
        serializeGoldCard.serializeGoldCards(cards, "goldCards.json");
    }
}
