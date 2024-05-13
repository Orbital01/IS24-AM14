package it.polimi.ingsw.is24am14.client.printer;

import it.polimi.ingsw.is24am14.server.model.card.Card;

public interface Printable {
    String printFirstRow(String firstRow, Card card);
    String  printSecondRow(String secondRow, Card card);
    String  printThirdRow(String thirdRow, Card card);
    String  printFourthRow(String fourthRow, Card card);
    String  printFifthRow(String fifthRow, Card card);
    String  printSixthRow(String sixthRow, Card card);
    String  printSeventhRow(String seventhRow, Card card);
}
