package com.example.belikov.instagramclient.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.belikov.instagramclient.model.MyData;
import com.example.belikov.instagramclient.view.IViewHolder;
import com.example.belikov.instagramclient.view.MainView;

import java.util.List;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

//    public MainPresenter(){};
    RecyclerMainPresenter recyclerMainPresenter = new RecyclerMainPresenter();

    private class RecyclerMainPresenter implements IRecyclerMainPresenter{
        private MyData data = new MyData();
        private List<String> fruitList = data.getFruitList();
        private int[] fruitImage = data.getFruit();
        private IViewHolder holder;

        @Override
        public void bindView(IViewHolder holder) {
            this.holder = holder;
            holder.setText(fruitList.get(holder.getPos()%4));
            holder.setImage(fruitImage[holder.getPos()%4]);
        }

        @Override
        public int getItemCount() {
            return fruitList.size();
        }

        public MyData getData(){
            return data;
        }
//
//        public IViewHolder getHolder(){
//            return this.holder;
//        }

        public void onCardClick(int pos){
            Log.d("Presenter", "" + pos);
            getViewState().getPosition(pos);
        }

    }

    public RecyclerMainPresenter getRecyclerMainPresenter(){
        return recyclerMainPresenter;
    }

    public void clickCount(){
        MyData data = recyclerMainPresenter.getData();
        data.setClickCount(data.getClickCount() + 1);
        Log.d("Click count", "" + data.getClickCount());
    }


}
