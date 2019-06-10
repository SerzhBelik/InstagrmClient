package com.example.belikov.instagramclient.app;


import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.belikov.instagramclient.model.AppDatabase;

public class App extends Application {

    private static AppDatabase appDatabase;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "room_database").build();
        appComponent = generateAppComponent();
    }

    public static AppDatabase getAppDatabase(){
        return appDatabase;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public AppComponent generateAppComponent() {
        //чтобы подтянулся DaggerAppComponent, необходимо сбилдить проект
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
