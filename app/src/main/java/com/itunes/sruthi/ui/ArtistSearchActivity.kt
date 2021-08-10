package com.itunes.sruthi.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.itunes.sruthi.Injection
import com.itunes.sruthi.databinding.ActivityArtistSearchBinding

class ArtistSearchActivity : AppCompatActivity() {

    private val adapter = TrackAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityArtistSearchBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@ArtistSearchActivity
            val vm = ViewModelProvider(this@ArtistSearchActivity, Injection.provideViewModelFactory())
                    .get(ArtistViewModel::class.java).apply {
                        error.observe(this@ArtistSearchActivity, Observer {
                            Toast.makeText(this@ArtistSearchActivity, getString(it), Toast.LENGTH_SHORT).show()
                        })
                        result.observe(this@ArtistSearchActivity, Observer {
                            adapter.submitList(it)
                        })
                    }
            viewModel = vm
            val decoration = DividerItemDecoration(this@ArtistSearchActivity, DividerItemDecoration.VERTICAL)
            tracks.let {
                it.adapter = this@ArtistSearchActivity.adapter
                it.addItemDecoration(decoration)
            }
            search.setOnClickListener {
                vm.getArtistTracks(searchTerm.text.toString())
            }
        }
        setContentView(binding.root)
    }
}
