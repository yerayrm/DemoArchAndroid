package com.yerayrm.feature.herolist.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.yerayrm.feature.herolist.R
import com.yerayrm.feature.herolist.adapters.HeroAdapter
import com.yerayrm.feature.herolist.databinding.FragmentHeroListBinding
import com.yerayrm.feature.herolist.viewmodels.HeroListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroListFragment : Fragment() {

    private val viewModel: HeroListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHeroListBinding.inflate(inflater, container, false)

        val adapter = HeroAdapter()
        binding.heroList.adapter = adapter
        subscribeUi(adapter)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHeroes()
    }

    private fun subscribeUi(adapter: HeroAdapter) {
        viewModel.heroes.observe(viewLifecycleOwner) { heroes ->
            adapter.submitList(heroes)
        }

        viewModel.showError.observe(viewLifecycleOwner) { mustShowError ->
            if (mustShowError)
                showErrorMessage()
        }
    }

    private fun showErrorMessage() {
        Snackbar.make(requireView(), R.string.error_getting_heroes, Snackbar.LENGTH_LONG).show()
    }
}
