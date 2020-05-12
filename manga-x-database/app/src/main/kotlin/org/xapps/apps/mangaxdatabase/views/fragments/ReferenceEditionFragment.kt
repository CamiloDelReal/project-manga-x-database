package org.xapps.apps.mangaxdatabase.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_reference_edition.*
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.databinding.FragmentReferenceEditionBinding
import org.xapps.apps.mangaxdatabase.viewmodels.ReferenceEditionViewModel
import org.xapps.apps.mangaxdatabase.viewmodels.ViewModelFactory
import org.xapps.apps.mangaxdatabase.views.activities.ReferenceActivity
import javax.inject.Inject


class ReferenceEditionFragment @Inject constructor(): Fragment() {

    private lateinit var viewBinding: FragmentReferenceEditionBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ReferenceEditionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_reference_edition, container, false)
        viewModel = viewModelFactory.create(ReferenceEditionViewModel::class.java)
        viewBinding.viewModel = viewModel
        viewBinding.ratingFocusListener = View.OnTouchListener { _, event ->
            when(event.action) {
                MotionEvent.ACTION_MOVE -> txfRatingContainer.requestFocus()
                MotionEvent.ACTION_UP -> txfRatingContainer.clearFocus()
            }
            false
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val referenceActivity = activity as ReferenceActivity
        referenceActivity.setSupportActionBar(toolbar)
        referenceActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        collapsingToolbarLayout.title = getString(R.string.new_reference)



        btnPosterPortrait.setOnClickListener {

        }

        txfChaptersLayout.setOnTouchListener { v, event ->
            true
        }

        ratEvaluation.setOnFocusChangeListener { v, hasFocus ->
            Log.i("AppLogger", "Rating focus changed $hasFocus")
        }

        ratEvaluation.setOnTouchListener { v, event ->
            Log.i("AppLogger", "Rating touched ${event.action}")
            false
        }
        ratEvaluation.setOnRatingChangeListener { ratingBar, rating ->
            Log.i("AppLogger", "Rating value changed $rating")
        }
//        btn.setOnClickListener {
//            Log.i("AppLogger", "Selected ${viewModel.reference.demography}")
//        }
//
//        btn2.setOnClickListener {
//            viewModel.change()
//        }
//
//        filled_exposed_dropdown.setOnItemClickListener { parent, view, position, id ->
//            Log.i("AppLogger", "setOnItemClickListener  >  position = $position")
//        }

//        filled_exposed_dropdown.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                Log.i("AppLogger", "Fragment nothing selected")
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                Log.i("AppLogger", "Fragment selection $position")
//            }
//
//        }
//
//        filled_exposed_dropdown.doOnTextChanged { text, start, count, after ->
//            Log.i("AppLogger", "Fragment text changed $text")
//        }



        viewModel.initialize()
    }

}


