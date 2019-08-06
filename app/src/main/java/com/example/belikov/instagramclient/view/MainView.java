package com.example.belikov.instagramclient.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = SkipStrategy.class)
public interface MainView extends MvpView {
    void updateRecyclerView(int size);
    void getUrlPos(int pos);
}
