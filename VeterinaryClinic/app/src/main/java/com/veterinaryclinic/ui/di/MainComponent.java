package com.veterinaryclinic.ui.di;

import com.veterinaryclinic.dagger.activity.ActivityModule;
import com.veterinaryclinic.dagger.activity.ActivityScope;
import com.veterinaryclinic.dagger.application.ApplicationComponent;
import com.veterinaryclinic.ui.MainActivity;
import com.veterinaryclinic.ui.MainNavigator;
import com.veterinaryclinic.ui.MainPresenter;
import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                MainModule.class
        }
)
public interface MainComponent extends ActivityModule.Exposes {

    void inject(MainActivity activity);

    void inject(MainPresenter presenter);

    void inject(MainNavigator navigator);

    final class Initializer {

        private Initializer() {
        }

        public static MainComponent init(final MainActivity mainActivity,
                                         final ApplicationComponent applicationComponent) {
            return DaggerMainComponent.builder()
                    .applicationComponent(applicationComponent)
                    .mainModule(new MainModule(mainActivity))
                    .build();
        }
    }
}
