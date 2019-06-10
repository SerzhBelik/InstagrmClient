package com.example.belikov.instagramclient.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainView  extends MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void updateRecyclerView();
    void getUrlPos(int pos);
}
