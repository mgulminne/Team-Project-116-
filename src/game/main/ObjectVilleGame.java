package game.main;
import game.io.InvalidMapException;
import game.io.MapReader;
import game.model.City;
import game.model.CellFactory;
import game.model.Cell;
import game.engine.SimulationEngine;

public class ObjectVilleGame {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please enter map file and tick count.");
            System.out.println("Example: java ObjectVilleGame map.txt 5");
            return;
        }
        String mapFile = args[0];
        int tickCount = 0;

        try {
            tickCount = Integer.parseInt(args[1]);

        } catch (NumberFormatException e) {
            System.out.println("Tick count must be a number. ");
            return;
        }
        if (tickCount <= 0) {
            System.out.println(" Tick count must be positive.");
            return;
        }
        try {
            String[][] map = MapReader.readMap(mapFile);

            //get the grid size from the parsed text map
            int size = map.length;

            //initialize the 2D array to hold actual Cell objects
            Cell [][] cellGrid = new Cell[size][size];

            //iterate through every row on the map
            for(int row = 0; row < size; row++){

                //iterate through every column on the map
                for(int col = 0; col <size; col++){

                    //extract the character symbol from the string array
                    char symbol = map[row][col].charAt(0);

                    //dynamically instantiate the correct building object
                     cellGrid [row][col] = CellFactory.createCell(symbol,row,col); //
                }
            }
            //add the fully populated grid into the city model
            City city = new City(cellGrid);

            //initialize the game engine with desired ticks
            SimulationEngine engine = new SimulationEngine(city,tickCount);

            System.out.println("Map loaded successfully.");
            System.out.println("Tick Count: " + tickCount);

            //start the main simulation loop
            engine.startSimulation();
            engine.printCityStatus(city,tickCount);

        } catch (InvalidMapException e) {
            System.out.println(e.getMessage());
        }
    }
}
