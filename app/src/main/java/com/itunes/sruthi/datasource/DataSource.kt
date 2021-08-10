package com.itunes.sruthi.datasource

import com.itunes.sruthi.model.ArtistItem

interface DataSource {
    suspend fun getArtistTracks(artistName: String): List<ArtistItem>
}