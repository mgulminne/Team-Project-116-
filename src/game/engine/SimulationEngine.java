package game.engine;
import game.model.City;
import game.model.Zone;
import game.model.Cell;
import game.model.Housing;
import game.model.Industrial;
import game.model.Commercial;
import java.util.ArrayList;

public class SimulationEngine {

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
        int popPerZone = (popReceiversCount>0) ? (city.getPreviousPopulation() / popReceiversCount) : 0;
        int goodsPerZone = (commercialCount>0) ? (city.getPreviousGoods() / commercialCount) : 0;
        int lifestylePerZone = (housingCount>0) ? (city.getPreviousLifestyle() / housingCount) : 0;

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

}
