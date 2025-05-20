package de.berlin.gd.kantine.controller.statistik.model;

import de.berlin.gd.kantine.domain.zeit.Zeitraum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KantinenStatistikSequenzDTO {
    private Zeitraum zeitraum;
    int sekundenProStatistik;
    private List<KantinenStatistikDTO> statistiken = new ArrayList<>();
}
