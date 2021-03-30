import java.sql.SQLOutput;

public class LogicCar {
    int ticksApplied;
    int laps;
    int pits; //how many pit decisions we've been past?
    float tyreDistance;
    float distance;
    float[] lapSpeeds;
    float pitSpeed;
    LogicTrack lTrack;
    boolean pittingThisLap;

    LogicCar(LogicTrack logicTrack, float distance, float[] lapSpeeds, float pitSpeed){
        this.distance = distance;
        this.lapSpeeds = lapSpeeds;
        this.pitSpeed = pitSpeed;
        this.lTrack = logicTrack;
        tyreDistance = 0;
        laps = 0;
        pits = 0;
        pittingThisLap = false;
        ticksApplied = 0;
    }

    void advance(int ticks){
        for (int i = 0; i < ticks; i++){
            //MOVE
            if(pittingThisLap && checkInPits(distance)){
                distance += pitSpeed; //no tyre wear in the pits
            }
            else{
                distance += lapSpeeds[(int) (tyreDistance/(float)lTrack.lapDistance)];
                tyreDistance += lapSpeeds[(int) (tyreDistance/(float)lTrack.lapDistance)]; //tyre wear
            }
            ticksApplied++;

            //CHECK
            if(distance > lTrack.raceDistance){
                System.out.println("I finish at t= " + ticks);

            }
            else if(distance > lTrack.lapStarts[laps+1]){
                laps++;
            }
        }
    }

    boolean checkInPits(float d) {
        return d >= lTrack.pitStarts[laps] && d < lTrack.pitEnds[laps];
    }


}
