package katas.dateidoubletten;

import lombok.Data;

import java.io.IOException;
import java.util.List;

public interface IDublettenpruefung {
    List<IDublette> sammleKandidaten(String pfad);
    List<IDublette> sammleKandidaten(String pfad, Vergleichsmodi modus);
    List<IDublette> pruefeKandidaten(List<IDublette> kandidaten);


}

