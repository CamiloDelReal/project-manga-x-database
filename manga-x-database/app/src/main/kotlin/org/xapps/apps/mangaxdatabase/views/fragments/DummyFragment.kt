package org.xapps.apps.mangaxdatabase.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.xapps.apps.mangaxdatabase.R
import javax.inject.Inject


class DummyFragment @Inject constructor(): Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val currentView = inflater.inflate(R.layout.fragment_dummy, container, false)
        return currentView
    }

}
