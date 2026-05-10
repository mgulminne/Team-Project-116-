package game.model;

public abstract class ServiceProvider extends Cell {

    protected int radius;

    //calling cell class constructor
    public ServiceProvider(int row,int col,char symbol,int radius){
        super(row,col,symbol);
        this.radius = radius;
    }


    //getter for radius
    public int getRadius(){
        return radius;
    }

    @Override
    public boolean isConnectable() {
        return false;
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
