package katas.kantinenstaumelder.domain.model.ereigniss;

import katas.kantinenstaumelder.domain.model.zeit.Zeitpunkt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KantinenEreignis {
   private Zeitpunkt zeitpunkt;
   private KantinenEreignisTyp ereignisTyp;
}
