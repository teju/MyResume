package com.amazing.portfolio.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amazing.portfolio.R;
import com.amazing.portfolio.ui.GlideApp;
import com.bumptech.glide.Glide;

import com.jakewharton.salvage.RecyclingPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * ImagePagerAdapter
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-23
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter {

    private boolean isInfiniteLoop = true;
    private Context context;
    public ArrayList<String> imageIdList = new ArrayList<>();

    public ImagePagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? Integer.MAX_VALUE : imageIdList.size();
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return isInfiniteLoop ? position % imageIdList.size() : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = holder.imageView = new ImageView(context);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        //holder.imageView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.loading));
        try {
            GlideApp.with(context)
                        .load(imageIdList.get(getPosition(position)))
                    .placeholder(R.drawable.loading)
                        .into(holder.imageView);

        }catch (Exception e){

        }

        return view;
    }

    private static class ViewHolder {

        ImageView imageView;
    }


}