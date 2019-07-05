package com.example.belikov.instagramclient.view;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.belikov.instagramclient.R;
import com.example.belikov.instagramclient.presenter.IRecyclerMainPresenter;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    IRecyclerMainPresenter mainPresenter;


    public MyAdapter(IRecyclerMainPresenter mainPresenter){
        this.mainPresenter = mainPresenter;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.pos = i;
        mainPresenter.bindView(myViewHolder);
    }

    @Override
    public int getItemCount() {
        return mainPresenter.getItemCount();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements IViewHolder, View.OnClickListener

    {
        private ImageView imageView;
        private int pos = 0;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.image_view);
        }


        @Override
        public int getPos() {
            return pos;
        }

        @Override
        public void setImage(String url) {

        }

        @Override
        public void onClick(View v) {
//            Log.d("Adapter", "" + pos);
            mainPresenter.onCardClick(pos);
        }
    }
}
