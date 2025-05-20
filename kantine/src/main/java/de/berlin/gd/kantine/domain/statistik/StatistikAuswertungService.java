package de.berlin.gd.kantine.domain.statistik;

import de.berlin.gd.kantine.domain.statistik.port.StatistikAuswertung;
import de.berlin.gd.kantine.domain.ereignis.port.EreignisBereitstellung;
import de.berlin.gd.kantine.domain.grafik.port.GrafikErstellung;
import de.berlin.gd.kantine.domain.statistik.model.BerechnungTyp;
import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignisTyp;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistik;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistikSequenz;
import de.berlin.gd.kantine.domain.zeit.Zeitraum;
import de.berlin.gd.kantine.domain.statistik.port.StatistikGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatistikAuswertungService implements StatistikAuswertung {

    @Autowired
    StatistikGenerator statistikGenerator;

    @Autowired
    EreignisBereitstellung ereignisBereitstellung;

    @Autowired
    GrafikErstellung grafikErstellung;

    @Override
    public KantinenStatistikSequenz berechneWerteAllerStatistiken(KantinenStatistikSequenz kantinenStatistikSequenz) {
        List<KantinenStatistik> statistiken = kantinenStatistikSequenz.getStatistiken();
        int besetztePlaetze = 0;
        for (KantinenStatistik statistik : statistiken) {
            besetztePlaetze = berechneWerte(statistik, besetztePlaetze);
        }
        return kantinenStatistikSequenz;
    }

    @Override
    public KantinenStatistikSequenz vollstaendigeStatistik(Zeitraum zeitraum, int sekundenProIntervall) {
        return berechneWerteAllerStatistiken(
                statistikGenerator.generiereStatistikSequenz(
                        zeitraum,
                        sekundenProIntervall,
                        ereignisBereitstellung.alleEreignisse()
                ));
    }

    @Override
    public byte[]  vollstaendigeStatistikAlsGrafik(Zeitraum zeitraum, int sekundenProIntervall) {
        return grafikErstellung.vollstaendigeStatistikAlsGrafik(
                vollstaendigeStatistik(zeitraum, sekundenProIntervall)
        );
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
