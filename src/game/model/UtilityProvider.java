package game.model;

public abstract class UtilityProvider extends Cell {

    protected final int capacity = 100; //fixed production capacity

    //Calling cell class constructor
    public UtilityProvider(int row,int col,char symbol){
        super(row, col, symbol);
    }

    //getter for capacity
    public int getCapacity(){
        return capacity;
    }


}
