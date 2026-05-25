package game.engine;
import game.model.City;
import game.model.Zone;
import game.model.Cell;
import game.model.Housing;
import game.model.Industrial;
import game.model.Commercial;
import java.util.ArrayList;

public class SimulationEngine {

    private int previousPopulation = 0;
    private int previousGoods = 0;
    private int previousLifestyle = 0;

    public SimulationEngine() {
    }

    //distribution of resources
    public void distributeResources(City city){
        ArrayList<Zone> allZones = city.getAllZones();

        int housingCount = 0;
        int industrialCount = 0;
        int commercialCount = 0;

        for(Zone zone : allZones){
            if (zone instanceof Housing) housingCount++;
            else if (zone instanceof  Industrial) industrialCount++;
            else if (zone instanceof Commercial) commercialCount++;
        }

        int popReceiversCount = industrialCount + commercialCount;
        int popPerZone = (popReceiversCount>0) ? (previousPopulation / popReceiversCount) : 0;
        int goodsPerZone = (commercialCount>0) ? (previousGoods / commercialCount) : 0;
        int lifestylePerZone = (housingCount>0) ? (previousLifestyle / housingCount) : 0;

        for(Zone zone : allZones){
            if(zone instanceof Housing){
                zone.receiveLifestyle(lifestylePerZone);
            }
            else if(zone instanceof Industrial){
                zone.receivePopulation(popPerZone);
            }
            else if(zone instanceof Commercial){
                zone.receivePopulation(popPerZone);
                zone.receiveGoods(goodsPerZone);
            }
        }
    }

    // getters and setters
    public int getPreviousGoods() {
        return previousGoods;
    }

    public void setPreviousGoods(int previousGoods) {
        this.previousGoods = previousGoods;
    }

    public int getPreviousLifestyle() {
        return previousLifestyle;
    }

    public void setPreviousLifestyle(int previousLifestyle) {
        this.previousLifestyle = previousLifestyle;
    }

    public int getPreviousPopulation() {
        return previousPopulation;
    }

    public void setPreviousPopulation(int previousPopulation) {
        this.previousPopulation = previousPopulation;
    }


}
