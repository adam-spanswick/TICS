package TICS;

public class EmergencyBox {
    private RoadController northSouth, eastWest;
    private Constants constants;

    /**
     * EmergencyBox initializes the emergency box and sets the roads based on direction.
     * @param northSouth
     * @param eastWest
     */
    public EmergencyBox(RoadController northSouth, RoadController eastWest){
        this.northSouth = northSouth;
        this.eastWest = eastWest;
        constants = new Constants();
    }

    /**
     * checkForEmergency checks if the emergency key is turned in the emergency box.
     * @return: true if the emergency key is turned.
     */
    public boolean checkForEmergancy(){
        return false;
    }

    /**
     * runSequence starts the emergency timing plane.
     */
    public void runSequence(){

    }
}
