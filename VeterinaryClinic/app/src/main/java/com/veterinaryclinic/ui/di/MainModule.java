package com.veterinaryclinic.ui.di;

import com.veterinaryclinic.dagger.activity.ActivityModule;
import com.veterinaryclinic.dagger.activity.ActivityScope;
import com.veterinaryclinic.ui.MainActivity;
import com.veterinaryclinic.ui.MainNavigator;
import com.veterinaryclinic.ui.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule extends ActivityModule {

    private MainActivity mainActivity;

    MainModule(final MainActivity mainActivity) {
        super(mainActivity);
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter() {
        final MainPresenter mainPresenter =
                new MainPresenter(mainActivity,
                        new MainNavigator(mainActivity));
        mainActivity.getMainComponent().inject(mainPresenter);
        return mainPresenter;
    }
}
