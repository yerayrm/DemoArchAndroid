package com.yerayrm.feature.herolist.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.yerayrm.dm.heroes.data.repository.HeroRepository
import com.yerayrm.dm.heroes.model.Hero
import com.yerayrm.dm.heroes.utils.DataResult
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class HeroListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    private val repository: HeroRepository = mockk()
    private lateinit var viewModel: HeroListViewModel

    private val heroesObserver: Observer<List<Hero>> = mockk(relaxed = true)
    private val showErrorObserver = mockk<Observer<Boolean>>(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        viewModel = HeroListViewModel(repository)

        viewModel.heroes.observeForever(heroesObserver)
        viewModel.showError.observeForever(showErrorObserver)
    }

    @Test
    fun `if the list of heroes is empty, the user will see an error`() {
        val slot = slot<Boolean>()
        val result = arrayListOf<Boolean>()

        every {
            showErrorObserver.onChanged(capture(slot))
        } answers {
            result.add(slot.captured)
        }

        coEvery {
            repository.getHeroes()
        } returns DataResult.Success(emptyList())

        viewModel.getHeroes()

        assertTrue(result.last())
    }

    @Test
    fun `if an exception happens while trying to get the heroes, the user will see an error`() {
        val slot = slot<Boolean>()
        val result = arrayListOf<Boolean>()

        every {
            showErrorObserver.onChanged(capture(slot))
        } answers {
            result.add(slot.captured)
        }

        coEvery {
            repository.getHeroes()
        } returns DataResult.Error(mockk())

        viewModel.getHeroes()

        assertTrue(result.last())
    }

    @Test
    fun `if the list of heroes has one hero at least, the user will see the list of heroes`() {
        val slot = slot<List<Hero>>()
        val result = arrayListOf<List<Hero>>()

        val mockedHero = mockk<Hero>()

        every {
            heroesObserver.onChanged(capture(slot))
        } answers {
            result.add(slot.captured)
        }

        coEvery {
            repository.getHeroes()
        } returns DataResult.Success(listOf(mockedHero))

        viewModel.getHeroes()

        assertEquals(mockedHero, result.last()[0])
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}