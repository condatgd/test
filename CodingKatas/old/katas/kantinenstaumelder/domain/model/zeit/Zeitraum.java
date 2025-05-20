package katas.kantinenstaumelder.domain.model.zeit;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Zeitraum {
    private Zeitpunkt von;
    private Zeitpunkt bis;

    public boolean enthaelt(Zeitpunkt zeitpunkt) {
        return  (zeitpunkt.getTimestamp().isEqual(von.getTimestamp()) ||
                 zeitpunkt.getTimestamp().isAfter(von.getTimestamp())
                ) &&
                zeitpunkt.getTimestamp().isBefore(bis.getTimestamp());
    }
}
