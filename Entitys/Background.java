package Entitys;

import Classes.Coordinates;

public class Background {
    private Coordinates coordinates;

    public Background() {
        this.coordinates = new Coordinates();
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
}
