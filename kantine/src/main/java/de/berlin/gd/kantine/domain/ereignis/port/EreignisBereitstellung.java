package de.berlin.gd.kantine.domain.ereignis.port;


import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignis;
import de.berlin.gd.kantine.domain.zeit.Zeitraum;

import java.util.List;

public interface EreignisBereitstellung {
    List<KantinenEreignis> alleEreignisse();
    List<KantinenEreignis> ereignisseImZeitraum(Zeitraum zeitraum);
}
