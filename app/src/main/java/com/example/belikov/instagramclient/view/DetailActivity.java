package com.example.belikov.instagramclient.view;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.belikov.instagramclient.R;
import com.example.belikov.instagramclient.presenter.DetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {
    private int pos;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.text_view)
    TextView textView;

    @InjectPresenter
    DetailPresenter presenter;

    @ProvidePresenter
    public DetailPresenter providePresenter(){
        return new DetailPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

            pos = getIntent().getIntExtra("Pos", 0);
            presenter.setCardView(pos);

    }

    @Override
    public void setImage(int id) {
        Log.d("Detail activity", "" + id);
        imageView.setImageResource(id);
    }

    @Override
    public void setText(String s) {
        Log.d("Detail activity", "" + s);
        textView.setText(s);
    }
}
