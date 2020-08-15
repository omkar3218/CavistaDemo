package com.omkar.cavistademo.ui.viewmodel

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.*
import com.omkar.cavistademo.data.model.Data
import com.omkar.cavistademo.data.remote.RemoteRepository
import com.omkar.cavistademo.data.remote.Resource
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ImageListViewModel @Inject constructor(override var coroutineContext: CoroutineContext) :
    ViewModel(), CoroutineScope {

    @Inject
    lateinit var remoteRepository: RemoteRepository

    var isLoading = MutableLiveData<Boolean>()

    var articleLiveData = MutableLiveData<List<Data>>()


    /**
     *  Fetch the image list based on the search term entered by the user and page no.
     */
    fun fetchImageList(pageNumber: Int, searchTerm: String) {
        launch {
            isLoading.postValue(true)
            when (val response = remoteRepository.requestImages(pageNumber, searchTerm)) {
                is Resource.Success -> {
                    isLoading.postValue(false)
                    articleLiveData.postValue(response.data?.data)
                }
                is Resource.DataError -> {
                    isLoading.postValue(false)

                }
            }

        }
    }

    /**
     *  Debounce the search after every 250 milliseconds
     */
    internal class DebouncingQueryTextListener(
        lifecycle: Lifecycle,
        private val onDebouncingQueryTextChange: (String?) -> Unit
    ) : SearchView.OnQueryTextListener,
        LifecycleObserver {
        private var debouncePeriod: Long = 250

        private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

        private var searchJob: Job? = null

        init {
            lifecycle.addObserver(this)
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            searchJob?.cancel()
            searchJob = coroutineScope.launch {
                newText?.let {
                    delay(debouncePeriod)
                    onDebouncingQueryTextChange(newText)
                }
            }
            return false
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        private fun destroy() {
            searchJob?.cancel()
        }
    }


}
