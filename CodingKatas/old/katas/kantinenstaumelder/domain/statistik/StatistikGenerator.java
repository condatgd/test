package katas.kantinenstaumelder.domain.statistik;

import katas.kantinenstaumelder.domain.model.ereigniss.KantinenEreignis;
import katas.kantinenstaumelder.domain.model.statistik.KantinenStatistik;
import katas.kantinenstaumelder.domain.model.statistik.KantinenStatistikSequenz;
import katas.kantinenstaumelder.domain.model.zeit.Zeitraum;

import java.util.List;

public interface StatistikGenerator {
    KantinenStatistik generiereStatistik(List<KantinenEreignis> ereignisse);

    KantinenStatistikSequenz generiereStatistikSequenz(Zeitraum zeitraum, int sekundenProStatistik, List<KantinenEreignis> ereignisse);

}
