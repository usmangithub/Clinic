package com.veterinaryclinic.ui.fragments.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.veterinaryclinic.R;
import com.veterinaryclinic.ui.fragments.pet_detail.PetDetailFragment;

public class HomeNavigator implements HomeContract.Navigator {
    private HomeFragment fragment;
    public HomeNavigator(HomeFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void back() {
        fragment.requireActivity().onBackPressed();
    }

    @Override
    public void gotoDetailFragment(String url) {
        FragmentTransaction fragmentTransaction = fragment.requireActivity()
                .getSupportFragmentManager().beginTransaction();
        Fragment newFragment = new PetDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("WebURL", url);
        newFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, newFragment);
        fragmentTransaction.addToBackStack(newFragment.getTag());
        fragmentTransaction.commit();
    }
}
