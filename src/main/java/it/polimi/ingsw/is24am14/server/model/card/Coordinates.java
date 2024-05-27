package it.polimi.ingsw.is24am14.server.model.card;

import java.io.Serializable;

/**
 * Represents the Coordinates (row and column index) of the position of a Card on the GameArea
 */
public class Coordinates implements Serializable, Comparable<Coordinates> {
    private int row;
    private int column;
    
    /**
     * Constructs a Coordinates type object given row and column index
     * @param row The row index
     * @param column The column index
     */
    public Coordinates(int row, int column) {
    this.row = row;
    this.column = column;
    }
    /**
     * Gets the row index
     * @return The row index
     */
    public int getRow(){
        return row;
    }
    /**
     * Sets the new row index
     * @param row The row index
     */
    public void setRow(int row){
        this.row = row;
    }
    /**
     * Gets the column index
     * @return The column index
     */
    public int getColumn(){
        return column;
    }
    /**
     * Sets column index
     * @param column The new column index
     */
    public void setColumn(int column){
        this.column = column;
    }
    /**
     * Calculates new, adjusted Coordinates based on the given corner Index, updating row and column
     * @param oldCoordinates The coordinates of an already placed card
     * @param cornerIndex Directional index of the Corner (e.g., NORTH_WEST)
     * @return The updated Coordinates (Where a new card will be placed)
     */
    public static Coordinates newCoordinates(Coordinates oldCoordinates, int cornerIndex) {
        Coordinates result = new Coordinates(oldCoordinates.getRow(), oldCoordinates.getColumn());
        if (cornerIndex == CornerIndex.TOP_LEFT) {
            result.setRow(oldCoordinates.getRow() + 1);
            result.setColumn(oldCoordinates.getColumn() - 1);
        } else if (cornerIndex == CornerIndex.TOP_RIGHT) {
            result.setRow(oldCoordinates.getRow() + 1);
            result.setColumn(oldCoordinates.getColumn() + 1);
        } else if (cornerIndex == CornerIndex.BOTTOM_RIGHT) {
            result.setRow(oldCoordinates.getRow() - 1);
            result.setColumn(oldCoordinates.getColumn() + 1);
        } else {
            result.setRow(oldCoordinates.getRow() - 1);
            result.setColumn(oldCoordinates.getColumn() - 1);
        }
        return result;
    }

    
    /**
     * Sums row and column indexes of two coordinates
     * @param baseCoordinate First term of the addition
     * @param addedCoordinate Second term of the addition
     * @return The sum of the two coordinates
     */
    public static Coordinates add(Coordinates baseCoordinate, Coordinates addedCoordinate){
        int baseRow = baseCoordinate.getRow();
        int baseColumn = baseCoordinate.getColumn();
        int addedRow = addedCoordinate.getRow();
        int addedColumn = addedCoordinate.getColumn();
        return new Coordinates(baseRow+addedRow, baseColumn+addedColumn);
    }

    @Override
    public int compareTo(Coordinates o) {
        if (this.row == o.row && this.column == o.column) {
            return 0;
        } else if (distance(this) > distance(o)) {
            return 1;
        }
        return -1;
    }

    private double distance(Coordinates coordinates) {
        return Math.sqrt(this.row * this.row + this.column * this.column);
    }

    @Override
    public String toString() {
        return "[" + row + ", " + column + "]";
    }
}
