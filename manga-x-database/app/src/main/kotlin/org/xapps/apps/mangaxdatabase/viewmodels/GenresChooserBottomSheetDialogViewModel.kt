package org.xapps.apps.mangaxdatabase.viewmodels

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.xapps.apps.mangaxdatabase.services.local.GenreDao
import org.xapps.apps.mangaxdatabase.services.models.Genre
import org.xapps.apps.mangaxdatabase.services.models.ReferenceDetails
import org.xapps.apps.mangaxdatabase.views.utils.WorkerState
import javax.inject.Inject


class GenresChooserBottomSheetDialogViewModel @Inject constructor(
    private val genreDao: GenreDao
): ViewModel() {

    private val workingEmitter: MutableLiveData<WorkerState> = MutableLiveData()
    val genres: ObservableField<List<Genre>> = ObservableField()

    fun working(): LiveData<WorkerState> = workingEmitter

    fun initialize() {
        workingEmitter.postValue(WorkerState(true))

        val genreDisposable = genreDao.genresAsync()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("AppLogger", "Genres from choose ${it.size}")
                genres.set(it)
                workingEmitter.postValue(WorkerState(false))
            }, {

            })
    }
}