package com.omkar.cavistademo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class Data : RealmObject(){
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("datetime")
    @Expose
    var datetime = 0

    @SerializedName("cover")
    @Expose
    var cover: String? = null

    @SerializedName("cover_width")
    @Expose
    var coverWidth = 0

    @SerializedName("cover_height")
    @Expose
    var coverHeight = 0

    @SerializedName("account_url")
    @Expose
    var accountUrl: String? = null

    @SerializedName("account_id")
    @Expose
    var accountId = 0

    @SerializedName("privacy")
    @Expose
    var privacy: String? = null

    @SerializedName("layout")
    @Expose
    var layout: String? = null

    @SerializedName("views")
    @Expose
    var views = 0

    @SerializedName("link")
    @Expose
    var link: String? = null

    @SerializedName("ups")
    @Expose
    var ups = 0

    @SerializedName("downs")
    @Expose
    var downs = 0

    @SerializedName("points")
    @Expose
    var points = 0

    @SerializedName("score")
    @Expose
    var score = 0

    @SerializedName("is_album")
    @Expose
    var isIsAlbum = false
        private set

    @SerializedName("vote")
    @Expose
    var vote: String? = null

    @SerializedName("favorite")
    @Expose
    var isFavorite = false

    @SerializedName("nsfw")
    @Expose
    var isNsfw = false

    @SerializedName("section")
    @Expose
    var section: String? = null

    @SerializedName("comment_count")
    @Expose
    var commentCount = 0

    @SerializedName("favorite_count")
    @Expose
    var favoriteCount = 0

    @SerializedName("topic")
    @Expose
    var topic: String? = null

    @SerializedName("topic_id")
    @Expose
    var topicId = 0

    @SerializedName("images_count")
    @Expose
    var imagesCount = 0

    @SerializedName("in_gallery")
    @Expose
    var isInGallery = false


    @SerializedName("ad_type")
    @Expose
    var adType = 0

    @SerializedName("ad_url")
    @Expose
    var adUrl: String? = null

    @SerializedName("in_most_viral")
    @Expose
    var isInMostViral = false

    @SerializedName("include_album_ads")
    @Expose
    var isIncludeAlbumAds = false

    @SerializedName("images")
    @Expose
    var images: RealmList<Image>? = null


    fun setIsAlbum(isAlbum: Boolean) {
        isIsAlbum = isAlbum
    }


}