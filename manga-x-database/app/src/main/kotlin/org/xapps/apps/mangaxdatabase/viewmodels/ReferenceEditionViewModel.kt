package org.xapps.apps.mangaxdatabase.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.xapps.apps.mangaxdatabase.services.local.DemographyDao
import org.xapps.apps.mangaxdatabase.services.local.GenreDao
import org.xapps.apps.mangaxdatabase.services.local.StateDao
import org.xapps.apps.mangaxdatabase.services.local.TypeDao
import org.xapps.apps.mangaxdatabase.services.models.*
import org.xapps.apps.mangaxdatabase.views.utils.WorkerState
import javax.inject.Inject


class ReferenceEditionViewModel @Inject constructor(
    private val typeDao: TypeDao,
    private val demographyDao: DemographyDao,
    private val stateDao: StateDao,
    private val genreDao: GenreDao
): ViewModel() {

    val workingEmitter: MutableLiveData<WorkerState> = MutableLiveData()

    val types: ObservableField<List<Type>> = ObservableField()
    val demographies: ObservableField<List<Demography>> = ObservableField()
    val states: ObservableField<List<State>> = ObservableField()
    val genres: ObservableField<List<Genre>> = ObservableField()

    lateinit var referenceDetails: ReferenceDetails

    fun working(): LiveData<WorkerState> = workingEmitter

    private fun ff(typeFinished: Boolean, demographyFinished: Boolean, stateFinished: Boolean, genreFinished: Boolean) {
        if(typeFinished && demographyFinished && stateFinished && genreFinished) {
            workingEmitter.postValue(WorkerState(false))
        }
    }

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

        val typeDisposable = typeDao.typesAsync()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                types.set(it)
                typeFinished = true
                validateFinalization()
            }, {

            })

        val demographyDisposable = demographyDao.demographiesAsync()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                demographies.set(it)
                demographyFinished = true
                validateFinalization()
            }, {

            })

        val stateDisposable = stateDao.statesAsync()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                states.set(it)
                stateFinished = true
                validateFinalization()
            }, {

            })

        val genreDisposable = genreDao.genresAsync()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                genres.set(it)
                genreFinished = true
                validateFinalization()
            }, {

            })
    }

    fun test() {
        referenceDetails = ReferenceDetails(
            Reference(1, "30-sai no Hoken Taiiku", "", 0, 3.9f, 0, 0, 0, 0, 0, false, true, false),
            null,//Type(1, "Anime", ""),
            Demography(1, "Kodomo", ""),
            State(1, "On Air", ""),
            listOf(Genre(1, "Accion", ""), Genre(4, "Romance", ""), Genre(3, "Fantasia", "")),
            listOf(Author(1, "Camilo", "")),
            PortraitPoster(1, ""),
            LandscapePoster(1, "")
        )

        types.set(listOf(
            Type(1, "Anime", ""),
            Type(2, "Manga", ""),
            Type(3, "Manhua", "")
        ))

        demographies.set(listOf(
            Demography(2, "Shounnen", ""),
            Demography(1, "Kodomo", ""),
            Demography(3, "Shoujo", ""),
            Demography(4, "Seinen", "")
        ))

        states.set(listOf(
            State(1, "On Air", ""),
            State(2, "Finished", "")
        ))

        genres.set(listOf(
            Genre(1, "Accion", ""),
            Genre(2, "Slice of live", ""),
            Genre(3, "Fantasia", ""),
            Genre(4, "Romance", ""),
            Genre(5, "Drama", "")
        ))
    }

}