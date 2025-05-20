package katas.gravitation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationCanvas extends JPanel {
    PlanetSimulation planetSimulation;

    private static double coordScale = 1E-6;

    public SimulationCanvas() {
        Planet erde = new Planet(5.972E24,  12742E3/2, new Vector2D(0,0),        new Vector2D(0,0));
        Planet mond = new Planet(7.3483E22, 3474E3/2,  new Vector2D(384403E3,0), new Vector2D(0,1.023E3));

        Planet raumschiff = new Planet(10000,  3474E3, new Vector2D(12742E3/2 + 1000E3 ,0),        new Vector2D(4E3,9E3));


        List<Planet> planets = List.of(erde, mond, raumschiff);

        planetSimulation = new PlanetSimulation(planets);
    }

    public void paintComponent(Graphics g) {
        // g.drawLine(0,0, 400, 400);
        planetSimulation.getPlanets().forEach(planet -> {
            Vector2D pos = planet.getPosition();
            int r = (int) (coordScale*planet.getRadius());
            int x = (int) (coordScale*pos.getX())+400;
            int y = (int) (coordScale*pos.getY())+400;
            g.drawArc(x-r, y-r, 2*r,2*r,0,360);
        });
        System.out.println("distance: " + planetSimulation.getPlanets().get(0).getDistance(planetSimulation.getPlanets().get(1)));
        planetSimulation.simulationStep(60);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SimulationCanvas());
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        while(true) {
            frame.repaint();
            /*try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
        }
    }
}
