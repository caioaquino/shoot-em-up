package Entitys;

import Types.Coordinates;
import Types.Explosion;
import Types.Velocity;

public class Player {
    private long nextShot;
    private Explosion explosion;
    private int state;
    private double radius;
    private Coordinates coordinates;
    private Velocity velocity;

    public Player(int state, double x, double y, double vX, double vY, double radius, double explosion_start,
            double explosion_end, long next_shot) {
        this.explosion = new Explosion();
        this.coordinates = new Coordinates();
        this.velocity = new Velocity();
        setState(state);
        setX(x);
        setY(y);
        setRadius(radius);
        setExplosionStart(explosion_start);
        setExplosionEnd(explosion_end);
        setVx(vX);
        setVy(vY);
        setNextShot(next_shot);

    };

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
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

    public long getNextShot() {
        return this.nextShot;
    }

    public void setNextShot(long next_shot) {
        this.nextShot = next_shot;
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

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getVx() {
        return this.velocity.vX;
    }

    public void setVx(double vx) {
        this.velocity.vX = vx;
    }

    public double getVy() {
        return this.velocity.vY;
    }

    public void setVy(double vy) {
        this.velocity.vY = vy;
    }
}