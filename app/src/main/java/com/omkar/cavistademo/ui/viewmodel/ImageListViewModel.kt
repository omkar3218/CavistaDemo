package com.omkar.cavistademo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omkar.cavistademo.data.model.Data
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ImageListViewModel @Inject constructor(override var coroutineContext: CoroutineContext) :
    ViewModel(), CoroutineScope {


    var isLoading = MutableLiveData<Boolean>()


    var articleLiveData = MutableLiveData<List<Data>>()


}
