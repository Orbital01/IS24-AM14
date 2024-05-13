package it.polimi.ingsw.is24am14.server.utils.ser_deser.objectiveCard;

import com.google.gson.GsonBuilder;
import it.polimi.ingsw.is24am14.server.model.card.*;

import java.util.ArrayList;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SerializeObjectiveCard {

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(ObjectiveCard.class, new ObjectiveCardTypeAdapter())
            .setPrettyPrinting()
            .create();

    public void serializeObjectiveCards(ArrayList<ObjectiveCard> cards, String filePath) {
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

        //le objective card hanno 4 corner frontali e nessun corner posteriore

        int points = 5;
        CardCondition cardCondition = new CardCondition();
            cardCondition.addClause(new Coordinates(0, 0), CornerEnum.ResourceEnum.ANIMAL);
            cardCondition.addClause(new Coordinates(0, 1), CornerEnum.ResourceEnum.FUNGI);
            cardCondition.addClause(new Coordinates(1, 0), CornerEnum.ResourceEnum.ANIMAL);
            cardCondition.addClause(new Coordinates(1, 1), CornerEnum.ResourceEnum.FUNGI);

        ObjectiveCard card1 = new ObjectiveCard(cardCondition, frontCorners, "frontImage", "backImage", points);

        ArrayList<CornerEnum.ObjectEnum> objects = new ArrayList<>();
        objects.add(CornerEnum.ObjectEnum.MANUSCRIPT);
        objects.add(CornerEnum.ObjectEnum.INKWELL);

        ObjectCondition objectCondition = new ObjectCondition();

        ObjectiveCard card2 = new ObjectiveCard(objectCondition, frontCorners, "frontImage", "backImage", points);

        ArrayList<CornerEnum.ResourceEnum> resources = new ArrayList<>();
        resources.add(CornerEnum.ResourceEnum.ANIMAL);
        resources.add(CornerEnum.ResourceEnum.FUNGI);

        ResourceCondition resourceCondition = new ResourceCondition();

        ObjectiveCard card3 = new ObjectiveCard(resourceCondition, frontCorners, "frontImage", "backImage", points);

        ArrayList<ObjectiveCard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        //provo a creare il Json da questo array
        SerializeObjectiveCard serializeObjectiveCard = new SerializeObjectiveCard();
        serializeObjectiveCard.serializeObjectiveCards(cards, "objectiveCards.json");
    }

}
