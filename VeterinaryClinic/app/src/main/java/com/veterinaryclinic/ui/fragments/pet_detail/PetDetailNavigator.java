package com.veterinaryclinic.ui.fragments.pet_detail;

public class PetDetailNavigator implements PetDetailContract.Navigator {
    private PetDetailFragment fragment;
    public PetDetailNavigator(PetDetailFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void back() {
        fragment.requireActivity().onBackPressed();
    }
}
