package org.xapps.apps.mangaxdatabase.views.fragments

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.viewmodels.HomeViewModel
import org.xapps.apps.mangaxdatabase.viewmodels.ViewModelFactory
import org.xapps.apps.mangaxdatabase.views.activities.HomeActivity
import org.xapps.apps.mangaxdatabase.views.adapters.HomeTabsAdapter
import javax.inject.Inject


class HomeFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var summaryFragment: SummaryFragment
    @Inject
    lateinit var listingFragment: ListingFragment

    private lateinit var currentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentView = inflater.inflate(R.layout.fragment_home, container, false)
        ViewCompat.setTranslationZ(currentView, 1f)
        setHasOptionsMenu(true)
        viewModel = viewModelFactory.create(HomeViewModel::class.java)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeActivity = (activity as HomeActivity)
        homeActivity.setSupportActionBar(toolbar)

        val orientation = resources.configuration.orientation

        viewPager.adapter = HomeTabsAdapter(
            this, listOf(
                summaryFragment,
                listingFragment
            )
        )
        val titles = listOf(
            getString(R.string.title_summary),
            getString(R.string.title_listing)
        )
        val icons = listOf(
            R.drawable.ic_summary_24dp,
            R.drawable.ic_listing_24dp
        )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                tab.setIcon(icons[position])
            } else {
                tab.text = titles[position]
                tab.setIcon(icons[position])
            }
        }.attach()

        btnAdd.setOnClickListener {
            showDetails()
        }
    }

    fun showDetails(referenceId: Long? = null) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsActivity(referenceId?.let { it } ?: run { 0L }))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionSearch -> {
                return true
            }
            R.id.actionSettings -> {
                return true
            }
            R.id.actionAbout -> {
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
