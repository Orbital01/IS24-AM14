package it.polimi.ingsw.is24am14.client.printer;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.util.Map;

public class ActualBoard {

    private GameArea board;

    public ActualBoard(GameArea board) {
        this.board = board;
    }

    private int boardMaxColumn(GameArea board) {
        int maxColumn;
        maxColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).max().orElse(0);
        return maxColumn;
    }

    private int boardMinColumn(GameArea board) {
        int minColumn;
        minColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).min().orElse(0);
        return minColumn;
    }

    private int boardMaxRow(GameArea board) {
        int maxRow;
        maxRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).max().orElse(0);
        return maxRow;
    }

    private int boardMinRow(GameArea board) {
        int minRow;
        minRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).min().orElse(0);
        return minRow;
    }


    public Card[][] getActualBoard() {
        // Calcola le dimensioni della board
        int maxRow = boardMaxRow(board) - boardMinRow(board);  // da verificare che funzioni, ma dovrebbe
        int maxColumn = boardMaxColumn(board) - boardMinColumn(board);

        // Crea una nuova matrice con le dimensioni determinate
        Card[][] actualBoard = new Card[maxRow + 1][maxColumn + 1];

        //popolo la matrice con le carte presenti nella board, nella posizione corretta
        for (Map.Entry<Coordinates, Card> entry : board.getBoard().entrySet()) {
            // Usa le coordinate come indici della matrice
            int row = entry.getKey().getRow() - boardMinRow(this.board); // come li faccio diventare valori assoluti?
            int column = entry.getKey().getColumn() - boardMinColumn(this.board);

            actualBoard[row][column] = entry.getValue();
        }
        return actualBoard;
    }
}
