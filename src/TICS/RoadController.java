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

    public void setRoads(ArrayList<Lanes> lanes){
        this.lanes = lanes;
        setLights();
        setCrosswalks();
        setLoop();
    }

    public void setLights(){
        light = new Light(lanes);
    }

    public void setCrosswalks(){
        xWalk = new Crosswalk(lanes);
    }

    public void setLoop(){
        loop = new InductionLoop(lanes);
    }

    // Lights
    public void turnLightOn(){
        light.turnLightOn();
    }

    public void turnLightOff(){
        light.turnLightOff();
    }

    public void setAllLights(SignalColor color){
        light.setAllLights(color);
    }

    public void setTurnLightColor(SignalColor color){
        light.setTurnLightColor(color);
    }

    public void straightLightOn(){
        light.straightLightOn();
        xWalk.setCrosswalks(SignalColor.GREEN);
    }

    public void straightLightOff(){
        xWalk.setCrosswalks(SignalColor.RED);
        light.straightLightOff();
    }

    public void setStraightLightColor(SignalColor color){
        light.setStraightLightColor(color);
    }

    // Induction Loops

    public boolean anyCarsInTurnLane(){
        return loop.anyCarsInTurnLane();
    }

    public ArrayList<Lanes> getLanes(){
        return lanes;
    }


}
