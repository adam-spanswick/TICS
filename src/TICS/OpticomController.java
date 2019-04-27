package TICS;

public class OpticomController {
    private Road northSouth, eastWest;
    private Constants constants;
    public OpticomController(Road northSouth, Road eastWest){
        this.northSouth = northSouth;
        this.eastWest = eastWest;
        constants = new Constants();
    }

    /**
     *
     * @param curSequence
     * @param emergency
     * @param isNorthSouth
     * @return
     */
    private TICS.lightSequences setCurSequence(TICS.lightSequences curSequence, Road.emergency emergency, boolean isNorthSouth) {
        TICS.lightSequences newSequence;
        switch (emergency) {
            case STRAIGHT:
                if (isNorthSouth) {
                    newSequence = TICS.lightSequences.NORTH_SOUTH;
                } else {
                    newSequence = TICS.lightSequences.EAST_WEST;
                }
                break;
            case TURN:
                if (isNorthSouth) {
                    newSequence = TICS.lightSequences.NORTH_SOUTH_TURN;
                } else {
                    newSequence = TICS.lightSequences.EAST_WEST_TURN;
                }
                break;
            case NONE:
            default:
                newSequence = curSequence;
        }
        return newSequence;
    }

    /**
     *
     * @param curSequence
     * @return
     */
    public TICS.lightSequences checkForEmergency(TICS.lightSequences curSequence){
        curSequence = setCurSequence(curSequence,northSouth.checkForEmergancyVehicle(), true);
        curSequence = setCurSequence(curSequence,eastWest.checkForEmergancyVehicle(), false);
        return curSequence;
    }
    /**
     * emergencyVehicleSeq puts the TICS into the emergency vehicle timing plan.
     */
    public TICS.lightSequences  runSequence(TICS.lightSequences curSequence){
        switch (curSequence) {
            case NORTH_SOUTH_TURN:
                // Turn Light To Green
                northSouth.turnLightOn();

                // Wait For Light Duration
                while(northSouth.checkForEmergancyVehicle() == Road.emergency.TURN) {
                    waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH);
                }
                // Turn Light to Yellow then Red
                northSouth.turnLightOff();

                // Set Next Sequence
                curSequence = TICS.lightSequences.NORTH_SOUTH;
                break;
            case NORTH_SOUTH:
                // Straight Lights to Green
                northSouth.straightLightOn();

                // Wait For Light Duration
                while(northSouth.checkForEmergancyVehicle() == Road.emergency.STRAIGHT) {
                    waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH);
                }

                // Straight Lights Yellow then Red
                northSouth.straightLightOff();
                curSequence = TICS.lightSequences.EAST_WEST_TURN;
                break;
            case EAST_WEST_TURN:
                // Turn Light To Green
                eastWest.turnLightOn();

                // Wait For Light Duration
                while(eastWest.checkForEmergancyVehicle() == Road.emergency.TURN) {
                    waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH);
                }

                // Turn Light to Yellow then Red
                eastWest.turnLightOff();

                // Set Next Sequence
                curSequence = TICS.lightSequences.EAST_WEST;
                break;
            case EAST_WEST:
                // Straight Lights to Green
                eastWest.straightLightOn();

                // Wait For Light Duration
                while(eastWest.checkForEmergancyVehicle() == Road.emergency.STRAIGHT) {
                    waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH);
                }

                // Straight Lights Yellow then Red
                eastWest.straightLightOff();
                curSequence = TICS.lightSequences.NORTH_SOUTH_TURN;
                break;
            default:
                System.out.println("Emergency Vehicle Timing Plan Sequence missing");
        }
        return curSequence;
    }

    /**
     *
     * @param seconds
     */
    private void waitLength(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {

        }
    }
}
