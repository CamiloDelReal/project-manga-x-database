package org.xapps.apps.mangaxdatabase.views.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.squareup.picasso.Picasso
import com.yalantis.ucrop.UCrop
import droidninja.filepicker.FilePickerBuilder
import droidninja.filepicker.FilePickerConst
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_reference_edition.*
import org.xapps.apps.mangaxdatabase.BR
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.databinding.FragmentReferenceEditionBinding
import org.xapps.apps.mangaxdatabase.services.utils.DateUtils
import org.xapps.apps.mangaxdatabase.services.utils.PathUtils
import org.xapps.apps.mangaxdatabase.viewmodels.ReferenceEditionViewModel
import org.xapps.apps.mangaxdatabase.viewmodels.ViewModelFactory
import org.xapps.apps.mangaxdatabase.views.activities.HomeActivity
import org.xapps.apps.mangaxdatabase.views.activities.ReferenceActivity
import org.xapps.apps.mangaxdatabase.views.listeners.DoneListener
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ReferenceEditionFragment @Inject constructor() : Fragment() {

    private lateinit var viewBinding: FragmentReferenceEditionBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ReferenceEditionViewModel

    private var buttonInterval: Disposable? = null

    private val genresChooserDoneListener by lazy {
        object : DoneListener {
            override fun done() {
                viewModel.referenceDetails.notifyPropertyChanged(BR.genres)
            }

        }
    }

    companion object {
        private const val LANDSCAPE_POSTER_REQUEST = 1000
        private const val CROP_LANDSCAPE_POSTER_REQUEST = 1001
        private const val PORTRAIT_POSTER_REQUEST = 1002
        private const val CROP_PORTRAIT_POSTER_REQUEST = 1003

        private const val ARROW_BUTTON_INITIAL_DELAY = 800L
        private const val ARROW_BUTTON_FRAME_DELAY = 16L
        private const val ARROW_BUTTON_STEP_SIZE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_reference_edition,
            container,
            false
        )
        setHasOptionsMenu(true)
        viewModel = viewModelFactory.create(ReferenceEditionViewModel::class.java)
        viewBinding.viewModel = viewModel
        viewBinding.ratingFocusListener = View.OnTouchListener { _, event ->
            when (event.action) {
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
        referenceActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)

        // New or edit?
        collapsingToolbarLayout.title = getString(R.string.new_reference)

        btnPlus.setOnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    updateChaptersValue(ARROW_BUTTON_STEP_SIZE)
                    txfChapters.requestFocus()

                    btnPlus.requestFocus()
                    btnPlus.isPressed = true
                    buttonInterval?.dispose()

                    buttonInterval = Observable.interval(
                        ARROW_BUTTON_INITIAL_DELAY,
                        ARROW_BUTTON_FRAME_DELAY,
                        TimeUnit.MILLISECONDS,
                        Schedulers.io()
                    )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            updateChaptersValue(ARROW_BUTTON_STEP_SIZE)
                        }
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    btnPlus.isPressed = false
                    buttonInterval?.dispose()
                    buttonInterval = null
                }
            }
            true
        }

        btnMinus.setOnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    updateChaptersValue(-ARROW_BUTTON_STEP_SIZE)
                    txfChapters.requestFocus()
                    btnMinus.requestFocus()
                    btnMinus.isPressed = true
                    buttonInterval?.dispose()

                    buttonInterval = Observable.interval(
                        ARROW_BUTTON_INITIAL_DELAY,
                        ARROW_BUTTON_FRAME_DELAY,
                        TimeUnit.MILLISECONDS,
                        Schedulers.io()
                    )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            updateChaptersValue(-ARROW_BUTTON_STEP_SIZE)
                        }
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    btnMinus.isPressed = false
                    buttonInterval?.dispose()
                    buttonInterval = null
                }
            }
            true
        }

        btnPosterLandscape.setOnClickListener {
            requestPoster(LANDSCAPE_POSTER_REQUEST)
        }

        btnPosterPortrait.setOnClickListener {
            requestPoster(PORTRAIT_POSTER_REQUEST)
        }

        btnSetGenres.setOnClickListener {
            val genresChooser = GenresChooserBottomSheetDialogFragment(
                viewModel.referenceDetails,
                genresChooserDoneListener
            )

            genresChooser.show(parentFragmentManager, "ddf")
            genresChooser.dialog?.setOnDismissListener {
                Log.i("AppLogger", "Bottom closed")
            }
        }

        btnSetAuthors.setOnClickListener {
            viewModel.referenceDetails.genres?.let {
                it.forEach {
                    Log.i("AppLogger", "Genre ${it.value}")
                }
            }
        }

        viewModel.working().observe(viewLifecycleOwner, Observer { workerState ->

        })

        viewModel.initialize()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_new_reference, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionSave -> {
                viewModel.save()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun updateChaptersValue(step: Int) {
        val currentValue =
            if (txfChapters.text.toString().isEmpty() || txfChapters.text.toString().isBlank())
                0
            else
                txfChapters.text.toString().toInt()

        txfChapters.setText((if ((currentValue + step) < 0) 0 else (currentValue + step)).toString())
    }

    private fun requestPoster(requestCode: Int) {
        Dexter.withContext(context)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (report.areAllPermissionsGranted()) {
                            FilePickerBuilder.instance
                                .setActivityTitle(getString(R.string.select_poster))
                                .setMaxCount(1)
                                .setActivityTheme(R.style.LibAppTheme)
                                .pickPhoto(this@ReferenceEditionFragment, requestCode)
                        } else {
                            Log.i("AppLogger", "Permissions denied")
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    Log.i("AppLogger", "Rationales")
                    // Remember to invoke this method when the custom rationale is closed
                    // or just by default if you don't want to use any custom rationale.
                    token?.continuePermissionRequest()
                }
            })
            .withErrorListener {
                Log.i("AppLogger", "Error permission ${it.name}")
            }
            .check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            LANDSCAPE_POSTER_REQUEST, PORTRAIT_POSTER_REQUEST -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val posterSourceUri =
                        data.getParcelableArrayListExtra<Uri>(FilePickerConst.KEY_SELECTED_MEDIA)!![0]
                    if (requestCode == LANDSCAPE_POSTER_REQUEST) {
                        UCrop.of(
                            posterSourceUri,
                            Uri.fromFile(File(requireContext().cacheDir, "${DateUtils.timestamp()}.png"))
                        )
                            .withAspectRatio(16f, 9f)
//                        .withMaxResultSize(maxWidth, maxHeight)
                            .start(
                                requireContext(),
                                this@ReferenceEditionFragment,
                                CROP_LANDSCAPE_POSTER_REQUEST
                            )
                    } else {
                        UCrop.of(
                            posterSourceUri,
                            Uri.fromFile(File(requireContext().cacheDir, "${DateUtils.timestamp()}.png"))
                        )
                            .withAspectRatio(3f, 4f)
//                        .withMaxResultSize(maxWidth, maxHeight)
                            .start(
                                requireContext(),
                                this@ReferenceEditionFragment,
                                CROP_PORTRAIT_POSTER_REQUEST
                            )
                    }
                }
            }
            CROP_LANDSCAPE_POSTER_REQUEST, CROP_PORTRAIT_POSTER_REQUEST -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val croppedPosterUri = UCrop.getOutput(data)
                    Log.i("AppLogger", "Cropper URI  $croppedPosterUri")
                    if (requestCode == CROP_LANDSCAPE_POSTER_REQUEST) {
                        viewModel.landscapePosterPath = croppedPosterUri?.path
                        Picasso.get()
                            .load(croppedPosterUri)
                            .into(imgLandscapePoster)
                    } else {
                        viewModel.portraitPosterPath = croppedPosterUri?.path
                        Picasso.get()
                            .load(croppedPosterUri)
                            .into(imgPortraintPoster)
                    }
                }
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}


