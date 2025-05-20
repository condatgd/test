package katas.kantinenstaumelder.domain.model.statistik;

import katas.kantinenstaumelder.domain.model.ereigniss.KantinenEreignisTyp;
import katas.kantinenstaumelder.domain.model.zeit.Zeitraum;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class KantinenStatistikSequenz {
    private Zeitraum zeitraum;
    int sekundenProStatistik;
    private List<KantinenStatistik> statistiken = new ArrayList<>();
}
