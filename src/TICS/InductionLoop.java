package TICS;

import Primary.Lanes;

import java.util.ArrayList;

public class InductionLoop {
    private ArrayList<Lanes> lanes = new ArrayList<>();

    public InductionLoop(ArrayList<Lanes> lanes){
        this.lanes = lanes;
    }

    public boolean anyCarsInTurnLane(){
        return (lanes.get(0).isCarOnLane() || lanes.get(3).isCarOnLane());
    }
}
