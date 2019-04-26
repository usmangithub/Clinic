package com.veterinaryclinic;

import android.support.annotation.CallSuper;

public abstract class BasePresenter <ViewT extends BaseView, NavigatorT extends BaseNavigator>{
    protected ViewT view;
    protected NavigatorT navigator;

    protected BasePresenter(final ViewT view, final NavigatorT navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    @CallSuper
    public void init() {
    }

    @CallSuper
    public void activate() {
    }

    @CallSuper
    public void deactivate() {
    }

    @CallSuper
    public void destroy() {
        view = null;
    }
}
