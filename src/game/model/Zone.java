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

    public int getLevel() {return level;}

    public int getOutput() {return output;}

    public int getElectricityDemand() {return electricityDemand;}

    public int getWaterDemand() {return waterDemand;}

    public int getInternetDemand() {return internetDemand;}


    // methods for adding received values
    public void receiveElectricity(int amount){
        receivedElectricity += amount;
    }

    public void receiveWater(int amount){
        receivedWater += amount;
    }

    public void receiveInternet(int amount){
        receivedInternet += amount;
    }

    public void receivePopulation(int amount){
        receivedPopulation += amount;
    }

    public void receiveGoods(int amount){
        receivedGoods += amount;
    }

    public void receiveLifestyle(int amount){
        receivedLifestyle += amount;
    }



    // methods for setting service status
    public void setSecurity(boolean value){
        hasSecurity=value;
    }

    public void setHealth(boolean value){
        hasHealth=value;
    }

    public void setEducation(boolean value){
        hasEducation=value;
    }


    @Override
    public boolean isConnectable(){return true;}

    @Override
    public boolean isZone(){return true;}

    @Override
    public boolean isEmpty(){return false;}

    // Sets utility demands equal to the current output
    // If the output is less than 1, demand stays at 1 to prevent zero demand
    public void updateUtilityDemands(){

        int newDemand = this.output;

         if(newDemand <1){
            newDemand = 1;
        }
        this.electricityDemand = newDemand;
        this.waterDemand = newDemand;
        this.internetDemand =newDemand;
    }

    //Resets all received utilities and resources back to 0
    //This method must be called at the start of each tick to prevent resource pile up
    public void resetReceivedResources() {
        this.receivedElectricity = 0;
        this.receivedWater = 0;
        this.receivedInternet = 0;
        this.receivedPopulation = 0;
        this.receivedGoods = 0;
        this.receivedLifestyle = 0;
        this.hasSecurity = false;
        this.hasHealth = false;
        this.hasEducation = false;
    }

    public abstract void updateZone();

}
