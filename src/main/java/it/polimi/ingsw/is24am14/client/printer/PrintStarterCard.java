package it.polimi.ingsw.is24am14.client.printer;

import it.polimi.ingsw.is24am14.server.model.card.*;

import java.util.ArrayList;

public class PrintStarterCard implements Printable, emoji{

    //ArrayList<Corner> frontCorners;
    //ArrayList<Corner> backCorners;
    //ArrayList<CornerEnum.ResourceEnum> frontResources;

    @Override
    public String printFirstRow(String firstRow, Card card) {
        firstRow = firstRow.concat("|-----|------------|-----|");
        return firstRow;
    }

    @Override
    public String printSecondRow(String secondRow, Card card) {
        secondRow = secondRow.concat("|  " + card.getFrontCorners().get(0).getType() + "  |            |  " + card.getBackCorners().get(0).getResource().toString() + "  |");
        return secondRow;
    }

    @Override
    public String printThirdRow(String thirdRow, Card card) {
        thirdRow = thirdRow.concat("|-----|------------|-----|");
        return thirdRow;
    }

    @Override
    public String printFourthRow(String fourthRow, Card card) {
        fourthRow = fourthRow.concat("|            |            |            |");
        return fourthRow;
    }

    @Override
    public String printFifthRow(String fifthRow, Card card) {
        fifthRow = fifthRow.concat("|  " + card.getFrontCorners().get(1).getType() + "  |            |  " + card.getBackCorners().get(1).getResource().toString() + "  |");
        return fifthRow;
    }

    @Override
    public String printSixthRow(String sixthRow, Card card) {
        sixthRow = sixthRow.concat("|-----|------------|-----|");
        return sixthRow;
    }

    @Override
    public String printSeventhRow(String seventhRow, Card card) {
        seventhRow = seventhRow.concat("|            |            |            |");
        return seventhRow;
    }

}
