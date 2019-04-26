package com.veterinaryclinic.ui.fragments.home;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.veterinaryclinic.BaseFragment;
import com.veterinaryclinic.BasePresenter;
import com.veterinaryclinic.R;
import com.veterinaryclinic.ui.fragments.home.di.HomeComponent;
import com.veterinaryclinic.ui.fragments.home.model.ConfigData;
import com.veterinaryclinic.ui.fragments.home.model.PetData;

import io.reactivex.Completable;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    public static final String TAG = HomeFragment.class.getSimpleName();

    private HomeComponent homeComponent;
    private HomeAdapter mHomeAdapter;
    private RecyclerView recyclerView;
    private Button btnChat;
    private Button btnCall;
    private TextView tvTime;
    private ProgressDialog progressDialog;

    @Inject
    HomePresenter presenter;

    /**
     * This callback will be used to get the selected tap list item for loading fragment detail
     */
    public interface FragmentLoadingCallback {
        void onLoadFragment(String url);
    }

    protected void inject(HomeComponent homeComponent) {
        homeComponent.inject(this);
    }

    public HomeComponent getCategoryComponent() {
        if (homeComponent == null) {
            homeComponent = HomeComponent.Initializer
                    .init(this, getTestApplication().getApplicationComponent());
        }
        return homeComponent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getCategoryComponent());
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle(getString(R.string.app_name));

        recyclerView = view.findViewById(R.id.recyclerView);
        btnChat = view.findViewById(R.id.btnChat);
        btnCall = view.findViewById(R.id.btnCall);
        tvTime = view.findViewById(R.id.tvTime);

        presenter.init();
        loadUI();

        return view;
    }

    private void loadUI() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        mHomeAdapter = new HomeAdapter(new ArrayList<>(), new ArrayList<>());
        mHomeAdapter.setFragmentLoadingCallback(url -> presenter.onClickPetItem(url));
        recyclerView.setAdapter(mHomeAdapter);

        presenter.onParsingConfigData(getString(R.string.server_url_config));
        presenter.onParsingPetPetData(getString(R.string.server_url_pet));

        btnChat.setOnClickListener(v ->
                Toast.makeText(getContext(), presenter.isOfficeHour() ?
                        getString(R.string.office_hour_in_time_text) :
                        getString(R.string.office_hour_out_time_text), Toast.LENGTH_SHORT).show());

        btnCall.setOnClickListener(v ->
                Toast.makeText(getContext(), presenter.isOfficeHour() ?
                        getString(R.string.office_hour_in_time_text) :
                        getString(R.string.office_hour_out_time_text), Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void refreshIconList(List<Bitmap> bitmapList) {
        mHomeAdapter.refreshPetList(bitmapList);
    }

    @Override
    public void showPetList(List<PetData> petList) {
        mHomeAdapter.showPetList(petList);
    }

    @Override
    public void showConfigView(ConfigData configData) {
        btnCall.setVisibility(configData.isCallEnabled() ? View.VISIBLE : View.GONE);
        btnChat.setVisibility(configData.isChatEnabled() ? View.VISIBLE : View.GONE);
        tvTime.setText(getString(R.string.office_hour_text, configData.getWorkHours()));
    }

    @Override
    public Completable showLoadingAnimation() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading ...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
        return Completable.complete();
    }

    @Override
    public Completable hideLoadingAnimation() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        return Completable.complete();
    }
}

