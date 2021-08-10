package com.itunes.sruthi.datasource

import com.itunes.sruthi.api.ArtistService
import com.itunes.sruthi.model.ArtistItem

class NetworkDataSource(private val service: ArtistService): DataSource {
    override suspend fun getArtistTracks(artistName: String): List<ArtistItem> {
        return service.getArtistTracks(artistName).results
    }
}