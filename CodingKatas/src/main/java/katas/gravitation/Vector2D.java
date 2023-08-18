package katas.gravitation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vector2D {
    private double x, y;


    public double getLength() {
        return Math.sqrt(x*x+y*y);
    }

    public void normalizeToLength(double newLength) {
        double length = getLength();
        if(length==0) return;
        x = newLength * (x / length);
        y=  newLength * (y / length);
    }

    public void add(Vector2D vectorDeltaV) {
        double dx = vectorDeltaV.getX();
        double dy = vectorDeltaV.getY();
        x += dx;
        y += dy;
    }

}
