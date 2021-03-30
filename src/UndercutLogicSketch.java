
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UndercutLogicSketch {


    public static void main(String[] args) throws IOException {

        int ticks = 0;
        int raceDistance = 150;
        int lapDistance = 30;
        int pitDistance = 6;

        float[] lapSpeeds = {1.0f,0.95f,0.87f,0.77f,0.70f};
        float pitSpeed = 0.5f;

        LogicTrack LT = new LogicTrack();

        LogicCar A = new LogicCar(LT,0f,lapSpeeds,pitSpeed);
        LogicCar B = new LogicCar(LT,-1f,lapSpeeds,pitSpeed);

        boolean nextIsA = true;

        GameState current = new GameState(A,B,LT, 0);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        current.displayGameState();
        boolean turnIsA;

        while(true){
            String in = reader.readLine();
            if (in.equals("q")) {
                System.exit(0);
            }
            if(in.equals("start")){
                turnIsA = current.nextTurn();
                current.displayGameState();
                System.out.println((turnIsA?"A's":"B's") + " decision to pit: (Y/N)");
            }
            if(in.equals("c")){
                turnIsA = current.nextTurn(); //consider correcting the distances to mitigate innacuracy.
                current.displayGameState();
                System.out.println((turnIsA?"A's":"B's") + " decision to pit: (Y/N)");
            }
            //System.out.println(in);
        }
    }




}
