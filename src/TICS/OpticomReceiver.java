package TICS;
import Primary.Lanes;

import java.util.ArrayList;

public class OpticomReceiver {
    public enum emergency{
        STRAIGHT,
        TURN,
        NONE
    }
    private ArrayList<Lanes> lanes;
    public OpticomReceiver(ArrayList<Lanes> lanes){
        this.lanes = lanes;
    }
    public emergency checkForEmergencyVehicle(){
        if(emergencyInStraight()){
            return emergency.STRAIGHT;
        }else if (emergencyInTurn()){
            return emergency.TURN;
        }else {
            return emergency.NONE;
        }
    }

    private boolean emergencyInStraight(){
        return lanes.get(1).getEmergencyOnLane() || lanes.get(2).getEmergencyOnLane() ||
                lanes.get(4).getEmergencyOnLane() || lanes.get(5).getEmergencyOnLane();
    }

    private boolean emergencyInTurn(){
        return lanes.get(0).getEmergencyOnLane() || lanes.get(3).getEmergencyOnLane();
    }
}
