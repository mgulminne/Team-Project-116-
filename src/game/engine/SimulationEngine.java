package game.engine;
import game.model.*;

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

            printCityStatus(city,tick); //Prints the city state at the end of the tick

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



    private void distributeServices(){

        // check all providers
        for (Cell provider : city.getProviders()) {

            // check service providers
            if (provider instanceof ServiceProvider) {
                ServiceProvider service = (ServiceProvider) provider;

                for (Zone zone : city.getAllZones()) {

                    // check service radius
                    if (zone.isWithinServiceRadius(service, service.getRadius())) {

                        if (service instanceof  PoliceStation) {
                            zone.setSecurity(true);
                        } else if (service instanceof Hospital ) {
                            zone.setHealth(true);
                        } else if (service instanceof  School) {
                            zone.setEducation(true);

                        }
                    }
                }
            }
        }
    }

    private void distributeUtilities(){
        UtilityBfs utilityBfs = new UtilityBfs();
        
        //check all providers
        for(Cell provider: city.getProviders()){

            if (provider instanceof PowerPlant){
                utilityBfs.runBfs(city,provider,"ELECTRICITY");

            } else if (provider instanceof WaterPumpingStation) {
                utilityBfs.runBfs(city,provider,"WATER");

            } else if (provider instanceof InternetHub) {
                utilityBfs.runBfs(city,provider,"INTERNET");
            }
        }
    }

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

    public void printCityStatus(City city, int tickNumber) {
        System.out.println("=== Tick " + tickNumber + " ===");

        for (int row = 0; row < city.getRowCount(); row++) {
            for (int col = 0; col < city.getColCount(); col++) {
                Cell cell = city.getCell(row, col);
                if (cell != null) {
                    if (cell.isZone()) {
                        Zone zone = (Zone) cell;
                        System.out.print(cell.getSymbol() + "" + zone.getLevel() + " ");
                    } else {
                        System.out.print(cell.getSymbol() + "  ");
                    }
                }
            }
            System.out.println();
        }

        System.out.println("\n--- Current Pools ---");
        System.out.println("Total Population : " + city.getPreviousPopulation());
        System.out.println("Total Goods      : " + city.getPreviousGoods());
        System.out.println("Total Lifestyle  : " + city.getPreviousLifestyle());
        System.out.println("=====================\n");
    }


}
