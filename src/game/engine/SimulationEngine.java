package game.engine;
import game.model.City;
import game.model.Zone;
import game.model.Cell;
import game.model.Housing;
import game.model.Industrial;
import game.model.Commercial;
import java.util.ArrayList;

public class SimulationEngine {
    private City city;
    private int tickCount; //number of simulation ticks

    public SimulationEngine(City city, int tickCount) {
        this.city=city;
        this.tickCount=tickCount;
    }

    public void startSimulation(){
        for(int tick=1 ; tick<=tickCount ; tick++){
            resetTickData();

            distributeServices();

            distributeUtilities();

            // resource distribution start after first tick
            if(tick > 1){
                distributeResources(city);
            }

            updateZones();

            collectCurrentProduction();

            city.prepareNextTick();
        }
    }

    private void collectCurrentProduction(){
        //collect productions from zones
        for(Zone zone : city.getAllZones()){

            if(zone instanceof Housing){
                city.addCurrentPopulation(zone.getOutput());

            } else if (zone instanceof Industrial) {
                city.addCurrentGoods(zone.getOutput());

            }else if(zone instanceof Commercial){
                city.addCurrentLifestyle(zone.getOutput());
            }
        }
    }

    private void updateZones(){
        for(Zone zone : city.getAllZones()) {
            zone.updateZone();
        }
    }

    private void distributeServices(){}

    private void distributeUtilities(){}

    private void resetTickData(){
        for(Zone zone : city.getAllZones()) {
            zone.resetReceivedResources();
        }
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
