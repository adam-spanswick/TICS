package TICS;

import Primary.Lanes;
import Primary.SignalColor;

import java.util.ArrayList;

public class Light {
    private ArrayList<Lanes> lanes;
    private final int YELLOW_LIGHT_LENGTH = 2;

    public Light(ArrayList<Lanes> lanes){
        this.lanes = lanes;
    }

    private void waitLength(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){}
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

    public void setAllLights(SignalColor color){
        for(Lanes l: lanes){
            l.setColor(color);
        }
    }

    public void setTurnLightColor(SignalColor color){
        lanes.get(0).setColor(color);
        lanes.get(3).setColor(color);
    }

    public void straightLightOn(){
        setStraightLightColor(SignalColor.GREEN);
    }

    public void straightLightOff(){
        // turn lights yellow
        setStraightLightColor(SignalColor.YELLOW);

        waitLength(YELLOW_LIGHT_LENGTH);

        // turn lights red
        setStraightLightColor(SignalColor.RED);
    }

    public void setStraightLightColor(SignalColor color){
        lanes.get(1).setColor(color);
        lanes.get(2).setColor(color);
        lanes.get(4).setColor(color);
        lanes.get(5).setColor(color);
    }
}
