package game.model;

public class CellFactory {
    public static Cell createCell(char symbol,int row,int col){
        switch (symbol){

            case 'R':
                return new Road(row,col);

            case 'E':
                return new Empty(row,col);

            case 'C':
                return new Commercial(row, col);

            case 'I':
                return new Industrial(row, col);

            case 'P':
                return new PowerPlant(row, col);

            case 'W':
                return new WaterPumpingStation(row, col);

            case 'T':
                return new InternetHub(row, col);

            case 'F':
                return new PoliceStation(row, col);

            case 'D':
                return new Hospital(row, col);

            case 'S':
                return new School(row, col);

            case  'H':
                return new Housing(row, col);

              //If an unknown character is encountered in the map
             // it returns an empty cell to prevent the program from crashing
             default:
                return new Empty(row, col);

        }
    }
}
