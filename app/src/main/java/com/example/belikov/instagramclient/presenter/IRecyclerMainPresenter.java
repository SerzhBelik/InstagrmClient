package com.example.belikov.instagramclient.presenter;

import com.example.belikov.instagramclient.view.IViewHolder;

public interface IRecyclerMainPresenter {
    void bindView(IViewHolder iViewHolder);
    int getItemCount();
    void onCardClick(int pos);
}
