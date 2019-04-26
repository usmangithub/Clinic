package com.veterinaryclinic.ui.fragments.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.veterinaryclinic.BasePresenter;
import com.veterinaryclinic.ui.fragments.home.model.ConfigData;
import com.veterinaryclinic.ui.fragments.home.model.PetData;
import com.veterinaryclinic.util.JSONUtil;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.SingleSubject;

public class HomePresenter
        extends BasePresenter<HomeContract.View, HomeContract.Navigator>
        implements HomeContract.Presenter {

    private JSONUtil jsonUtil;
    private Disposable bitmapDataDisposable;

    public HomePresenter(HomeContract.View view,
                         HomeContract.Navigator navigator) {
        super(view, navigator);
        jsonUtil = new JSONUtil();
    }

    @Override
    public void onParsingConfigData(String url) {
        Schedulers.io().scheduleDirect(() ->
                getConfigData(url)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(configData ->
                                view.showConfigView(configData)));
    }

    /**
     * Fetch JSON data rom worker thread
     *
     * @param url
     * @return
     */
    private Single<ConfigData> getConfigData(String url) {
        SingleSubject<ConfigData> singleSubject = SingleSubject.create();
        Schedulers.io().scheduleDirect(() -> singleSubject.onSuccess(
                getJsonData(url)));
        return singleSubject;
    }

    /**
     * Get JSON data from a URL
     *
     * @param url
     * @return
     */
    private ConfigData getJsonData(String url) {
        ConfigData configData = new ConfigData();
        JSONObject jObj = jsonUtil.readJsonDataFromHTTPUrl(url);
        try {
            JSONObject jObject = jObj.getJSONObject("settings");
            configData.setCallEnabled(jObject.getBoolean("isCallEnabled"));
            configData.setChatEnabled(jObject.getBoolean("isChatEnabled"));
            configData.setWorkHours(jObject.getString("workHours"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return configData;
    }

    @Override
    public void onParsingPetPetData(String url) {
        Schedulers.io().scheduleDirect(() -> {
            getPetData(url)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(petList -> {
                        view.showPetList(petList);
                        view.showLoadingAnimation().subscribe(() -> loadBitmapDataList(petList));
                    });

        });
    }

    /**
     * Get pet data list from worker thread
     *
     * @param url
     * @return
     */
    private Single<List<PetData>> getPetData(String url) {
        SingleSubject<List<PetData>> singleSubject = SingleSubject.create();
        Schedulers.io().scheduleDirect(() -> singleSubject.onSuccess(
                getPetDataList(url)));
        return singleSubject;
    }

    /**
     * get the Pet data list from URL
     *
     * @param url
     * @return
     */
    private List<PetData> getPetDataList(String url) {
        List<PetData> petList = new ArrayList<>();
        JSONObject jObj = jsonUtil.readJsonDataFromHTTPUrl(url);
        try {
            JSONArray jsonArray = jObj.getJSONArray("pets");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject = jsonArray.getJSONObject(i);
                petList.add(new PetData(jObject.getString("image_url"),
                        jObject.getString("title"),
                        jObject.getString("content_url"),
                        jObject.getString("date_added")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return petList;
    }

    /**
     * Get the Bitmap data lst from Json image url
     *
     * @param petList
     * @return
     */
    private Single<List<Bitmap>> getBitmapData(List<PetData> petList) {
        SingleSubject<List<Bitmap>> singleSubject = SingleSubject.create();
        Schedulers.io().scheduleDirect(() -> singleSubject.onSuccess(
                getBitmapList(petList)));
        return singleSubject;
    }

    /**
     * Get the downloaded thumbnail bitmap list
     *
     * @param petList
     * @return bitmap list
     */
    private List<Bitmap> getBitmapList(List<PetData> petList) {
        List<Bitmap> photos = new ArrayList<>();
        for (int i = 0; i < petList.size(); i++) {
            try {
                HttpURLConnection connection =
                        (HttpURLConnection) new URL(petList.get(i).getImageUrl()).openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                photos.add(ThumbnailUtils.extractThumbnail(
                        BitmapFactory.decodeStream(input), 320, 240));
            } catch (IOException e) {
                Log.e("TAG", e.getMessage());
            }
        }
        return photos;
    }

    /**
     * Load the bitmap icon in the List view item
     *
     * @param petList
     */
    private void loadBitmapDataList(List<PetData> petList) {
        bitmapDataDisposable = getBitmapData(petList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmapList ->
                        view.hideLoadingAnimation()
                                .subscribe(() -> view.refreshIconList(bitmapList)));
    }

    @Override
    public boolean isOfficeHour() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        return (hourOfDay > 8 && hourOfDay < 18 &&
                dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY);
    }

    @Override
    public void onClickPetItem(String url) {
        navigator.gotoDetailFragment(url);
    }

    @Override
    public void destroy() {
        super.destroy();
        if (bitmapDataDisposable != null && !bitmapDataDisposable.isDisposed()) {
            bitmapDataDisposable.dispose();
        }
    }
}
