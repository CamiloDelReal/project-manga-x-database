package org.xapps.apps.mangaxdatabase.injections.modules

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import org.xapps.apps.mangaxdatabase.injections.utils.FragmentKey
import org.xapps.apps.mangaxdatabase.injections.utils.InjectingFragmentFactory
import org.xapps.apps.mangaxdatabase.injections.utils.InjectingNavHostFragment
import org.xapps.apps.mangaxdatabase.views.activities.ReferenceActivity
import org.xapps.apps.mangaxdatabase.views.activities.HomeActivity
import org.xapps.apps.mangaxdatabase.views.fragments.*


@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeReferenceActivity(): ReferenceActivity

    @ContributesAndroidInjector
    abstract fun navHostFragmentInjector(): InjectingNavHostFragment

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(SplashFragment::class)
    abstract fun bindSplashFragment(fragment: SplashFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    abstract fun bindHomeFragment(fragment: HomeFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SummaryFragment::class)
    abstract fun bindSummaryFragment(fragment: SummaryFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ListingFragment::class)
    abstract fun bindListingFragment(fragment: ListingFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ReferenceDetailsFragment::class)
    abstract fun bindReferenceDetailsFragment(fragment: ReferenceDetailsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ReferenceEditionFragment::class)
    abstract fun bindReferenceEditionFragment(fragment: ReferenceEditionFragment): Fragment

}