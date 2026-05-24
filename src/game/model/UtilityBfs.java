package game.model;

import java.util.ArrayList;

public class UtilityBfs {

    //up,down,left,right
    private static final int[] rowDirections = {-1,1,0,0};
    private static final int[] colDirections = {0,0,-1,1};

    public void runBfs(City city, Cell startCell) {

        //keeps visited cells
        boolean[][] visited = new boolean[city.getRowCount()][city.getColCount()];
        ArrayList<Cell> list = new ArrayList<>();

        list.add(startCell);
        visited[startCell.getRow()][startCell.getCol()]=true;

        while (!list.isEmpty()){

            Cell current = list.remove(0);

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
