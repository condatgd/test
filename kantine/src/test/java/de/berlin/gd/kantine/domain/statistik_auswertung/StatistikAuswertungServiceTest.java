package de.berlin.gd.kantine.domain.statistik_auswertung;

import de.berlin.gd.kantine.domain.statistik.StatistikAuswertungService;
import de.berlin.gd.kantine.domain.statistik.model.BerechnungTyp;
import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignisTyp;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistik;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistikSequenz;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatistikAuswertungServiceTest {

    @Test
    public void berechneWerteAllerStatistikenTest1() {
        // Setup
        // Creating a KantinenStatistikSequenz with one KantinenStatistik
        KantinenStatistikSequenz kantinenStatistikSequenz = new KantinenStatistikSequenz();
        List<KantinenStatistik> statistiken = new ArrayList<>();
        
        // Setting up a single KantinenStatistik
        KantinenStatistik statistik = new KantinenStatistik();
        statistik.setBerechneteWerte(new HashMap<>());
        statistik.getBerechneteWerte().put(BerechnungTyp.BESETZTE_PLAETZE, 0);
        statistik.setKummulierteEreignisse(new HashMap<>());
        statistik.getKummulierteEreignisse().put(KantinenEreignisTyp.BETRETEN, 5);
        statistik.getKummulierteEreignisse().put(KantinenEreignisTyp.VERLASSEN, 0);
        statistiken.add(statistik);
        
        // Assign to KantinenStatistikSequenz
        kantinenStatistikSequenz.setStatistiken(statistiken);
        
        // Test
        StatistikAuswertungService service = new StatistikAuswertungService();
        service.berechneWerteAllerStatistiken(kantinenStatistikSequenz);

        // Verify
        assertEquals(5, (int)kantinenStatistikSequenz.getStatistiken().get(0).getBerechneteWerte().get(BerechnungTyp.BESETZTE_PLAETZE));
    }

    @Test
    public void berechneWerteAllerStatistikenTest2() {
        // Setup
        // Creating a KantinenStatistikSequenz with one KantinenStatistik
        KantinenStatistikSequenz kantinenStatistikSequenz = new KantinenStatistikSequenz();
        List<KantinenStatistik> statistiken = new ArrayList<>();

        // Setting up first KantinenStatistik
        KantinenStatistik statistik = new KantinenStatistik();
        statistik.setBerechneteWerte(new HashMap<>());
        statistik.getBerechneteWerte().put(BerechnungTyp.BESETZTE_PLAETZE, 0);
        statistik.setKummulierteEreignisse(new HashMap<>());
        statistik.getKummulierteEreignisse().put(KantinenEreignisTyp.BETRETEN, 5);
        statistik.getKummulierteEreignisse().put(KantinenEreignisTyp.VERLASSEN, 0);
        statistiken.add(statistik);

        // Setting up second KantinenStatistik
        statistik = new KantinenStatistik();
        statistik.setBerechneteWerte(new HashMap<>());
        statistik.getBerechneteWerte().put(BerechnungTyp.BESETZTE_PLAETZE, 0);
        statistik.setKummulierteEreignisse(new HashMap<>());
        statistik.getKummulierteEreignisse().put(KantinenEreignisTyp.BETRETEN, 2);
        statistik.getKummulierteEreignisse().put(KantinenEreignisTyp.VERLASSEN, 3);
        statistiken.add(statistik);

        // Assign to KantinenStatistikSequenz
        kantinenStatistikSequenz.setStatistiken(statistiken);

        // Test
        StatistikAuswertungService service = new StatistikAuswertungService();
        service.berechneWerteAllerStatistiken(kantinenStatistikSequenz);

        // Verify
        assertEquals(5, (int)kantinenStatistikSequenz.getStatistiken().get(0).getBerechneteWerte().get(BerechnungTyp.BESETZTE_PLAETZE));
        assertEquals(4, (int)kantinenStatistikSequenz.getStatistiken().get(1).getBerechneteWerte().get(BerechnungTyp.BESETZTE_PLAETZE));
    }

}