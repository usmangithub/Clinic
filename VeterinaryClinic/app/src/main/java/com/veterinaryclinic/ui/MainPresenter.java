package com.veterinaryclinic.ui;

import com.veterinaryclinic.BasePresenter;

public class MainPresenter
        extends BasePresenter<MainContract.View, MainContract.Navigator>
        implements MainContract.Presenter {

    public MainPresenter(MainContract.View view,
                         MainContract.Navigator navigator) {
        super(view, navigator);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void activate() {
        super.activate();
    }

    @Override
    public void deactivate() {
        super.deactivate();
    }
}
