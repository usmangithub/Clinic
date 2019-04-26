package com.veterinaryclinic.dagger.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.veterinaryclinic.BaseFragment;
import com.veterinaryclinic.dagger.activity.ForActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private final BaseFragment fragment;

    public FragmentModule(final BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    @ForActivity
    public Context provideActivityContext() {
        return fragment.getContext();
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }

    @Provides
    @FragmentScope
    public FragmentManager provideFragmentManager() {
        FragmentActivity fragmentActivity = fragment.getActivity();
        if (fragmentActivity == null) {
            return null;
        } else {
            return fragmentActivity.getSupportFragmentManager();
        }
    }

    public interface Exposes {
        Activity activity();
        @ForActivity
        Context context();
        FragmentManager fragmentManager();
    }
}
