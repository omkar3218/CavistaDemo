package com.omkar.cavistademo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omkar.cavistademo.data.local.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ImageDetailsViewModel @Inject constructor(override var coroutineContext: CoroutineContext) :
    ViewModel(), CoroutineScope {

    var comment = MutableLiveData<String>()

    var isSaved = MutableLiveData<Boolean>()

    fun fetchCommentFromDB(imageId: String) {
        launch {
            comment.postValue(AppDatabase.instance?.getCommentForTheImage(imageId))
        }
    }

    fun saveCommentInDB(imageId: String, comment: String) {
        launch {
            isSaved.postValue(AppDatabase.instance?.saveCommentForTheImage(imageId, comment))
        }
    }

}
