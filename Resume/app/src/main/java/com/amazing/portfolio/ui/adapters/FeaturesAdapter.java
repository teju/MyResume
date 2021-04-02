package com.amazing.portfolio.ui.adapters;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amazing.portfolio.R;

public class FeaturesAdapter extends RecyclerView.Adapter<FeaturesAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private Animation animationUp, animationDown;
    private Context context;
    private final int COUNTDOWN_RUNNING_TIME = 500;

    public FeaturesAdapter(Context context, Animation animationUp, Animation animationDown) {
        this.layoutInflater = LayoutInflater.from(context);
        this.animationDown = animationDown;
        this.animationUp = animationUp;
        this.context = context;
    }

    @Override
    public ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_features_view, parent, false);

        return new ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ReyclerViewHolder holder, int position) {

        holder.showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.contentLayout.isShown()) {
                    holder.contentLayout.startAnimation(animationUp);

                    CountDownTimer countDownTimerStatic = new CountDownTimer(COUNTDOWN_RUNNING_TIME, 16) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }

                        @Override
                        public void onFinish() {
                            holder.contentLayout.setVisibility(View.GONE);
                        }
                    };
                    countDownTimerStatic.start();

                    holder.showMore.setText("Show");
                } else {
                    holder.contentLayout.setVisibility(View.VISIBLE);
                    holder.contentLayout.startAnimation(animationDown);

                    holder.showMore.setText("Hide");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView showMore;
        private TextView contentLayout;

        private ReyclerViewHolder(final View v) {
            super(v);

            image = (ImageView) v.findViewById(R.id.image);
            contentLayout = (TextView) v.findViewById(R.id.content);
            showMore = (TextView) v.findViewById(R.id.show_more);
        }
    }
}
