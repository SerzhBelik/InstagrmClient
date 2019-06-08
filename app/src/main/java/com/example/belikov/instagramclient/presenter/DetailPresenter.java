package com.example.belikov.instagramclient.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.belikov.instagramclient.App;
import com.example.belikov.instagramclient.model.MyData;
import com.example.belikov.instagramclient.model.entity.Hit;
import com.example.belikov.instagramclient.model.entity.HitDao;
import com.example.belikov.instagramclient.view.DetailActivity;
import com.example.belikov.instagramclient.view.DetailView;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private MyData data;
    private DetailActivity activity;
    private HitDao hitDao = App.getAppDatabase().hitDao();
    private static final String TAG = "Detail Presenter";
    private List<Hit> hitList;

    public DetailPresenter(DetailActivity activity){
        data = new MyData();
        this.activity = activity;
    }

    @Override
    protected void onFirstViewAttach() {

        Log.d("Presenter", "Attach");
        attachView(activity);
    }

    public void setCardView(){
        onFirstViewAttach();
        int pos = activity.getIntent().getIntExtra("POS", 0);
        getUrl(pos);
        Log.d(TAG, "pos = " + pos);

    }

    private void getUrl(int pos) {
        Disposable disposable = hitDao.getAllById(pos + 1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> {
                    hitList = hits;
                    getViewState().setImage(hitList.get(0).webformatURL);
                });

    }



}
