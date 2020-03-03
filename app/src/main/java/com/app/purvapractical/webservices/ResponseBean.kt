package com.app.purvapractical.webservices

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseBean {
    @Expose
    @SerializedName("results")
    var results: List<ResultsEntity>? = null
    @Expose
    @SerializedName("resultCount")
    var resultCount = 0

    class ResultsEntity {
        @Expose
        @SerializedName("isStreamable")
        var isStreamable = false
        @Expose
        @SerializedName("primaryGenreName")
        var primaryGenreName: String? = null
        @Expose
        @SerializedName("currency")
        var currency: String? = null
        @Expose
        @SerializedName("country")
        var country: String? = null
        @Expose
        @SerializedName("trackTimeMillis")
        var trackTimeMillis = 0
        @Expose
        @SerializedName("trackNumber")
        var trackNumber = 0
        @Expose
        @SerializedName("trackCount")
        var trackCount = 0
        @Expose
        @SerializedName("discNumber")
        var discNumber = 0
        @Expose
        @SerializedName("discCount")
        var discCount = 0
        @Expose
        @SerializedName("trackExplicitness")
        var trackExplicitness: String? = null
        @Expose
        @SerializedName("collectionExplicitness")
        var collectionExplicitness: String? = null
        @Expose
        @SerializedName("releaseDate")
        var releaseDate: String? = null
        @Expose
        @SerializedName("trackPrice")
        var trackPrice = 0.0
        @Expose
        @SerializedName("collectionPrice")
        var collectionPrice = 0.0
        @Expose
        @SerializedName("artworkUrl100")
        var artworkUrl100: String? = null
        @Expose
        @SerializedName("artworkUrl60")
        var artworkUrl60: String? = null
        @Expose
        @SerializedName("artworkUrl30")
        var artworkUrl30: String? = null
        @Expose
        @SerializedName("previewUrl")
        var previewUrl: String? = null
        @Expose
        @SerializedName("trackViewUrl")
        var trackViewUrl: String? = null
        @Expose
        @SerializedName("collectionViewUrl")
        var collectionViewUrl: String? = null
        @Expose
        @SerializedName("artistViewUrl")
        var artworkUrl100artistViewUrl: String? = null
        @Expose
        @SerializedName("trackCensoredName")
        var trackCensoredName: String? = null
        @Expose
        @SerializedName("collectionCensoredName")
        var collectionCensoredName: String? = null
        @Expose
        @SerializedName("trackName")
        var trackName: String? = null
        @Expose
        @SerializedName("collectionName")
        var collectionName: String? = null
        @Expose
        @SerializedName("artistName")
        var artistName: String? = null
        @Expose
        @SerializedName("trackId")
        var trackId = 0
        @Expose
        @SerializedName("collectionId")
        var collectionId = 0
        @Expose
        @SerializedName("artistId")
        var artistId = 0
        @Expose
        @SerializedName("kind")
        var kind: String? = null
        @Expose
        @SerializedName("wrapperType")
        var wrapperType: String? = null
    }
}