package com.yerayrm.feature.herolist.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.yerayrm.feature.herolist.R
import com.yerayrm.feature.herolist.databinding.ActivityHeroesBinding

class HeroesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityHeroesBinding>(this, R.layout.activity_heroes)
    }
}
