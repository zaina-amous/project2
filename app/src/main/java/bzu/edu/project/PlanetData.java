package bzu.edu.project;
//POJO class
public class PlanetData {


      // planets characteristics
        private String name;
        private double size;
        private double distanceFromSun;
        private int numMoons;

        public PlanetData(String name, double size, double distanceFromSun, int numMoons) {
            this.name = name;
            this.size = size;
            this.distanceFromSun = distanceFromSun;
            this.numMoons = numMoons;
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

        public int getNumMoons() {
            return numMoons;
        }

        // Sample data for the planets in our solar system which is a mockup
        public static final PlanetData[] PLANETS = {
                new PlanetData("Mercury", 0.055, 0.39, 0),
                new PlanetData("Venus", 0.815, 0.72, 0),
                new PlanetData("Earth", 1.0, 1.0, 1),
                new PlanetData("Mars", 0.107, 1.52, 2),
                new PlanetData("Jupiter", 318.0, 5.20, 79),
                new PlanetData("Saturn", 95.0, 9.58, 82),
                new PlanetData("Uranus", 14.5, 19.18, 27),
                new PlanetData("Neptune", 17.2, 30.07, 14)
        };
    }



