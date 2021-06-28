package dev.patrick.dncassignment.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.patrick.dncassignment.presentation.ui.model.PageType

const val API_PAGE = 0
const val FAVORITE_PAGE = 1

class UserPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        API_PAGE to { SearchFragment.getInstance(PageType.API) },
        FAVORITE_PAGE to { SearchFragment.getInstance(PageType.FAVORITE) }
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment = tabFragmentsCreators[position]?.invoke() ?:
        throw IndexOutOfBoundsException()
}