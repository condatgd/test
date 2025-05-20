package katas.kantinenstaumelder.domain.ereignis;

import katas.kantinenstaumelder.domain.model.ereigniss.KantinenEreignis;
import katas.kantinenstaumelder.domain.model.zeit.Zeitraum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EreignisService implements EreignisEmpfaenger, EreignisBereitstellung {

    private List<KantinenEreignis> ereignisListe = new ArrayList<>();
    @Override
    public void empfangeEreignis(KantinenEreignis ereignis) {
        ereignisListe.add(ereignis);
    }

    @Override
    public List<KantinenEreignis> alleEreignisse() {
        return ereignisListe;
    }

    @Override
    public List<KantinenEreignis> ereignisseImZeitraum(Zeitraum zeitraum) {
        return ereignisListe.stream().filter(kantinenEreignis -> zeitraum.enthaelt(kantinenEreignis.getZeitpunkt())).toList();
    }


}
