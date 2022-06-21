package Entitys;

import Classes.Coordinates;
import Classes.Velocity;

public class Projectiles {

    private int state;
    private Coordinates coordinates;
    private Velocity velocity;

    public Projectiles() {
        this.coordinates = new Coordinates();
        this.velocity = new Velocity();
    }

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
