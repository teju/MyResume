package com.amazing.portfolio.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amazing.portfolio.R;
import com.amazing.portfolio.etc.callback.ItemClickListener;
import com.amazing.portfolio.model.Products;
import com.amazing.portfolio.ui.views.MatrixView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MyProjectsAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final RecyclerView rv;
    private final ItemClickListener itemClickListener;
    public ArrayList<Products> mDatas = new ArrayList<>();

    public MyProjectsAdapter(Context context, RecyclerView rv, ItemClickListener itemClickListener) {
        this.context = context;
        this.rv = rv;
        this.itemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyProjectsAdapter.VH(LayoutInflater.from(context).inflate(R.layout.item_arc, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyProjectsAdapter.VH vh = (MyProjectsAdapter.VH) holder;
        ((MatrixView) vh.itemView).setParentHeight(rv.getHeight());
        try {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
//            vh.text.setText(mDatas.get(position + 2).getApp_name());
//            if (mDatas.get(position + 2).getText_colour().contains("light")) {
//                vh.text.setTextColor(context.getResources().getColor(R.color.White));
//            } else {
//                vh.text.setTextColor(context.getResources().getColor(R.color.Black));
//            }
            Glide.with(context)
                    .load(mDatas.get(position ).getLogo())
                    .into(vh.app_logo);

           // vh.parent.setCardBackgroundColor(Color.parseColor(mDatas.get(position + 2).getBg_colour()));
            vh.app_logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClickpos(position);
                    //context.

                }
            });
        } catch (Exception e){
            e.printStackTrace();

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class VH extends RecyclerView.ViewHolder {

        public CardView parent;
        public TextView text;
        public ImageView app_logo;

        public VH(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            text = itemView.findViewById(R.id.text);
            app_logo = itemView.findViewById(R.id.app_logo);
        }
    }
}
