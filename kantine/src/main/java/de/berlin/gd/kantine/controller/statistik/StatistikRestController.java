package de.berlin.gd.kantine.controller.statistik;

import de.berlin.gd.kantine.controller.mapper.KantinenStatistikSequenzMapper;
import de.berlin.gd.kantine.controller.statistik.model.KantinenStatistikSequenzDTO;
import de.berlin.gd.kantine.domain.statistik.port.StatistikAuswertung;
import de.berlin.gd.kantine.domain.zeit.Zeitpunkt;
import de.berlin.gd.kantine.domain.zeit.Zeitraum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/statistik")
public class StatistikRestController {

    @Autowired
    StatistikAuswertung statistikAuswertung;

    @GetMapping
    public KantinenStatistikSequenzDTO statistik(
            @RequestParam int startStunde,
            @RequestParam int startMinute,
            @RequestParam int sekundenProIntervall
    ) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime von = now.withHour(startStunde).withMinute(startMinute).withSecond(0);
        Zeitraum zeitraum = new Zeitraum(new Zeitpunkt(von), new Zeitpunkt(now));
        return KantinenStatistikSequenzMapper.INSTANCE.toKantinenStatistikSequenzDTO(
                statistikAuswertung.vollstaendigeStatistik(zeitraum, sekundenProIntervall)
        );
    }

}
