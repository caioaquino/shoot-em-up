package Entitys;

public class EnimyConfig {
    private double radius;
    private long nextEnimy;
    private double spawnX;
    private int count;

    public EnimyConfig(double radius, long nextEnimy, double spawnX, int count) {
        this.radius = radius;
        this.nextEnimy = nextEnimy;
        this.spawnX = spawnX;
        this.count = count;
    }

    public EnimyConfig(double radius, long nextEnimy) {
        this.radius = radius;
        this.nextEnimy = nextEnimy;

    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public long getNextEnimy() {
        return this.nextEnimy;
    }

    public void setNextEnimy(long nextEnimy) {
        this.nextEnimy = nextEnimy;
    }

    public double getSpawnX() {
        return this.spawnX;
    }

    public void setSpawnX(double spawnX) {
        this.spawnX = spawnX;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
