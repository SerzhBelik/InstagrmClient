package com.example.belikov.instagramclient.view;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.belikov.instagramclient.R;
import com.example.belikov.instagramclient.app.App;
import com.example.belikov.instagramclient.model.GlideLoader;
import com.example.belikov.instagramclient.presenter.DetailPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {
    @Inject
    GlideLoader glideLoader;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.likePhoto)
    ImageView imageLike;
    @BindView(R.id.favoritePhoto)
    ImageView imageFavorite;
    @BindView(R.id.description)
    TextView textView;
    @BindView(R.id.likes_count)
    TextView likesCount;
    @BindView(R.id.favorite_count)
    TextView favoriteCount;
    @BindView(R.id.comment_count)
    TextView commentCount;

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
        App.getAppComponent().inject(this);
        presenter.setCardView(getPos());

    }

    private int getPos() {
        return getIntent().getIntExtra("POS", 0);
    }

    @Override
    public void setImage(String url) {
        imageView.setLayoutParams(new LinearLayout.LayoutParams(500, 500));
        glideLoader.setContext(this);
        glideLoader.loadImage(url, imageView);
    }

    @Override
    public void setUser(String user) {
        textView.setText(user);
    }

    @Override
    public void setLikeCount(Integer likes) {
        likesCount.setText(likes.toString());
    }

    @Override
    public void setFavoriteCount(Integer favorites) {
        favoriteCount.setText(favorites.toString());
    }

    @Override
    public void setCommentCount(Integer comments) {
        commentCount.setText(comments.toString());
    }

    @Override
    public void setLikeImage(Integer resource) {
        imageLike.setImageResource(resource);
    }

    @Override
    public void setFavoriteImage(Integer resource) {
        imageFavorite.setImageResource(resource);
    }


    public DetailView getView(){
        return this;
    }

    @OnClick(R.id.likePhoto)
    public void likedPhoto(){
        presenter.likedPhoto();
    }

    @OnClick(R.id.favoritePhoto)
    public void favoritedPhoto(){
        presenter.favoritedPhoto();
    }


//    public void onClickLike(View view){
////        ImageView imageView = findViewById(R.id.likePhoto);
//        ImageView imageView = (ImageView)view;
//        imageView.setImageResource(R.drawable.ic_favorite_black_48dp);
//    }
//
//    public void onClickFavorite(View view){
////        ImageView imageView = findViewById(R.id.likePhoto);
//        ImageView imageView = (ImageView)view;
//        imageView.setImageResource(R.drawable.ic_star_black_48dp);
//    }
}
