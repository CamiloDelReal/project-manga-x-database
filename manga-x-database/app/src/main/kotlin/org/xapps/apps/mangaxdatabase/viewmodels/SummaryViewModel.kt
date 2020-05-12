package org.xapps.apps.mangaxdatabase.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.services.models.Reference
import org.xapps.apps.mangaxdatabase.services.models.ReferenceItemSummary
import org.xapps.apps.mangaxdatabase.services.models.Type
import org.xapps.apps.mangaxdatabase.views.utils.WorkerState
import javax.inject.Inject


class SummaryViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    private var workingEmitter: MutableLiveData<WorkerState> = MutableLiveData()
    private var referencesEmitter: MutableLiveData<List<ReferenceItemSummary>> = MutableLiveData()

    fun working(): LiveData<WorkerState> = workingEmitter
    fun references(): LiveData<List<ReferenceItemSummary>> = referencesEmitter

    fun getReferences() {
        workingEmitter.postValue(WorkerState(true, context.getString(R.string.reading_references)))
//        val disposable =
        referencesEmitter.postValue(
            listOf(
                ReferenceItemSummary(
                    Reference(1, "30-sai no Hoken Taiiku", "", 0, 3.9f, 0, 0, 0, 0, 0, false, true, false),
                    Type(1, "Anime", "")
                ),
                ReferenceItemSummary(
                    Reference(2, "91 Days", "", 0, 3.9f, 0, 0, 0, 0, 0, false, true, false),
                    Type(1, "Anime", "")
                ),
                ReferenceItemSummary(
                    Reference(3, "Overlord II", "", 0, 3.9f, 0, 0, 0, 0, 0, true, true, true),
                    Type(1, "Anime", "")
                ),
                ReferenceItemSummary(
                    Reference(4, "Nanatsu no Taizai", "", 0, 3.9f, 0, 0, 0, 0, 0, true, true, true),
                    Type(1, "Anime", "")
                ),
                ReferenceItemSummary(
                    Reference(5, "Fate/Extra Last Encore", "", 0, 3.9f, 0, 0, 0, 0, 0, true, false, false),
                    Type(1, "Anime", "")
                ),
                ReferenceItemSummary(
                    Reference(6, "Acchi Kocchi", "", 0, 3.9f, 0, 0, 0, 0, 0, true, true, true),
                    Type(1, "Anime", "")
                ),
                ReferenceItemSummary(
                    Reference(7, "3-Gatsu no Lion I", "", 0, 3.9f, 0, 0, 0, 0, 0, true, true, true),
                    Type(1, "Anime", "")
                ),
                ReferenceItemSummary(
                    Reference(8, "3-Gatsu no Lion II", "", 0, 3.9f, 0, 0, 0, 0, 0, true, true, true),
                    Type(1, "Anime", "")
                ),
                ReferenceItemSummary(
                    Reference(9, "A Channel", "", 0, 3.9f, 0, 0, 0, 0, 0, false, false, false),
                    Type(1, "Anime", "")
                ),
                ReferenceItemSummary(
                    Reference(10, "Aldnoah Zero", "", 0, 3.9f, 0, 0, 0, 0, 0, true, true, true),
                    Type(1, "Anime", "")
                )
            )
        )
        workingEmitter.postValue(WorkerState(false))
    }

}