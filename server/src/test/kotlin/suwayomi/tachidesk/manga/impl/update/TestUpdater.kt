package suwayomi.tachidesk.manga.impl.update

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import suwayomi.tachidesk.manga.model.dataclass.CategoryDataClass
import suwayomi.tachidesk.manga.model.dataclass.MangaDataClass
import java.util.concurrent.CopyOnWriteArrayList

class TestUpdater : IUpdater {
    private val updateQueue = CopyOnWriteArrayList<UpdateJob>()
    private var isRunning = false
    private val _status = MutableStateFlow(UpdateStatus())

    override fun getLastUpdateTimestamp(): Long {
        TODO("Not yet implemented")
    }

    override fun addCategoriesToUpdateQueue(
        categories: List<CategoryDataClass>,
        clear: Boolean?,
        forceAll: Boolean,
    ) {
        TODO("Not yet implemented")
    }

    override val status: StateFlow<UpdateStatus> = _status.asStateFlow()

    fun addMangasToQueue(mangas: List<MangaDataClass>) {
        mangas.forEach { updateQueue.add(UpdateJob(it)) }
        isRunning = true
        updateStatus()
    }

    override fun reset() {
        updateQueue.clear()
        isRunning = false
        updateStatus()
    }

    private fun updateStatus() {
        // _status.update { UpdateStatus(updateQueue.toList(), isRunning) }
    }
}
