package com.veterinaryclinic.ui.fragments.pet_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import javax.inject.Inject;

import com.veterinaryclinic.BaseFragment;
import com.veterinaryclinic.BasePresenter;
import com.veterinaryclinic.R;
import com.veterinaryclinic.ui.fragments.pet_detail.di.PetDetailComponent;

public class PetDetailFragment extends BaseFragment implements PetDetailContract.View {
    public static final String TAG = PetDetailFragment.class.getSimpleName();

    private PetDetailComponent petDetailComponent;
    private WebView webView;

    @Inject
    PetDetailPresenter presenter;

    protected void inject(PetDetailComponent petDetailComponent) {
        petDetailComponent.inject(this);
    }

    public PetDetailComponent getCategoryComponent() {
        if (petDetailComponent == null) {
            petDetailComponent = PetDetailComponent.Initializer
                    .init(this, getTestApplication().getApplicationComponent());
        }
        return petDetailComponent;
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
        View view = inflater.inflate(R.layout.fragment_pet_detail, container, false);
        webView = view.findViewById(R.id.webView);
        presenter.init();
        if (getArguments() != null) {
            String url = getArguments().getString("WebURL", "");
            Log.i(TAG, "Content URL: " + url);
            presenter.onLoadDetailView(url);
        }
        return view;
    }

    @Override
    public void showPetDetailView(String url) {
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }
}

