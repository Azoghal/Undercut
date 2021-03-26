import processing.core.*;



public class Track {
    PVector[] trackPoints;
    PVector[] pitPoints;
    PApplet p;
    int trackPointCount;
    int pitPointCount;

    PVector pitStart;
    PVector pitEnd;

    float t = 0;
    int v = 0;

    public Track(PApplet p) {
        this.p = p;
        generateTrack();
    }

    void generateTrack(){
        trackPointCount = 30;
        int degreeSeperation = 360/trackPointCount;
        PVector centre = new PVector(p.width/2,p.height/2);
        int radius = 200;
        trackPoints = new PVector[trackPointCount];
        for(int i = 0; i <trackPointCount; i++){
            trackPoints[i] = new PVector(PApplet.cos(PApplet.radians(i*degreeSeperation)),PApplet.sin(PApplet.radians(i*degreeSeperation)));
            trackPoints[i].mult(radius);
            trackPoints[i].add(centre);
            trackPoints[i].add(new PVector(p.random(-10,10),p.random(-10,10)));
        }

        pitPointCount = 4;
        pitPoints = new PVector[4];
        pitPoints[0] = trackPoints[trackPointCount/10].copy();
        pitPoints[1] = PVector.add(pitPoints[0], new PVector(-40,20));
        pitPoints[3] = trackPoints[trackPointCount/2 - trackPointCount/10].copy();
        pitPoints[2] = PVector.add(pitPoints[3], new PVector(40,20));
        pitStart = pitPoints[0];
        pitEnd = pitPoints[3];
    }

    public void display()
    {
        t+=0.1;
        if (t>=1){t=0;v++;}
        p.noFill();
        p.strokeWeight(4);
        p.stroke(200,0,0);
        p.curveTightness(0.6f);
        p.beginShape();
        p.curveVertex(pitPoints[0].x,pitPoints[0].y);
        for(int i = 0; i < pitPointCount; i++){ //-1
            //p.strokeWeight(PApplet.map(i,0,pointCount,5,2));
            //drawLine(pitPoints[i],pitPoints[i+1]);
            PVector point = pitPoints[i];
            p.curveVertex(point.x,point.y);
        }
        p.curveVertex(pitPoints[pitPointCount-1].x,pitPoints[pitPointCount-1].y);
        p.endShape();
        p.strokeWeight(6);
        p.stroke(0);
        p.curveTightness(0);
        p.beginShape();
        p.curveVertex(trackPoints[trackPointCount-1].x,trackPoints[trackPointCount-1].y);
        for(int i = 0; i < trackPointCount; i++){ //-1
            //p.strokeWeight(PApplet.map(i,0,pointCount,5,2));
            //drawLine(trackPoints[i],trackPoints[i+1]);
            PVector point = trackPoints[i];
            p.curveVertex(point.x,point.y);
        }
        p.curveVertex(trackPoints[0].x,trackPoints[0].y);
        p.curveVertex(trackPoints[1].x,trackPoints[1].y);
        //drawLine(trackPoints[trackPointCount-1],trackPoints[0]);
        p.endShape();

        v = v%trackPointCount;
        int v1 = (v+1)%trackPointCount;
        int v2 = (v+2)%trackPointCount;
        int v3 = (v+3)%trackPointCount;

        p.fill(255);
        p.ellipse(trackPoints[v].x,trackPoints[v].y,3,3);
        p.ellipse(trackPoints[v1].x,trackPoints[v1].y,3,3);
        p.ellipse(trackPoints[v2].x,trackPoints[v2].y,3,3);
        p.ellipse(trackPoints[v3].x,trackPoints[v3].y,3,3);

        float x = p.curvePoint(trackPoints[v].x, trackPoints[v1].x, trackPoints[v2].x, trackPoints[v3].x, t);
        float y = p.curvePoint(trackPoints[v].y, trackPoints[v1].y, trackPoints[v2].y, trackPoints[v3].y, t);

        p.fill(255,0,0);
        p.strokeWeight(1);
        p.ellipse(x, y, 10, 10);

    }

    void drawLine(PVector a, PVector b){
        p.line(a.x,a.y,b.x,b.y);
    }
}
