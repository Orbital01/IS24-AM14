package it.polimi.ingsw.is24am14.client.view.printer;

import it.polimi.ingsw.is24am14.server.model.card.Card;
import it.polimi.ingsw.is24am14.server.model.card.Coordinates;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class responsible for rendering the game board in the console
 */
public class RenderBoard {
    //prendo la hashmap nel costruttore
    GameArea board;

    /**
     * Constructor for the RenderBoard class.
     * @param board The game area to be rendered.
     */
    public RenderBoard(GameArea board) {
        this.board = board;
    }

    /**
     * Method to get the maximum column index of the board.
     * it uses functional programming to get the maximum column index of the board.
     * it must be called every time the board is printed.
     * @return The maximum column index.
     */
    private int boardMaxColumn() {
        int maxColumn;
        maxColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).max().orElse(0);
        return maxColumn;
    }

    /**
     * Method to get the minimum column index of the board.
     * it uses functional programming to get the minimum column index of the board.
     * it must be called every time the board is printed.
     * @return The minimum column index.
     */
    private int boardMinColumn() {
        int minColumn;
        minColumn = board.getBoard().keySet().stream().mapToInt(Coordinates::getColumn).min().orElse(0);
        return minColumn;
    }

    /**
     * Method to get the maximum row index of the board.
     * it uses functional programming to get the maximum row index of the board.
     * it must be called every time the board is printed.
     * @return The maximum row index.
     */
    private int boardMaxRow() {
        int maxRow;
        maxRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).max().orElse(0);
        return maxRow;
    }

    /**
     * Method to get the minimum row index of the board.
     * it uses functional programming to get the minimum row index of the board.
     * it must be called every time the board is printed.
     * @return The minimum row index.
     */
    private int boardMinRow() {
        int minRow;
        minRow = board.getBoard().keySet().stream().mapToInt(Coordinates::getRow).min().orElse(0);
        return minRow;
    }

    /**
     * Method to draw an empty card on the board
     * it is used by this class to fill the empty spaces on the board.
     * @return An empty card.
     */
    private ArrayList<String> drawEmptyCard() {
        ArrayList<String> emptyCard = new ArrayList<>();
        emptyCard.add("                   ");
        emptyCard.add("                   ");
        emptyCard.add("                   ");
        emptyCard.add("                   ");
        emptyCard.add("                   ");
        emptyCard.add("                   ");
        emptyCard.add("                   ");
        return emptyCard;
    }

    /**
     + this method prints one row of the Player board
     * @return The top border of the board.
     */
    //this method prints a row of the hashmap
    private ArrayList<String> printRow(int rowNumber) {
        ArrayList<String> row = new ArrayList<>();
        //initialize the row with 7 empty strings
        for(int i=0; i<7 ;i++){
            row.add("");
        }
        //for each card in the row add all the rows of the card
        for(int j=boardMinColumn(); j<=boardMaxColumn(); j++){

            ArrayList<String> cardRow;
            if(board.getCard(new Coordinates(rowNumber, j)) != null){
                Card card = board.getCard(new Coordinates(rowNumber, j));
                cardRow = card.drawCard();
            } else {
                //printing an empty card
                cardRow = drawEmptyCard();
            }

            int counter=0;
            for (String s : cardRow) {
                // normalize the string to 20 characters
                String normalizedString = s;
                int maxLength = 20;
                if (s.length() < maxLength) {
                    normalizedString = String.format("%-" + maxLength + "s", s);
                }
                row.set(counter, row.get(counter).concat(normalizedString));
                counter++;
            }
        }
        return row;
    }

    /**
     * Method to prints the board.
     * it prints the board row by row.
     */
    //the method printROw mus be iterated for all the rows of the hashmap in a printBoard method
    // and then all the rows must be printed in order
    public void printBoard(){
        for(int i=boardMaxRow(); i>=boardMinRow(); i--){
            ArrayList<String> render = printRow(i);
            for(String s : render){
                System.out.println(s);
            }
        }
    }

}