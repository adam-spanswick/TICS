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
    private Boolean noEmergency;
    private lightSequences curSequence;
    private final int TURN_LIGHT_LENGTH = 3;
    private final int  STRAIGHT_LIGHT_LENGTH = 5;
    private final int TIME_BETWEEN_SEQS = 1;
    // opticom receiver object
    // inductive loop object
    // Emergency box object

    // Road 1 object
    private Road northSouth = new Road();
    // Road 2 object
    private Road eastWest = new Road();


    private void waitlength(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){}
    }

    @Override
    public void run() {
        noEmergency = true;

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
            if(noEmergency) {
                switch (curSequence) {
                    case NORTH_SOUTH_TURN:
                        // Turn Light To Green
                        if (northSouth.anyCarsInTurnLane()) {
                            northSouth.turnLightOn();

                            // Wait For Light Duration
                            waitlength(TURN_LIGHT_LENGTH);

                            // Turn Light to Yellow then Red
                            northSouth.turnLightOff();

                        }
                        // Set Next Sequence
                        curSequence = lightSequences.NORTH_SOUTH;
                        break;
                    case NORTH_SOUTH:
                        // Straight Lights to Green
                        northSouth.straightLightOn();

                        // Wait For Light Duration
                        waitlength(STRAIGHT_LIGHT_LENGTH);

                        // Straight Lights Yellow then Red
                        northSouth.straightLightOff();

                        curSequence = lightSequences.EAST_WEST_TURN;
                        break;
                    case EAST_WEST_TURN:
                        // Turn Light To Green
                        if (eastWest.anyCarsInTurnLane()) {
                            eastWest.turnLightOn();

                            // Wait For Light Duration
                            waitlength(TURN_LIGHT_LENGTH);

                            // Turn Light to Yellow then Red
                            eastWest.turnLightOff();

                        }
                        // Set Next Sequence
                        curSequence = lightSequences.EAST_WEST;
                        break;
                    case EAST_WEST:
                        // Straight Lights to Green
                        eastWest.straightLightOn();

                        // Wait For Light Duration
                        waitlength(STRAIGHT_LIGHT_LENGTH);

                        // Straight Lights Yellow then Red
                        eastWest.straightLightOff();

                        curSequence = lightSequences.NORTH_SOUTH_TURN;
                        break;
                    default:
                        System.out.println("light sequence missing");
                }
                // Wait For Light Duration
                waitlength(TIME_BETWEEN_SEQS);
            }
            // Else if opticom signal received => Enter emergency vehicle timing plan
            // Else if Emergency key turned => Enter emergency timing plan
            // If system failure => Enter system failure timing mode
        }
    }

    public static void main(String[] args) {
        Coordinator sim = new Coordinator();
        sim.run();
    }
}
