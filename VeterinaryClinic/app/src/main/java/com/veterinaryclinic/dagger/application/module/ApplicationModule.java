package com.veterinaryclinic.dagger.application.module;

import android.content.Context;

import javax.inject.Singleton;

import com.veterinaryclinic.TestApplication;
import com.veterinaryclinic.dagger.application.ForApplication;
import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final TestApplication testApplication;

    public ApplicationModule(final TestApplication testApplication) {
        this.testApplication = testApplication;
    }

    @Provides
    @Singleton
    TestApplication provideTestApplication() {
        return testApplication;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideContext() {
        return testApplication;
    }

    public interface Exposes {
        TestApplication testApplication();
        @ForApplication
        Context context();
    }
}
