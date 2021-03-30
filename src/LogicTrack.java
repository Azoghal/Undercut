public class LogicTrack {
    int laps;
    int lapDistance;
    int raceDistance;
    int pitDistance;
    int pitStart;
    int pitEnd;
    int pitDecisionPoint;
    int[] lapStarts;
    int[] pitStarts;
    int[] pitEnds;
    int[] pitDecisionPoints;

    LogicTrack(){
        lapDistance = 30;
        laps = 5;
        raceDistance = lapDistance * laps;
        pitDistance = 6;
        pitStart = 24;
        pitEnd = 30;
        pitDecisionPoint = 20;
        pitDecisionPoints = new int[laps];
        lapStarts = new int[laps+1];
        pitStarts = new int[laps];
        pitEnds = new int[laps];
        for(int i=0; i < laps; i++){
            pitDecisionPoints[i] = i*lapDistance + pitDecisionPoint;
            lapStarts[i] = i*lapDistance;
            pitStarts[i] = lapStarts[i] + pitStart;
            pitEnds[i] = lapStarts[i] + pitEnd;
            //System.out.println(pitDecisionPoints[i]);
        }
        lapStarts[laps] = raceDistance;
    }
}
