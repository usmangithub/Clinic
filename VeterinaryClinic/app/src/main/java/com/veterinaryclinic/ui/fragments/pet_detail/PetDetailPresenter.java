package com.veterinaryclinic.ui.fragments.pet_detail;

import com.veterinaryclinic.BasePresenter;

public class PetDetailPresenter
        extends BasePresenter<PetDetailContract.View, PetDetailContract.Navigator>
        implements PetDetailContract.Presenter {
    public PetDetailPresenter(PetDetailContract.View view,
                         PetDetailContract.Navigator navigator) {
        super(view, navigator);
    }

    @Override
    public void onLoadDetailView(String url) {
        view.showPetDetailView(url);
    }
}
