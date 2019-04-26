package com.veterinaryclinic.ui.fragments.home.di;

import com.veterinaryclinic.dagger.fragment.FragmentModule;
import com.veterinaryclinic.dagger.fragment.FragmentScope;
import com.veterinaryclinic.ui.fragments.home.HomeFragment;
import com.veterinaryclinic.ui.fragments.home.HomeNavigator;
import com.veterinaryclinic.ui.fragments.home.HomePresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule extends FragmentModule {

    private HomeFragment homeFragment;

    HomeModule(final HomeFragment homeFragment) {
        super(homeFragment);
        this.homeFragment = homeFragment;
    }

    @Provides
    @FragmentScope
    HomePresenter provideHomePresenter() {
        final HomePresenter homePresenter =
                new HomePresenter(homeFragment,
                        new HomeNavigator(homeFragment));
        homeFragment.getCategoryComponent().inject(homePresenter);
        return homePresenter;
    }
}
