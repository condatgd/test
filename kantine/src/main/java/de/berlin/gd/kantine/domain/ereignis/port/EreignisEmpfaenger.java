package de.berlin.gd.kantine.domain.ereignis.port;

import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignis;

public interface EreignisEmpfaenger {
    void empfangeEreignis(KantinenEreignis ereignis);
}
