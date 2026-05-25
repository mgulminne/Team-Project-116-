package game.model;

public class PowerPlant extends UtilityProvider{

    public PowerPlant(int row,int col){
        super(row,col,'P');
    }

    public void distributeElectricity(City city,UtilityBfs bfsAlgorithm){
        bfsAlgorithm.runBfs(city,this, "electricity");
    }
}
