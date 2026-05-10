package game.model;

public abstract class Cell {
    private int row;
    private int col;
    private char symbol;

    public Cell(int row ,int col,char symbol){
        this.row=row;
        this.col=col;
        this.symbol=symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getSymbol() {
        return symbol;
    }
    public abstract boolean isConnectable();  // Checks if the cell allows electricity and water to pass through(roads)
    public abstract boolean isZone();         // Checks if the cell is a specific zone (Residential, Commercial, or Industrial)
    public abstract boolean isEmpty();        // Checks whether the cell is completely empty


    public int calculateManhattanDistance(Cell otherCell){      //Calculates the Manhattan distance between this cell and other cell.
        int rowDifference = Math.abs(this.row- otherCell.row);  //The formula: d = |x1 - x2| + |y1 - y2|
        int colDifference = Math.abs(this.col - otherCell.col);
        return rowDifference + colDifference;                   // Total steps required to reach the destination
    }
    public boolean isWithinServiceRadius  (Cell providerCell,int radius){
        return this.calculateManhattanDistance(providerCell) <= radius;
    }
}

