import processing.core.PApplet;
import processing.core.PVector;

public class Car {
    PApplet p;
    float d;
    int vertex;

    float speed;
    float pitSpeed;

    PVector[] trackPath;
    int trackPathCount;
    PVector[] pitPath;
    int pitPathCount;

    int pitStart;
    int pitEnd;

    boolean pitting;
    int colour;

    float stopDistance;

    boolean move;

    Car(PApplet p, Track t, int colour){
        this.p = p;
        this.colour = colour;
        this.stopDistance = 1000;
        this.pitStart = t.pitStart;
        this.pitEnd = t.pitEnd;
        trackPath = t.trackPoints;
        trackPathCount = t.trackPointCount;
        pitPath = new PVector[trackPathCount];
        move = false;
        int index = 0;
        for(int i = 0; i<trackPathCount; i++){
            if (i >= t.pitStart && i<=t.pitEnd){
                index = i - t.pitStart;
                pitPath[i] = t.pitPoints[index];
            }
            else{
                pitPath[i] = trackPath[i];
            }
        }

        pitPathCount = trackPathCount; // expecting them to have same number of nodes, such that two cars at d = x will always be in same place
        speed = 0.1f;
        pitSpeed = 0.05f;
        d = 0;
        vertex = 0;
        pitting = false;

    }

    void updateStopDistance(float maxd){
        stopDistance = maxd;
    }

    void display(){

        if (move && d < stopDistance) {
            if (pitting && vertex % trackPathCount >= pitStart && vertex % trackPathCount < pitEnd) {
                d += pitSpeed;
            } else {
                d += speed;
            }
            if (d > vertex + 1) {
                vertex++;
            }
        }
        int va = vertex%trackPathCount; //v1 v2 are the same track point for some reason.
        int v1 = (vertex+1)%trackPathCount;
        int v2 = (vertex+2)%trackPathCount;
        int v3 = (vertex+3)%trackPathCount;

        p.fill(255);

        float x,y;
        if(pitting){
            x = p.curvePoint(pitPath[va].x, pitPath[v1].x, pitPath[v2].x, pitPath[v3].x, d-vertex);
            y = p.curvePoint(pitPath[va].y, pitPath[v1].y, pitPath[v2].y, pitPath[v3].y, d-vertex);
            /*p.ellipse(pitPath[va].x,pitPath[va].y,3,3);
            p.ellipse(pitPath[v1].x,pitPath[v1].y,3,3);
            p.ellipse(pitPath[v2].x,pitPath[v2].y,3,3);
            p.ellipse(pitPath[v3].x,pitPath[v3].y,3,3);*/
        }
        else{
            x = p.curvePoint(trackPath[va].x, trackPath[v1].x, trackPath[v2].x, trackPath[v3].x, d-vertex);
            y = p.curvePoint(trackPath[va].y, trackPath[v1].y, trackPath[v2].y, trackPath[v3].y, d-vertex);
            /*p.ellipse(trackPath[va].x,trackPath[va].y,3,3);
            p.ellipse(trackPath[v1].x,trackPath[v1].y,3,3);
            p.ellipse(trackPath[v2].x,trackPath[v2].y,3,3);
            p.ellipse(trackPath[v3].x,trackPath[v3].y,3,3);*/
        }

        setFill(colour);
        p.strokeWeight(1);
        p.ellipse(x, y, 10, 10);
        p.text(vertex,20,20 + ((colour&0xff0000) >0 ?0:30));
        p.text(d,50,20 + ((colour&0xff0000) >0 ?0:30));

    }

    void setFill(int c){
        int R = c & 0xFF0000;
        int G = c & 0x00FF00;
        int B = c & 0x0000FF;
        p.fill(R,G,B);
    }
}
