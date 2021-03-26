import processing.core.PApplet;

public class UndercutSketch extends PApplet {

    Track track;
    boolean once = true;

    public static void main(String[] args) {
        UndercutSketch us = new UndercutSketch();
        String[] processingArgs = {"UndercutSketch"};
        runSketch(processingArgs,us);

    }

    public void settings() {
        size(500,500);
    }

    public void start(){
        track = new Track(this);
    }

    public void draw() {
        //noLoop();
        if(once){
            background(255);
            track.display();
            //once = false;
        }
    }

    public void keyPressed(){
        once = true;
    }
}
