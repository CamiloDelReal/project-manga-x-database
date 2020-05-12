package org.xapps.apps.mangaxdatabase.views.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_listing.*
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.viewmodels.ListingViewModel
import org.xapps.apps.mangaxdatabase.viewmodels.ViewModelFactory
import org.xapps.apps.mangaxdatabase.views.adapters.ListingAdapter
import javax.inject.Inject


class ListingFragment @Inject constructor() : Fragment() {

    companion object {
        private const val LAYOUT_MANAGER_STATE = "LAYOUT_MANAGER_STATE"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: ListingViewModel by viewModels { viewModelFactory }

    private lateinit var listingAdapter: ListingAdapter
    private var listLayoutManagerState: Parcelable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_listing, container, false)
//        viewModel = viewModelFactory.create(ListingViewModel::class.java)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orientation = resources.configuration.orientation
        val columns = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            1
        } else {
            2
        }

        listingAdapter = ListingAdapter()
        listingList.layoutManager = GridLayoutManager(context, columns)
        listingList.adapter = listingAdapter

        viewModel.working().observe(viewLifecycleOwner, Observer {

        })

        viewModel.references().observe(viewLifecycleOwner, Observer { references ->
            listingAdapter.add(references)
        })

//        if (savedInstanceState != null) {
//            listLayoutManagerState = savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE)
//            listLayoutManagerState?.let {
//                Log.i("AppLogger", "Restoring layout manager")
//                listingList.layoutManager!!.onRestoreInstanceState(listLayoutManagerState)
//            }
//        }

        if(listingAdapter.references.isEmpty()) {
            viewModel.getReferences()
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//
//        listLayoutManagerState?.let {
//            outState.putParcelable(LAYOUT_MANAGER_STATE, listLayoutManagerState)
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//        listingList.layoutManager!!.onSaveInstanceState()?.let {
//            Log.i("AppLogger", "Salvado manager")
//            listLayoutManagerState = it
//        }
//    }
}
