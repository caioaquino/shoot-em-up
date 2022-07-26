package Entitys;

import java.util.ArrayList;

import GameLib.GameLib;
import Interfaces.IBackground;

public class BackgroundConfig implements IBackground {
    private double count;
    private double speed;
    private int quantity;

    public BackgroundConfig(double count, double speed, int quantity, ArrayList<Background> backgrounds) {
        this.count = count;
        this.speed = speed;
        this.quantity = quantity;

        for (int i = 0; i < quantity; i++) {
            Background background = new Background();
            background.setX(Math.random() * GameLib.WIDTH);
            background.setY(Math.random() * GameLib.HEIGHT);
            backgrounds.add(background);
        }
    }

    public double getCount() {
        return this.count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementCount(double count) {
        this.count += count;
    }

}
