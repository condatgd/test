package katas.dateidoubletten.impl_java;

import katas.dateidoubletten.IDublette;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Dublette implements IDublette {
    private final List<Path> paths;
    public Dublette(List<Path> paths) {
        this.paths = paths;
    }

    @Override
    public List<String> dateipfade() {
        return paths.stream().map(Path::toString).collect(Collectors.toList());
    }
}
