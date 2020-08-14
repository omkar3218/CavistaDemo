package com.omkar.cavistademo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResponse {
    @SerializedName("data")
    @Expose
    var data: List<Data>? = null

    @SerializedName("success")
    @Expose
    var isSuccess = false

    @SerializedName("status")
    @Expose
    var status = 0

}