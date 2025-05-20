package de.berlin.gd.kantine.domain.statistik.model;

import de.berlin.gd.kantine.domain.zeit.Zeitraum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KantinenStatistikSequenz {
    private Zeitraum zeitraum;
    int sekundenProStatistik;
    private List<KantinenStatistik> statistiken = new ArrayList<>();
}
