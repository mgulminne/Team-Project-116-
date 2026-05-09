package game.model;

public abstract class ServiceProvider extends Cell {

    protected int radius;

    //calling cell class constructor
    public ServiceProvider(int row,int col,char symbol){
        super(row,col,symbol);
        this.radius = radius;
    }


    //getter for radius
    public int getRadius(){
        return radius;
    }
}
