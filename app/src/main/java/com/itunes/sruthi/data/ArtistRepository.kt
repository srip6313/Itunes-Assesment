package com.itunes.sruthi.data

import com.itunes.sruthi.datasource.DataSource
import com.itunes.sruthi.datasource.NetworkDataSource
import com.itunes.sruthi.model.ArtistItem

open class ArtistRepository(private val dataSource: NetworkDataSource): DataSource {

    override suspend fun getArtistTracks(artistName: String): List<ArtistItem> {
        return dataSource.getArtistTracks(artistName)
    }
}
