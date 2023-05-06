

public class Planet {
    private String name;
    private double size; // in Earth masses
    private double distanceFromSun; // in astronomical units (AU)
    private int numberOfMoons;

    public Planet(String name, double size, double distanceFromSun, int numberOfMoons) {
        this.name = name;
        this.size = size;
        this.distanceFromSun = distanceFromSun;
        this.numberOfMoons = numberOfMoons;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public double getDistanceFromSun() {
        return distanceFromSun;
    }

    public int getNumberOfMoons() {
        return numberOfMoons;
    }
}

