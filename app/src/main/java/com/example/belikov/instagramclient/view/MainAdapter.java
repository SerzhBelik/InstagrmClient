package com.example.belikov.instagramclient.view;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.belikov.instagramclient.R;
import com.example.belikov.instagramclient.model.GlideLoader;
import com.example.belikov.instagramclient.presenter.IRecyclerMainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private static final String TAG = "MainAdapter";

    private IRecyclerMainPresenter iRecyclerMain;
    private GlideLoader glideLoader;

    public MainAdapter(Context context, IRecyclerMainPresenter iRecyclerMain) {
        this.iRecyclerMain = iRecyclerMain;
        glideLoader = new GlideLoader(context);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int position) {
        mainViewHolder.position = position;
        iRecyclerMain.bindView(mainViewHolder);
    }

    @Override
    public int getItemCount() {
        return iRecyclerMain.getItemCount();
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements IViewHolder, View.OnClickListener {

        private int position = 0;

        @BindView(R.id.image_view)
        ImageView imageView;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setImage(String url) {
            glideLoader.loadImage(url, imageView);
        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void onClick(View v) {
            Log.d("Adapter", "" + position);
            iRecyclerMain.onCardClick(position);
        }
    }
}