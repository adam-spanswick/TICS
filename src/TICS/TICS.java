package TICS;

import Primary.Lanes;
import Primary.SignalColor;
import java.util.ArrayList;

public class TICS implements Runnable {

    public enum lightSequences {
        NORTH_SOUTH_TURN,
        NORTH_SOUTH,
        EAST_WEST_TURN,
        EAST_WEST
    }
    
    private ArrayList<Lanes> north_south = new ArrayList<>();
    private ArrayList<Lanes> east_west = new ArrayList<>();
    private Boolean noEmergency;
    private Boolean SysFail;
    private OpticomController opticomController;
    private lightSequences curSequence;
    private RoadController northSouth = new RoadController();
    private RoadController eastWest = new RoadController();
    private Constants constants;

    @Override
    public void run() {
        constants = new Constants();
        noEmergency = true;
        SysFail = false;

        // Initialize Roads
        for (Lanes l : Lanes.values()) {
            if (l.toString().contains("N") || l.toString().contains("S")) {
                north_south.add(l);
            } else {
                east_west.add(l);
            }
        }
        // Set all lights to red and choose north/south as starting road.
        curSequence = lightSequences.NORTH_SOUTH_TURN;
        northSouth.setRoads(north_south);
        northSouth.setAllLights(SignalColor.RED);
        eastWest.setRoads(east_west);
        eastWest.setAllLights(SignalColor.RED);
        opticomController = new OpticomController(northSouth,eastWest);

        // Normal, Emergency Vehicle, and System Failure Timing Plans
        while (true) {
            if (noEmergency && !SysFail) {
                System.out.println("Normal Operation Timing Plan");
                normalSequence();

                waitLength(constants.TIME_BETWEEN_SEQS);

                curSequence = opticomController.checkForEmergency(curSequence);
            } else{
                if (!noEmergency && !SysFail){
                    System.out.println("Emergency Vehicle Timing Plan");
                    runEmergencySequence();

                    noEmergency = true;

                    waitLength(constants.TIME_BETWEEN_SEQS);
                }
                if(SysFail){
                    System.out.println("System Failure Timing Plan");
                    systemFailSeq();
                    waitLength(constants.TIME_BETWEEN_SEQS);
                }
            }
        }
    }

    /**
     * emergencyVehicleSeq puts the TICS into the emergency vehicle timing plan.
     */
    public void  runEmergencySequence(){
        switch (curSequence) {
            case NORTH_SOUTH_TURN:
                // Turn Light To Green
                northSouth.turnLightOn();

                // Wait For Light Duration
                while(opticomController.isEmergencyVehicleInLane(curSequence)) {
                    waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH);
                }
                waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH*2);
                // Turn Light to Yellow then Red
                northSouth.turnLightOff();

                // Set Next Sequence
                curSequence = TICS.lightSequences.NORTH_SOUTH;
                break;
            case NORTH_SOUTH:
                // Straight Lights to Green
                northSouth.straightLightOn();

                // Wait For Light Duration
                while(opticomController.isEmergencyVehicleInLane(curSequence)) {
                    waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH);
                }
                waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH*2);
                // Straight Lights Yellow then Red
                northSouth.straightLightOff();
                curSequence = TICS.lightSequences.EAST_WEST_TURN;
                break;
            case EAST_WEST_TURN:
                // Turn Light To Green
                eastWest.turnLightOn();

                // Wait For Light Duration
                while(opticomController.isEmergencyVehicleInLane(curSequence)) {
                    waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH);
                }
                waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH*2);
                // Turn Light to Yellow then Red
                eastWest.turnLightOff();

                // Set Next Sequence
                curSequence = TICS.lightSequences.EAST_WEST;
                break;
            case EAST_WEST:
                // Straight Lights to Green
                eastWest.straightLightOn();

                // Wait For Light Duration
                while(opticomController.isEmergencyVehicleInLane(curSequence)) {
                    waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH);
                }
                waitLength(constants.EMERGENCY_VEHICLE_LIGHT_LENGTH*2);
                // Straight Lights Yellow then Red
                eastWest.straightLightOff();
                curSequence = TICS.lightSequences.NORTH_SOUTH_TURN;
                break;
            default:
                System.out.println("Emergency Vehicle Timing Plan Sequence missing");
        }
    }

    /**
     * waitLength determines how long the systems should wait when changing light phases, crosswalk phases, roads
     * and checks for emergency vehicle signals.
     * @param seconds time to wait
     */
    private void waitLength(double seconds) {
        int time = (int)(seconds * 1000);

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    /**
     * waitLength determines how long the systems should wait when changing light phases, crosswalk phases, roads
     * and checks for emergency vehicle signals.
     * @param seconds time to wait
     */
    private void waitLength(int seconds) {
        int count = 0;
        boolean curEmergency;
        lightSequences newSequence;
        while (count < seconds*4) {
            newSequence = opticomController.checkForEmergency(curSequence);
            if(newSequence != curSequence){
                curSequence = newSequence;
                curEmergency = true;
            }else{
                curEmergency = false;
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {

            }

            if (curEmergency) {
                noEmergency = false;
                break;
            }
            count++;
        }
    }

    /**
     * systemFailSeq sets the lights to flashing yellow.
     */
    public void systemFailSeq() {
        northSouth.setStraightLightColor(SignalColor.YELLOW);
        northSouth.setTurnLightColor(SignalColor.YELLOW);
        eastWest.setStraightLightColor(SignalColor.YELLOW);
        eastWest.setTurnLightColor(SignalColor.YELLOW);

        waitLength(constants.SYSTEM_FAIL_TIME);
        if(!SysFail){return;}

        northSouth.setStraightLightColor(SignalColor.BLACK);
        northSouth.setTurnLightColor(SignalColor.BLACK);

        waitLength(constants.SYSTEM_FAIL_TIME);
        if(!SysFail){return;}

        northSouth.setStraightLightColor(SignalColor.YELLOW);
        northSouth.setTurnLightColor(SignalColor.YELLOW);

        waitLength(constants.SYSTEM_FAIL_TIME);
        if(!SysFail){return;}

        eastWest.setStraightLightColor(SignalColor.BLACK);
        eastWest.setTurnLightColor(SignalColor.BLACK);

        waitLength(constants.SYSTEM_FAIL_TIME);
    }

    /**
     * setSysFail puts the TICS into system failure mode when the system failure button is pressed.
     */
    public void setSysFail(){
        System.out.println("System Failure Detected");
        noEmergency = true;
        SysFail = true;
    }

    /**
     * cancelSysFail puts the TICS back into the normal timing plan when the cancel system failure button is pressed.
     */
    public void cancelSysFail() {
        System.out.println("TICS back online");
        noEmergency = true;
        SysFail = false;

        northSouth.setStraightLightColor(SignalColor.RED);
        northSouth.setTurnLightColor(SignalColor.RED);
        eastWest.setStraightLightColor(SignalColor.RED);
        eastWest.setTurnLightColor(SignalColor.RED);

        curSequence = lightSequences.NORTH_SOUTH_TURN;
    }


    /**
     * normalSequence puts the TICS into the normal operation timing plan.
     */
    private void normalSequence() {
        switch (curSequence) {
            case NORTH_SOUTH_TURN:
                // Turn Light To Green
                if (northSouth.anyCarsInTurnLane()) {
                    northSouth.turnLightOn();

                    // Wait For Light Duration
                    waitLength(constants.TURN_LIGHT_LENGTH);

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
                waitLength(constants.STRAIGHT_LIGHT_LENGTH);

                // Straight Lights Yellow then Red
                northSouth.straightLightOff();
                curSequence = lightSequences.EAST_WEST_TURN;
                break;
            case EAST_WEST_TURN:
                // Turn Light To Green
                if (eastWest.anyCarsInTurnLane()) {
                    eastWest.turnLightOn();

                    // Wait For Light Duration
                    waitLength(constants.TURN_LIGHT_LENGTH);

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
                waitLength(constants.STRAIGHT_LIGHT_LENGTH);

                // Straight Lights Yellow then Red
                eastWest.straightLightOff();
                curSequence = lightSequences.NORTH_SOUTH_TURN;
                break;
            default:
                System.out.println("Normal Operation Timing Plan Sequence Missing");
        }
    }

    public static void main(String[] args) {
        TICS sim = new TICS();
        sim.run();
    }
}
