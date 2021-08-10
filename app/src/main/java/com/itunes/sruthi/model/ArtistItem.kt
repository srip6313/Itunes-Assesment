package com.itunes.sruthi.model

import com.google.gson.annotations.SerializedName

data class ArtistItem(
        @SerializedName("trackId") val trackId: Long,
        @SerializedName("artistName") val artistName: String,
        @SerializedName("trackName") val trackName: String,
        @SerializedName("releaseDate") val releaseDate: String,
        @SerializedName("trackPrice") val trackPrice: Double,
        @SerializedName("primaryGenreName") val primaryGenreName: String
)
