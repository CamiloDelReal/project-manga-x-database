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
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindSHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SummaryViewModel::class)
    abstract fun bindSummaryViewModel(viewModel: SummaryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListingViewModel::class)
    abstract fun bindListingViewModel(viewModel: ListingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReferenceDetailsViewModel::class)
    abstract fun bindReferenceDetailsViewModel(viewModel: ReferenceDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReferenceEditionViewModel::class)
    abstract fun bindReferenceEditionViewModel(viewModel: ReferenceEditionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GenresChooserBottomSheetDialogViewModel::class)
    abstract fun bindGenresChooserBottomSheetDialogViewModel(viewModel: GenresChooserBottomSheetDialogViewModel): ViewModel

}