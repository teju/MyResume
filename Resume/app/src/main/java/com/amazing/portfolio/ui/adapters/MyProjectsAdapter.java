package com.amazing.portfolio.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amazing.portfolio.R;
import com.amazing.portfolio.model.Products;
import com.amazing.portfolio.ui.views.MatrixView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MyProjectsAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final RecyclerView rv;
    public ArrayList<Products> mDatas = new ArrayList<>();

    public MyProjectsAdapter(Context context, RecyclerView rv) {
        this.context = context;
        this.rv = rv;
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
                    //  TUtils.show(ArcActivity.this, "点击" +mDatas.get(fp));
                }
            });
            vh.parent.setCardBackgroundColor(Color.parseColor(mDatas.get(position).getBg_colour()));
            vh.text.setText(mDatas.get(position).getApp_name());
            if (mDatas.get(position).getText_colour().contains("light")) {
                vh.text.setTextColor(context.getResources().getColor(R.color.White));
            } else {
                vh.text.setTextColor(context.getResources().getColor(R.color.Black));
            }
            Glide.with(context)
                    .load(mDatas.get(position).getLogo())
                    .into(vh.app_logo);
        } catch (Exception e){
            vh.itemView.setVisibility(View.GONE);
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
