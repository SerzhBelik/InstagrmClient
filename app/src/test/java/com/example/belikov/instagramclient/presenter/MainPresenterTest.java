package com.example.belikov.instagramclient.presenter;

import com.example.belikov.instagramclient.app.App;
import com.example.belikov.instagramclient.app.DaggerTestComponent;
import com.example.belikov.instagramclient.app.TestComponent;
import com.example.belikov.instagramclient.app.TestModule;
import com.example.belikov.instagramclient.model.entity.Hit;
import com.example.belikov.instagramclient.model.entity.HitDao;
import com.example.belikov.instagramclient.model.entity.Photo;
import com.example.belikov.instagramclient.model.retrofit.ApiHelper;
import com.example.belikov.instagramclient.view.MainView;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private MainPresenter presenter;
    private List<Hit> hitList = new ArrayList<>();
//    private HitDao hitDao;

    @Mock
    MainView mainView;
    @Mock
    HitDao hitDao;


    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(new MainPresenter());


    }

    @Test
    public void getPhotoFromJson_isCorrect() {
        TestComponent component = DaggerTestComponent.builder()
                .testModule(new TestModule() {

                    @Override
                    public ApiHelper provideApiHelper() {
                        ApiHelper apiHelper = super.provideApiHelper();

                        Photo photo = new Photo();
                        photo.hits = new ArrayList<>();
                        photo.hits.add(new Hit());
                        Mockito.when(apiHelper.requestServer()).thenReturn(Observable.just(photo));
                        return apiHelper;
                    }

                    @Override
                    public HitDao provideHitDao() {
                       hitDao = super.provideHitDao();
                       Mockito.when(hitDao.getAll()).thenReturn(Single.just(hitList));
                       return hitDao;
                    }
                }).build();

        component.inject(presenter);
        presenter.attachView(mainView);
        presenter.getPhotoFromJson();
        Mockito.verify(mainView).updateRecyclerView(1);

    }


}
