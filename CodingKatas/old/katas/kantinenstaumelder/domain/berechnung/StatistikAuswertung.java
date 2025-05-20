package katas.kantinenstaumelder.domain.berechnung;

import katas.kantinenstaumelder.domain.model.statistik.KantinenStatistik;
import katas.kantinenstaumelder.domain.model.statistik.KantinenStatistikSequenz;

public interface StatistikAuswertung {
    void berechneWerteAllerStatistiken(KantinenStatistikSequenz kantinenStatistikSequenz);
}
