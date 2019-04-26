package com.veterinaryclinic;

import android.app.Activity;

public abstract class BaseFragment extends android.support.v4.app.Fragment {

    protected abstract BasePresenter getPresenter();

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().activate();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().deactivate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }

    protected TestApplication getTestApplication() {
        Activity activity = getActivity();
        if (activity == null) {
            return null;
        } else {
            return ((TestApplication) activity.getApplication());
        }
    }
}
