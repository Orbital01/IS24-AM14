package it.polimi.ingsw.is24am14.server.model.card;
/**
 * Represents the Coordinates (row and column index) of the position of a Card on the GameArea
 */
public class Coordinates {
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
        if (cornerIndex == CornerIndex.NORTH_WEST) {
            oldCoordinates.setRow(oldCoordinates.getRow() + 1);
            oldCoordinates.setColumn(oldCoordinates.getColumn() - 1);
        } else if (cornerIndex == CornerIndex.NORTH_EAST) {
            oldCoordinates.setRow(oldCoordinates.getRow() + 1);
            oldCoordinates.setColumn(oldCoordinates.getColumn() + 1);
        } else if (cornerIndex == CornerIndex.SOUTH_EAST) {
            oldCoordinates.setRow(oldCoordinates.getRow() - 1);
            oldCoordinates.setColumn(oldCoordinates.getColumn() + 1);
        } else {
            oldCoordinates.setRow(oldCoordinates.getRow() - 1);
            oldCoordinates.setColumn(oldCoordinates.getColumn() - 1);
        }
        return oldCoordinates;
    }
}
