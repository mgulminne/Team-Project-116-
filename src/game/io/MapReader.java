package game.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class MapReader {
    public static String[][] readMap(String filePath) throws InvalidMapException {
        ArrayList<String> lines = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }

        }
        catch (IOException e) {
            throw new InvalidMapException();
        }

        int size = lines.size();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.length() != size) {
                throw new InvalidMapException();
            }
        }

        String[][] grid = new String[size][size];
        for (int i = 0; i < size; i++) {
            String line = lines.get(i);
            for (int j = 0; j < size; j++) {
                grid[i][j] = String.valueOf(line.charAt(j));
            }
        }
        return grid;
    }
}