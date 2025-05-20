package katas.kantinenstaumelder.domain.ereignis;

import katas.kantinenstaumelder.domain.model.ereigniss.KantinenEreignis;

import java.util.List;

public interface EreignisEmpfaenger {
    void empfangeEreignis(KantinenEreignis ereignis);
}
