package TICS;

import Primary.Lanes;
import Primary.Lights;
import Primary.SignalColor;

import java.util.ArrayList;

public class RoadController {

    private ArrayList<Lanes> lanes = new ArrayList<>();
    private Light light;
    private Crosswalk xWalk;
    private InductionLoop loop;

    /**
     * setRoads initializes the roads, lights, crosswalks and induction loops.
     * @param lanes: list of lanes for the roads
     */
    public void setRoads(ArrayList<Lanes> lanes){
        this.lanes = lanes;
        setLights();
        setCrosswalks();
        setLoop();
    }

    /**
     * setLights initializes the lights.
     */
    public void setLights(){
        light = new Light(lanes);
    }

    /**
     * setCrosswalks initializes the crosswalks.
     */
    public void setCrosswalks(){
        xWalk = new Crosswalk(lanes);
    }

    /**
     * setLoop initializes the induction loops.
     */
    public void setLoop(){
        loop = new InductionLoop(lanes);
    }

    /**
     * setTurnLightOn tells the light object to turn the turn lights to green.
     */
    public void turnLightOn(){
        light.turnLightOn();
    }

    /**
     * turnlightOff tells the light object to turn the turn lights to yellow then red.
     */
    public void turnLightOff(){
        light.turnLightOff();
    }

    /**
     * setAllLights tells the light object to turn all the lights to a specific color.
     * @param color: color to set the lights to.
     */
    public void setAllLights(SignalColor color){
        light.setAllLights(color);
    }

    /**
     * setTurnLightColor tells the lights object to set just the turn lights to a specific color.
     * @param color: color to set the turn lights to.
     */
    public void setTurnLightColor(SignalColor color){
        light.setTurnLightColor(color);
    }

    /**
     * straightLightOn tells the light object and crosswalk object to set the straight lights and the corresponding crosswalks to walk.
     */
    public void straightLightOn(){
        light.straightLightOn();
        xWalk.setCrosswalks(SignalColor.GREEN);
    }

    /**
     * straightLightOff tells the light object and crosswalk object to set the straight lights to yellow then red and the corresponding crosswalks to don't walk.
     */
    public void straightLightOff(){
        xWalk.setCrosswalks(SignalColor.RED);
        light.straightLightOff();
    }

    /**
     * setStraightLightColor tells the light object to set the straight lights to a specific color.
     * @param color: color to set the straight lights to.
     */
    public void setStraightLightColor(SignalColor color){
        light.setStraightLightColor(color);
    }

    /**
     * anCarsInTurnLane ask the crosswalk object if there are cars in the turn lanes.
     * @return: true if there are cars in the turn lanes.
     */
    public boolean anyCarsInTurnLane(){
        return loop.anyCarsInTurnLane();
    }

    /**
     * getLanes returns the current directions lanes.
     * @return
     */
    public ArrayList<Lanes> getLanes(){
        return lanes;
    }
}
