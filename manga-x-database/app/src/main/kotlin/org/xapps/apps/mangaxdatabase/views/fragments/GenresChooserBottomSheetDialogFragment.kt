package org.xapps.apps.mangaxdatabase.views.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.databinding.FragmentGenresChooserBinding
import org.xapps.apps.mangaxdatabase.injections.utils.BaseBottomSheetDialogFragment
import org.xapps.apps.mangaxdatabase.services.models.ReferenceDetails
import org.xapps.apps.mangaxdatabase.viewmodels.GenresChooserBottomSheetDialogViewModel
import org.xapps.apps.mangaxdatabase.viewmodels.ViewModelFactory
import org.xapps.apps.mangaxdatabase.views.listeners.DoneListener
import javax.inject.Inject


class GenresChooserBottomSheetDialogFragment @Inject constructor(
    private val referenceDetails: ReferenceDetails,
    private val doneListener: DoneListener?
) :
    BaseBottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: GenresChooserBottomSheetDialogViewModel

    private lateinit var viewBinding: FragmentGenresChooserBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheet = super.onCreateDialog(savedInstanceState)

        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_genres_chooser, null, false)
        bottomSheet.setContentView(viewBinding.root)

        viewModel = viewModelFactory.create(GenresChooserBottomSheetDialogViewModel::class.java)
        viewBinding.referenceDetails = referenceDetails
        viewBinding.viewModel = viewModel

        bottomSheetBehavior = BottomSheetBehavior.from(viewBinding.root.parent as View)
        bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
//                    BottomSheetBehavior.STATE_EXPANDED ->
//                    BottomSheetBehavior.STATE_COLLAPSED ->
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        dismiss()
                    }
                }
            }

        })

        viewBinding.toolbar.setNavigationOnClickListener {
            dismiss()
        }

        viewModel.initialize()

        return bottomSheet
    }

    override fun onStart() {
        super.onStart()
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        doneListener?.apply { done() }
    }

    private fun hideView() {

    }
}