package Primary;

import java.util.ArrayList;
import java.util.Timer;

public class Coordinator implements Runnable {
    private enum lightSequences{
        NORTH_SOUTH_TURN,
        NORTH_SOUTH,
        EAST_WEST_TURN,
        EAST_WEST
    }
    private SignalColor north_south_color, east_west_color;
    private ArrayList<Lanes> north_south = new ArrayList<>();
    private ArrayList<Lanes> east_west = new ArrayList<>();
    private Boolean running = true;
    private lightSequences curSequence;
    private final int TURN_LIGHT_LENGTH = 3;
    private final int  STRAIGHT_LIGHT_LENGTH = 5;
    // opticom receiver object
    // inductive loop object
    // Emergency box object

    // Road 1 object
    private Road northSouth;
    // Road 2 object
    private Road eastWest;

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
        curSequence = lightSequences.NORTH_SOUTH_TURN;
        northSouth.setRoads(north_south);
        northSouth.setLightColor(SignalColor.RED);
        eastWest.setRoads(east_west);
        eastWest.setLightColor(SignalColor.RED);

        while(running) {
            // Timing Mode Logic
            // If no opticom signal received or no emergency key detected => Enter normal mode
            // Else if opticom signal received => Enter emergency vehicle timing plan
            // Else if Emergency key turned => Enter emergency timing plan
            // If system failure => Enter system failure timing mode
            switch (curSequence){
                case NORTH_SOUTH_TURN:
                    // set light
                    // wait light duration
                    curSequence = lightSequences.NORTH_SOUTH;
                    break;
                case NORTH_SOUTH:
                    curSequence = lightSequences.EAST_WEST_TURN;
                    break;
                case EAST_WEST_TURN:
                    curSequence = lightSequences.EAST_WEST;
                    break;
                case EAST_WEST:
                    curSequence = lightSequences.NORTH_SOUTH_TURN;
                    break;
                    default:
                        System.out.println("light sequence missing");
            }

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
