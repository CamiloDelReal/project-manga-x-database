package org.xapps.apps.mangaxdatabase.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.services.local.*
import org.xapps.apps.mangaxdatabase.services.models.*
import org.xapps.apps.mangaxdatabase.services.settings.SettingsService
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private val context: Context,
    private val settings: SettingsService,
    private val typeDao: TypeDao,
    private val demographyDao: DemographyDao,
    private val stateDao: StateDao,
    private val genreDao: GenreDao,
    private val relationshipDao: RelationshipDao
) : ViewModel() {

    private val initializedEmitter: MutableLiveData<Boolean> = MutableLiveData()

    fun initialized(): LiveData<Boolean> = initializedEmitter

    fun initialize() {
        if(settings.isFirstTime()) {
            val disposable = Single.fromCallable {
                // Create default Types
                val typeValues = context.resources.getStringArray(R.array.types)
                for(i in typeValues.indices)
                    typeDao.insert(Type(value = typeValues[i], description = ""))

                // Create default Demographies
                val demographyValues = context.resources.getStringArray(R.array.demographies)
                for(i in demographyValues.indices)
                    demographyDao.insert(Demography(value = demographyValues[i], description = ""))

                // Create default States
                val stateValues = context.resources.getStringArray(R.array.states)
                for(i in stateValues.indices)
                    stateDao.insert(State(value = stateValues[i], description = ""))

                // Create default Genres
                val genreValues = context.resources.getStringArray(R.array.genres)
                for(i in genreValues.indices)
                    genreDao.insert(Genre(value = genreValues[i], description = ""))

                // Create default relationships
                val relationshipValues = context.resources.getStringArray(R.array.relationships)
                for(i in relationshipValues.indices)
                    relationshipDao.insert(Relationship(value = relationshipValues[i], description = ""))
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    initializedEmitter.postValue(true)
                }, {
                    it.printStackTrace()
                })
        } else {
            initializedEmitter.postValue(true)
        }
    }

}