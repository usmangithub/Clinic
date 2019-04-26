package com.veterinaryclinic.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import com.veterinaryclinic.BaseActivity;
import com.veterinaryclinic.BasePresenter;
import com.veterinaryclinic.R;
import com.veterinaryclinic.ui.di.MainComponent;
import com.veterinaryclinic.ui.fragments.home.HomeFragment;

public class MainActivity extends BaseActivity implements MainContract.View {

    private MainComponent mainComponent;

    @Inject
    MainPresenter presenter;

    protected void inject(MainComponent mainComponent) {
        mainComponent.inject(this);
    }

    public MainComponent getMainComponent() {
        if (mainComponent == null) {
            mainComponent = MainComponent.Initializer
                    .init(this, getTestApplication().getApplicationComponent());
        }
        return mainComponent;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getMainComponent());
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, new HomeFragment(),
                        HomeFragment.class.getSimpleName())
                .commit();

        presenter.init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}