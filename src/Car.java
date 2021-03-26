import processing.core.PApplet;
import processing.core.PVector;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

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
    boolean red;

    Car(PApplet p, Track t, boolean red){
        this.p = p;
        this.red = red;
        this.pitStart = t.pitStart;
        this.pitEnd = t.pitEnd;
        trackPath = t.trackPoints;
        trackPathCount = t.trackPointCount;
        pitPath = new PVector[trackPathCount];
        int index = 0;
        for(int i = 0; i<trackPathCount; i++){
            if (i >= t.pitStart && i<=t.pitEnd){
                index = i - t.pitStart;
                pitPath[i] = t.pitPoints[index];
                //PApplet.println("in: " + i + " other in: " + (i - t.pitStart));
            }
            else{
                pitPath[i] = trackPath[i];
            }
        }
        /*PApplet.println(pitPath[7].x);
        PApplet.println(pitPath[8].x);
        PApplet.println(pitPath[9].x);
        PApplet.println(pitPath[10].x);
        PApplet.println(pitPath[11].x);
        PApplet.println(pitPath[12].x);
        PApplet.println(pitPath[13].x);
        PApplet.println(pitPath[14].x);*/
        PApplet.print(pitPath);
        pitPathCount = trackPathCount; // expecting them to have same number of nodes, such that two cars at d = x will always be in same place
        speed = 0.1f;
        pitSpeed = 0.05f;
        d = 0;
        vertex = 0;
        pitting = false;
    }

    void display(){
        //for(PVector pv : pitPath){
          //  p.ellipse(pv.x,pv.y,5,5);
        //}

        if(pitting && vertex%trackPathCount >= pitStart && vertex%trackPathCount < pitEnd){
            d += pitSpeed;
        }
        else{
            d+=speed;
        }
        if (d>vertex+1){vertex++;}

        int va = vertex%trackPathCount; //v1 v2 are the same track point for some reason.
        int v1 = (vertex+1)%trackPathCount;
        int v2 = (vertex+2)%trackPathCount;
        int v3 = (vertex+3)%trackPathCount;

        p.fill(255);

        float x,y;
        if(pitting){
            x = p.curvePoint(pitPath[va].x, pitPath[v1].x, pitPath[v2].x, pitPath[v3].x, d-vertex);
            y = p.curvePoint(pitPath[va].y, pitPath[v1].y, pitPath[v2].y, pitPath[v3].y, d-vertex);
            p.ellipse(pitPath[va].x,pitPath[va].y,3,3);
            p.ellipse(pitPath[v1].x,pitPath[v1].y,3,3);
            p.ellipse(pitPath[v2].x,pitPath[v2].y,3,3);
            p.ellipse(pitPath[v3].x,pitPath[v3].y,3,3);
        }
        else{
            x = p.curvePoint(trackPath[va].x, trackPath[v1].x, trackPath[v2].x, trackPath[v3].x, d-vertex);
            y = p.curvePoint(trackPath[va].y, trackPath[v1].y, trackPath[v2].y, trackPath[v3].y, d-vertex);
            p.ellipse(trackPath[va].x,trackPath[va].y,3,3);
            p.ellipse(trackPath[v1].x,trackPath[v1].y,3,3);
            p.ellipse(trackPath[v2].x,trackPath[v2].y,3,3);
            p.ellipse(trackPath[v3].x,trackPath[v3].y,3,3);
        }


        p.fill(red?255:0,red?0:255,0);
        p.strokeWeight(1);
        p.ellipse(x, y, 10, 10);
        p.text(vertex,20,20 + (red?0:30));
        p.text(d,50,20 + (red?0:30));

    }
}
