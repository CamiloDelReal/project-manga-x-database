package org.xapps.apps.mangaxdatabase.viewmodels

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.services.local.*
import org.xapps.apps.mangaxdatabase.services.models.*
import org.xapps.apps.mangaxdatabase.services.utils.FileUtils
import org.xapps.apps.mangaxdatabase.views.utils.WorkerState
import java.util.*
import javax.inject.Inject


class ReferenceEditionViewModel @Inject constructor(
    private val context: Context,
    private val typeDao: TypeDao,
    private val demographyDao: DemographyDao,
    private val stateDao: StateDao,
    private val genreDao: GenreDao,
    private val landscapePosterDao: LandscapePosterDao,
    private val portraitPosterDao: PortraitPosterDao,
    private val referenceDao: ReferenceDao,
    private val referenceGenreDao: ReferenceGenreDao,
    private val referenceAuthorDao: ReferenceAuthorDao
) : ViewModel() {

    private val workingEmitter: MutableLiveData<WorkerState> = MutableLiveData()
    private val errorEmitter: MutableLiveData<String> = MutableLiveData()

    val types: ObservableField<List<Type>> = ObservableField()
    val demographies: ObservableField<List<Demography>> = ObservableField()
    val states: ObservableField<List<State>> = ObservableField()
    val genres: ObservableField<List<Genre>> = ObservableField()

    val isWorking: ObservableField<Boolean> = ObservableField()

    lateinit var referenceDetails: ReferenceDetails
    var landscapePosterPath: String? = null
    var portraitPosterPath: String? = null

    fun working(): LiveData<WorkerState> = workingEmitter
    fun error(): LiveData<String> = errorEmitter

    fun initialize() {
        isWorking.set(true)
        workingEmitter.postValue(WorkerState(true))
        var typeFinished = false
        var demographyFinished = false
        var stateFinished = false
        var genreFinished = false

        referenceDetails = ReferenceDetails()

        fun validateFinalization() {
            if (typeFinished && demographyFinished && stateFinished && genreFinished) {
                isWorking.set(false)
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

        types.set(
            listOf(
                Type(1, "Anime", ""),
                Type(2, "Manga", ""),
                Type(3, "Manhua", "")
            )
        )

        demographies.set(
            listOf(
                Demography(2, "Shounnen", ""),
                Demography(1, "Kodomo", ""),
                Demography(3, "Shoujo", ""),
                Demography(4, "Seinen", "")
            )
        )

        states.set(
            listOf(
                State(1, "On Air", ""),
                State(2, "Finished", "")
            )
        )

        genres.set(
            listOf(
                Genre(1, "Accion", ""),
                Genre(2, "Slice of live", ""),
                Genre(3, "Fantasia", ""),
                Genre(4, "Romance", ""),
                Genre(5, "Drama", "")
            )
        )
    }

    fun save() {
        isWorking.set(true)
        workingEmitter.postValue(WorkerState(true))

        val disposable = Single.fromCallable {
            Log.i("AppLogger", "Save 1")
            referenceDetails.type?.let { referenceDetails.reference.typeId = it.id }
            referenceDetails.demography?.let { referenceDetails.reference.demographyId = it.id }
            referenceDetails.state?.let { referenceDetails.reference.stateId = it.id }

            val timestamp = Date().time
            var titleSanitized =
                referenceDetails.reference.title.replace(Regex("[\\s-:\'\"]"), "_").toLowerCase(
                    Locale.ROOT
                )
            if (titleSanitized.length > 15)
                titleSanitized = titleSanitized.substring(0, 14)
            val fileNameLandscapePoster = "${titleSanitized}_landscape_${timestamp}"
            val fileNamePortraitPoster = "${titleSanitized}_portrait_${timestamp}"
            Log.i("AppLogger", "Save 2")
            referenceDetails.reference.landscapePosterId = if (FileUtils.copyPosterToAppFolder(
                    context,
                    landscapePosterPath,
                    fileNameLandscapePoster
                )
            )
                landscapePosterDao.insert(LandscapePoster(path = fileNameLandscapePoster))
            else null
            Log.i("AppLogger", "Save 3")
            referenceDetails.reference.portraitPosterId = if (FileUtils.copyPosterToAppFolder(
                    context,
                    portraitPosterPath,
                    fileNamePortraitPoster
                )
            )
                portraitPosterDao.insert(PortraitPoster(path = fileNamePortraitPoster))
            else
                null

            val referenceId = referenceDao.insert(referenceDetails.reference)
            Log.i("AppLogger", "Save 4")
            referenceDetails.genres?.let { genres ->
                genres.forEach {
                    referenceGenreDao.insert(ReferenceGenre(referenceId, it.id))
                }
            }
            Log.i("AppLogger", "Save 5")
            referenceDetails.authors?.let { authors ->
                authors.forEach {
                    referenceAuthorDao.insert(ReferenceAuthor(referenceId, it.id))
                }
            }
            Log.i("AppLogger", "Save 6")

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
//                isWorking.set(false)
                workingEmitter.postValue(WorkerState(false))
            }, { errorEx ->
                errorEx.printStackTrace()
                isWorking.set(false)
                workingEmitter.postValue(WorkerState(false))
                errorEmitter.postValue(context.getString(R.string.unkown_error))
            })
    }

    init {
        isWorking.set(false)
    }

}