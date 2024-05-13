package it.polimi.ingsw.is24am14.server.utils.ser_deser;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.utils.ser_deser.starterCards.StarterCardDeckDeparser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StarterCardDeckDeparserTest {
    //testo che il metodo deparse di questa classe ritorni le carte effettivaemnte presenti nel documento JSON
    @Test
    public void testDeparse() {

        //test da sistemare per via del fatto che l'istanza è diversa e devo confrontare i singoli elementi

        //creo le due carte contenute dal file per poi confrontarle con quelle ottenute dal metodo deparse
        ArrayList<Corner> frontCorners = new ArrayList<>();
        ArrayList<Corner> backCorners = new ArrayList<>();

        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.EMPTY));

        backCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        backCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        ArrayList<CornerEnum.ResourceEnum> resources = new ArrayList<>();
        resources.add(CornerEnum.ResourceEnum.ANIMAL);
        resources.add(CornerEnum.ResourceEnum.FUNGI);
        resources.add(CornerEnum.ResourceEnum.INSECT);

        ArrayList<StarterCard> cards = new ArrayList<>();
        cards.add(new StarterCard(frontCorners, backCorners, resources,
                "src/main/resources/images/cards/starter_cards/starter_fronts/page_81.png",
                "src/main/resources/images/cards/starter_cards/starter_backs/page_81.png"));
        cards.add(new StarterCard(frontCorners, backCorners, resources,
                "src/main/resources/images/cards/starter_cards/starter_fronts/page_82.png",
                "src/main/resources/images/cards/starter_cards/starter_backs/page_82.png"));

        //deparso il file JSON
        StarterCardDeckDeparser deparser = new StarterCardDeckDeparser();
        deparser.deparse();

        //confronto le due liste di carte
        ArrayList<StarterCard> deparsedCards = new ArrayList<>();
        deparsedCards.add(deparser.deparse().removeTop());
        deparsedCards.add(deparser.deparse().removeTop());

        assertSame(cards, deparsedCards);

    }
}
