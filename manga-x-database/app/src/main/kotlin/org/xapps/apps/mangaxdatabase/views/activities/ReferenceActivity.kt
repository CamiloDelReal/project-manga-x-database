package org.xapps.apps.mangaxdatabase.views.activities

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.injections.utils.BaseActivity
import org.xapps.apps.mangaxdatabase.views.fragments.DummyFragmentDirections


class ReferenceActivity : BaseActivity() {

    override fun layoutRes(): Int = R.layout.activity_reference

    private val args: ReferenceActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (args.referenceId == 0L) {
            findNavController(R.id.detailsNavHostFragment).navigate(
                DummyFragmentDirections.actionDummyFragmentToReferenceEditionFragment()
            )
        } else {
            findNavController(R.id.detailsNavHostFragment).navigate(
                DummyFragmentDirections.actionDummyFragmentToReferenceDetailsFragment(
                    args.referenceId
                )
            )
        }

    }

}
