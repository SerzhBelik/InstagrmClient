package com.example.belikov.instagramclient.presenter;

import android.util.Log;
import io.reactivex.android.schedulers.AndroidSchedulers;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.belikov.instagramclient.model.MyData;
import com.example.belikov.instagramclient.model.entity.Hit;
import com.example.belikov.instagramclient.model.entity.Photo;
import com.example.belikov.instagramclient.model.retrofit.ApiHelper;
import com.example.belikov.instagramclient.view.IViewHolder;
import com.example.belikov.instagramclient.view.MainView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private ApiHelper apiHelper;
    private List<Hit> hitList;
    private static  final String TAG = "Main presenter";
    private RecyclerMainPresenter recyclerMain;

    public MainPresenter() {
        Log.d(TAG, "MainPresenter: ");
        recyclerMain = new RecyclerMainPresenter();
        apiHelper = new ApiHelper();
    }

            @Override
        protected void onFirstViewAttach() {
            getAllPhoto();
        }

    public void getAllPhoto() {
        Observable<Photo> single = apiHelper.requestServer();
        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
            //Log.d(TAG, "onNext: " + photos.totalHits);

//            for (Photo.Hit hit : photos.hits) {
//                Log.d(TAG, "getAllPhoto: " + hit.webformatURL);
//            }
            hitList = photos.hits;

            getViewState().updateRecyclerView();

        }, throwable -> {
            Log.e(TAG, "onError " + throwable);
        });
    }

//    public MainPresenter(){};
//    RecyclerMainPresenter recyclerMainPresenter = new RecyclerMainPresenter();

    private class RecyclerMainPresenter implements IRecyclerMainPresenter{
//        private MyData data = new MyData();
//        private List<String> fruitList = data.getFruitList();
//        private int[] fruitImage = data.getFruit();
//        private IViewHolder holder;




        @Override
        public void bindView(IViewHolder holder) {
            holder.setImage(hitList.get(holder.getPos()).webformatURL);
//            this.holder = holder;
//            holder.setText(fruitList.get(holder.getPos()%4));
//            holder.setImage(fruitImage[holder.getPos()%4]);
        }

        @Override
        public int getItemCount() {
            if (hitList != null) {
                return hitList.size();
            }
            return 0;
        }

//        public MyData getData(){
//            return data;
//        }
//
//        public IViewHolder getHolder(){
//            return this.holder;
//        }

        public void onCardClick(int pos){
            Log.d("Presenter", "" + pos);
            getViewState().getUrl(hitList.get(pos).webformatURL);
        }


    }

    public RecyclerMainPresenter getRecyclerMainPresenter(){
        return recyclerMain;
    }

//    public void clickCount(){
//        MyData data = recyclerMain.getData();
//        data.setClickCount(data.getClickCount() + 1);
//        Log.d("Click count", "" + data.getClickCount());
//    }


}
