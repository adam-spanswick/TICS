package TICS;

import Primary.Lanes;

import java.util.ArrayList;

public class InductionLoop {
    private ArrayList<Lanes> lanes;

    /**
     * InductionLoop initializes the induction loop.
     * @param lanes
     */
    public InductionLoop(ArrayList<Lanes> lanes){
        this.lanes = lanes;
    }

    /**
     * anCarsInTurnLane detects if there are cars in the turn lanes.
     * @return: true if there are cars in the turn lanes.
     */
    public boolean anyCarsInTurnLane(){
        return (lanes.get(0).isCarOnLane() || lanes.get(3).isCarOnLane());
    }
}
