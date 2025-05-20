package de.berlin.gd.kantine.domain.ereignis.model;

import de.berlin.gd.kantine.domain.zeit.Zeitpunkt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KantinenEreignis {
   private Zeitpunkt zeitpunkt;
   private KantinenEreignisTyp ereignisTyp;
}
