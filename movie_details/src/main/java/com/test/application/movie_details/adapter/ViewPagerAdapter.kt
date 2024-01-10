package com.test.application.movie_details.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.application.movie_details.view.MovieCastFragment
import com.test.application.movie_details.view.MovieCommentsFragment
import com.test.application.movie_details.view.MovieInfoFragment
import com.test.application.movie_details.view.SimilarMovieFragment

class ViewPagerAdapter(
    parentFragment: Fragment
) : FragmentStateAdapter(parentFragment) {

    val tabTitles = listOf("Информация", "В ролях", "Комментарии", "Похожие фильмы")
    override fun getItemCount(): Int = tabTitles.size

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MovieInfoFragment()
            1 -> MovieCastFragment()
            2 -> MovieCommentsFragment()
            3 -> SimilarMovieFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}