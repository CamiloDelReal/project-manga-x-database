package org.xapps.apps.mangaxdatabase.views.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class HomeTabsAdapter(
    fragment: Fragment,
    var views: List<Fragment>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =
        views.size

    override fun createFragment(position: Int): Fragment =
        views[position]

}