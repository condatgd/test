package katas.domino;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Domino {

    public interface BreitesObjekt {
        int getBreite();

    }

    @Data
    @AllArgsConstructor
    static class DominoStein implements BreitesObjekt {
        int hoehe;
        int breite;
        boolean steht;
    }

    @Data
    @AllArgsConstructor
    static class Abstand  implements BreitesObjekt {
        int breite;
    }

    @Data
    static class Reihe {

        private int umgefalleneSteine = 0;

        List<BreitesObjekt> steine = new ArrayList<>();
        void add(BreitesObjekt stein) {
            steine.add(stein);
        }

        public void start() {
            int i=0;
            boolean fehler = false;
            while (i<steine.size() && !fehler) {
                BreitesObjekt aktuellerStein = steine.get(i);
                if(i+1<steine.size() && steine.get(i+1) instanceof Abstand) {
                    BreitesObjekt naechsterStein = steine.get(i+1);
                    if(aktuellerStein instanceof DominoStein dominoStein && naechsterStein instanceof Abstand) {
                        if(dominoStein.isSteht()) {
                            dominoStein.setSteht(false);
                            umgefalleneSteine++;
                        }
                        fehler = dominoStein.getHoehe() <= naechsterStein.getBreite();
                    }
                } else {
                    if(aktuellerStein instanceof DominoStein dominoStein) {
                        if(dominoStein.isSteht()) {
                            dominoStein.setSteht(false);
                            umgefalleneSteine++;
                        }
                    }
                }
                i++;
            }
        }
    }

}
