package de.berlin.gd.kantine.domain.grafik.port;

import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistikSequenz;

public interface GrafikErstellung {
    byte[]  vollstaendigeStatistikAlsGrafik(KantinenStatistikSequenz kantinenStatistikSequenz);
}
