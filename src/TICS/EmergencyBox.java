package TICS;

public class EmergencyBox {
    private Road northSouth, eastWest;
    private Constants constants;
    public EmergencyBox(Road northSouth, Road eastWest){
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
