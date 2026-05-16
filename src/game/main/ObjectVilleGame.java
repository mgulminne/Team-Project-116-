package game.main;

import game.io.InvalidMapException;
import game.io.MapReader;

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
            System.out.println("Map loaded successfully.");
            System.out.println("Tick Count: " + tickCount);

        } catch (InvalidMapException e) {
            System.out.println(e.getMessage());
        }
    }
}
