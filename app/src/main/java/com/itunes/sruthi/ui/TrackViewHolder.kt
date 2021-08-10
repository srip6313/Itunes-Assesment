package com.itunes.sruthi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itunes.sruthi.databinding.TrackViewItemBinding
import com.itunes.sruthi.model.ArtistItem

class TrackViewHolder(private val binding: TrackViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(artistItem: ArtistItem?) {
        binding.item = artistItem
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): TrackViewHolder {
            val binding = TrackViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TrackViewHolder(binding)
        }
    }
}
