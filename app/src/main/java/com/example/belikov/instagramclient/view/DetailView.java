package com.example.belikov.instagramclient.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface DetailView extends MvpView {
    @StateStrategyType(value = SkipStrategy.class)
    void setImage(String url);

    void setUser(String user);

    void setLikeCount(Integer likes);

    void setFavoriteCount(Integer favorites);

    void setCommentCount(Integer comments);

    void setLikeImage(Integer resource);

    void setFavoriteImage(Integer resource);
}


