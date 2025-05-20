package de.berlin.gd.kantine.controller.grafik;

import de.berlin.gd.kantine.domain.statistik.port.StatistikAuswertung;
import de.berlin.gd.kantine.domain.zeit.Zeitpunkt;
import de.berlin.gd.kantine.domain.zeit.Zeitraum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/grafik")
public class GrafikRestController {

    @Autowired
    StatistikAuswertung statistikAuswertung;

    @GetMapping(
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[]  grafik(
            @RequestParam int startStunde,
            @RequestParam int startMinute,
            @RequestParam int sekundenProIntervall
    ) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime von = now.withHour(startStunde).withMinute(startMinute).withSecond(0);
        Zeitraum zeitraum = new Zeitraum(new Zeitpunkt(von), new Zeitpunkt(now));
        return statistikAuswertung.vollstaendigeStatistikAlsGrafik(zeitraum, sekundenProIntervall);
    }

}
