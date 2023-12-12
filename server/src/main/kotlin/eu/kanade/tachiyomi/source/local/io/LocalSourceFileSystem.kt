package eu.kanade.tachiyomi.source.local.io

import suwayomi.tachidesk.server.ApplicationDirs
import java.io.File

class LocalSourceFileSystem(
    private val applicationDirs: ApplicationDirs,
) {
    // private var allMangaDirecotry: Sequence<File>

    // init {
    //     // Get all the files(mangga Folder) under baseDir/{any manga source}
    //     allMangaDirecotry =
    //         getBaseDirectories()
    //             .flatMap { it.listFiles().orEmpty().toList() }
    //             .flatMap { it.listFiles().orEmpty().toList() }
    // }

    fun getBaseDirectories(): Sequence<File> {
        return sequenceOf(File(applicationDirs.localMangaRoot))
    }

    fun getFilesInBaseDirectories(): Sequence<File> {
        return getBaseDirectories()
            // Get all the files inside all baseDir
            .flatMap { it.listFiles().orEmpty().toList() }
    }

    fun getMangaDirectory(name: String): File? {
        var parts = name.split('/')
        var source = parts[0]
        var mangaName = parts[1]

        var mangaDirectory =
            getFilesInBaseDirectories()
                // Get the first mangaDir or null
                .firstOrNull { it.isDirectory && it.name == source }
        return mangaDirectory?.listFiles().orEmpty().toList()
            .firstOrNull { it.isDirectory && it.name == mangaName }
    }

    fun getFilesInMangaDirectory(name: String): Sequence<File> {
        var filesUnderMangaDirectory =
            getMangaDirectory(name)
                // Filter out ones that are not related to the manga and is not a directory
//            .filter { it.isDirectory && it.name == name }
                // Get all the files inside the filtered folders
                ?.listFiles().orEmpty().asSequence()
        return filesUnderMangaDirectory
    }
}
