

public class GameState {
    LogicCar A,B;
    int ticks;
    LogicTrack lTrack;

    GameState(LogicCar A, LogicCar B, LogicTrack lTrack, int ticks){
        this.A = A;
        this.B = B;
        this.ticks = ticks;
        this.lTrack = lTrack;
    }

    int calculateTicksToDistance(LogicCar c, float d){
        if(c.distance > d){return Integer.MAX_VALUE;}
        float delta = d - c.distance;
        int ticks = (int) (delta/c.lapSpeeds[c.laps]);
        return ticks;
    }



    boolean nextTurn(){
        int nextPointA = lTrack.pitDecisionPoints[A.pits];
        int nextPointB = lTrack.pitDecisionPoints[B.pits];
        int ticksA = calculateTicksToDistance(A,nextPointA);
        int ticksB = calculateTicksToDistance(B,nextPointB);
        System.out.println(ticksA);
        System.out.println(ticksB);
        if(ticksA < ticksB){
            System.out.println("advancing " + ticksA + " ticks");
            A.pits++; //we've moved past the pit decision
            A.advance(ticksA);
            B.advance(ticksA);
            ticks += ticksA;
            return true; //A's turn
        }else{
            System.out.println("advancing " + ticksB + " ticks");
            B.pits++;
            A.advance(ticksB);
            B.advance(ticksB);
            ticks += ticksB;
            return false; // B's turn;
        }
    }

    void displayGameState(){
        System.out.println();
        System.out.printf("Ticks: %d \n", ticks);
        System.out.printf("Car A \n*Laps: %d  *Distance: %f   *TyreDistance: %f   *InThisLap? %b\n",A.laps, A.distance, A.tyreDistance, A.pittingThisLap);
        System.out.printf("Car B \n*Laps: %d  *Distance: %f   *TyreDistance: %f   *InThisLap? %b\n",B.laps, B.distance, B.tyreDistance, B.pittingThisLap);

    }
}
