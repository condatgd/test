package katas.kantinenstaumelder.domain.model.zeit;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Data
public class Zeitpunkt implements Comparable<Zeitpunkt> {
    private LocalDateTime timestamp;

    public Zeitpunkt(LocalDateTime time) {
        timestamp = time;
    }

    @Override
    public int compareTo(@NotNull Zeitpunkt z) {
        return timestamp.compareTo(z.timestamp);
    }

    public Zeitpunkt plusSekunden(int sekunden) {
        return new Zeitpunkt(timestamp.plusSeconds(sekunden));
    }
}
