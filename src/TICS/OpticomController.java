package TICS;

public class OpticomController {
    private RoadController northSouth, eastWest;
    private Constants constants;
    private OpticomReceiver northSouthLanes, eastWestLanes;
    public OpticomController(RoadController northSouth, RoadController eastWest){
        this.northSouth = northSouth;
        this.eastWest = eastWest;
        constants = new Constants();
        northSouthLanes = new OpticomReceiver(northSouth.getLanes());
        eastWestLanes = new OpticomReceiver(eastWest.getLanes());
    }

    public boolean isEmergencyVehicleInLane(TICS.lightSequences lane){
        switch(lane){
            case EAST_WEST:
                return eastWestLanes.checkForEmergencyVehicle() == OpticomReceiver.emergency.STRAIGHT;
            case EAST_WEST_TURN:
                return eastWestLanes.checkForEmergencyVehicle() == OpticomReceiver.emergency.TURN;
            case NORTH_SOUTH_TURN:
                return northSouthLanes.checkForEmergencyVehicle() == OpticomReceiver.emergency.TURN;
            case NORTH_SOUTH:
                return northSouthLanes.checkForEmergencyVehicle() == OpticomReceiver.emergency.STRAIGHT;
                default:
                    return false;
        }
    }
    /**
     *
     * @param curSequence
     * @param emergency
     * @param isNorthSouth
     * @return
     */
    private TICS.lightSequences setCurSequence(TICS.lightSequences curSequence, OpticomReceiver.emergency emergency, boolean isNorthSouth) {
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
        curSequence = setCurSequence(curSequence,northSouthLanes.checkForEmergencyVehicle(), true);
        curSequence = setCurSequence(curSequence,eastWestLanes.checkForEmergencyVehicle(), false);
        return curSequence;
    }

}
