package katas.gravitation;

import java.util.List;

public class PlanetSimulation {

    private List<Planet> planets;

    public PlanetSimulation(List<Planet> planets) {
        this.planets = planets;
    }

    public void simulationStep(double time) {
        planets.forEach(currentPlanet ->
                planets.stream()
                        .filter(otherPlanet -> otherPlanet!=currentPlanet)
                        .forEach(otherPlanet -> currentPlanet.changeVelocityPerTime(otherPlanet, time))
        );
        planets.forEach(currentPlanet -> currentPlanet.changePositionPerTime(time));

    }

    public List<Planet> getPlanets() {
        return planets;
    }



}
