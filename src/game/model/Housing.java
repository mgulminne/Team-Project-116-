package game.model;
public class Housing extends Zone {
    public Housing(int row, int col) {
        super(row, col, 'H');
    }
    @Override
    public void updateZone() {

        // calculate min utility
        int m = receivedElectricity;

        if (receivedWater < m) {
            m = receivedWater;
        }

        if (receivedInternet < m) {
            m = receivedInternet;
        }

        // level update rules
        if ( m == 0) {
            level = 0;

        }
        else {
             if (level == 0 && receivedElectricity > 0 && receivedWater > 0 && receivedInternet > 0)  {

                 level = 1;
             }

             else if (level == 1 && hasSecurity && hasHealth && hasEducation ) {

                 level = 2;
             }

             else  if (level == 2 && receivedLifestyle > 0) {

                 level = 3;

             } else if (level == 3 &&
                     receivedLifestyle == 0) {

                 level = 2;

             } else if (level == 2 &&
                     (!hasSecurity || !hasHealth || !hasEducation)) {

                 level = 1;

             }


             // output calc
             if (level == 0) {
                 output = 0;
             }

            else if (level == 1 ) {
                 output = m;

             }

             else if (level == 2) {
                 output = 2 * m;

             }

             else if (level == 3) {
                 output = (2 * m) + receivedLifestyle;
             }
        }
        updateUtilityDemands();

    }

}
