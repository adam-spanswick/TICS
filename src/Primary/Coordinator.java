package Primary;

import java.util.ArrayList;
import java.util.Timer;

public class Coordinator implements Runnable {
    private SignalColor north_south_color, east_west_color;
    private ArrayList<Lanes> north_south = new ArrayList<>();
    private ArrayList<Lanes> east_west = new ArrayList<>();
    private Boolean running = true;
    // opticom receiver object
    // inductive loop object
    // Emergency box object

    // Road 1 object
    // Road 2 object

    @Override
    public void run() {

        for(Lanes l: Lanes.values())
        {
            if(l.toString().contains("N") || l.toString().contains("S")){
                north_south.add(l);
            } else {
                east_west.add(l);
            }
        }

        while(running) {
            // Timing Mode Logic
            // If no opticom signal received or no emergency key detected => Enter normal mode
            // Else if opticom signal received => Enter emergency vehicle timing plan
            // Else if Emergency key turned => Enter emergency timing plan
            // If system failure => Enter system failure timing mode
        }
    }

    private void NormalMode() {

    }

    private void EmergencyMode() {

    }

    private void EmerVehicleMode() {

    }

    private void SysFailureMode() {

    }

    public static void main(String[] args) {
        Coordinator sim = new Coordinator();
        sim.run();
    }
}
