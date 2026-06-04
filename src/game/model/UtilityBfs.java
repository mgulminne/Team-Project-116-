package game.model;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UtilityBfs {

    //up,down,left,right
    private static final int[] rowDirections = {-1,1,0,0};
    private static final int[] colDirections = {0,0,-1,1};

    public void runBfs(City city,Cell startCell, String utilityType)  {

        UtilityProvider provider = (UtilityProvider) startCell;

        // utility capacity
        int remaining = provider.getCapacity();

        //keeps visited cells
        boolean[][] visited = new boolean[city.getRowCount()][city.getColCount()];
        ArrayList<Cell> list = new ArrayList<>();

        list.add(startCell);
        visited[startCell.getRow()][startCell.getCol()]=true;

        while (!list.isEmpty() && remaining > 0){

            Cell current = list.remove(0);

            if (current.isZone()) {

                Zone zone = (Zone) current;
                int demand = 0;

                if (utilityType.equals("ELECTRICITY")) {
                    demand = zone.getElectricityDemand();
                } else if (utilityType.equals("WATER")) {
                    demand = zone.getWaterDemand();
                } else if (utilityType.equals("INTERNET")) {
                    demand = zone.getInternetDemand();
                }

                // amount to give
                int given;

                if (remaining < demand) {
                    given = remaining;
                }
                else {
                    given = demand;
                }

                // delivering utility
                if (utilityType.equals("ELECTRICITY")) {
                    zone.receiveElectricity(given);
                } else if (utilityType.equals("WATER")) {
                    zone.receiveWater(given);
                } else if (utilityType.equals("INTERNET")) {
                    zone.receiveInternet(given);
                }

                // update remaining capacity
                remaining = remaining - given;
            }

            //check four directions
            for (int i=0 ; i<4 ; i++){

                int nextRow = current.getRow() + rowDirections[i];
                int nextCol = current.getCol() + colDirections[i];

                // check bounds and visited cells
                if(city.inBounds(nextRow,nextCol )&& !visited[nextRow][nextCol]){

                    Cell neighbour = city.getCell(nextRow,nextCol);

                    //check connectable cells
                    if(neighbour != null && neighbour.isConnectable()){

                        visited[nextRow][nextCol]=true;
                        list.add(neighbour);
                    }
                }
            }
        }
    }
}
