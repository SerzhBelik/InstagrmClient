package com.example.belikov.instagramclient.presenter;

import android.util.Log;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.belikov.instagramclient.model.entity.Hit;
import com.example.belikov.instagramclient.model.entity.HitDao;
import com.example.belikov.instagramclient.model.entity.Photo;
import com.example.belikov.instagramclient.model.retrofit.ApiHelper;
import com.example.belikov.instagramclient.view.IViewHolder;
import com.example.belikov.instagramclient.view.MainView;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    ApiHelper apiHelper;
    @Inject
    HitDao hitDao;
    private List<Hit> hitList;
    private static  final String TAG = "Main presenter";
    private RecyclerMainPresenter recyclerMain;

    public MainPresenter() {
        Log.d(TAG, "MainPresenter: ");
        recyclerMain = new RecyclerMainPresenter();

    }

            @Override
        protected void onFirstViewAttach() {
            getAllPhoto();
        }

    public void getAllPhoto() {
        Disposable disposable = hitDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> {
                    if (hits.size() == 0){
                        Log.d(TAG, "hits == 0");
                        getPhotoFromJson();
                    } else {
                        Log.d(TAG, "hits != 0");
                        getPhotoFromeDatabase();
                        }
                });

    }

    public void getPhotoFromeDatabase() {

        Disposable disposable = hitDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> {
                   hitList = hits;
                   Log.d(TAG, "from DB");
                   getViewState().updateRecyclerView(hitList.size());
                });
    }


    public void getPhotoFromJson() {
        Log.d(TAG, "!!!");
                Observable<Photo> single = apiHelper.requestServer();
        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
            hitList = photos.hits;
            Single<Long> single1= insertIntoDB();
            single1.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(l -> {
                        Log.d(TAG, "insert " + l + " elements");
                    });
            Log.d(TAG, "from Json");
            getViewState().updateRecyclerView(hitList.size());
        }, throwable -> {
            Log.e(TAG, "onError " + throwable);
        });
    }

    private Single<Long> insertIntoDB() {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            Log.d(TAG, "adding " + hitList.size());
            hitDao.insertList(hitList);
            emitter.onSuccess(new Long(hitList.size()));
        }).subscribeOn(Schedulers.io());
    }

    private class RecyclerMainPresenter implements IRecyclerMainPresenter{

        @Override
        public void bindView(IViewHolder holder) {
            holder.setImage(hitList.get(holder.getPos()).webformatURL);
        }

        @Override
        public int getItemCount() {
            if (hitList != null) {
                return hitList.size();
            }
            return 0;
        }

        public void onCardClick(int pos){
            Log.d("Presenter", "" + pos);
            getViewState().getUrlPos(pos);
        }
    }

    public RecyclerMainPresenter getRecyclerMainPresenter(){
        return recyclerMain;
    }

}
