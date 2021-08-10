package com.itunes.sruthi.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.itunes.sruthi.data.ArtistRepository
import com.itunes.sruthi.model.ArtistItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArtistViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: ArtistRepository

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var resultObserver: Observer<List<ArtistItem>>

    @Mock
    private lateinit var progressObserver: Observer<Int>

    @Mock
    private lateinit var errorObserver: Observer<Int>

    @Test
    fun `givenServer Response 200 When Fetch Should Return Success`() {
        val list = ArrayList<ArtistItem>()
        list.add(ArtistItem(1, "Artist1", "Track1", "08-09-2021", 3.4, "PrimaryGenre"))
        testCoroutineRule.runBlockingTest {
            doReturn(list)
                    .`when`(repository)
                    .getArtistTracks("artist1")
            val viewModel = ArtistViewModel(repository)
            viewModel.result.observeForever(resultObserver)
            viewModel.progress.observeForever(progressObserver)
            viewModel.getArtistTracks("artist1")
            verify(repository).getArtistTracks("artist1")
            verify(resultObserver).onChanged(list)
            verify(progressObserver, times(2)).onChanged(ArgumentMatchers.anyInt())
            viewModel.result.removeObserver(resultObserver)
            viewModel.progress.removeObserver(progressObserver)
        }
    }

    @Test
    fun `given Server Response When Fetch Should Return Response`() {
        testCoroutineRule.runBlockingTest {
            doReturn(Exception())
                    .`when`(repository)
                    .getArtistTracks("artist1")
            val viewModel = ArtistViewModel(repository)
            viewModel.error.observeForever(errorObserver)
            viewModel.progress.observeForever(progressObserver)
            viewModel.getArtistTracks("artist1")
            verify(repository).getArtistTracks("artist1")
            verify(progressObserver, times(2)).onChanged(ArgumentMatchers.anyInt())
            viewModel.error.removeObserver(errorObserver)
            viewModel.progress.removeObserver(progressObserver)
        }
    }

}