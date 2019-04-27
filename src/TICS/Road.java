package TICS;

import Primary.Lanes;
import Primary.Lights;
import Primary.SignalColor;

import java.util.ArrayList;

public class Road {
    public enum emergency{
        STRAIGHT,
        TURN,
        NONE
    }

    private ArrayList<Lanes> lanes = new ArrayList<>();
    private final int YELLOW_LIGHT_LENGTH = 2;

    public void setRoads(ArrayList<Lanes> lanes){
        this.lanes = lanes;
    }

    private void waitLength(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){}
    }

    public boolean anyCarsInTurnLane(){
        return (lanes.get(0).isCarOnLane() || lanes.get(3).isCarOnLane());
    }

    public void turnLightOn(){
        setTurnLightColor(SignalColor.GREEN);
    }

    public void turnLightOff(){
        // turn lights yellow
        setTurnLightColor(SignalColor.YELLOW);

        waitLength(YELLOW_LIGHT_LENGTH);

        // turn lights red
        setTurnLightColor(SignalColor.RED);
    }

    public void setTurnLightColor(SignalColor color){
        lanes.get(0).setColor(color);
        lanes.get(3).setColor(color);
    }

    public emergency checkForEmergancyVehicle(){
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

    public void straightLightOn(){
        // Set Stop Lights
        setStraightLightColor(SignalColor.GREEN);

        //Set Crosswalks
        setCrosswalks(SignalColor.GREEN);
    }

    public void straightLightOff(){
        // turn crosswalks to don't walk
        setCrosswalks(SignalColor.RED);

        // turn lights yellow
        setStraightLightColor(SignalColor.YELLOW);

        waitLength(YELLOW_LIGHT_LENGTH);

        // turn lights red
        setStraightLightColor(SignalColor.RED);
    }

    private void setCrosswalks(SignalColor color){
        if(lanes.get(1).toString().contains("N")) {
            Lights.EAST.setColor(color);
            Lights.WEST.setColor(color);
        }else{
            Lights.NORTH.setColor(color);
            Lights.SOUTH.setColor(color);
        }
    }

    public void setStraightLightColor(SignalColor color){
        lanes.get(1).setColor(color);
        lanes.get(2).setColor(color);
        lanes.get(4).setColor(color);
        lanes.get(5).setColor(color);
    }

    public void setAllLights(SignalColor color){
        for(Lanes l: lanes){
            l.setColor(color);
        }
    }
}
