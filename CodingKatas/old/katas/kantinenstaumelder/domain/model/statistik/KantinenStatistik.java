package katas.kantinenstaumelder.domain.model.statistik;

import katas.kantinenstaumelder.domain.model.berechnung.BerechnungTyp;
import katas.kantinenstaumelder.domain.model.ereigniss.KantinenEreignisTyp;
import katas.kantinenstaumelder.domain.model.zeit.Zeitraum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class KantinenStatistik {
    private Zeitraum zeitraum;
    private Map<KantinenEreignisTyp, Integer> kummulierteEreignisse = new HashMap<>();
    private Map<BerechnungTyp,       Integer> berechneteWerte       = new HashMap<>();
}
