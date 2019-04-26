package com.veterinaryclinic;

import android.app.Application;
import android.content.Context;

import com.veterinaryclinic.dagger.ComponentFactory;
import com.veterinaryclinic.dagger.application.ApplicationComponent;

public class TestApplication extends Application {
    private ApplicationComponent applicationComponent;

    public static TestApplication from(final Context context) {
        return (TestApplication) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = ComponentFactory.createApplicationComponent(this);
        applicationComponent.inject(this);
    }
}
