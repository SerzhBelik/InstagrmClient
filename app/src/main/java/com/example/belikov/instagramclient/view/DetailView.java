package com.example.belikov.instagramclient.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface DetailView extends MvpView {
    @StateStrategyType(value = SkipStrategy.class)
    void setImage(String url);
}
