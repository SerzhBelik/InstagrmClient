package com.example.belikov.instagramclient.app;

import android.app.Application;

import com.example.belikov.instagramclient.model.GlideLoader;
import com.example.belikov.instagramclient.model.entity.HitDao;
import com.example.belikov.instagramclient.model.retrofit.ApiHelper;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    GlideLoader provideGlideLoader(){
        return new GlideLoader();
    }

    @Singleton
    @Provides
    ApiHelper provideApiHelper(){
        return new ApiHelper();
    }

    @Singleton
    @Provides
    HitDao provideHitDao(){
        return App.getAppDatabase().hitDao();
    }

}
