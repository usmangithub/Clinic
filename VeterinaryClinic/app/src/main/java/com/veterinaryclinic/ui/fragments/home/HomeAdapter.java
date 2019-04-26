package com.veterinaryclinic.ui.fragments.home;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.veterinaryclinic.R;
import com.veterinaryclinic.ui.fragments.home.model.PetData;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private List<Bitmap> mPhotoList;
    private List<PetData> mPetList;
    private HomeFragment.FragmentLoadingCallback loadCallback;

    public HomeAdapter(List<PetData> mPetList, List<Bitmap> mPhotoList) {
        this.mPetList = mPetList;
        this.mPhotoList = mPhotoList;
    }

    public void setFragmentLoadingCallback(
            HomeFragment.FragmentLoadingCallback loadCallback) {
        this.loadCallback = loadCallback;
    }

    public void showPetList(List<PetData> petList) {
        this.mPetList = petList;
        notifyDataSetChanged();
    }

    public void refreshPetList(List<Bitmap> photoList) {
        this.mPhotoList = photoList;
        notifyDataSetChanged();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent,
                false);
        return new HomeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position) {
        if (!mPhotoList.isEmpty()) {
            holder.mImage.setImageBitmap(mPhotoList.get(position));
        }
        holder.mTitle.setText(mPetList.get(position).getTitle());
        holder.mCardView.setOnClickListener(v -> loadCallback.onLoadFragment(
                mPetList.get(position).getContentUrl()));
    }

    @Override
    public int getItemCount() {
        return mPetList.size();
    }
}

