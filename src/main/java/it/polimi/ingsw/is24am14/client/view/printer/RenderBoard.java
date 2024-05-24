package it.polimi.ingsw.is24am14.client.view.printer;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.util.ArrayList;
import java.util.HashMap;

public class RenderBoard {
    //prendo la hashmap nel costruttore
    GameArea board;
    public RenderBoard(GameArea board) {
        this.board = board;
    }

    private int boardMaxColumn() {
        int maxColumn;
        maxColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).max().orElse(0);
        return maxColumn;
    }

    private int boardMinColumn() {
        int minColumn;
        minColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).min().orElse(0);
        return minColumn;
    }

    private int boardMaxRow() {
        int maxRow;
        maxRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).max().orElse(0);
        return maxRow;
    }

    private int boardMinRow() {
        int minRow;
        minRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).min().orElse(0);
        return minRow;
    }

    private ArrayList<String> drawEmptyCard() {
        ArrayList<String> emptyCard = new ArrayList<>();
        emptyCard.add("                         ");
        emptyCard.add("                         ");
        emptyCard.add("                         ");
        emptyCard.add("                         ");
        emptyCard.add("                         ");
        emptyCard.add("                         ");
        emptyCard.add("                         ");
        return emptyCard;
    }

    //creo un metodo per stampare la singola riga della hashmap
    private ArrayList<String> printRow(int rowNumber) {
        ArrayList<String> row = new ArrayList<>();
        //inizializzo le righe della riga da stampare
        for(int i=0; i<7 ;i++){
            row.add("");
        }
        //per ogni carta nella riga aggiungo tutte le righe della carta
        for(int j=boardMinColumn(); j<=boardMaxColumn(); j++){

            if(board.getCard(new Coordinates(rowNumber, j)) != null){
                Card card = board.getCard(new Coordinates(rowNumber, j));
                ArrayList<String> cardRow = card.drawCard();

                int counter=0;
                for (String s : cardRow) {
                    row.set(counter, row.get(counter).concat(s));
                    counter++;
                }
            } else {
                //stampo la carta vuota
                ArrayList<String> emptyCard = drawEmptyCard();
                int counter=0;
                for (String s : emptyCard) {
                    row.set(counter, row.get(counter).concat(s));
                    counter++;
                }

            }
        }
    return row;
    }

    //il metodo sopra va iterato per tutte le righe della hashmap in un metodo printBoard
    // e poi vanno tutte stampate in ordine
    public void printBoard(){
        for(int i=boardMaxRow(); i>=boardMinRow(); i--) {
            ArrayList<String> render = printRow(i);
                for (String s : render) {
                    System.out.println(s);
                }
        }
    }

}