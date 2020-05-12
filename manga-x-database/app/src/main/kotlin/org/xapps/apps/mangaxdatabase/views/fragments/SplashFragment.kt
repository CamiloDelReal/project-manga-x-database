package org.xapps.apps.mangaxdatabase.views.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.viewmodels.SplashViewModel
import org.xapps.apps.mangaxdatabase.viewmodels.ViewModelFactory
import javax.inject.Inject


class SplashFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SplashViewModel

    private var initialized = false
    private var splashFinished = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_splash, container, false)
        viewModel = viewModelFactory.create(SplashViewModel::class.java)
        ViewCompat.setTranslationZ(currentView, 2f)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initialized().observe(viewLifecycleOwner, Observer {
            initialized = true
            Log.i("AppLogger", "Initialized $initialized, $splashFinished")
            if(initialized && splashFinished)
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        })

        Handler().postDelayed({
            context?.let {
                splashFinished = true
                Log.i("AppLogger", "Finised  $initialized, $splashFinished")
                if(initialized && splashFinished)
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }, 2000)

        viewModel.initialize()
    }
}
