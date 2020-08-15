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

    var isExistingComment = MutableLiveData<Boolean>()


    /**
     *  Fetch the comment from Database based on the image id and update the user
     */
    fun fetchCommentFromDB(imageId: String) {
        launch {
            comment.postValue(AppDatabase.instance?.getCommentForTheImage(imageId))
        }
    }

    /**
     *  Save the comment in Database and update the user
     */
    fun saveCommentInDB(imageId: String, comment: String) {
        launch {
            isSaved.postValue(AppDatabase.instance?.saveCommentForTheImage(imageId, comment))
        }
    }

    /**
     *  Check if the comment for the image is already exists in the database and update the user
     */
    fun isExistingCommentForTheImage(imageId: String, comment: String) {
        launch {
            isExistingComment.postValue(AppDatabase.instance?.isExistingCommentForTheImage(imageId, comment))
        }
    }

}
