package com.example.belikov.instagramclient.presenter;

import android.util.Log;
import com.example.belikov.instagramclient.model.MyData;
import com.example.belikov.instagramclient.view.IViewHolder;
import java.util.List;

public class MainPresenter {
    RecyclerMainPresenter recyclerMainPresenter = new RecyclerMainPresenter();

    private class RecyclerMainPresenter implements IRecyclerMainPresenter{
        private MyData data = new MyData();
        private List<String> fruitList = data.getFruitList();
        private int[] fruitImage = data.getFruit();

        @Override
        public void bindView(IViewHolder holder) {
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
