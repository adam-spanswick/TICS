package Primary;

import java.util.ArrayList;

public class Road {
    private ArrayList<Lanes> lanes = new ArrayList<>();
    private SignalColor lightColor;

    public void setRoads(ArrayList<Lanes> lanes){
        this.lanes = lanes;
    }

    public ArrayList<Lanes> getRoads(){
        return this.lanes;
    }

    public void setLightColor(SignalColor color){
        this.lightColor = color;
    }

    public SignalColor getLightColor(){
        return this.lightColor;
    }
}
