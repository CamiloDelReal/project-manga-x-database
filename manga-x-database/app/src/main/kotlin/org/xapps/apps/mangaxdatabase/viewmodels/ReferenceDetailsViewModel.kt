package org.xapps.apps.mangaxdatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.xapps.apps.mangaxdatabase.services.models.ReferenceDetails
import org.xapps.apps.mangaxdatabase.views.utils.WorkerState
import javax.inject.Inject


class ReferenceDetailsViewModel @Inject constructor(): ViewModel() {

    private val workingEmitter: MutableLiveData<WorkerState> = MutableLiveData()

    lateinit var referenceDetails: ReferenceDetails

    fun working(): LiveData<WorkerState> = workingEmitter

    fun initialize() {
        workingEmitter.postValue(WorkerState(true))
        var typeFinished = false
        var demographyFinished = false
        var stateFinished = false
        var genreFinished = false

        referenceDetails = ReferenceDetails()

        fun validateFinalization() {
            if(typeFinished && demographyFinished && stateFinished && genreFinished) {
                workingEmitter.postValue(WorkerState(false))
            }
        }

    }
}