package com.example.belikov.instagramclient.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.belikov.instagramclient.app.App;
import com.example.belikov.instagramclient.model.entity.Hit;
import com.example.belikov.instagramclient.model.entity.HitDao;
import com.example.belikov.instagramclient.view.DetailView;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    @Inject
    HitDao hitDao;
    private static final String TAG = "Detail Presenter";
    private List<Hit> hitList;

    public DetailPresenter(){
    }

    @Override
    protected void onFirstViewAttach() {
        Log.d("Presenter", "Attach");
        App.getAppComponent().inject(this);
    }

    public void setCardView(int pos){
        onFirstViewAttach();

        getUrl(pos);
        Log.d(TAG, "pos = " + pos);

    }

    private void getUrl(int pos) {
        Disposable disposable = hitDao.getAllById(pos + 1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> {
                    hitList = hits;
                    getViewState().setImage(hitList.get(0).webformatURL);
                    getViewState().setUser(hitList.get(0).user);
                });

    }



}
