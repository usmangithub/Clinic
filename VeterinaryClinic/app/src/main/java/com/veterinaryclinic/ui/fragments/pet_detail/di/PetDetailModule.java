package com.veterinaryclinic.ui.fragments.pet_detail.di;

import com.veterinaryclinic.dagger.fragment.FragmentModule;
import com.veterinaryclinic.dagger.fragment.FragmentScope;
import com.veterinaryclinic.ui.fragments.pet_detail.PetDetailFragment;
import com.veterinaryclinic.ui.fragments.pet_detail.PetDetailNavigator;
import com.veterinaryclinic.ui.fragments.pet_detail.PetDetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PetDetailModule extends FragmentModule {

    private PetDetailFragment petDetailFragment;

    PetDetailModule(final PetDetailFragment petDetailFragment) {
        super(petDetailFragment);
        this.petDetailFragment = petDetailFragment;
    }

    @Provides
    @FragmentScope
    PetDetailPresenter provideHomePresenter() {
        final PetDetailPresenter petDetailPresenter =
                new PetDetailPresenter(petDetailFragment,
                        new PetDetailNavigator(petDetailFragment));
        petDetailFragment.getCategoryComponent().inject(petDetailPresenter);
        return petDetailPresenter;
    }
}
