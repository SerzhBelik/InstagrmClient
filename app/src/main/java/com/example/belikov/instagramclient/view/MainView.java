package com.example.belikov.instagramclient.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainView  extends MvpView {

    @StateStrategyType(value = SkipStrategy.class)
    void updateRecyclerView(int size);
    @StateStrategyType(value = SkipStrategy.class)
    void getUrlPos(int pos);
}
