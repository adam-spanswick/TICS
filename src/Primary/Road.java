package Primary;

import java.util.ArrayList;

public class Road {

    private ArrayList<Lanes> lanes = new ArrayList<>();
    private SignalColor lightColor;
    private final int YELLOW_LIGHT_LENGTH = 1;

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
        // turn lights green
    }

    public void turnLightOff(){
        // turn lights yellow
        waitLength(YELLOW_LIGHT_LENGTH);
        // turn lights red
    }

    public void straightLightOn(){
        // turn lights green
    }

    public void straightLightOff(){
        // turn lights yellow
        waitLength(YELLOW_LIGHT_LENGTH);
        // turn lights red
    }
    public ArrayList<Lanes> getRoads(){
        return this.lanes;
    }

    public void setLightColor(SignalColor color){
        for(Lanes l: lanes){
            l.setColor(color);
        }
    }

    public SignalColor getLightColor(){
        return this.lightColor;
    }
}
