package com.veterinaryclinic.ui.fragments.pet_detail;

import com.veterinaryclinic.BaseNavigator;
import com.veterinaryclinic.BaseView;

public interface PetDetailContract {

    interface View extends BaseView {
        void showPetDetailView(String url);
    }

    interface Presenter {
        void onLoadDetailView(String url);
    }

    interface Navigator extends BaseNavigator {
        void back();
    }
}
