package katas.pid;

import lombok.Data;

@Data
public class Raum {
    private double temperatur;
    private double heizungsTemperatur;
    public Raum(double temperatur, double heizungsTemperatur) {
        this.temperatur = temperatur;
        this.heizungsTemperatur = heizungsTemperatur;
    }
    public void heizung(double hTemp) {
        // System.out.println("heizung set temperatur: " + hTemp);
        double diff = hTemp - temperatur;
        // System.out.println("heizung diff: " + diff);
        temperatur = temperatur + diff/10;
        heizungsTemperatur = hTemp;

        temperatur += Math.random()/2.0;
    }
}
