package de.berlin.gd.kantine.domain.statistik.port;

import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistikSequenz;
import de.berlin.gd.kantine.domain.zeit.Zeitraum;

public interface StatistikAuswertung {
    KantinenStatistikSequenz berechneWerteAllerStatistiken(KantinenStatistikSequenz kantinenStatistikSequenz);

    KantinenStatistikSequenz vollstaendigeStatistik(Zeitraum zeitraum, int sekundenProIntervall);

    byte[]  vollstaendigeStatistikAlsGrafik(Zeitraum zeitraum, int sekundenProIntervall);

}
