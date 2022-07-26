package Entitys;

import Types.Coordinates;
import Types.Explosion;

public class PowerUp {
    private int state;
    private long nextPowerUp;
    private double angle;
    private double velocity;
    private double radius;
    private double rotationVelocity;
    private Explosion explosion;
    private Coordinates coordinates;

    public PowerUp() {
        this.coordinates = new Coordinates();
        this.explosion = new Explosion();
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getNextPowerUp() {
        return this.nextPowerUp;
    }

    public void setNextPowerUp(long nextPowerUp) {
        this.nextPowerUp = nextPowerUp;
    }

    public double getAngle() {
        return this.angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getVelocity() {
        return this.velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getExplosionStart() {
        return this.explosion.start;
    }

    public void setExplosionStart(double start) {
        this.explosion.start = start;
    }

    public double getExplosionEnd() {
        return this.explosion.end;
    }

    public void setExplosionEnd(double end) {
        this.explosion.end = end;
    }

    public double getX() {
        return this.coordinates.x;
    }

    public void setX(double x) {
        this.coordinates.x = x;
    }

    public double getY() {
        return this.coordinates.y;
    }

    public void setY(double y) {
        this.coordinates.y = y;
    }

    public double getRotationVelocity() {
        return this.rotationVelocity;
    }

    public void setRotationVelocity(double rotationVelocity) {
        this.rotationVelocity = rotationVelocity;
    }
}
