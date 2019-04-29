package TICS;

import Primary.Lanes;
import Primary.Lights;
import Primary.SignalColor;

import java.util.ArrayList;

public class Crosswalk {
    private ArrayList<Lanes> lanes;

    /**
     * Crosswalk initializes the crosswalk with the lanes.
     * @param lanes: the current lanes.
     */
    public Crosswalk(ArrayList<Lanes> lanes){
        this.lanes = lanes;
    }

    /**
     * setCrosswalks: sets the current directions crosswalks to a specific color.
     * @param color to set the crosswalks to.
     */
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
