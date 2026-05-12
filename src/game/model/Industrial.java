package game.model;

public class Industrial extends Zone{
    public Industrial(int row, int col) {
        super(row, col, 'I');
    }
    @Override
    public void updateZone(){
        int m;

        if (receivedElectricity < receivedWater) { //calculate m:
            m = receivedElectricity;
        }
        else {
            m = receivedWater;
        }

        if (m == 0) {
            level = 0;
        }
        else {

            if (level == 0 && receivedPopulation > 0) { // Level update rules
                level = 1;

            } else if (level == 1 && hasSecurity) {
                level = 2;

            } else if (level == 2 && receivedPopulation > 1) {
                level = 3;

            } else if (level > 1 && !hasSecurity) {
                level = level - 1;
            }
        }


        if (level == 0) { //calculate output
            output = 0;

        } else if (level == 1) {
            output = m;

        } else if (level == 2) {
            output = 2 * m;

        } else if (level == 3) {
            int extraPopulation;
            if (receivedPopulation - 1 > 0) {
                extraPopulation = receivedPopulation - 1;
            } else {
                extraPopulation = 0;
            }
            output = (2 * m) + extraPopulation;
        }
        //update demands

        if (output > 1) {
            electricityDemand = output;
            waterDemand = output;

        } else {
            electricityDemand = 1;
            waterDemand = 1;
        }

    }
}
