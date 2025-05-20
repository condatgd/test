package katas.ratewort;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class RateWort1 {

    public record Buchstabe(char zeichen, AtomicBoolean getroffen){
        @Override
        public String toString() {
            return getroffen.get()? ""+ zeichen : "_";
        }
    }

    List<Buchstabe> buchstaben;

    public RateWort1(String wortStr) {
        buchstaben = wortStr.chars()
                .mapToObj(c -> new Buchstabe((char) c, new AtomicBoolean(false)))
                .toList();
    }

    public void rateBuchstabe(char rateZeichen) {
        buchstaben.forEach(buchstabe -> {
            if(buchstabe.zeichen ==rateZeichen) buchstabe.getroffen.set(true);
        });
        System.out.println(displayWort() + " " + isGeloest());
    }

    public boolean isGeloest() {
        return buchstaben.stream().allMatch(buchstabe -> buchstabe.getroffen.get());
    }

    public String displayWort() {
        return buchstaben.stream().map(Buchstabe::toString).collect(Collectors.joining());
    }
}
