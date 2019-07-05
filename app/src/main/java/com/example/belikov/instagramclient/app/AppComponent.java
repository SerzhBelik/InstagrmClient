package com.example.belikov.instagramclient.app;

import com.example.belikov.instagramclient.presenter.DetailPresenter;
import com.example.belikov.instagramclient.presenter.MainPresenter;
import com.example.belikov.instagramclient.view.DetailActivity;
import com.example.belikov.instagramclient.view.MainAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainAdapter mainAdapter);

    void inject(MainPresenter mainPresenter);

    void inject(DetailActivity activity);

    void inject(DetailPresenter detailPresenter);

}
