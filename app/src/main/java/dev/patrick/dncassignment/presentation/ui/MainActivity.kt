package dev.patrick.dncassignment.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.patrick.dncassignment.R
import dev.patrick.dncassignment.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        viewPager.adapter = UserPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        setSupportActionBar(binding.toolbar)
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            API_PAGE -> getString(R.string.api)
            FAVORITE_PAGE -> getString(R.string.local)
            else -> throw IndexOutOfBoundsException("Not available index")
        }
    }
}