package it.polimi.ingsw.is24am14.client.printer;

import it.polimi.ingsw.is24am14.server.model.card.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DrawCardTest {

    @Test
    void drawStarterCard() {
        //creo una starter card e la disegno
        Card card;

        ArrayList<Corner> empPlaInsEmp = new ArrayList<>();
        empPlaInsEmp.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        empPlaInsEmp.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        empPlaInsEmp.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        empPlaInsEmp.add(new Corner(CornerEnum.ResourceEnum.PLANT));

        ArrayList<Corner> funPlaInsAni = new ArrayList<>();
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        funPlaInsAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        ArrayList<CornerEnum.ResourceEnum> insect = new ArrayList<>();
        insect.add(CornerEnum.ResourceEnum.INSECT);
        insect.add(CornerEnum.ResourceEnum.INSECT);

        card = new StarterCard(empPlaInsEmp, funPlaInsAni, insect, "src/main/resources/images/cards/starter_cards/starter_fronts/page_81.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_81.png");

        ArrayList<String> carta = card.drawCard();

        for (String s : carta) {
            System.out.println(s);
        }
    }

}
