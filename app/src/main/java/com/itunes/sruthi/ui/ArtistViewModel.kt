package com.itunes.sruthi.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itunes.sruthi.R
import com.itunes.sruthi.data.ArtistRepository
import com.itunes.sruthi.model.ArtistItem
import kotlinx.coroutines.launch

class ArtistViewModel(private val repository: ArtistRepository) : ViewModel() {

    var progress = MutableLiveData<Int>()
    var error = MutableLiveData<Int>()
    var result = MutableLiveData<List<ArtistItem>>()

    init {
        progress.postValue(View.GONE)
    }

    fun getArtistTracks(artistName: String) {
        viewModelScope.launch {
            if(artistName.isNotEmpty()) {
                progress.postValue(View.VISIBLE)
                try {
                    result.postValue(repository.getArtistTracks(artistName))
                } catch (e: Exception) {
                    error.postValue(R.string.retry_error)
                } finally {
                    progress.postValue(View.GONE)
                }
            } else {
                error.postValue(R.string.search_term_blank)
            }
        }
    }
}
