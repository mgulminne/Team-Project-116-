package game.model;

import game.engine.SimulationConfig;

public abstract class UtilityProvider extends Cell {

    protected final int capacity = SimulationConfig.UTILITY_CAPACITY; //fixed production capacity

    //Calling cell class constructor
    public UtilityProvider(int row,int col,char symbol){
        super(row, col, symbol);
    }

    //getter for capacity
    public int getCapacity(){
        return capacity;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }
    @Override
    public boolean isZone(){
        return false;
    }
    @Override
    public boolean isEmpty(){
        return false;
    }
}
