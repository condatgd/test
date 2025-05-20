package katas.pid;

public class PidTest {
    public static void main(String[] args) throws InterruptedException {
        Raum raum = new Raum(20.0, 20.0);
        double temperaturSoll = 22.0;
        while(true) {
            double raumTemperatur = raum.getTemperatur();
            // System.out.println("Raum vor Heizung: " + raum.getTemperatur());
            raum.heizung(pidHeizung(raumTemperatur, temperaturSoll));
            Thread.sleep(1000);
            System.out.println("Raum nach Heizung: " + raum.getTemperatur() + " " + raum.getHeizungsTemperatur());
        }
    }

    private static double pidHeizung(double raumTemperatur, double temperaturSoll) {
        double fehler = temperaturSoll - raumTemperatur;
        double prop = fehler * 11.0;
        double pid = raumTemperatur + prop;
        // System.out.println("pid heizung: " + pid);
        return pid;
    }
}
