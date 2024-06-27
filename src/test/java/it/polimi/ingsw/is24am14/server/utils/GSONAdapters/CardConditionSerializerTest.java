package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;

import com.google.gson.Gson;
import it.polimi.ingsw.is24am14.server.model.card.CardCondition;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CardConditionSerializerTest {
    Gson gson = InitGSON.init();
    Coordinates topLeft = new Coordinates(1, -1);
    Coordinates topMid = new Coordinates(2, 0);
    Coordinates topRight = new Coordinates(1, 1);
    Coordinates bottomRight = new Coordinates(-1, 1);
    Coordinates bottomLeft = new Coordinates(-1, -1);
    Coordinates bottomMid = new Coordinates(-2, 0);
    Coordinates midLeft = new Coordinates(0, -2);
    Coordinates centre = new Coordinates(0, 0);
    Coordinates midRight = new Coordinates(0, 2);
    Coordinates mostUpperLeft = new Coordinates(2, -2);
    Coordinates mostUpperRight = new Coordinates(2, 2);

    @Test
    void redDiagCondition() {
        CardCondition redDiag = new CardCondition();
        redDiag.addClause(centre, CornerEnum.ResourceEnum.FUNGI);
        redDiag.addClause(topRight, CornerEnum.ResourceEnum.FUNGI);
        redDiag.addClause(mostUpperRight, CornerEnum.ResourceEnum.FUNGI);

        String json = gson.toJson(redDiag);

        CardCondition restored = gson.fromJson(json, CardCondition.class);

        ArrayList<Coordinates> originalCoordinates = new ArrayList<>(redDiag.getListCard().keySet());

        ArrayList<Coordinates> restoredCoordinates = new ArrayList<>(restored.getListCard().keySet());

        for (int i = 0; i < originalCoordinates.size(); i++) {
            assertEquals(originalCoordinates.get(i).getRow(), restoredCoordinates.get(i).getRow());
            assertEquals(originalCoordinates.get(i).getColumn(), restoredCoordinates.get(i).getColumn());
        }

        for (int i = 0; i < originalCoordinates.size(); i++) {
            assertEquals(redDiag.getListCard().get(originalCoordinates.get(i)), restored.getListCard().get(restoredCoordinates.get(i)));
        }
    }
}