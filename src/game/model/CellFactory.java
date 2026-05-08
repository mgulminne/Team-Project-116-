package game.model;

public class CellFactory {
    public static Cell createCell(char symbol,int row,int col){
        switch (symbol){

            case 'R':
                return new Road(row,col);

            case 'E':
                return new Empty(row,col);

          //More will be added in later stages




            default:
                return null;

        }
    }
}
