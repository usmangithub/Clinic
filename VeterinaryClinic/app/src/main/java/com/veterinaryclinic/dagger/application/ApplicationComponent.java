package com.veterinaryclinic.dagger.application;

import javax.inject.Singleton;

import com.veterinaryclinic.TestApplication;
import com.veterinaryclinic.dagger.application.module.ApplicationModule;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})

public interface ApplicationComponent
        extends ApplicationComponentInjects, ApplicationComponentExposes {
    final class Initializer {
        static public ApplicationComponent init(final TestApplication testApplication) {
            return DaggerApplicationComponent
                    .builder()
                    .applicationModule(new ApplicationModule(testApplication))
                    .build();
        }

        private Initializer() {
        }
    }
}
