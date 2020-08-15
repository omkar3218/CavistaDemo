package com.omkar.cavistademo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Image : RealmObject() {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("datetime")
    @Expose
    var datetime = 0L

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("animated")
    @Expose
    var isAnimated = false

    @SerializedName("width")
    @Expose
    var width = 0L

    @SerializedName("height")
    @Expose
    var height = 0L

    @SerializedName("size")
    @Expose
    var size = 0

    @SerializedName("views")
    @Expose
    var views = 0

    @SerializedName("bandwidth")
    @Expose
    var bandwidth = 0L

    @SerializedName("vote")
    @Expose
    var vote: String? = null

    @SerializedName("favorite")
    @Expose
    var isFavorite = false

    @SerializedName("nsfw")
    @Expose
    var nsfw: String? = null

    @SerializedName("section")
    @Expose
    var section: String? = null

    @SerializedName("account_url")
    @Expose
    var accountUrl: String? = null

    @SerializedName("account_id")
    @Expose
    var accountId: String? = null

    @SerializedName("is_ad")
    @Expose
    var isIsAd = false
        private set

    @SerializedName("in_most_viral")
    @Expose
    var isInMostViral = false

    @SerializedName("has_sound")
    @Expose
    var isHasSound = false

    @SerializedName("ad_type")
    @Expose
    var adType = 0

    @SerializedName("ad_url")
    @Expose
    var adUrl: String? = null

    @SerializedName("edited")
    @Expose
    var edited: String? = null

    @SerializedName("in_gallery")
    @Expose
    var isInGallery = false

    @SerializedName("link")
    @Expose
    var link: String? = null

    @SerializedName("comment_count")
    @Expose
    var commentCount: String? = null

    @SerializedName("favorite_count")
    @Expose
    var favoriteCount: String? = null

    @SerializedName("ups")
    @Expose
    var ups: String? = null

    @SerializedName("downs")
    @Expose
    var downs: String? = null

    @SerializedName("points")
    @Expose
    var points: String? = null

    @SerializedName("score")
    @Expose
    var score: String? = null

    fun setIsAd(isAd: Boolean) {
        isIsAd = isAd
    }

    var comments: String? = null

    var imageTitle: String? = null


}