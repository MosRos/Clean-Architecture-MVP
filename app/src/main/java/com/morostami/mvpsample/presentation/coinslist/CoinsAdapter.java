package com.morostami.mvpsample.presentation.coinslist;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.morostami.mvpsample.R;
import com.morostami.mvpsample.domain.Coin;

import java.util.ArrayList;
import java.util.List;

public class CoinsAdapter extends RecyclerView.Adapter<CoinsAdapter.CoinsViewHolder> {
    private List<Coin> coins = new ArrayList<Coin>();

    public void addCoins(List<Coin> newItems){
        this.coins = newItems;
    }

    @NonNull
    @Override
    public CoinsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_coin, parent, false);
        return new CoinsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinsViewHolder holder, int position) {
        Coin coin = coins.get(position);
        if (coin != null) {
            holder.bind(coin);
        }
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    class CoinsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, symbolTxt;

        public CoinsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.coinNameTxt);
            symbolTxt = itemView.findViewById(R.id.coinSymbolTxt);

            Animator slideAnimator = ObjectAnimator.ofFloat(itemView, "translationY", itemView.getMeasuredHeight()*2, 0.0f);
            slideAnimator.setDuration(300);
            slideAnimator.setInterpolator(new DecelerateInterpolator(1.3f));

            Animator fadAnim = ObjectAnimator.ofFloat(itemView, "alpha", 0.0f, 1.0f);
            fadAnim.setDuration(230);
            fadAnim.setInterpolator(new LinearInterpolator());

            Animator slideBySide;
            if (itemView.getX() <= itemView.getWidth()) {
                slideBySide = ObjectAnimator.ofFloat(itemView, "translationX", -itemView.getRootView().getWidth(), 0f);
                slideBySide.setDuration(300);
                slideBySide.setInterpolator(new DecelerateInterpolator(1.3f));
            } else {
                slideBySide = ObjectAnimator.ofFloat(itemView, "translationX", itemView.getRootView().getWidth(), 0.0f);
                slideBySide.setDuration(400);
                slideBySide.setInterpolator(new DecelerateInterpolator(1.3f));
            }

            slideAnimator.start();
            fadAnim.start();
            slideBySide.start();

        }

        public void bind(@NonNull Coin coin) {
            titleTxt.setText(coin.getName());
            symbolTxt.setText(coin.getSymbol());
        }
    }
}
