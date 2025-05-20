package katas.kantinenstaumelder.domain.berechnung;

import katas.kantinenstaumelder.domain.model.berechnung.BerechnungTyp;
import katas.kantinenstaumelder.domain.model.ereigniss.KantinenEreignisTyp;
import katas.kantinenstaumelder.domain.model.statistik.KantinenStatistik;
import katas.kantinenstaumelder.domain.model.statistik.KantinenStatistikSequenz;

import java.util.List;

public class StatistikAuswertungService implements StatistikAuswertung {
    @Override
    public void berechneWerteAllerStatistiken(KantinenStatistikSequenz kantinenStatistikSequenz) {
        List<KantinenStatistik> statistiken = kantinenStatistikSequenz.getStatistiken();
        int besetztePlaetze = 0;
        for (KantinenStatistik statistik : statistiken) {
            besetztePlaetze = berechneWerte(statistik, besetztePlaetze);
        }
    }

    private int berechneWerte(KantinenStatistik statistik, int besetztePlaetze) {
        //  BETRETEN,ABRECHNUNG,VERLASSEN
        besetztePlaetze +=
                statistik.getKummulierteEreignisse().get(KantinenEreignisTyp.BETRETEN) -
                statistik.getKummulierteEreignisse().get(KantinenEreignisTyp.VERLASSEN);
        statistik.getBerechneteWerte().put(BerechnungTyp.BESETZTE_PLAETZE, besetztePlaetze);
        return besetztePlaetze;
    }
}
