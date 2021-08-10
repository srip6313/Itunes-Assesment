package com.itunes.sruthi.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.itunes.sruthi.model.ArtistItem

class TrackAdapter : ListAdapter<ArtistItem, TrackViewHolder>(TRACK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(repoItem)
        }
    }

    companion object {
        private val TRACK_COMPARATOR = object : DiffUtil.ItemCallback<ArtistItem>() {
            override fun areItemsTheSame(oldItem: ArtistItem, newItem: ArtistItem): Boolean =
                oldItem.trackId == newItem.trackId

            override fun areContentsTheSame(oldItem: ArtistItem, newItem: ArtistItem): Boolean =
                oldItem == newItem
        }
    }
}
