package de.berlin.gd.kantine.domain.statistik;

import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignis;
import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignisTyp;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistikSequenz;
import de.berlin.gd.kantine.domain.zeit.Zeitpunkt;
import de.berlin.gd.kantine.domain.zeit.Zeitraum;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// StatistikServiceUnitTest class tests the functionality of generiereStatistikSequenz method in StatistikService class
public class StatistikServiceSequenzTest {

    private StatistikService service = new StatistikService();

    // Tests where the input List<Ereignis> is empty
    @Test
    void testGeneriereStatistikSequenz_EmptyList() {
        LocalDateTime now = LocalDateTime.now();
        Zeitpunkt von = new Zeitpunkt(now);
        Zeitpunkt bis = new Zeitpunkt(now.plusSeconds(60));
        Zeitraum zeitraum = new Zeitraum(von, bis);
        List<KantinenEreignis> events = new ArrayList<>();

        KantinenStatistikSequenz result = service.generiereStatistikSequenz(zeitraum, 10, events);

        assertEquals(6, result.getStatistiken().size());
    }

    // Tests where the input List<Ereignis> has one item.
    @Test
    void testGeneriereStatistikSequenz_MultipleItems() {
        LocalDateTime now = LocalDateTime.now();
        Zeitpunkt von = new Zeitpunkt(now);
        Zeitpunkt bis = new Zeitpunkt(now.plusSeconds(70));
        Zeitraum zeitraum = new Zeitraum(von, bis);
        List<KantinenEreignis> events = new ArrayList<>();
        events.add(new KantinenEreignis(von.plusSekunden(15), KantinenEreignisTyp.BETRETEN));
        events.add(new KantinenEreignis(von.plusSekunden(16), KantinenEreignisTyp.BETRETEN));
        events.add(new KantinenEreignis(von.plusSekunden(17), KantinenEreignisTyp.BETRETEN));

        events.add(new KantinenEreignis(von.plusSekunden(25), KantinenEreignisTyp.ABRECHNUNG));
        events.add(new KantinenEreignis(von.plusSekunden(26), KantinenEreignisTyp.ABRECHNUNG));
        events.add(new KantinenEreignis(von.plusSekunden(26), KantinenEreignisTyp.BETRETEN));

        events.add(new KantinenEreignis(von.plusSekunden(45), KantinenEreignisTyp.ABRECHNUNG));
        events.add(new KantinenEreignis(von.plusSekunden(46), KantinenEreignisTyp.ABRECHNUNG));
        events.add(new KantinenEreignis(von.plusSekunden(46), KantinenEreignisTyp.BETRETEN));

        events.add(new KantinenEreignis(von.plusSekunden(65), KantinenEreignisTyp.ABRECHNUNG));
        events.add(new KantinenEreignis(von.plusSekunden(66), KantinenEreignisTyp.ABRECHNUNG));
        events.add(new KantinenEreignis(von.plusSekunden(69), KantinenEreignisTyp.BETRETEN));

        events.add(new KantinenEreignis(von.plusSekunden(70), KantinenEreignisTyp.VERLASSEN));

        KantinenStatistikSequenz result = service.generiereStatistikSequenz(zeitraum, 10, events);

        assertEquals(7, result.getStatistiken().size());

        assertEquals(0, result.getStatistiken().get(1).getKummulierteEreignisse().get(KantinenEreignisTyp.ABRECHNUNG).intValue());
        assertEquals(3, result.getStatistiken().get(1).getKummulierteEreignisse().get(KantinenEreignisTyp.BETRETEN).intValue());

        assertEquals(2, result.getStatistiken().get(2).getKummulierteEreignisse().get(KantinenEreignisTyp.ABRECHNUNG).intValue());
        assertEquals(1, result.getStatistiken().get(2).getKummulierteEreignisse().get(KantinenEreignisTyp.BETRETEN).intValue());
    }
  
}