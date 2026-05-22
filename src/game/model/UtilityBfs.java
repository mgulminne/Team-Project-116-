package game.model;

import java.util.LinkedList;
import java.util.Queue;

public class UtilityBfs {

    // up,down,left,right
    private static final int[] rowDirections= {-1,1,0,0};
    private static final int[] colDirections={0,0,-1,1};

    public void runBfs(City city,Cell startCell){
        boolean [][] visited = new boolean[city.getRowCount()][city.getColCount()];  //keeps visited cells

        Queue<Cell> queue= new LinkedList<>();
        queue.add(startCell);

        visited[startCell.getRow()][startCell.getCol()]=true;

    }


}
