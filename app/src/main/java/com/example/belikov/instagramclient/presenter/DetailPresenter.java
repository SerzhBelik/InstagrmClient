package com.example.belikov.instagramclient.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.belikov.instagramclient.model.MyData;
import com.example.belikov.instagramclient.model.entity.Hit;
import com.example.belikov.instagramclient.model.entity.Photo;
import com.example.belikov.instagramclient.view.DetailActivity;
import com.example.belikov.instagramclient.view.DetailView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private MyData data;
    private DetailActivity activity;

    public DetailPresenter(DetailActivity activity){
        data = new MyData();
        this.activity = activity;
    }

    @Override
    protected void onFirstViewAttach() {

        Log.d("Presenter", "Attach");
        attachView(activity);
//        attachView();
    }

    public void setCardView(){
        onFirstViewAttach();
        String url = activity.getIntent().getStringExtra("URL");
        getViewState().setImage(url);

//        Observable<Photo> single = apiHelper.requestServer();
//        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
//            //Log.d(TAG, "onNext: " + photos.totalHits);
//
////            for (Photo.Hit hit : photos.hits) {
////                Log.d(TAG, "getAllPhoto: " + hit.webformatURL);
////            }
//            hitList = photos.hits;
//
//            getViewState().updateRecyclerView();
//
//        }, throwable -> {
//            Log.e(TAG, "onError " + throwable);
//        });
//        Log.d("Detail presenter", "position: " + pos);
//        getViewState().setImage(getId(pos));
//        getViewState().setText(data.getFruitList().get(pos%4));
    }

//    private int getId(int pos) {
//
//        return data.getFruit()[pos%4];
//    }

}
