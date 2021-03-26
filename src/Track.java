import processing.core.*;



public class Track {
    PVector[] trackPoints;
    PVector[] pitPoints;
    PApplet p;
    int trackPointCount;
    int pitPointCount;

    int pitStart;
    int pitEnd;

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
            trackPoints[i].add(new PVector(p.random(-15,15),p.random(-15,15)));
        }

        pitPointCount = trackPointCount/2 - trackPointCount/5 +1;
        PApplet.println(pitPointCount);
        pitPoints = new PVector[pitPointCount];
        pitPoints[0] = trackPoints[trackPointCount/10].copy();
        pitPoints[1] = PVector.add(pitPoints[0], new PVector(-40,20));
        pitPoints[pitPointCount-1] = trackPoints[trackPointCount/2 - trackPointCount/10].copy();
        pitPoints[pitPointCount-2] = PVector.add(pitPoints[pitPointCount-1], new PVector(40,20));
        for(int i = 2; i < pitPointCount -2; i++){
            pitPoints[i] = new PVector(PApplet.map(i,1,pitPointCount-2, pitPoints[1].x,pitPoints[pitPointCount-2].x),PApplet.map(i,2,pitPointCount-1, pitPoints[1].y,pitPoints[pitPointCount-2].y));
        }
        pitStart = trackPointCount/10;
        pitEnd = trackPointCount/2 - trackPointCount/10;
        PApplet.println(pitStart);
        PApplet.println(pitEnd);
    }

    public void display()
    {
        //t+=0.1;
        //if (t>v+1){v++;}
        p.noFill();
        p.strokeWeight(4);
        p.stroke(200,0,0);
        //p.curveTightness(0.6f);
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

        /*int va = v%trackPointCount;
        int v1 = (v+1)%trackPointCount;
        int v2 = (v+2)%trackPointCount;
        int v3 = (v+3)%trackPointCount;

        p.fill(255);
        p.ellipse(trackPoints[va].x,trackPoints[va].y,3,3);
        p.ellipse(trackPoints[v1].x,trackPoints[v1].y,3,3);
        p.ellipse(trackPoints[v2].x,trackPoints[v2].y,3,3);
        p.ellipse(trackPoints[v3].x,trackPoints[v3].y,3,3);

        float x = p.curvePoint(trackPoints[va].x, trackPoints[v1].x, trackPoints[v2].x, trackPoints[v3].x, t-v);
        float y = p.curvePoint(trackPoints[va].y, trackPoints[v1].y, trackPoints[v2].y, trackPoints[v3].y, t-v);

        p.fill(255,0,0);
        p.strokeWeight(1);
        p.ellipse(x, y, 10, 10);
        p.text(t,20,20);*/
    }

    void drawLine(PVector a, PVector b){
        p.line(a.x,a.y,b.x,b.y);
    }
}
