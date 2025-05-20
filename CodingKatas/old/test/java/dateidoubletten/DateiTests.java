package dateidoubletten;

import katas.dateidoubletten.IDublette;
import katas.dateidoubletten.Vergleichsmodi;
import katas.dateidoubletten.impl_java.DublettenPruefung;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateiTests {

    @Test
    void testKandidaten() {
        Path resourceDirectory = Paths.get("src","test","resources", "testfiles");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        FileSystem fs  = FileSystems.getDefault();
        DublettenPruefung dublettenpruefung = new DublettenPruefung(fs);
        List<IDublette> iDublettesKandidaten = dublettenpruefung.sammleKandidaten(absolutePath, Vergleichsmodi.GROESSE);
        iDublettesKandidaten.forEach(d -> System.out.println(d.dateipfade()));

        assertEquals(1, iDublettesKandidaten.size());
        List<String> dateipfade0 = iDublettesKandidaten.get(0).dateipfade();
        assertEquals(3, dateipfade0.size());
        assertTrue(dateipfade0.get(0).endsWith("testfiles\\a.txt"));
        assertTrue(dateipfade0.get(1).endsWith("testfiles\\dir1\\a.txt"));
        assertTrue(dateipfade0.get(2).endsWith("testfiles\\dir2\\a.txt"));
    }

    @Test
    void testPruefe() {
        Path resourceDirectory = Paths.get("src","test","resources", "testfiles");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        FileSystem fs  = FileSystems.getDefault();
        DublettenPruefung dublettenpruefung = new DublettenPruefung(fs);
        List<IDublette> iDublettesKandidaten = dublettenpruefung.sammleKandidaten(absolutePath, Vergleichsmodi.GROESSE);
        List<IDublette> iDublettesGeprueft = dublettenpruefung.pruefeKandidaten(iDublettesKandidaten);
        iDublettesGeprueft.forEach(d -> System.out.println(d.dateipfade()));

        assertEquals(1, iDublettesGeprueft.size());
        List<String> dateipfade0 = iDublettesGeprueft.get(0).dateipfade();
        assertEquals(2, dateipfade0.size());
        assertTrue(dateipfade0.get(0).endsWith("testfiles\\a.txt"));
        assertTrue(dateipfade0.get(1).endsWith("testfiles\\dir1\\a.txt"));
    }

}
