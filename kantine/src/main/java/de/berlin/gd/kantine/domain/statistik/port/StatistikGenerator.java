package de.berlin.gd.kantine.domain.statistik.port;


import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignis;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistik;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistikSequenz;
import de.berlin.gd.kantine.domain.zeit.Zeitraum;

import java.util.List;

public interface StatistikGenerator {
    KantinenStatistik generiereStatistik(List<KantinenEreignis> ereignisse);

    KantinenStatistikSequenz generiereStatistikSequenz(Zeitraum zeitraum, int sekundenProStatistik, List<KantinenEreignis> ereignisse);

}
