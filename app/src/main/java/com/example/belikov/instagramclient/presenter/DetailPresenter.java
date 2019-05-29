package com.example.belikov.instagramclient.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.belikov.instagramclient.model.MyData;
import com.example.belikov.instagramclient.view.DetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private MyData data;

    public DetailPresenter(){
        data = new MyData();
    }

    public void setCardView(int pos){
        Log.d("Detail presenter", "position: " + pos);
        int id = data.getFruit()[pos];
        getViewState().setImage(id);
        getViewState().setText(data.getFruitList().get(pos));
    }

}
