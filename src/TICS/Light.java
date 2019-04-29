package TICS;

import Primary.Lanes;
import Primary.SignalColor;

import java.util.ArrayList;

public class Light {
    private ArrayList<Lanes> lanes;
    private final int YELLOW_LIGHT_LENGTH = 2;

    /**
     * Light initialzes the light object with the lanes.
     * @param lanes: Lanes for the direction.
     */
    public Light(ArrayList<Lanes> lanes){
        this.lanes = lanes;
    }

    /**
     * waitLength waits a specified period of time to simulate light changes.
     * @param seconds: How long to wait.
     */
    private void waitLength(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){}
    }

    /**
     * turnLightOn turns the turn lights to green.
     */
    public void turnLightOn(){
        setTurnLightColor(SignalColor.GREEN);
    }

    /**
     * turnlightOff turns the turn lights to yellow then red.
     */
    public void turnLightOff(){
        setTurnLightColor(SignalColor.YELLOW);

        waitLength(YELLOW_LIGHT_LENGTH);

        setTurnLightColor(SignalColor.RED);
    }

    /**
     * setAllLights turns all the lights to a specific color.
     * @param color: color to set the lights to.
     */
    public void setAllLights(SignalColor color){
        for(Lanes l: lanes){
            l.setColor(color);
        }
    }

    /**
     * setTurnLightColor sets just the turn lights to a specific color.
     * @param color: color to set the turn lights to.
     */
    public void setTurnLightColor(SignalColor color){
        lanes.get(0).setColor(color);
        lanes.get(3).setColor(color);
    }

    /**
     * straightLightOn sets the straight lights and the corresponding crosswalks to walk.
     */
    public void straightLightOn(){
        setStraightLightColor(SignalColor.GREEN);
    }

    /**
     * straightLightOff sets the straight lights to yellow then red and the corresponding crosswalks to don't walk.
     */
    public void straightLightOff(){
        setStraightLightColor(SignalColor.YELLOW);

        waitLength(YELLOW_LIGHT_LENGTH);

        setStraightLightColor(SignalColor.RED);
    }

    /**
     * setStraightLightColor sets the straight lights to a specific color.
     * @param color: color to set the straight lights to.
     */
    public void setStraightLightColor(SignalColor color){
        lanes.get(1).setColor(color);
        lanes.get(2).setColor(color);
        lanes.get(4).setColor(color);
        lanes.get(5).setColor(color);
    }
}
