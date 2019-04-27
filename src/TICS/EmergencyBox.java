package TICS;

public class EmergencyBox {
    private RoadController northSouth, eastWest;
    private Constants constants;
    public EmergencyBox(RoadController northSouth, RoadController eastWest){
        this.northSouth = northSouth;
        this.eastWest = eastWest;
        constants = new Constants();
    }
    public boolean checkForEmergancy(){
        return false;
    }

    public void runSequence(){

    }
}
