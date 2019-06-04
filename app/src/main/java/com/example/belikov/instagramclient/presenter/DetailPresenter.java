package com.example.belikov.instagramclient.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.belikov.instagramclient.model.MyData;
import com.example.belikov.instagramclient.view.DetailActivity;
import com.example.belikov.instagramclient.view.DetailView;

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
    }

    public void setCardView(){
        onFirstViewAttach();
        String url = activity.getIntent().getStringExtra("URL");
        getViewState().setImage(url);
    }

}
