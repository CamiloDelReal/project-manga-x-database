package org.xapps.apps.mangaxdatabase.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.services.models.*
import org.xapps.apps.mangaxdatabase.views.utils.WorkerState
import javax.inject.Inject


class ListingViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    private var workingEmitter: MutableLiveData<WorkerState> = MutableLiveData()
    private var referencesEmitter: MutableLiveData<List<ReferenceItemListing>> = MutableLiveData()

    fun working(): LiveData<WorkerState> = workingEmitter
    fun references(): LiveData<List<ReferenceItemListing>> = referencesEmitter

    fun getReferences() {
        workingEmitter.postValue(WorkerState(true, context.getString(R.string.reading_references)))
//        val disposable =
        referencesEmitter.postValue(
            listOf(
                ReferenceItemListing(
                    Reference(1, "30-sai no Hoken Taiiku", "", 0, 3.9f, 0, 0, 0, 0, 0, false, true, false),
                    Type(1, "Anime", ""),
                    Demography(1, "Shounen", ""),
                    listOf(Genre(1, "Accion", ""), Genre(4, "Romance", ""), Genre(3, "Fantasia", "")),
                    listOf(Author(1, "Camilo", ""))
                ),
                ReferenceItemListing(
                    Reference(2, "91 Days", "", 0, 3.9f, 0, 0, 0, 0, 0, false, true, false),
                    Type(1, "Anime", ""),
                    Demography(1, "Shounen", ""),
                    listOf(Genre(1, "Accion", ""), Genre(2, "Adventura", ""), Genre(3, "Fantasia", "")),
                    listOf(Author(2, "Aragorn", ""))
                ),
                ReferenceItemListing(
                    Reference(3, "Overlord II", "", 0, 3.9f, 0, 0, 0, 0, 0, true, true, false),
                    Type(1, "Anime", ""),
                    Demography(1, "Shounen", ""),
                    listOf(Genre(2, "Adventura", ""), Genre(3, "Fantasia", "")),
                    listOf(Author(3, "DarkLink", ""))
                ),
                ReferenceItemListing(
                    Reference(4, "Nanatsu no Taizai", "", 0, 3.9f, 0, 0, 0, 0, 0, true, true, true),
                    Type(1, "Anime", ""),
                    Demography(1, "Shounen", ""),
                    listOf(Genre(1, "Accion", ""), Genre(2, "Adventura", ""), Genre(3, "Fantasia", "")),
                    listOf(Author(1, "Camilo", ""))
                ),
                ReferenceItemListing(
                    Reference(5, "Fate/Extra Last Encore", "", 0, 3.9f, 0, 0, 0, 0, 0, true, false, false),
                    Type(1, "Anime", ""),
                    Demography(1, "Shounen", ""),
                    listOf(Genre(1, "Accion", ""), Genre(2, "Adventura", ""), Genre(3, "Fantasia", "")),
                    listOf(Author(1, "Camilo", ""), Author(2, "Zelda", ""))
                ),
                ReferenceItemListing(
                    Reference(6, "Acchi Kocchi", "", 0, 3.9f, 0, 0, 0, 0, 0, false, true, true),
                    Type(1, "Anime", ""),
                    Demography(1, "Shounen", ""),
                    listOf(Genre(1, "Accion", ""), Genre(2, "Adventura", ""), Genre(3, "Fantasia", "")),
                    listOf(Author(1, "Camilo", ""))
                ),
                ReferenceItemListing(
                    Reference(7, "3-Gatsu no Lion I", "", 0, 3.9f, 0, 0, 0, 0, 0, true, true, true),
                    Type(1, "Anime", ""),
                    Demography(1, "Shounen", ""),
                    listOf(Genre(1, "Accion", ""), Genre(2, "Adventura", ""), Genre(3, "Fantasia", "")),
                    listOf(Author(1, "Camilo", ""))
                ),
                ReferenceItemListing(
                    Reference(8, "3-Gatsu no Lion II", "", 0, 3.9f, 0, 0, 0, 0, 0, true, true, true),
                    Type(1, "Anime", ""),
                    Demography(1, "Shounen", ""),
                    listOf(Genre(1, "Accion", ""), Genre(2, "Adventura", ""), Genre(3, "Fantasia", "")),
                    listOf(Author(1, "Camilo", ""))
                ),
                ReferenceItemListing(
                    Reference(9, "A Channel", "", 0, 3.9f, 0, 0, 0, 0, 0, true, false, false),
                    Type(1, "Anime", ""),
                    Demography(1, "Shounen", ""),
                    listOf(Genre(1, "Accion", ""), Genre(2, "Adventura", ""), Genre(3, "Fantasia", "")),
                    listOf(Author(1, "Camilo", ""))
                ),
                ReferenceItemListing(
                    Reference(10, "Aldnoah Zero", "", 0, 3.9f, 0, 0, 0, 0, 0, true, true, true),
                    Type(1, "Anime", ""),
                    Demography(1, "Shounen", ""),
                    listOf(Genre(1, "Accion", ""), Genre(2, "Adventura", ""), Genre(3, "Fantasia", "")),
                    listOf(Author(1, "Camilo", ""))
                )
            )
        )
        workingEmitter.postValue(WorkerState(false))
    }
}