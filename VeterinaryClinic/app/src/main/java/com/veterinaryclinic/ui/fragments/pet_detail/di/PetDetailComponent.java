package com.veterinaryclinic.ui.fragments.pet_detail.di;

import com.veterinaryclinic.dagger.application.ApplicationComponent;
import com.veterinaryclinic.dagger.fragment.FragmentModule;
import com.veterinaryclinic.dagger.fragment.FragmentScope;
import com.veterinaryclinic.ui.fragments.pet_detail.PetDetailFragment;
import com.veterinaryclinic.ui.fragments.pet_detail.PetDetailNavigator;
import com.veterinaryclinic.ui.fragments.pet_detail.PetDetailPresenter;

import dagger.Component;

@FragmentScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                PetDetailModule.class
        }
)
public interface PetDetailComponent extends FragmentModule.Exposes {

    void inject(PetDetailFragment fragment);

    void inject(PetDetailPresenter presenter);

    void inject(PetDetailNavigator navigator);

    final class Initializer {

        private Initializer() {
        }

        public static PetDetailComponent init(final PetDetailFragment petDetailFragment,
                                         final ApplicationComponent applicationComponent) {
            return DaggerPetDetailComponent.builder()
                    .applicationComponent(applicationComponent)
                    .petDetailModule(new PetDetailModule(petDetailFragment))
                    .build();
        }
    }
}
