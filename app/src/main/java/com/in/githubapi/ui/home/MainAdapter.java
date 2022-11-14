package com.in.githubapi.ui.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.githubapi.data.model.ItemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.in.githubapi.R;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private static List<ItemModel> mData = new ArrayList<>();
    int selectedPosition=-1;
    Callback callback;


    public MainAdapter(Callback callback){
        this.callback=callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item,parent,false);

        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder mViewHolder, int pos) {
        ItemModel data = mData.get(pos);
        if(data.isClicked())
            mViewHolder.itemView.setBackgroundColor(Color.parseColor("#808080"));
        else
            mViewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));

        if(data.getName()!= null) mViewHolder.setTitle(data.getName());
        if (data.getOwners() != null) mViewHolder.setAvatar(data.getOwners().getAvatar_url());
        mViewHolder.itemView.setOnClickListener(view -> {
            selectedPosition=pos;
            callback.savePosition(selectedPosition);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return (mData == null ? 0 : mData.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTitle;
        private final ImageView mAvatatImg;

        public ViewHolder(@NonNull final View mView) {
            super(mView);
            mTitle = mView.findViewById(R.id.description);
            mAvatatImg = mView.findViewById(R.id.avatarImg);
        }

        public void setTitle(String title) {
            mTitle.setText(title);
        }
        public void setAvatar(String avatar) {
            Picasso.get().load(avatar).into(mAvatatImg);
        }
    }

    public void addData(List<ItemModel> mData){
        if(mData != null) {
            this.mData.clear();
            this.mData.addAll(mData);
            notifyDataSetChanged();
        }
    }

    public interface Callback{
        void savePosition(int pos);
    }

}
