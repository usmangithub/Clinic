package com.veterinaryclinic.ui.fragments.home.di;

import com.veterinaryclinic.dagger.application.ApplicationComponent;
import com.veterinaryclinic.dagger.fragment.FragmentModule;
import com.veterinaryclinic.dagger.fragment.FragmentScope;
import com.veterinaryclinic.ui.fragments.home.HomeFragment;
import com.veterinaryclinic.ui.fragments.home.HomeNavigator;
import com.veterinaryclinic.ui.fragments.home.HomePresenter;

import dagger.Component;

@FragmentScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                HomeModule.class
        }
)
public interface HomeComponent extends FragmentModule.Exposes {

    void inject(HomeFragment fragment);

    void inject(HomePresenter presenter);

    void inject(HomeNavigator navigator);

    final class Initializer {

        private Initializer() {
        }

        public static HomeComponent init(final HomeFragment homeFragment,
                                         final ApplicationComponent applicationComponent) {
            return DaggerHomeComponent.builder()
                    .applicationComponent(applicationComponent)
                    .homeModule(new HomeModule(homeFragment))
                    .build();
        }
    }
}
