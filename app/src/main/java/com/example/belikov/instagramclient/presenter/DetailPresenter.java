package com.example.belikov.instagramclient.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.belikov.instagramclient.R;
import com.example.belikov.instagramclient.app.App;
import com.example.belikov.instagramclient.model.entity.Hit;
import com.example.belikov.instagramclient.model.entity.HitDao;
import com.example.belikov.instagramclient.view.DetailView;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    @Inject
    HitDao hitDao;
    private static final String TAG = "Detail Presenter";
    private Hit hit;
    private int pos;

    public DetailPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        Log.d("Presenter", "Attach");
        App.getAppComponent().inject(this);
    }

    public void setCardView(int pos) {
        onFirstViewAttach();
        this.pos = pos;
        setView(pos);
        Log.d(TAG, "pos = " + pos);

    }

    private void setView(int pos) {
        Disposable disposable = hitDao.getAllById(pos + 1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> {
                    hit = hits.get(0);
                    getViewState().setImage(hit.webformatURL);
                    getViewState().setUser(hit.user);
                    getViewState().setLikeCount(hit.likes);
                    getViewState().setFavoriteCount(hit.favorites);
                    getViewState().setCommentCount(hit.comments);
                    if (hit.isLiked) getViewState().setLikeImage(R.drawable.ic_favorite_black_48dp);
                    if (hit.isFavorite)
                        getViewState().setFavoriteImage(R.drawable.ic_star_black_48dp);
                });

    }


    public void likedPhoto() {
        if (hit.isLiked) {
            hit.isLiked = false;
            hit.likes--;
            getViewState().setLikeImage(R.drawable.ic_favorite_border_black_48dp);
            getViewState().setLikeCount(hit.likes);
            updateDatabase();
            return;
        }
        hit.isLiked = true;
        hit.likes++;
        getViewState().setLikeImage(R.drawable.ic_favorite_black_48dp);
        getViewState().setLikeCount(hit.likes);
        updateDatabase();
    }

    private void updateDatabase() {
        Single<Integer> single = Single.create((SingleOnSubscribe<Integer>) emitter -> {
            hitDao.update(hit);
            Log.d(TAG, "update id " + hit.id);
            emitter.onSuccess(new Integer(hit.id));
        }).subscribeOn(Schedulers.io());

        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(l -> {
                });
    }

    public void favoritedPhoto() {
        if (hit.isFavorite) {
            hit.isFavorite = false;
            hit.favorites--;
            getViewState().setFavoriteImage(R.drawable.ic_star_border_black_48dp);
            getViewState().setFavoriteCount(hit.favorites);
            updateDatabase();
            return;
        }
        hit.isFavorite = true;
        hit.favorites++;
        getViewState().setFavoriteImage(R.drawable.ic_star_black_48dp);
        getViewState().setFavoriteCount(hit.favorites);
        updateDatabase();
    }
}
