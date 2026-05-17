package game.model;

import java.util.ArrayList;

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
        return grid[0].length;
    }

    //returns all zones in the city
    public ArrayList<Zone> getAllZones(){
        ArrayList<Zone> zones = new ArrayList<>();

        for (int row=0 ; row<grid.length ; row++){
            for (int col=0 ; col<grid[row].length ; col++){

                if (grid[row][col].isZone()){
                    zones.add((Zone) grid[row][col]);
                }
            }
        }
        return zones;
    }

    //returns all providers in the city
    public ArrayList<Cell> getProviders(){
        ArrayList<Cell> providers = new ArrayList<>();

        for (int row=0 ; row<grid.length ; row++ ){

            for (int col=0 ; col<grid[row].length ; col++){

                if (grid[row][col] instanceof UtilityProvider || grid[row][col] instanceof ServiceProvider) {
                    providers.add(grid[row][col]);
                }
            }
        }
        return providers;
    }


    //returns all cells in the city
    public ArrayList<Cell> getAllCells(){
        ArrayList<Cell> cells = new ArrayList<>();

        for (int row=0 ; row<grid.length ; row++ ){

            for (int col=0 ; col<grid[row].length ; col++){
                cells.add(grid[row][col]);
            }
        }
        return cells;
    }
}
