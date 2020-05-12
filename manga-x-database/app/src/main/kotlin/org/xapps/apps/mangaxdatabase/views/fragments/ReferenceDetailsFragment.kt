package org.xapps.apps.mangaxdatabase.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.viewmodels.ReferenceDetailsViewModel
import org.xapps.apps.mangaxdatabase.viewmodels.ViewModelFactory
import javax.inject.Inject


class ReferenceDetailsFragment @Inject constructor(): Fragment() {

    private val args: ReferenceDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ReferenceDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val currentView = inflater.inflate(R.layout.fragment_reference_details, container, false)
        viewModel = viewModelFactory.create(ReferenceDetailsViewModel::class.java)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("AppLogger", "Reference id ${args.referenceId}")
    }
}
