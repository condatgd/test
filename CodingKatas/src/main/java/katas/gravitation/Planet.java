package katas.gravitation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Planet {

    private double mass;    // kg

    private double radius;    // m

    private Vector2D position;
    private Vector2D velocity;  // position, direction, velocity=length:  m / s

    public double acceleration(Planet p2) {  // m / s2
        return Constants.G*(p2.getMass()/getDistanceSquared(p2));
    }

    private double getDistanceSquared(Planet p2) {
        double dx = getPosition().getX() - p2.getPosition().getX();
        double dy = getPosition().getY() - p2.getPosition().getY();
        return (dx*dx)+(dy*dy);
    }
    public double getDistance(Planet p2) {
        return Math.sqrt(getDistanceSquared(p2));
    }

    public Vector2D changeVelocityPerTime(Planet p2, double time) {
        double acceleration = acceleration(p2); // m / s2
        double deltaV = acceleration * time;    // v = a * t  (m/s)
        Vector2D deltaVelocity = new Vector2D(
                p2.getPosition().getX() - getPosition().getX(),
                p2.getPosition().getY() - getPosition().getY()
        );
        deltaVelocity.normalizeToLength(deltaV);
        velocity.add(deltaVelocity);
        return velocity;
    }

    public Vector2D changePositionPerTime(double time) {
        // length of velocity is m/s and keeps constant.  time is s
        double dxTimescaled = velocity.getX() * time;
        double dyTimescaled = velocity.getY() * time;
        position.add(new Vector2D(dxTimescaled, dyTimescaled));
        return position;
    }

}
