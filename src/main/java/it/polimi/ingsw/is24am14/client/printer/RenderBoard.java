package it.polimi.ingsw.is24am14.client.printer;

import it.polimi.ingsw.is24am14.server.model.card.Card;

import java.util.ArrayList;

public class RenderBoard {

    private final ActualBoard actualBoard;

    public RenderBoard(ActualBoard actualBoard) {
        this.actualBoard = actualBoard;
    }

    private ArrayList<String> renderRow(int row) {
        ArrayList<String> rowString = new ArrayList<>();

        //inizializzo la riga della board con tot righe vuote in base alla dimensione della carta
        ArrayList<String> testCard = actualBoard.getActualBoard()[row][0].drawCard();
        for (int i = 0; i < testCard.size()+1; i++) {
            rowString.add("");
        }

        for (int i = 0; i < actualBoard.getActualBoard()[row].length+1; i++) {
            //per ogni carta della riga corrente chiamo il disegno della carta
            ArrayList<String> card = actualBoard.getActualBoard()[row][i].drawCard();

            //aggiungo ogni riga della carta alla riga della board concatenandole -> la prima riga di tutte le carte
            for (int j=0; j<card.size()+1 ;j++ ){
                rowString.add(j, rowString.get(j).concat(card.get(j))); //concatena la riga della carta alla riga della board
            }
        }
        return rowString;
    }
    public void render() {
        //a questo punto stampo tutte le righe in ordine
        for (int i = 0; i < actualBoard.getActualBoard().length; i++) {
            ArrayList<String> row = renderRow(i);
            for (String s : row) {
                System.out.println(s);
            }
        }
    }
}
