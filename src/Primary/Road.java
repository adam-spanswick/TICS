package Primary;

import java.util.ArrayList;

public class Road {

    private ArrayList<Lanes> lanes = new ArrayList<>();
    private SignalColor lightColor;
    private final int YELLOW_LIGHT_LENGTH = 1;

    public void setRoads(ArrayList<Lanes> lanes){
        this.lanes = lanes;
    }
    private void waitlength(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){}
    }

    public void turnLightOn(){
        // turn lights green
    }

    public void turnLightOff(){
        // turn lights yellow
        waitlength(YELLOW_LIGHT_LENGTH);
        // turn lights red
    }

    public void straightLightOn(){
        // turn lights green
    }

    public void straightLightOff(){
        // turn lights yellow
        waitlength(YELLOW_LIGHT_LENGTH);
        // turn lights red
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
