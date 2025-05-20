package katas.dateidoubletten.impl_java;

import katas.dateidoubletten.IDublette;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DublettenSammler {
    private final Map<String, List<Path>> map = new HashMap<>();

    void addPathWithHash(Path path, String hash) {
        List<Path> hashFiles = map.computeIfAbsent(hash, l -> new ArrayList<>());
        hashFiles.add(path);
    }

    List<IDublette> getDubletten() {
        return map.values().stream()
                .filter(list -> list.size() > 1)
                .map(Dublette::new)
                .collect(Collectors.toList());
    }

}
