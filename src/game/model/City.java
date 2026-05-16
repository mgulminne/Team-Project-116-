package game.model;

public class City {
    private Cell[][] grid;   //city map

    public City(Cell[][] grid) {this.grid = grid;}
    public Cell[][] getGrid() {return grid;}

    //checks if the position is inside the map
    public boolean inBounds(int row,int col){
        return row>=0 && row<grid.length && col>=0 && col<grid[0].length;
    }

    // returns the cell at the given position
    public Cell getCell(int row,int col){
        if(inBounds(row,col)){
            return grid[row][col];
        }
        return null;
    }

    //returns row count
    public int getRowCount(){
        return grid.length;
    }
    // returns column count
    public int getColCount(){
        return  grid[0].length;
    }


}
