package TICS;

import Primary.Lanes;
import Primary.Lights;
import Primary.SignalColor;

import java.util.ArrayList;

public class Crosswalk {
    private ArrayList<Lanes> lanes;

    public Crosswalk(ArrayList<Lanes> lanes){
        this.lanes = lanes;
    }

    public void setCrosswalks(SignalColor color){
        if(lanes.get(1).toString().contains("N")) {
            Lights.EAST.setColor(color);
            Lights.WEST.setColor(color);
        }else{
            Lights.NORTH.setColor(color);
            Lights.SOUTH.setColor(color);
        }
    }
}
