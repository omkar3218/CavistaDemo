package com.omkar.cavistademo.data.local

import com.omkar.cavistademo.BuildConfig
import com.omkar.cavistademo.data.model.Image
import io.realm.Realm
import io.realm.RealmConfiguration

class AppDatabase private constructor() {


    private fun prepareRealmInstance() {
        val config = RealmConfiguration.Builder().name("search.realm")
            .schemaVersion(BuildConfig.SCHEMA_VERSION.toLong())
            .build()
        Realm.setDefaultConfiguration(config)
        Realm.compactRealm(config)
    }

    fun closeDB() {
        if (Realm.getDefaultInstance() != null) Realm.getDefaultInstance()!!.close()
    }


    fun getCommentForTheImage(imageId: String): String? {
        return Realm.getDefaultInstance()!!.where(Image::class.java)
            .equalTo("id", imageId).findFirst()?.comments
    }

    fun isExistingCommentForTheImage(imageId: String,comment: String): Boolean {
        val image: Image? =
            Realm.getDefaultInstance()!!.where(Image::class.java)
                .equalTo("id", imageId).findFirst()
        return image!=null && image.comments==comment
    }


    fun saveCommentForTheImage(imageId: String, comment: String): Boolean {
        val image: Image? =
            Realm.getDefaultInstance()!!.where(Image::class.java)
                .equalTo("id", imageId).findFirst()
        return if (image != null) {
            with(Realm.getDefaultInstance()) {
                beginTransaction()
                image.comments = comment
                copyToRealmOrUpdate(image)
                commitTransaction()
                true
            }
        } else {
            with(Realm.getDefaultInstance()) {
                beginTransaction()
                val newImage = Image()
                newImage.id = imageId
                newImage.comments = comment
                copyToRealmOrUpdate(newImage)
                commitTransaction()
                true
            }
        }

    }

    companion object {
        private var appDatabase: AppDatabase? = null

        val instance: AppDatabase?
            get() {
                if (appDatabase == null)
                    appDatabase = AppDatabase()
                return appDatabase
            }
    }

    init {
        prepareRealmInstance()
    }
}