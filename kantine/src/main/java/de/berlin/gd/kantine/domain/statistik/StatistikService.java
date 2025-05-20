package de.berlin.gd.kantine.domain.statistik;


import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignis;
import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignisTyp;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistik;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistikSequenz;
import de.berlin.gd.kantine.domain.zeit.Zeitpunkt;
import de.berlin.gd.kantine.domain.zeit.Zeitraum;
import de.berlin.gd.kantine.domain.statistik.port.StatistikGenerator;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class StatistikService implements StatistikGenerator {

    @Override
    public KantinenStatistik generiereStatistik(List<KantinenEreignis> ereignisse) {
        KantinenStatistik kantinenStatistik = new KantinenStatistik();
        LocalDateTime minTime = LocalDateTime.MAX;
        LocalDateTime maxTime = LocalDateTime.MIN;
        Map<KantinenEreignisTyp, Integer> map = new HashMap<>();
        for(KantinenEreignisTyp value: KantinenEreignisTyp.values()) {
            map.put(value, 0);
        }
        for(KantinenEreignis ereignis: ereignisse) {
            Zeitpunkt zeitpunkt = ereignis.getZeitpunkt();
            KantinenEreignisTyp ereignisTyp = ereignis.getEreignisTyp();
            if (zeitpunkt.getTimestamp().isBefore(minTime)) {
                minTime = zeitpunkt.getTimestamp();
            }
            if (zeitpunkt.getTimestamp().isAfter(maxTime)) {
                maxTime = zeitpunkt.getTimestamp();
            }
            map.put(ereignisTyp, map.getOrDefault(ereignisTyp,0) + 1);
        }
        kantinenStatistik.setZeitraum(new Zeitraum(new Zeitpunkt(minTime), new Zeitpunkt(maxTime)));
        kantinenStatistik.setKummulierteEreignisse(map);
        return kantinenStatistik;
    }

    @Override
    public KantinenStatistikSequenz generiereStatistikSequenz(Zeitraum zeitraum, int sekundenProStatistik, List<KantinenEreignis> ereignisse) {
        List<KantinenEreignis> ereignisseSortiert = sortiereEreignisse(ereignisse);
        KantinenStatistikSequenz sequenz = new KantinenStatistikSequenz();
        sequenz.setZeitraum(zeitraum);
        sequenz.setSekundenProStatistik(sekundenProStatistik);
        List<Zeitraum> statistikIntervalle = erzeugeStatistikIntervalle(zeitraum, sekundenProStatistik);
        for(Zeitraum intervallZeitraum:statistikIntervalle) {
            List<KantinenEreignis> ereignisseImIntervall =
                    ereignisseSortiert.stream().filter(e -> intervallZeitraum.enthaelt(e.getZeitpunkt())).toList();
            KantinenStatistik statistik = generiereStatistik(ereignisseImIntervall);
            statistik.setZeitraum(intervallZeitraum);
            sequenz.getStatistiken().add(statistik);
        }
        return sequenz;
    }

    private List<Zeitraum> erzeugeStatistikIntervalle(Zeitraum zeitraum, int sekundenProStatistik) {
        List<Zeitraum> intervalle = new ArrayList<>();
        Zeitpunkt von = zeitraum.getVon();
        Zeitpunkt bis = zeitraum.getVon().plusSekunden(sekundenProStatistik);
        while(bis.compareTo(zeitraum.getBis()) <= 0) {
            Zeitraum intervall = new Zeitraum(von, bis);
            intervalle.add(intervall);
            von = bis;
            bis = von.plusSekunden(sekundenProStatistik);
        }
        return intervalle;
    }

    @NotNull
    private static List<KantinenEreignis> sortiereEreignisse(List<KantinenEreignis> ereignisse) {
        return ereignisse.stream()
                .sorted(Comparator.comparing(KantinenEreignis::getZeitpunkt))
                .toList();
    }

}
