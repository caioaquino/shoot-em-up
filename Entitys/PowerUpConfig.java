package Entitys;

public class PowerUpConfig {
    private double radius;
    private long nextPowerUp;

    public PowerUpConfig(double radius, long nextPowerUp){
        this.radius = radius;
        this.nextPowerUp = nextPowerUp;
    }
    
    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public long getNextPowerUp() {
        return this.nextPowerUp;
    }

    public void setNextPowerUp(long nextPowerUp) {
        this.nextPowerUp = nextPowerUp;
    }

}
