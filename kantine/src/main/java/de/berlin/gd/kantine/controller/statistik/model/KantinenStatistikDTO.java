package de.berlin.gd.kantine.controller.statistik.model;

import de.berlin.gd.kantine.domain.statistik.model.BerechnungTyp;
import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignisTyp;
import de.berlin.gd.kantine.domain.zeit.Zeitraum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class KantinenStatistikDTO {
    private Zeitraum zeitraum;
    private Map<KantinenEreignisTyp, Integer> kummulierteEreignisse = new HashMap<>();
    private Map<BerechnungTyp,       Integer> berechneteWerte       = new HashMap<>();
}
