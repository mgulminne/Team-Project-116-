package game.model;

public class Commercial extends Zone {
    public Commercial(int row, int col) {
        super(row, col, 'C');
    }

    @Override
    public void updateZone() {
        int m = receivedElectricity;
        if (receivedWater < m) { //Calculate m
            m = receivedWater;
        }
        if (receivedInternet < m) {
            m = receivedInternet;
        }

        if (m == 0) { //Reset rule
            level = 0;

        } else {

            if (level == 0 && receivedPopulation > 0 && receivedGoods > 0) { //Level update
                level = 1;

            } else if (level == 1 && hasSecurity) {
                level = 2;

            } else if (level == 2 && receivedPopulation > 1 && receivedGoods > 1) {
                level = 3;

            } else if (level > 1 && !hasSecurity) {
                level = level - 1;
            }
        }
        //Calculate output (lifestyle)

        if (level == 0) {
            output = 0;

        } else if (level == 1) {
            output = m;

        } else if (level == 2) {
            output = 2 * m;

        } else if (level == 3) {
            int extraPopulation;
            int extraGoods;

            if (receivedPopulation - 1 > 0) {
                extraPopulation = receivedPopulation - 1;
            } else {
                extraPopulation = 0;
            }

            if (receivedGoods - 1 > 0) {
                extraGoods = receivedGoods - 1;
            } else {
                extraGoods = 0;
            }

            int minimumExtra;

            if (extraPopulation < extraGoods) {
                minimumExtra = extraPopulation;
            } else {
                minimumExtra = extraGoods;
            }
            output = (2 * m) + minimumExtra;
        }
        // Update demands

        if (output > 1) {
            electricityDemand = output;
            waterDemand = output;
            internetDemand = output;

        } else {
            electricityDemand = 1;
            waterDemand = 1;
            internetDemand = 1;
        }
    }
}