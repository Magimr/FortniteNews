package com.example.fortnitenews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    private List<Data> mItemList;
    private LayoutInflater mInflater;
    private Context context;

    public NewsAdapter(Context context, List<Data> itemsList){
        mInflater = LayoutInflater.from(context);
        this.mItemList = itemsList;
        this.context = context;


    }


    class NewsViewHolder extends RecyclerView.ViewHolder{
        public final TextView txtTitle;
        public final TextView txtBody;
        public final ImageView imgItem;
        final NewsAdapter mAdapter;

        public NewsViewHolder(View itemView, NewsAdapter adapter){
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtBody = itemView.findViewById(R.id.txtBody);
            imgItem = itemView.findViewById(R.id.imgItem);
            this.mAdapter = adapter;

        }

    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mItemView = mInflater
                .inflate(R.layout.items_item, viewGroup, false);
        return new NewsViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {
        Data mCurrent = mItemList.get(position);
        newsViewHolder.txtTitle.setText((mCurrent.getTitle()));
        newsViewHolder.txtBody.setText(mCurrent.getBody());
        Glide.with(context).load(mCurrent.getImage()).into(newsViewHolder.imgItem);


    }

    @Override
    public int getItemCount() {
        if(mItemList == null) {
            return 0;
        }

        return mItemList.size();
    }

    public void setData(List <Data> newList){
        mItemList = newList;
        notifyDataSetChanged();
    }
}

