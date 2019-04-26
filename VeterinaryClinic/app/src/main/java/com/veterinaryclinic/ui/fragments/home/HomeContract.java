package com.veterinaryclinic.ui.fragments.home;

import android.graphics.Bitmap;

import java.util.List;

import com.veterinaryclinic.BaseNavigator;
import com.veterinaryclinic.BaseView;
import com.veterinaryclinic.ui.fragments.home.model.ConfigData;
import com.veterinaryclinic.ui.fragments.home.model.PetData;

import io.reactivex.Completable;

public interface HomeContract {

    interface View extends BaseView {
        void refreshIconList(List<Bitmap> bitmapList);
        void showPetList(List<PetData> petList);
        void showConfigView(ConfigData configData);
        Completable showLoadingAnimation();
        Completable hideLoadingAnimation();
    }

    interface Presenter {
        void onParsingConfigData(String url);
        void onParsingPetPetData(String url);
        boolean isOfficeHour();
        void onClickPetItem(String url);
    }

    interface Navigator extends BaseNavigator {
        void gotoDetailFragment(String url);
        void back();
    }
}
