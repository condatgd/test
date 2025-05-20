package de.berlin.gd.kantine.controller.ereignis.model;

import de.berlin.gd.kantine.domain.zeit.Zeitpunkt;
import lombok.Data;

@Data
public class EreignisResultDTO extends EreignisDTO {
    Zeitpunkt zeitpunkt;
}
