package com.example.belikov.instagramclient.app;

import com.example.belikov.instagramclient.model.entity.HitDao;
import com.example.belikov.instagramclient.model.retrofit.ApiHelper;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {
    @Provides
    public ApiHelper provideApiHelper(){
        return Mockito.mock(ApiHelper.class);
    }
    @Provides
    public HitDao provideHitDao(){
//        return App.getAppDatabase().hitDao();
        return Mockito.mock(HitDao.class);
    }
}
