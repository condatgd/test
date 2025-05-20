package de.berlin.gd.kantine.domain.ereignis;

import de.berlin.gd.kantine.domain.ereignis.port.EreignisBereitstellung;
import de.berlin.gd.kantine.domain.ereignis.port.EreignisEmpfaenger;
import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignis;
import de.berlin.gd.kantine.domain.zeit.Zeitraum;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EreignisService implements EreignisEmpfaenger, EreignisBereitstellung {

    private List<KantinenEreignis> ereignisListe = new ArrayList<>();
    @Override
    @Retryable(retryFor = RuntimeException.class, maxAttempts = 2, backoff = @Backoff(
            delay = 1000,
            // maxDelay = 10000,
            multiplier = 2.0))
    public void empfangeEreignis(KantinenEreignis ereignis) {
        System.out.println("empfangeEreignis ...");
        System.out.println("empfangeEreignis retry context: " + RetrySynchronizationManager.getContext());
        addEreignis(ereignis);
    }

    private void addEreignis(KantinenEreignis ereignis) {
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
