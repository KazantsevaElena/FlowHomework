package otus.homework.flowcats

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class CatsRepository(
    private val catsService: CatsService,
    private val refreshIntervalMs: Long = 5000
) {
    private val tag = "CR"

    fun listenForCatFacts() = flow {
        while (true) {
            try {
                val latestNews = catsService.getCatFact()
                emit(latestNews)
            } catch (e: Throwable) {
                Log.e(tag, e.toString())
                emit(null)
            }
            delay(refreshIntervalMs)
        }
    }
}