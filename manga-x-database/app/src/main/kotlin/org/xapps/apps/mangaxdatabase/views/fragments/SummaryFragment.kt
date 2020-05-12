package org.xapps.apps.mangaxdatabase.views.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_listing.*
import kotlinx.android.synthetic.main.fragment_summary.*
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.viewmodels.SummaryViewModel
import org.xapps.apps.mangaxdatabase.viewmodels.ViewModelFactory
import org.xapps.apps.mangaxdatabase.views.activities.HomeActivity
import org.xapps.apps.mangaxdatabase.views.adapters.SummaryAdapter
import org.xapps.apps.mangaxdatabase.views.layoutmanagers.SpannedGridLayoutManager
import javax.inject.Inject


class SummaryFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SummaryViewModel

    private lateinit var summaryAdapter: SummaryAdapter
    private val listener = object: SummaryAdapter.Listener {
        override fun clicked(referenceId: Long) {
            (parentFragment as HomeFragment).showDetails(referenceId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_summary, container, false)
        viewModel = viewModelFactory.create(SummaryViewModel::class.java)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orientation = resources.configuration.orientation
        val columns = if(orientation == Configuration.ORIENTATION_PORTRAIT) { 2 } else { 4 }

        summaryAdapter = SummaryAdapter(listener)
//        summaryList.layoutManager =
//            SpannedGridLayoutManager(object : SpannedGridLayoutManager.GridSpanLookup {
//                override fun getSpanInfo(position: Int): SpannedGridLayoutManager.SpanInfo {
//                    return when (position) {
//                        0 -> SpannedGridLayoutManager.SpanInfo(2, 1)
//                        3 -> SpannedGridLayoutManager.SpanInfo(1, 2)
//                        7 -> SpannedGridLayoutManager.SpanInfo(1, 2)
//                        else -> SpannedGridLayoutManager.SpanInfo(1, 1)
//                    }
//                }
//
//            }, columns, 1f)
        val gridLayoutManager = GridLayoutManager(context, columns)
        gridLayoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    0 -> 2
                    else -> 1
                }
            }
        }
        summaryList.layoutManager = gridLayoutManager
        summaryList.adapter = summaryAdapter

        viewModel.working().observe(viewLifecycleOwner, Observer {

        })

        viewModel.references().observe(viewLifecycleOwner, Observer { references ->
            summaryAdapter.add(references)
        })

        if(summaryAdapter.references.isEmpty()) {
            viewModel.getReferences()
        }
    }

}
