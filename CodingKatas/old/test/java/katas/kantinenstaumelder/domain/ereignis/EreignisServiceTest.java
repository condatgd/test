package katas.kantinenstaumelder.domain.ereignis;

import katas.kantinenstaumelder.domain.model.ereigniss.KantinenEreignis;
import katas.kantinenstaumelder.domain.model.ereigniss.KantinenEreignisTyp;
import katas.kantinenstaumelder.domain.model.zeit.Zeitpunkt;
import katas.kantinenstaumelder.domain.model.zeit.Zeitraum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

class EreignisServiceTest {

    @Test
    void empfangeEreignis1() {
        EreignisService ereignisService = new EreignisService();

        KantinenEreignis exampleEreignis = new KantinenEreignis();
        exampleEreignis.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        exampleEreignis.setZeitpunkt(new Zeitpunkt(LocalDateTime.now()));

        ereignisService.empfangeEreignis(exampleEreignis);

        List<KantinenEreignis> kantinenEreignisse = ereignisService.alleEreignisse();

        assertEquals(kantinenEreignisse.size(),1);
        assertEquals(kantinenEreignisse.get(0),exampleEreignis);
    }

    @Test
    void empfangeEreignis2() {
        EreignisService ereignisService = new EreignisService();

        KantinenEreignis exampleEreignis1 = new KantinenEreignis();
        exampleEreignis1.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        exampleEreignis1.setZeitpunkt(new Zeitpunkt(LocalDateTime.now()));

        KantinenEreignis exampleEreignis2 = new KantinenEreignis();
        exampleEreignis2.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        exampleEreignis2.setZeitpunkt(new Zeitpunkt(LocalDateTime.now()));

        ereignisService.empfangeEreignis(exampleEreignis1);
        ereignisService.empfangeEreignis(exampleEreignis2);

        List<KantinenEreignis> kantinenEreignisse = ereignisService.alleEreignisse();

        assertEquals(kantinenEreignisse.size(),2);
        assertEquals(kantinenEreignisse.get(0),exampleEreignis1);
        assertEquals(kantinenEreignisse.get(1),exampleEreignis2);
    }

    @Test
    void empfangeEreignis2Filtered2() {
        LocalDateTime specificDateTime1 = LocalDateTime.of(2023, 12, 24, 15, 30, 30);
        LocalDateTime specificDateTime2 = LocalDateTime.of(2023, 12, 24, 15, 30, 32);
        LocalDateTime specificDateTime33 = LocalDateTime.of(2023, 12, 24, 15, 30, 33);

        EreignisService ereignisService = new EreignisService();

        KantinenEreignis exampleEreignis1 = new KantinenEreignis();
        exampleEreignis1.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        exampleEreignis1.setZeitpunkt(new Zeitpunkt(specificDateTime1));

        KantinenEreignis exampleEreignis2 = new KantinenEreignis();
        exampleEreignis2.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        exampleEreignis2.setZeitpunkt(new Zeitpunkt(specificDateTime2));

        ereignisService.empfangeEreignis(exampleEreignis1);
        ereignisService.empfangeEreignis(exampleEreignis2);

        List<KantinenEreignis> kantinenEreignisse = ereignisService.ereignisseImZeitraum(
                new Zeitraum(new Zeitpunkt(specificDateTime1),new Zeitpunkt(specificDateTime33)));

        assertEquals(kantinenEreignisse.size(),2);
        assertEquals(kantinenEreignisse.get(0),exampleEreignis1);
        assertEquals(kantinenEreignisse.get(1),exampleEreignis2);
    }

    @Test
    void empfangeEreignis2Filtered1() {
        LocalDateTime specificDateTime1 = LocalDateTime.of(2023, 12, 24, 15, 30, 30);
        LocalDateTime specificDateTime31 = LocalDateTime.of(2023, 12, 24, 15, 30, 31);
        LocalDateTime specificDateTime2 = LocalDateTime.of(2023, 12, 24, 15, 30, 32);
        LocalDateTime specificDateTime33 = LocalDateTime.of(2023, 12, 24, 15, 30, 33);

        EreignisService ereignisService = new EreignisService();

        KantinenEreignis exampleEreignis1 = new KantinenEreignis();
        exampleEreignis1.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        exampleEreignis1.setZeitpunkt(new Zeitpunkt(specificDateTime1));

        KantinenEreignis exampleEreignis2 = new KantinenEreignis();
        exampleEreignis2.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        exampleEreignis2.setZeitpunkt(new Zeitpunkt(specificDateTime2));

        ereignisService.empfangeEreignis(exampleEreignis1);
        ereignisService.empfangeEreignis(exampleEreignis2);

        List<KantinenEreignis> kantinenEreignisse = ereignisService.ereignisseImZeitraum(
                new Zeitraum(new Zeitpunkt(specificDateTime31),new Zeitpunkt(specificDateTime33)));

        assertEquals(kantinenEreignisse.size(),1);
        assertEquals(kantinenEreignisse.get(0),exampleEreignis2);
    }

    @Test
    void empfangeEreignis2Filtered0() {
        LocalDateTime specificDateTime1 = LocalDateTime.of(2023, 12, 24, 15, 30, 30);
        LocalDateTime specificDateTime31 = LocalDateTime.of(2023, 12, 24, 15, 30, 31);
        LocalDateTime specificDateTime2 = LocalDateTime.of(2023, 12, 24, 15, 30, 32);
        LocalDateTime specificDateTime33 = LocalDateTime.of(2023, 12, 24, 15, 30, 33);
        LocalDateTime specificDateTime34 = LocalDateTime.of(2023, 12, 24, 15, 30, 34);

        EreignisService ereignisService = new EreignisService();

        KantinenEreignis exampleEreignis1 = new KantinenEreignis();
        exampleEreignis1.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        exampleEreignis1.setZeitpunkt(new Zeitpunkt(specificDateTime1));

        KantinenEreignis exampleEreignis2 = new KantinenEreignis();
        exampleEreignis2.setEreignisTyp(KantinenEreignisTyp.BETRETEN);
        exampleEreignis2.setZeitpunkt(new Zeitpunkt(specificDateTime2));

        ereignisService.empfangeEreignis(exampleEreignis1);
        ereignisService.empfangeEreignis(exampleEreignis2);

        List<KantinenEreignis> kantinenEreignisse = ereignisService.ereignisseImZeitraum(
                new Zeitraum(new Zeitpunkt(specificDateTime33),new Zeitpunkt(specificDateTime34)));

        assertEquals(kantinenEreignisse.size(),0);
    }

}