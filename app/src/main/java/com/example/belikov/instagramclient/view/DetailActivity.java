package com.example.belikov.instagramclient.view;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.belikov.instagramclient.R;
import com.example.belikov.instagramclient.model.GlideLoader;
import com.example.belikov.instagramclient.presenter.DetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {
    private GlideLoader glideLoader = new GlideLoader(this);
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.image_view)
    ImageView imageView;

    @InjectPresenter
    DetailPresenter presenter;

    @ProvidePresenter
    public DetailPresenter providePresenter(){
        return new DetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        presenter.setCardView();

    }

    @Override
    public void setImage(String url) {
        imageView.setLayoutParams(new LinearLayout.LayoutParams(600, 600));
        glideLoader.loadImage(url, imageView);
    }


    public DetailView getView(){
        return this;
    }
}
