package katas.dateidoubletten.impl_kotlin

import katas.dateidoubletten.IDublette
import java.nio.file.Path


class Dublette(paths: List<Path>) : IDublette {
    private var pfade: List<Path> = paths
    override fun dateipfade(): List<String> {
       // return pfade.stream().map(Path::toString).toList()
        return emptyList()
    }

}