package com.yerayrm.feature.herolist.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.yerayrm.feature.herolist.R
import com.yerayrm.feature.herolist.databinding.FragmentHeroDetailBinding

class HeroDetailFragment : Fragment() {

    val args: HeroDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentHeroDetailBinding>(
            inflater,
            R.layout.fragment_hero_detail,
            container,
            false
        ).apply {
            hero = args.hero

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }

            var isToolbarShown = false
            heroDetailScroll.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
                    val shouldShowToolbar = scrollY > toolbar.height
                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar

                        appbar.isActivated = shouldShowToolbar

                        toolbarLayout.isTitleEnabled = shouldShowToolbar
                    }
                }
            )
        }

        return binding.root
    }
}