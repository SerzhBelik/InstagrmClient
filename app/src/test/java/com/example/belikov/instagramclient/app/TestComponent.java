package com.example.belikov.instagramclient.app;

import com.example.belikov.instagramclient.model.entity.HitDao;
import com.example.belikov.instagramclient.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestModule.class})
public interface TestComponent {
    void inject(MainPresenter presenter);
    void inject(HitDao hitDao);

}