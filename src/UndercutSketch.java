import processing.core.PApplet;

public class UndercutSketch extends PApplet {

    Track track;
    Car pitCar;
    Car trackCar;
    Car[] cars;
    boolean once = true;
    float distance;

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
        //trackCar = new Car(this,track,true);
        //pitCar = new Car(this, track,false);
        //pitCar.pitting = true;
        cars = new Car[2];
        cars[0] = new Car(this,track,0xFF0000);
        cars[1] = new Car(this,track,0x0066FF);
        distance = 60;
    }

    public void draw() {
        //noLoop();
        if(once){

            background(255);
            fill(80);
            rect(0,0,100,60);
            track.display();
            for(int i = 0; i < 2; i++){
                cars[i].display();
            }
        }
    }

    public void keyPressed(){
        //distance+=30;
        //cars[0].updateStopDistance(distance);
        //cars[1].updateStopDistance(distance);
        //cars[0].pitting = random(1) > 0.9;
        //cars[1].pitting = random(1) > 0.8;
        //trackCar.updateStopDistance(distance);
        //pitCar.updateStopDistance(distance);
        if(key == ' '){
            cars[0].move = !cars[0].move;
            cars[1].move = !cars[1].move;
        }
        else if (key == 'r'){
            cars[0].pitting = !cars[0].pitting;
        }
        else if (key == 'b'){
            cars[1].pitting = !cars[1].pitting;
        }
    }
}
