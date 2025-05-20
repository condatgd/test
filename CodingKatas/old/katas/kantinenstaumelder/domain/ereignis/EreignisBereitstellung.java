package katas.kantinenstaumelder.domain.ereignis;

import katas.kantinenstaumelder.domain.model.ereigniss.KantinenEreignis;
import katas.kantinenstaumelder.domain.model.zeit.Zeitraum;

import java.time.LocalDateTime;
import java.util.List;

public interface EreignisBereitstellung {
    List<KantinenEreignis> alleEreignisse();
    List<KantinenEreignis> ereignisseImZeitraum(Zeitraum zeitraum);
}
