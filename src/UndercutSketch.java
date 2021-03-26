import processing.core.PApplet;

public class UndercutSketch extends PApplet {

    Track track;
    Car pitCar;
    Car trackCar;
    boolean once = true;

    public static void main(String[] args) {
        UndercutSketch us = new UndercutSketch();
        String[] processingArgs = {"UndercutSketch"};
        runSketch(processingArgs,us);
    }

    public void settings() {
        size(500,500);
        smooth();
    }

    public void start(){
        track = new Track(this);
        trackCar = new Car(this,track,true);
        pitCar = new Car(this, track,false);
        pitCar.pitting = true;
    }

    public void draw() {
        //noLoop();
        if(once){
            background(255);
            track.display();
            trackCar.display();
            pitCar.display();
            //once = false;
        }
    }

    public void keyPressed(){
        once = true;
    }
}
