package com.amazing.portfolio.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amazing.portfolio.R;
import com.amazing.portfolio.ui.views.MatrixView;

import java.util.ArrayList;


public class MyProjectsAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final RecyclerView rv;
    public ArrayList<String> mDatas = new ArrayList<>();

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
        System.out.println("MatrixView1234 "+rv.getHeight());

        ((MatrixView) vh.itemView).setParentHeight(rv.getHeight());
        //vh.tv.setText(mDatas.get(position));
        final int fp = position;
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  TUtils.show(ArcActivity.this, "点击" +mDatas.get(fp));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 999;
    }

    class VH extends RecyclerView.ViewHolder {

        public TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
           // tv = itemView.findViewById(R.id.tv);
        }
    }
}
