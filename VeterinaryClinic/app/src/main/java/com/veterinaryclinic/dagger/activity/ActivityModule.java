package com.veterinaryclinic.dagger.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.veterinaryclinic.BaseActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final BaseActivity baseActivity;

    public ActivityModule(final BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @ActivityScope
    @ForActivity
    public Context provideActivityContext() {
        return baseActivity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return baseActivity;
    }

    @Provides
    @ActivityScope
    public FragmentManager provideFragmentManager() {
        return baseActivity.getSupportFragmentManager();
    }

    public interface Exposes {
        Activity activity();
        @ForActivity
        Context context();
        FragmentManager fragmentManager();
    }
}
