package org.xapps.apps.mangaxdatabase.injections.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.xapps.apps.mangaxdatabase.injections.utils.ViewModelKey
import org.xapps.apps.mangaxdatabase.viewmodels.*


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindSHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SummaryViewModel::class)
    abstract fun bindSummaryViewModel(summaryViewModel: SummaryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListingViewModel::class)
    abstract fun bindListingViewModel(listingViewModel: ListingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReferenceDetailsViewModel::class)
    abstract fun bindReferenceDetailsViewModel(referenceDetailsViewModel: ReferenceDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReferenceEditionViewModel::class)
    abstract fun bindReferenceEditionViewModel(referenceEditionViewModel: ReferenceEditionViewModel): ViewModel

}