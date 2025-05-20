package katas.dateidoubletten.impl_kotlin

import katas.dateidoubletten.IDublette
import katas.dateidoubletten.IDublettenpruefung
import katas.dateidoubletten.Vergleichsmodi
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.name
import kotlin.io.path.pathString

class Dublettenpruefung : IDublettenpruefung {


    override fun sammleKandidaten(pfad: String): List<IDublette> {
        return sammleKandidaten(pfad, Vergleichsmodi.GROESSE_UND_NAME)
    }

    override fun sammleKandidaten(pfad: String, modus: Vergleichsmodi): List<IDublette> {
        return sammleKandidaten(Path(pfad), modus, HashMap<String, MutableList<Path>>())
    }

    private fun sammleKandidaten(pPath: Path, modus: Vergleichsmodi, map: java.util.HashMap<String, MutableList<Path>>): List<IDublette> {
        return try {
            File(pPath.name).walkTopDown().forEach {
                println(it)
                if (it.isFile) {
                    addPathWithHashToMap(map, it.toPath(), getHash(it.toPath(), modus))
                }
                if (it.isDirectory && !it.path.equals(pPath.pathString)) {
                    sammleKandidaten(it.toPath(), modus, map) // recursion
                }
            }
            return getDublettenFromMap(map)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    private fun getDublettenFromMap(map: java.util.HashMap<String, MutableList<Path>>): List<IDublette> {
        /*
        return map.values.stream()
            .filter { list -> list.size > 1 }
            .map {Dublette(it)}
            .toList()

         */
        return emptyList()
    }

    private fun addPathWithHashToMap(map: java.util.HashMap<String, MutableList<Path>>, path: Path, hash: String) {
        val hashFiles = map.computeIfAbsent(hash) { l: String -> mutableListOf() }
        hashFiles.add(path)
    }

    private fun getHash(path: Path, modus: Vergleichsmodi): String {
        return try {
            when (modus) {
                Vergleichsmodi.GROESSE_UND_NAME -> {
                    "hash_" + path.fileName + "_" + Files.size(path)
                }
                Vergleichsmodi.GROESSE -> {
                    "hash__" + Files.size(path)
                }
            }
        } catch (e: IOException) {
            throw java.lang.RuntimeException(e)
        }
    }

    override fun pruefeKandidaten(kandidaten: List<IDublette>?): List<IDublette> {
        TODO("Not yet implemented")
    }
}

fun main() {
    val dp = Dublettenpruefung()
    val sammleKandidaten = dp.sammleKandidaten("C:\\workspace\\content-service\\src")
    println(sammleKandidaten)
}
