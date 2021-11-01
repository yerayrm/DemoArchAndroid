package com.yerayrm.feature.herolist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yerayrm.dm.heroes.model.Hero
import com.yerayrm.feature.herolist.databinding.ListItemHeroBinding
import com.yerayrm.feature.herolist.views.HeroListFragmentDirections

class HeroAdapter : ListAdapter<Hero, RecyclerView.ViewHolder>(HeroDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HeroItemHolder(
            ListItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hero = getItem(position)
        (holder as HeroItemHolder).bind(hero)
    }

    class HeroItemHolder(
        private val binding: ListItemHeroBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {  view ->
                binding.hero?.let { hero ->
                    navigateToHeroDetail(hero, view)
                }
            }
        }

        private fun navigateToHeroDetail(hero: Hero, view: View) {
            HeroListFragmentDirections.actionHeroListToHeroDetail(hero).let {
                view.findNavController().navigate(it)
            }
        }

        fun bind(item: Hero) {
            binding.apply {
                hero = item
                executePendingBindings()
            }
        }
    }
}

private class HeroDiffCallback : DiffUtil.ItemCallback<Hero>() {

    override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem == newItem
    }
}