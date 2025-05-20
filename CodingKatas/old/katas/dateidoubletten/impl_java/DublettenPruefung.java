package katas.dateidoubletten.impl_java;

import katas.dateidoubletten.IDublette;
import katas.dateidoubletten.IDublettenpruefung;
import katas.dateidoubletten.Vergleichsmodi;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DublettenPruefung implements IDublettenpruefung {
    private final FileSystem fileSystem;
    public DublettenPruefung(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }
    @Override
    public List<IDublette> sammleKandidaten(String pfad) {
        return sammleKandidaten(pfad, Vergleichsmodi.GROESSE_UND_NAME); // default
    }

    @Override
    public List<IDublette> sammleKandidaten(String pfad, Vergleichsmodi modus) {
        return sammleKandidaten(fileSystem.getPath(pfad), modus);
    }

    private List<IDublette> sammleKandidaten(Path pPath, Vergleichsmodi modus) {
        DublettenSammler dublettenSammler = new DublettenSammler();
        try (Stream<Path> walk = Files.walk(pPath)) {
            walk.filter(Files::isRegularFile).forEach( filePath ->
                    dublettenSammler.addPathWithHash(filePath, getHash(filePath, modus)));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return dublettenSammler.getDubletten();
    }

    private String getHash(Path path, Vergleichsmodi modus) {
        try {
            return switch (modus) {
                case GROESSE_UND_NAME -> "hash_" + path.getFileName() + "_" + Files.size(path);
                case GROESSE ->          "hash__" + Files.size(path);
                default -> "";
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<IDublette> pruefeKandidaten(List<IDublette> kandidaten) {
        List<IDublette> dubletten = new ArrayList<>();
        kandidaten.forEach(kandidat -> {
            List<String> dateipfade = kandidat.dateipfade();
            dubletten.addAll(pruefeKandidaten(dateipfade, new DublettenSammler()));
        });
        return dubletten;
    }

    private List<IDublette> pruefeKandidaten(List<String> dateipfade, DublettenSammler dublettenSammler) {
        dateipfade.forEach(dateipfad -> {
            Path path = fileSystem.getPath(dateipfad);
            dublettenSammler.addPathWithHash(path, getMD5Hash(path));
        });
        return dublettenSammler.getDubletten();
    }

    private String getMD5Hash(Path filePath) {
        try {
            byte[] data = Files.readAllBytes(filePath);
            byte[] hash = MessageDigest.getInstance("MD5").digest(data);
            return new BigInteger(1, hash).toString(16);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
