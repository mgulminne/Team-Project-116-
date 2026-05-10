package game.model;

public abstract class Zone extends Cell {
    protected int level;   //zone level (0-3)

    //utility demands of the zone
    protected int electricityDemand;
    protected int waterDemand;
    protected int internetDemand;

    //utilities received in the current tick
    protected int receivedElectricity;
    protected int receivedWater;
    protected int receivedInternet;

    //service status
    protected boolean hasSecurity;
    protected boolean hasHealth;
    protected boolean hasEducation;

    //resources received in the current tick
    protected int receivedPopulation;
    protected int receivedGoods;
    protected int receivedLifestyle;

    protected int output;     //amount produced by the zone

    public Zone (int row,int col,char symbol){
        super(row, col, symbol);

        level=0;   //starting level

        //starting utility demands
        electricityDemand=1;
        waterDemand=1;
        internetDemand=1;
    }


}
