package com.itunes.sruthi

import androidx.lifecycle.ViewModelProvider
import com.itunes.sruthi.api.ArtistService
import com.itunes.sruthi.data.ArtistRepository
import com.itunes.sruthi.datasource.NetworkDataSource
import com.itunes.sruthi.ui.ViewModelFactory

object Injection {

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideRepository())
    }

    private fun provideRepository(): ArtistRepository {
        return ArtistRepository(provideDataSource())
    }

    private fun provideDataSource(): NetworkDataSource {
        return NetworkDataSource(ArtistService.create())
    }
}
