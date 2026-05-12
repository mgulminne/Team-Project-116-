package game.model;

public class Empty extends Cell {
    public Empty(int row,int col){
       super(row,col,'E');
    }
    @Override
    public boolean isConnectable(){
        return false;
    }
    @Override
    public boolean isZone(){
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}

