package game.model;

import java.util.ArrayList;

public class City {
    private Cell[][] grid;   //city map

    //previous tick production
    private int previousPopulation;
    private int previousGoods;
    private int previousLifestyle;
    //current tick production
    private int currentPopulation;
    private int currentGoods;
    private int currentLifestyle;


    public City(Cell[][] grid) {
        this.grid = grid;

        previousPopulation = 0;
        previousGoods = 0;
        previousLifestyle = 0;

        currentPopulation = 0;
        currentGoods = 0;
        currentLifestyle = 0;
    }
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

    //prints the current state of the city grid to the console
    public void printCityMap() {
        for (int row=0 ; row < grid.length; row++) {

            for (int col = 0; col < grid [row].length; col++) {

                System.out.println(grid [row][col].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public int getPreviousPopulation(){ return previousPopulation; }
    public int getPreviousGoods(){ return previousGoods; }
    public int getPreviousLifestyle(){ return previousLifestyle; }

    // add current lifestyle-goods-population production
    public void addCurrentPopulation(int amount){
        currentPopulation+=amount;
    }

    public void addCurrentGoods(int amount){
        currentGoods+=amount;
    }

    public void addCurrentLifestyle(int amount){
        currentLifestyle+=amount;
    }

    // prepare value for next tick
    public void prepareNextTick(){
        previousPopulation=currentPopulation;
        previousGoods=currentGoods;
        previousLifestyle=currentLifestyle;

        currentPopulation=0;
        currentGoods=0;
        currentLifestyle=0;
    }
}
