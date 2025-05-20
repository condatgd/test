package katas.kantinenstaumelder.domain.statistik;

import katas.kantinenstaumelder.domain.model.ereigniss.KantinenEreignis;
import katas.kantinenstaumelder.domain.model.ereigniss.KantinenEreignisTyp;
import katas.kantinenstaumelder.domain.model.statistik.KantinenStatistik;
import katas.kantinenstaumelder.domain.model.zeit.Zeitpunkt;
import katas.kantinenstaumelder.domain.model.zeit.Zeitraum;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatistikServiceTest {

    @Test
    void testGeneriereStatistik0() {
        // Prepare a statistic service
        StatistikService service = new StatistikService();

        // Prepare Ereignisse list
        List<KantinenEreignis> ereignisse = new ArrayList<>();
        // Add Ereignisse, if required

        // Prepare expected result
        KantinenStatistik expectedStatistik = new KantinenStatistik();
        // Set the attributes of expectedStatistik if necessary

        // Test the generiereStatistik() method
        KantinenStatistik result = service.generiereStatistik(ereignisse);

        // Assert the results
        assertEquals(expectedStatistik, result);
    }

    @Test
    void testGeneriereStatistik1() {
        Zeitpunkt now = new Zeitpunkt(LocalDateTime.now());
        // Prepare a statistic service
        StatistikService service = new StatistikService();

        // Prepare Ereignisse list
        List<KantinenEreignis> ereignisse = new ArrayList<>();
        // Add Ereignisse, if required
        KantinenEreignis kantinenEreignis1 = new KantinenEreignis();
        kantinenEreignis1.setZeitpunkt(now);
        kantinenEreignis1.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        ereignisse.add(kantinenEreignis1);

        // Prepare expected result
        KantinenStatistik expectedStatistik = new KantinenStatistik();
        // Set the attributes of expectedStatistik if necessary
        Map<KantinenEreignisTyp, Integer> map = new HashMap<>();
        map.put(KantinenEreignisTyp.VERLASSEN, 0);
        map.put(KantinenEreignisTyp.ABRECHNUNG, 0);
        map.put(KantinenEreignisTyp.BETRETEN, 1);
        expectedStatistik.setKummulierteEreignisse(map);
        expectedStatistik.setZeitraum(new Zeitraum(now,now));

        // Test the generiereStatistik() method
        KantinenStatistik result = service.generiereStatistik(ereignisse);

        // Assert the results
        assertEquals(expectedStatistik, result);
    }

    @Test
    void testGeneriereStatistik2() {
        Zeitpunkt now = new Zeitpunkt(LocalDateTime.now());
        // Prepare a statistic service
        StatistikService service = new StatistikService();

        // Prepare Ereignisse list
        List<KantinenEreignis> ereignisse = new ArrayList<>();
        // Add Ereignisse, if required
        KantinenEreignis kantinenEreignis1 = new KantinenEreignis();
        kantinenEreignis1.setZeitpunkt(now);
        kantinenEreignis1.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        ereignisse.add(kantinenEreignis1);

        KantinenEreignis kantinenEreignis2 = new KantinenEreignis();
        kantinenEreignis2.setZeitpunkt(now);
        kantinenEreignis2.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        ereignisse.add(kantinenEreignis2);

        // Prepare expected result
        KantinenStatistik expectedStatistik = new KantinenStatistik();
        // Set the attributes of expectedStatistik if necessary
        Map<KantinenEreignisTyp, Integer> map = new HashMap<>();
        map.put(KantinenEreignisTyp.VERLASSEN, 0);
        map.put(KantinenEreignisTyp.ABRECHNUNG, 0);
        map.put(KantinenEreignisTyp.BETRETEN, 2);
        expectedStatistik.setKummulierteEreignisse(map);
        expectedStatistik.setZeitraum(new Zeitraum(now,now));

        // Test the generiereStatistik() method
        KantinenStatistik result = service.generiereStatistik(ereignisse);

        // Assert the results
        assertEquals(expectedStatistik, result);
    }
}