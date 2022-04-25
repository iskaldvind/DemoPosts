package studio.iskaldvind.demoposts.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import studio.iskaldvind.demoposts.model.Post
import studio.iskaldvind.demoposts.repository.PostsPageSource

class MainViewModel(
    private val pagingSource: PostsPageSource
) : ViewModel() {

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    val posts: StateFlow<PagingData<Post>> = Pager(PagingConfig(pageSize = 5)) {
        pagingSource
    }
        .flow
        .cachedIn(viewModelCoroutineScope)
        .stateIn(viewModelCoroutineScope, SharingStarted.Lazily, PagingData.empty())

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    private fun cancelJob() =
        viewModelCoroutineScope.coroutineContext.cancelChildren()

    private fun handleError(error: Throwable) {
        Log.e("VM", "Error ${error.stackTraceToString()}")
    }
}